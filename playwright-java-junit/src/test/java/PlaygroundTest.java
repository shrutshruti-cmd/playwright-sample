import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)  public class PlaygroundTest extends BaseTest {
	@Test
    @UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
        public void validateMessageInputInLambdaTestSeleniumPlayground(JsonObject capability) throws Exception {
		  Driver driver = null;
         Page page = null;
      driver = super.createConnection(capability);
      page = driver.getPage();
		page.navigate("https://www.lambdatest.com/selenium-playground");
		page.click("text=Simple Form Demo");
		assertTrue(page.url().contains("simple-form-demo"));

		String testMessage = "Welcome to LambdaTest";
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("simple-form-demo.png")));

		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Please enter your Message")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Please enter your Message")).fill(testMessage);

		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Checked Value")).click();

		String actualMessage = page.textContent("#message");
		System.out.println("Actual message: " + actualMessage);
		assertEquals(testMessage, actualMessage != null ? actualMessage.trim() : null);

		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("message.png")));
	}

	@Test
     @UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
        public void dragDefaultValue15SliderTo95(JsonObject capability) throws Exception {
		Driver driver = null;
        Page page = null;
		page.navigate("https://www.lambdatest.com/selenium-playground");
		page.click("text=Drag & Drop Sliders");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("drag-drop.png")));

		Locator slider = page.locator("#slider3");
		// Set value to 95 and dispatch input event so UI updates
		slider.evaluate("el => { el.value = 95; el.dispatchEvent(new Event('input')); }");

		Locator valueById = page.locator("#rangeSuccess");
		valueById.waitFor();
		assertEquals("95", valueById.textContent().trim());
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("value-set.png")));
	}

	@Test
     @UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
        public void inputFormSubmit(JsonObject capability) throws Exception {
		Driver driver = null;
         Page page = null;
		page.navigate("https://www.lambdatest.com/selenium-playground");
		page.click("text=Input Form Submit");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("input-form-submit.png")));

		page.click("text=Submit");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("error-message.png")));

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Input form validations")).scrollIntoViewIfNeeded();

		// Fill the required fields. Labels/role names may differ slightly on the page; adjust if needed.
		page.getByLabel("Name").fill("John Doe");
		page.getByLabel("Email").fill("john.doe@example.com");
		page.getByLabel("Password").fill("Password123");
		page.getByLabel("Company").fill("LambdaTest");
		page.getByLabel("Website").fill("https://www.lambdatest.com/");
		page.getByLabel("City").fill("Dallas");
		page.getByLabel("Address 1").fill("123 Main St");
		page.getByLabel("Address 2").fill("Suite 120");
		page.getByLabel("State").fill("TX");
		page.getByLabel("Zip Code").fill("75001");
		page.selectOption("select[name='country']", "United States");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("details-filled.png")));

		page.click("text=Submit");

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Input form validations")).scrollIntoViewIfNeeded();
		Locator successMsg = page.locator("#success_message, .success-msg").first();
		String successText = successMsg.textContent();
		assertEquals("Thanks for contacting us, we will get back to you shortly.", successText != null ? successText.trim() : null);
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("confirmation-message.png")));
	}
}


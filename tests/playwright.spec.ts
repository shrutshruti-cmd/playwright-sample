import { test, expect } from '@playwright/test';


//Test Scenario 1
test('Validate message input in LambdaTest Selenium Playground', async ({ page }) => {
  // 1. Open LambdaTest’s Selenium Playground
  await page.goto('https://www.lambdatest.com/selenium-playground');
  
  // 2. Click “Simple Form Demo”
  await page.click('text=Simple Form Demo');
  
  // 3. Validate the URL contains “simple-form-demo”
  await expect(page).toHaveURL(/.*simple-form-demo/);
  
  // 4. Create a variable for a string value
  const testMessage = "Welcome to LambdaTest";
 await page.screenshot({ path: 'simple-form-demo.png' }); 
  
  // 5. Enter the string in the “Enter Message” text box
  await page.getByRole('textbox', { name: 'Please enter your Message' }).click();
  await page.getByRole('textbox', { name: 'Please enter your Message' }).fill(testMessage);
  
  // 6. Click “Get Checked Value”
  await page.getByRole('button', { name: 'Get Checked Value' }).click();
  
  // 7. Validate the right-hand message matches the input
  const actualMessage = await page.textContent('#message');
  console.log('Actual message:', actualMessage);
  expect(actualMessage?.trim()).toBe(testMessage);

 await page.screenshot({ path: 'message.png' });
});


//Test Scenario 2
test('Drag Default value 15 slider to 95', async ({ page }) => {
  // 1. Open the playground page
  await page.goto('https://www.lambdatest.com/selenium-playground');

  // 2. Click “Drag & Drop Sliders” 
  await page.click('text=Drag & Drop Sliders');

   // 3. Locate the slider 
await page.screenshot({ path: 'drag-drop.png' }); 
   const slider = page.locator('div').filter({ hasText: /^15$/ });

   await page.locator('#slider3').getByRole('slider').fill('95');
   await page.locator('div').filter({ hasText: /^95$/ }).click();

  // 4. Assert the value 
  const valueById = page.locator('#rangeSuccess');
  await expect(valueById).toHaveText('95');
  await page.screenshot({ path: 'value-set.png' }); 
});


//Test Scenario 3
test('Input Form Submit', async ({ page }) => {
  // 1. Open the Selenium Playground page
  await page.goto('https://www.lambdatest.com/selenium-playground');

  // 2. Click "Input Form Submit"
  await page.click('text=Input Form Submit');
  await page.screenshot({ path: 'input-form-submit.png' });

  // 3. Click "Submit"
  await page.click('text=Submit');

  // 4. Check for validation error messages
  await page.screenshot({ path: 'error-message.png' });

  // 5. Fill required fields
  await page.getByRole('heading', { name: 'Input form validations' }).scrollIntoViewIfNeeded();
  await page.getByRole('textbox', { name: 'Name' }).fill('John Doe');
  await page.getByRole('textbox', { name: 'Email*' }).fill('john.doe@example.com');
  await page.getByRole('textbox', { name: 'Password*' }).fill('Password123');
  await page.getByRole('textbox', { name: 'Company' }).fill('LambdaTest');
  await page.getByRole('textbox', { name: 'Website' }).fill("https://www.lambdatest.com/");
  await page.getByRole('textbox', { name: 'City', exact: true }).fill('Dallas');
  await page.getByRole('textbox', { name: 'Address 1' }).fill('123 Main St');
  await page.getByRole('textbox', { name: 'Address 2' }).fill('Suite 120');
  await page.getByRole('textbox', { name: 'City* State*' }).fill('Dallas, TX');
  await page.getByRole('textbox', { name: 'Zip Code*' }).fill('75001');

  // 6. Select United States
  await page.selectOption('select[name="country"]', { label: 'United States' });
  await page.screenshot({ path: 'details-filled.png' });

  // 7. Click Submit 
  await page.click('text=Submit');

  // 8. Check the success message
  await page.getByRole('heading', { name: 'Input form validations' }).scrollIntoViewIfNeeded();
  const successMsg = page.locator('#success_message, .success-msg').first();
  await expect(successMsg).toHaveText('Thanks for contacting us, we will get back to you shortly.');
  await page.screenshot({ path: 'confirmation-message.png' });
});





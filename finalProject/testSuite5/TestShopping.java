package finalProject.testSuite5;

import finalProject.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestShopping {

    private WebDriver driver;
    private LoginSauce loginSauce;
    private InventorySauce inventorySauce;
    private CartSauce cartSauce;
    private CheckoutInformation checkout;
    private CheckoutOverview overview;
    private CheckoutComplete complete;

    @BeforeSuite
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        inventorySauce = new InventorySauce(driver);
        loginSauce = new LoginSauce(driver);
        cartSauce = new CartSauce(driver);
        checkout = new CheckoutInformation(driver);
        overview = new CheckoutOverview(driver);
        complete = new CheckoutComplete(driver);
    }

    @AfterClass
    public void finish(){
        driver.quit();
    }

    @AfterSuite
    public void close(){
        driver.quit();
    }


    /**
     * This test verifies that when all the input is correct, checkout is completed successfully
     * and the right message is displayed for placing order
     * Test data: korisnicko: standard_user, lozinka: secret_sauce, firstName: jovana,
     * lastName: milicic, zipCode: 10001
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on the cart icon, in the top right corner
     * 7. Assert "Sauce Labs Backpack" item is displayed in the cart
     * 8. Click on checkout button
     * 9. Input test data in fistName, lastName, zipCode fields
     * 10. Click on continue button
     * 11. Assert current URL is https://www.saucedemo.com/checkout-step-two.html
     * 12. Click on finish button
     * 13. Assert you get checkout confirmation message "THANK YOU FOR YOUR ORDER"
     */

    @Test
    public void testShoppingOneDressHappyPath(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.setAddToCart();
        inventorySauce.clickOnCart();

        String productTitle = cartSauce.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));

        cartSauce.clickOnCheckoutButton();
        checkout.enterFirstName("jovana");
        checkout.enterLastName("milicic");
        checkout.enterPostalCode("10001");
        checkout.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html",
                "current URL should be equal to https://www.saucedemo.com/checkout-step-two.html");

        Assert.assertTrue(overview.checkIfCorrectHeaderIsDisplayed());
        overview.clickOnFinishButton();
        Assert.assertTrue(complete.checkCheckoutConfirmation());

    }

    /**
     * This test verifies correct alert message is displayed when tried to complete checkout
     * without entering zipCode
     * Test data: korisnicko: standard_user, lozinka: secret_sauce, firstName: jovana,
     * lastName: milicic
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on the cart icon, in the top right corner
     * 7. Assert "Sauce Labs Backpack" item is displayed in the cart
     * 8. Click on checkout button
     * 9. Input test data only in fistName, lastName fields
     * 10. Click on continue button
     * 11. Assert "Error: Postal Code is required" message is displayed
     */

    @Test
    public void testShoppingWithNoZipCodeEnteredAlertIsPresent(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.setAddToCart();
        inventorySauce.clickOnCart();

        String productTitle = cartSauce.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));

        cartSauce.clickOnCheckoutButton();
        checkout.enterFirstName("jovana");
        checkout.enterLastName("milicic");
        checkout.clickOnContinueButton();
        Assert.assertTrue(checkout.isAlertMessageDisplayed());
    }
}

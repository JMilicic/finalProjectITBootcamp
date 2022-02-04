package finalProject.testSuite3;

import finalProject.pages.CartSauce;
import finalProject.pages.InventorySauce;
import finalProject.pages.LoginSauce;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestRemoveFromCart {

    private WebDriver driver;
    private LoginSauce loginSauce;
    private CartSauce cartSauce;
    private InventorySauce inventorySauce;

    @BeforeSuite
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginSauce = new LoginSauce(driver);
        cartSauce = new CartSauce(driver);
        inventorySauce = new InventorySauce(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
     * This test verifies that when 1 item is in the cart when you remove it, the cart is empty
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" description
     * 6. Click on cart icon, in the top right corner
     * 7. Assert one "Sauce Labs Backpack" is present in the cart
     * 8. Click on remove button
     * 9. Assert the cart is empty
     */

    @Test()
    public void testOneItemIsRemoved() throws InterruptedException {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.setAddToCart();
        inventorySauce.clickOnCart();
        String productTitle = cartSauce.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));
        cartSauce.clickOnRemoveButton1();
        Thread.sleep(4000);
        Assert.assertFalse(cartSauce.checkRemoveButtonIsNotDisplayed());

    }

    /**
     * This test verifies that when there are 2 items in the cart, if you remove 1, the correct one is left
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on add to cart button below "Sauce Labs Bike Light" item
     * 7. Click on cart icon, in the top right corner
     * 8. Click on remove button next to "Sauce Labs Backpack" item
     * 9. Assert that only "Sauce Labs Bike Light" item is displayed in the cart
     */

    @Test()
    public void testRemoveOneOutOfOneItems() throws InterruptedException {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.setAddToCart();
        inventorySauce.clickOnAddToCart2Button();
        inventorySauce.clickOnCart();
        cartSauce.clickOnRemoveButton1();
        Thread.sleep(4000);
        Assert.assertTrue(cartSauce.checkCartContentAfterRemoving1Item());
    }

    /**
     * This test verifies that when there is no items in the cart, remove button is not displayed
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on cart icon, in the top right corner
     * 6. Assert remove button is not displayed
     */

    @Test()
    public void testRemoveButtonIsNotDisplayed(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnCart();
        Assert.assertFalse(cartSauce.checkRemoveButtonIsNotDisplayed());

    }

}

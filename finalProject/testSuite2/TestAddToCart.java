package finalProject.testSuite2;

import finalProject.pages.LoginSauce;
import finalProject.pages.CartSauce;
import finalProject.pages.InventorySauce;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestAddToCart {

    private WebDriver driver;
    private CartSauce cartSauce;
    private InventorySauce inventorySauce;
    private LoginSauce loginSauce;

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
     * This test verifies that when 1 item is selected and added to cart,
     * that one item is present in the cart
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" description
     * 6. Click on cart icon, in the top right corner
     * 7. Assert one "Sauce Labs Backpack" is present in the cart
     */

    @Test()
    public void testOneItemAddedToCartHappyPath(){

        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.setAddToCart();
        inventorySauce.clickOnCart();
        String productTitle = cartSauce.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));

    }

    /**
     * This test verifies that when 3 different items are added to cart,
     * those 3 items are present in the cart
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on add to cart button below "Sauce Labs Bike Light" item
     * 7. Click on add to cart button below "Sauce Labs Bolt T-Shirt" item
     * 8. Click on the cart icon, in the top right corner
     * 9. Assert all 3 items are present in the cart
     */


    @Test()
    public void testThreeDifferentItemsAddedToCartHappyPath() throws InterruptedException {

        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnAddToCart2Button();
        inventorySauce.clickOnAddToCart3Button();
        inventorySauce.clickOnAddToCart4Button();
        inventorySauce.clickOnCart();
        Thread.sleep(5000);
        Assert.assertTrue(cartSauce.checkCartContentFor3Items());
    }

    /**
     * This test verifies that when there is no items added to the cart, nothing is present in
     * the cart
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on the cart icon, in the top right corner
     * 6. Assert there is no items in the cart
     */

    @Test()
    public void testNoItemsAddedToCart(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnCart();
        Assert.assertFalse(cartSauce.getCartElements());

    }
}

package finalProject.testSuite4;

import finalProject.pages.CartSauce;
import finalProject.pages.InventorySauce;
import finalProject.pages.LoginSauce;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestSorting {

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
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
     * This test verifies when you choose sorting option for price - high to low, items are displayed in descending order by price
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on sorting button
     * 6. Select "high to low" option from the sorting drop down menu
     * 7. Assert prices are sorted in descending order
     */


    @Test
    public void testHighToLowPriceOrder(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnSortingButton();
        inventorySauce.selectSortingOptionHighToLow();
        Assert.assertTrue(inventorySauce.checkSortingByPriceHighToLow());
    }

    /**
     * This test verifies when you choose sorting option for name - A to Z,
     * the first item displayed is not "Test.allTheThings() T-Shirt (Red)"
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on sorting button
     * 6. Select "A to Z" option from the sorting drop down menu
     * 7. Assert "Test.allTheThings() T-Shirt (Red)" is not first on the items' list
     */

    @Test
    public void testSortingByNameAtoZ(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnSortingButton();
        inventorySauce.selectSortingOptionAtoZ();
        Assert.assertFalse(inventorySauce.checkSortingByNameAtoZ());
    }

}

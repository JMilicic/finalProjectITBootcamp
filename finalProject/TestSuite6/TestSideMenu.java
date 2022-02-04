package finalProject.TestSuite6;

import finalProject.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestSideMenu {

    private WebDriver driver;
    private LoginSauce loginSauce;
    private InventorySauce inventorySauce;

    @BeforeSuite
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        inventorySauce = new InventorySauce(driver);
        loginSauce = new LoginSauce(driver);
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
     * This test verifies all 4 options are listed when clicked on side menu
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Assert all 4 options are listed in the side menu
     */

    @Test
    public void testSideMenuItemsAreAllListed(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnSideMenuButton();
        Assert.assertTrue(inventorySauce.checkSideMenuItemsAllAreListed());
    }

    /**
     * This test verifies that when clicked on logout button from inside the side menu,
     * you are successfully logged out
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Click on logout button from the side menu
     * 7. Assert you are logged out - login button is now displayed
     */

    @Test
    public void testWhenClickedOnLogoutInsideSideMenuYouAreLoggedOut(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnSideMenuButton();
        inventorySauce.clickOnLogoutButtonSideMenu();
        Assert.assertTrue(loginSauce.loginButtonIsDisplayed());
    }

    /**
     * This test verifies when clicked on about button, you are navigated to https://saucelabs.com/
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Click on about button from the side menu
     * 7. Assert you are navigated to https://saucelabs.com/
     */

    @Test
    public void testWhereNavigatedWhenClickedOnAboutButton(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnSideMenuButton();
        inventorySauce.clickOnAboutButtonSideMenu();
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/");
    }

    /**
     * This test verifies after opening the side menu, if you click on x (close) button, side menu is collapsed
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Click on x (close) button in the top right corner of the side menu
     * 7. Assert that side menu is collapsed
     */

    @Test
    public void testIfSideMenuIsCollapsedWhenClickedOnX(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.logIn();
        inventorySauce.clickOnSideMenuButton();
        inventorySauce.clickOnXButtonSideMenu();
        Assert.assertTrue(inventorySauce.sideMenuButtonIsClickable());
    }
}

package finalProject.testSuite1;

import finalProject.pages.LoginSauce;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestLogin {

    private WebDriver driver;
    private LoginSauce loginSauce;

    @BeforeSuite
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginSauce = new LoginSauce(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite
    public void close(){
        driver.close();
    }

    /**
     * This test verifies user is successfully logged in with valid data entered
     * Test data: korisnicko: standard_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password filed
     * 4. Click on login
     * 5. Assert that user is logged in - https://www.saucedemo.com/inventory.html page shows
     */


    @Test()
    public void testSuccessfulLogin(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.inputUsername("standard_user");
        loginSauce.inputPassword("secret_sauce");
        loginSauce.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html",
                "You are successfully logged in");
    }

    /**
     * This test verifies correct message is displayed when tried logging in with incorrect data
     * Test data: korisnicko: jovana, lozinka: milicic
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password field
     * 4. Click on login button
     * 5. Assert "Username and password do not match any user in this service" message is displayed
     */

    @Test()
    public void testLoginWithInvalidData(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);
        loginSauce.inputUsername("jovana");
        loginSauce.inputPassword("milicic");
        loginSauce.clickOnLoginButton();
        String poruka = loginSauce.getAlertMessage();
        System.out.println(poruka);
        Assert.assertTrue(poruka.contains("do not match any user"));
    }

    /**
     * This test verifies an alert message is displayed when trying to login with locked out user
     * Test data: korisnicko: locked_out_user, lozinka: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input korisnicko in username field
     * 3. Input lozinka in password field
     * 4. Click on login button
     * 5. Assert message "Epic sadface: Sorry, this user has been locked out." is displayed
     */

    @Test
    public void testLoginWithLockedOutUser(){
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(),baseURL,
                "The current URL should be equal to " + baseURL);

        loginSauce.inputUsername("locked_out_user");
        loginSauce.inputPassword("secret_sauce");
        loginSauce.clickOnLoginButton();
        Assert.assertTrue(loginSauce.alertMessageIsDisplayed());
    }

}

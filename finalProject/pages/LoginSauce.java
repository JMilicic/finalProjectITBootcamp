package finalProject.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginSauce {

    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(id = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    WebElement alertMessage;

    public LoginSauce(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputUsername(String korisnickoIme) {
        userName.sendKeys(korisnickoIme);
    }

    public void inputPassword(String lozinka) {
        password.sendKeys(lozinka);
    }

    public void clickOnLoginButton() {
        js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }

    public String getAlertMessage() {
        return alertMessage.getText();
    }

    public void logIn() {
        inputUsername("standard_user");
        inputPassword("secret_sauce");
        clickOnLoginButton();
    }

    public boolean loginButtonIsDisplayed() {
        if (loginButton.isDisplayed()) return true;
        else return false;
    }

    public boolean alertMessageIsDisplayed() {
        if (alertMessage.isDisplayed()) return true;
        else return false;
    }
}

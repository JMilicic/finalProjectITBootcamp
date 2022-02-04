package finalProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutComplete {

    WebDriver driver;

    @FindBy(id = "checkout_complete_container")
    WebElement checkoutConfirmation;

    public CheckoutComplete(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkCheckoutConfirmation() {
        if (checkoutConfirmation.isDisplayed()) return true;
        else return false;
    }
}

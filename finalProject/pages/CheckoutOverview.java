package finalProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverview {

    WebDriver driver;

    @FindBy(id = "item_4_title_link")
    WebElement itemDisplayed;

    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckoutOverview(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfCorrectHeaderIsDisplayed() {
        if (itemDisplayed.isDisplayed()) return true;
        else return false;
    }

    public void clickOnFinishButton() {
        finishButton.click();
    }
}

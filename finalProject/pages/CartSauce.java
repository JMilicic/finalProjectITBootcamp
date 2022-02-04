package finalProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartSauce {

    WebDriver driver;

    @FindBy(className = "inventory_item_name")
    WebElement productTitle;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]")
    WebElement cartContent;

    @FindBy(xpath = "//*[@id=\"item_0_title_link\"]/div")
    WebElement cartItemLeft;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeButton1;

    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement removeButton2;


    public CartSauce(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    public boolean checkCartContentFor3Items() {
        if (cartContent.getText().contains("Sauce Labs Fleece Jacket") &&
                cartContent.getText().contains("Sauce Labs Bike Light") &&
                cartContent.getText().contains("Sauce Labs Bolt T-Shirt")) return true;
        else return false;
    }

    public void clickOnRemoveButton1() {
        removeButton1.click();
    }

    public boolean checkCartContentAfterRemoving1Item() {
        if (cartItemLeft.getText().contains("Sauce Labs Bike Light")) return true;
        else return false;
    }

    public boolean checkRemoveButtonIsNotDisplayed() {
        return driver.findElements(By.id("remove-sauce-labs-backpack")).size() > 0;
    }

    public boolean getCartElements() {
        return driver.findElements(By.className("cart_item")).size() > 0;
    }

}

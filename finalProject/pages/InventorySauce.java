package finalProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventorySauce {

    WebDriver driver;
    Select select;

    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-backpack\"]")
    WebElement addToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement addToCart2;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement addToCart3;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    WebElement addToCart4;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a")
    WebElement cart;

    @FindBy(id = "react-burger-menu-btn")
    WebElement sideMenuButton;

    @FindBy(className = "menu-item")
    List<WebElement> sideMenuItems;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButtonSideMenu;

    @FindBy(id = "about_sidebar_link")
    WebElement aboutButtonSideMenu;

    @FindBy(id = "react-burger-cross-btn")
    WebElement xButtonSideMenu;

    @FindBy(className = "inventory_item_price")// lista cena
    List<WebElement> inventoryPrice;

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemNames;

    @FindBy(id = "item_3_title_link")
    WebElement redTShirtItem;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select")
    WebElement sortingButton;

    @FindBy(className = "product_sort_container")
    WebElement sortingButtonOptions;


    public InventorySauce(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setAddToCart() {
        addToCart.click();
    }

    public void clickOnAddToCart2Button() {
        addToCart2.click();
    }

    public void clickOnAddToCart3Button() {
        addToCart3.click();
    }

    public void clickOnAddToCart4Button() {
        addToCart4.click();
    }

    public void clickOnCart() {
        cart.click();
    }

    public void clickOnSortingButton() {
        sortingButton.click();
    }

    public void selectSortingOptionHighToLow() {
        select = new Select(sortingButtonOptions);
        select.selectByValue("hilo");
    }

    public boolean checkSortingByPriceHighToLow() {
        int totalNumberOfEntries = inventoryPrice.size() - 1;
        boolean test = true;
        for (int i = 0; i <= totalNumberOfEntries; i++) {
            if (i == totalNumberOfEntries) {
                test = true;
            } else {
                String currentPrice = inventoryPrice.get(i).getText().replace("$", "");
                String nextPrice = inventoryPrice.get(i + 1).getText().replace("$", "");
                double currentPrice2 = Double.parseDouble(currentPrice);
                double nextPrice2 = Double.parseDouble(nextPrice);
                if (currentPrice2 > nextPrice2) test = true;

                else test = false;
            }
        }
        return test;
    }

    public void selectSortingOptionAtoZ() {
        select = new Select(sortingButtonOptions);
        select.selectByValue("az");
    }

    public boolean checkSortingByNameAtoZ() {
        for (int i = 0; i < inventoryItemNames.size(); i++) {
            if (redTShirtItem == inventoryItemNames.get(0)) return true;
        }
        return false;
    }

    public void clickOnSideMenuButton() {
        sideMenuButton.click();
    }

    public boolean checkSideMenuItemsAllAreListed() {
        if (sideMenuItems.size() == 4) return true;
        else return false;
    }

    public void clickOnLogoutButtonSideMenu() {
        logoutButtonSideMenu.click();
    }

    public void clickOnAboutButtonSideMenu() {
        aboutButtonSideMenu.click();
    }

    public void clickOnXButtonSideMenu() {
        xButtonSideMenu.click();
    }

    public boolean sideMenuButtonIsClickable() {
        if (sideMenuButton.isEnabled()) return true;
        else return false;
    }

}

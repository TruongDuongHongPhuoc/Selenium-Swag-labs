import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.className("btn_inventory");
    private By productFillter = By.xpath("//select[@class='product_sort_container']");
    private By productImage = By.xpath("//img[@class='inventory_item_img']");
    private By removeButton = By.xpath("//button[@class='btn_secondary btn_inventory']");

    private By cartQuantity = By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']");
    private By cartButton = By.xpath("//a[@class='shopping_cart_link fa-layers fa-fw']//*[name()='svg']");

    private By burgerButton = By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button");
    private By closeButton = By.xpath("//button[text()='Close Menu']");

    private By inventoryContainer = By.className("bm-overlay");
    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.saucedemo.com/v1/inventory.html");
    }
    public WebElement getInventoryContainer(){
        return driver.findElement(inventoryContainer);
    }
    // Get list of all product names
    public List<WebElement> getAllProductNames() {
        return driver.findElements(productNames);
    }
    // Get product names as text
    public List<String> getAllProductNamesAsText() {
        List<WebElement> nameElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (WebElement nameElement : nameElements) {
            names.add(nameElement.getText());
        }
        return names;
    }

    // Get list of all product prices
    public List<WebElement> getAllProductPrices() {
        return driver.findElements(productPrices);
    }

    // Get the price of a specific product
    public String getProductPrice(String productName) {
        List<WebElement> products = driver.findElements(productNames);
        List<WebElement> prices = driver.findElements(productPrices);

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getText().equals(productName)) {
                return prices.get(i).getText();
            }
        }
        return "Product not found";
    }

    // Click "Add to Cart" for a specific product
    public void addProductToCart(String productName) {
        List<WebElement> products = driver.findElements(productNames);
        List<WebElement> buttons = driver.findElements(addToCartButtons);

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getText().equals(productName)) {
                buttons.get(i).click();
                break; // Stop loop once product is found
            }
        }
    }

    // Get all product prices as numbers
    public List<Double> getAllProductPricesAsNumbers() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            prices.add(Double.parseDouble(priceElement.getText().replace("$", "")));
        }
        return prices;
    }

    public List<WebElement> getAllProductImage(){
        return driver.findElements(productImage);
    }

    public Integer getCartQuantity(){
        return Integer.valueOf(driver.findElement(cartQuantity).getText());
    }
    public List<WebElement> getRemoveButtons(){
        return driver.findElements(removeButton);
    }

    public WebElement getCartButton(){
        return driver.findElement(cartButton);
    }

    public WebElement getBurgerButton() {
        return driver.findElement(burgerButton);
    }

    public WebElement getLeftNavigationCloseButton(){
        return driver.findElement(closeButton);
    }

    //Get Dropbar Select Price High to Low
    // Select sorting option
    public void sortProducts(String option) {
        Select dropdown = new Select(driver.findElement(productFillter));
        dropdown.selectByValue(option);
    }

}

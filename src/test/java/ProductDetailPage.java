import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class ProductDetailPage {
    private WebDriver driver;

    private By productImage = By.xpath("//img[@class='inventory_details_img']");
    private By productName = By.xpath("//div[@class='inventory_details_name']");
    private By productPrice = By.className("inventory_details_price");
    private By productRemoveButton = By.xpath("//button[@class='btn_secondary btn_inventory']");
    private By productAddToCartButton = By.cssSelector(".btn_primary.btn_inventory");
    private By productBackButton = By.xpath("//button[@class='inventory_details_back_button']");
    private By cartQuantity = By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']");
    public String productURL = "https://www.saucedemo.com/v1/inventory-item.html?id=2"; // Sauce Labs Onesie // 7.99

    public ProductDetailPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getProdudctImage(){
        return driver.findElement(productImage);
    }

    public WebElement getProductName(){
        return driver.findElement(productName);
    }

    public String getProductNameAsString(){
        return driver.findElement(productName).getText();
    }

    public WebElement getProductPrice(){
        return driver.findElement(productPrice);
    }

    public Float getProductPriceAsFloat(){
        return Float.parseFloat(driver.findElement(productPrice).getText().replace("$",""));
    }

    public WebElement getProductRemoveButton(){
        return driver.findElement(productRemoveButton);
    }
    public WebElement getProductBackButton(){
        return driver.findElement(productBackButton);
    }
    public WebElement getProductAddToCartButton(){
        return driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']"));
    }
    public Integer getCartQuantityAsInt() {
        return Integer.valueOf(driver.findElement(cartQuantity).getText());
    }






}

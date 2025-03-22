import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPageTest extends baseTest{

    @Test(groups = "CartPageTest",dataProvider = "productData", dataProviderClass = ProductProvider.class, dependsOnMethods = "HomePageTest.testAddToCart") // get the data from Provider
    public void verifyDisplayItemAddedToCart(String productName){
        CartPage cartPage = new CartPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> productNameElement = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className("inventory_item_name")) ));

        Assert.assertTrue(cartPage.getProductsNameAsString().contains(productName));
    }

    @Test(groups = "CartPageTest",dataProvider = "ProductPriceData", dataProviderClass = ProductProvider.class, dependsOnMethods = "HomePageTest.testAddToCart")
    public void verifyCorrectPriceInCart(String productName,String price){
        float productPrice = Float.valueOf(price);
        CartPage cartPage = new CartPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertEquals(cartPage.getPriceOfProductName(productName),productPrice);
    }

    @Test(groups = "CartPageTest",dependsOnMethods = {"verifyCorrectPriceInCart","verifyDisplayItemAddedToCart"})
    public void verifyRemoveItemFromCart(){
        CartPage cartPage = new CartPage(driver);
        List<String> productNames = cartPage.getProductsNameAsString();

        cartPage.getRemoveButtonOfProductName(productNames.get(1)).click();
        cartPage.getRemoveButtonOfProductName(productNames.get(2)).click();

       Assert.assertEquals(cartPage.getProductsNameAsElement().size(),2);
    }

    @Test(groups = "CartPageTest",dependsOnMethods = "verifyRemoveItemFromCart")
    public void verifyNavigateToCheckOutInformation(){
        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckOutButton().click();
        Assert.assertEquals(cartPage.getHeaderText().getText(),"Checkout: Your Information");
    }

    @AfterClass
    public void convertCartToData() {
        CartPage cartPage = new CartPage(driver);
        List<WebElement> productNamesElement = cartPage.getProductsNameAsElement();

        for (int i = 0; i < productNamesElement.size(); i++) {
            String productName = productNamesElement.get(i).getText();
            Float productPrice = cartPage.getPriceOfProductName(productName);
            Integer quantity = cartPage.getQuantityOfProductName(productName);

            Map<String, Float> priceMap = new HashMap<>();
            priceMap.put(productName, productPrice);
            ProductProvider.productPriceMap.add(priceMap);

            Map<String, Integer> quantityMap = new HashMap<>();
            quantityMap.put(productName, quantity);
            ProductProvider.productQuantityMap.add(quantityMap);
        }
    }

}

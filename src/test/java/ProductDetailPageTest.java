import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductDetailPageTest extends baseTest{

    @Test (dependsOnMethods ="LoginTest.testInvalidLogin")
    public void dependency(){

    }

    @Test (dependsOnMethods = "dependency")
    public void verifyImageIsLoaded(){
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        driver.get(productDetailPage.productURL);

        WebElement img = productDetailPage.getProdudctImage();
        String imageSource = img.getAttribute("src");

        Assert.assertNotNull(imageSource);
        Assert.assertFalse(imageSource.isEmpty());
    }

    @Test (dependsOnMethods = "dependency")
    public void verifyCorrectProductName(){
        ProductDetailPage pDP = new ProductDetailPage(driver);
        driver.get(pDP.productURL);

        String productName = pDP.getProductNameAsString();
        Assert.assertEquals(productName,"Sauce Labs Onesie");
    }

    @Test (dependsOnMethods = "dependency")
    public void verifyCorrectProductPrice(){
        ProductDetailPage pDP = new ProductDetailPage(driver);
        driver.get(pDP.productURL);

        String productPrice = pDP.getProductPrice().getText();
        Assert.assertEquals(productPrice,"$7.99");
    }

    @Test (dependsOnMethods = "dependency")
    public void verifyButtonAddRemoveToCartButton(){
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        driver.get(productDetailPage.productURL);

        productDetailPage.getProductAddToCartButton().click();
        Assert.assertEquals(productDetailPage.getCartQuantityAsInt(),1);

        productDetailPage.getProductRemoveButton().click();
    }

}

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CheckOutStep2Test extends baseTest{

    @Test(dependsOnGroups = "CartPageTest")
    public void CheckOutCartPageTest(){
        //general depencency for class
    }

    @Test(dependsOnMethods = "CheckOutCartPageTest")
    public void verifyProductAreMatchInCart(){
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com/v1/checkout-step-two.html");

        List<Map<String,Float>> productPrice = ProductProvider.productPriceMap;
        List<Map<String,Integer>> productQuantity = ProductProvider.productQuantityMap;


        List<String> productNames = cartPage.getCheckOutStep2ProductsNameAsString();

        //verify productDisplay count
        Assert.assertEquals(productNames.size(),productPrice.size(),"Display size is different from cart and check out" + productNames.size() + productPrice.size());
        Assert.assertEquals(productNames.size(),productQuantity.size(),"Display size is different from cart and check out" + productNames.size() + productQuantity.size());

        //verify product have valid price
        for (int i = 0; i < productNames.size(); i++) {
           Float checkOutPrice = cartPage.getCheckOutStep2PriceOfProductName(productNames.get(i));
           Integer checkOutQuantity = cartPage.getCheckOutStep2QuantityOfProductName(productNames.get(i));

            Assert.assertEquals(checkOutPrice,getProductPriceFromListMap(productPrice,productNames.get(i)),"Price is mismatch between cart and check out at: "+ productNames.get(i));
            Assert.assertEquals(checkOutQuantity,getProductQuantityFromListMap(productQuantity,productNames.get(i)), "Quantity is mismatch between cart and check out at: "+ productNames.get(i));
        }
    }

    @Test(dependsOnMethods = "verifyProductAreMatchInCart")
    public void verifyFinishCheckOut(){
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com/v1/checkout-step-two.html");
        String currentURL = driver.getCurrentUrl();
        cartPage.getCheckOutStep2FinishButton().click();
        Assert.assertNotEquals(currentURL,driver.getCurrentUrl());
    }

    @Test
    public void verifyCancelCheckOut(){
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com/v1/checkout-step-two.html");
        String currentURL = driver.getCurrentUrl();
        cartPage.getCheckOutStep2CancelButton().click();

        Assert.assertNotEquals(currentURL,driver.getCurrentUrl());
    }

    @Test
    public void verifyTotalPrice(){
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com/v1/checkout-step-two.html");
        Float totalPriceOfProduct = cartPage.getCheckOutStep2TotalProductPrice();
        Float totalAfterTax = totalPriceOfProduct + cartPage.getCheckOutStep2TaxAsFloat();
        Assert.assertEquals(cartPage.getCheckOutStep2TotalAsFloat(),totalAfterTax);
    }

    public Float getProductPriceFromListMap(List<Map<String, Float>> maps, String productName) {
        for (Map<String, Float> map : maps) {
            if (map.containsKey(productName)) {
                return map.get(productName); // Return the price when product name matches
            }
        }
        return null;
    }

    public Integer getProductQuantityFromListMap(List<Map<String,Integer>> maps, String productName){
        for(Map<String,Integer> map : maps){
            if(map.containsKey(productName)){
                return map.get(productName);
            }
        }
        return null;
    }
}

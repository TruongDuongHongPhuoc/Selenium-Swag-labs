import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePageTest extends baseTest{

    @Test(dependsOnMethods = "LoginTest.testValidLogin")
    public void VerifyProductDisplay(){
        HomePage hp = new HomePage(driver);
        // Check if at least one product is displayed
        Assert.assertFalse(hp.getAllProductNames().isEmpty(), "Product list is not displayed!");

        // Check if at least one product image and price exist
        Assert.assertFalse(hp.getAllProductPrices().isEmpty(), "Product prices are not displayed!");
    }

    @Test(dependsOnMethods = "LoginTest.testValidLogin")
    public void VerifySortProductHighToLow(){
        HomePage hp = new HomePage(driver);
        hp.sortProducts("hilo");
        List<Double> prices = hp.getAllProductPricesAsNumbers();
        Assert.assertTrue(isSortedDescending(prices));
    }

    @Test(dependsOnMethods = "LoginTest.testValidLogin")
    public void VerifySortProductLowToHigh(){
        HomePage hp = new HomePage(driver);
        hp.sortProducts("lohi");
        List<Double> prices = hp.getAllProductPricesAsNumbers();
        Assert.assertTrue(isSortedAscending(prices));
    }

    @Test(dependsOnMethods = "LoginTest.testValidLogin")
    public void testSortByNameAToZ() {
        HomePage productPage = new HomePage(driver);
        productPage.sortProducts("az");

        List<String> names = productPage.getAllProductNamesAsText();
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);  // Sort manually

        Assert.assertEquals(names, sortedNames, "Products are NOT sorted alphabetically (A to Z)!");

    }

    @Test(dependsOnMethods = "LoginTest.testValidLogin")
    public void testSortByNameZToA() {
        HomePage productPage = new HomePage(driver);
        productPage.sortProducts("za"); // Sort products Z to A

        // Get product names as text
        List<String> names = productPage.getAllProductNamesAsText();

        // Manually sort in Z to A order
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames, Collections.reverseOrder()); // Reverse alphabetical order

        // Assert that the actual order matches the expected Z to A order
        Assert.assertEquals(names, sortedNames, "Products are NOT sorted alphabetically (Z to A)!");
    }

    @Test(dataProvider = "productData", dataProviderClass = ProductProvider.class,dependsOnMethods = "LoginTest.testValidLogin" ) // get the data from Provider
    public void testAddToCart(String productName) throws InterruptedException {
        HomePage hpt = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        hpt.addProductToCart(productName);
        WebElement productLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[@class='btn_secondary btn_inventory']"))
        );
        Assert.assertEquals(hpt.getCartQuantity(),hpt.getRemoveButtons().size(),"Quanitity is not match with the added item");
        Assert.assertTrue(true);
    }

    @Test
    public void verifyProductImageDisplay(){
        HomePage hpt = new HomePage(driver);
        List<WebElement> imgs = hpt.getAllProductImage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item_img")));

        for (int i = 0; i < imgs.size(); i++) {
            WebElement element = imgs.get(i);

            if(element.isDisplayed()){
                String imageSource = element.getAttribute("src");

                Assert.assertNotNull(imageSource,"Image " + (i + 1) + " has a null src attribute.");
                Assert.assertFalse(imageSource.isEmpty(),"Image " + (i + 1) + " has an empty src attribute.");
            }else {
                System.out.println("Image is not display at " + i+1);
            }
        }
    }

    @Test (priority = 1, dependsOnMethods = "LoginTest.testValidLogin")
    public void VerifyUserRedirectToCart(){
        HomePage hpt = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        hpt.getCartButton().click();
        Assert.assertEquals(cartPage.getHeaderAsString(),"Your Cart");
    }

    @Test(dependsOnMethods = "LoginTest.testValidLogin")
    public void verifyUserCanOpenLeftNavigation(){
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));



        homePage.getBurgerButton().click();
        WebElement isNavigationOpened = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='bm-item-list']")));

        Assert.assertTrue(isNavigationOpened != null);
    }

    @Test(dependsOnMethods = {"verifyUserCanOpenLeftNavigation"})
    public void verifyUserCanCloseLeftNavigationByButton() {
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the left navigation
        homePage.getBurgerButton().click();
        wait.until(ExpectedConditions.visibilityOf(homePage.getLeftNavigationCloseButton()));
        // Close the left navigation
        homePage.getLeftNavigationCloseButton().click();

        // Verify the left navigation is closed
        boolean isNavigationClosed = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath("//nav[@class='bm-item-list']"))
        );

        // Assert the sidebar is closed
        Assert.assertTrue(isNavigationClosed, "The navigation sidebar did not close as expected.");
    }


    @Test(dependsOnMethods = {"verifyUserCanOpenLeftNavigation"})
    public void verifyUserCanCloseLeftNavigationClickOutSide() {
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open sidebar
        homePage.getBurgerButton().click();
        wait.until(ExpectedConditions.visibilityOf(homePage.getLeftNavigationCloseButton()));

        // Click outside the sidebar
        homePage.getInventoryContainer().click();

        // ✅ Better way to check if sidebar is closed
        boolean isNavigationClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//nav[@class='bm-item-list']")));

        // ✅ Assertion with meaningful message
        Assert.assertTrue(isNavigationClosed, "Sidebar did not close when clicking outside.");
    }



    // Utility method to check low to high price
    private boolean isSortedDescending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    // Utility method to check high to low price
    private boolean isSortedAscending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}

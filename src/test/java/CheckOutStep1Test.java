import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckOutStep1Test extends baseTest{

    @Test(groups = "CheckOutStep1Test",dependsOnGroups = "CartPageTest")
    public void dependenceToGroupTestCartPage(){
        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
    }

    @Test(groups = "CheckOutStep1Test",dependsOnMethods = {"dependenceToGroupTestCartPage"})
    public void verifyCheckOutWithValidInputs(){
        CartPage cartPage = new CartPage(driver);
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
        cartPage.getCheckOutFirstName().sendKeys("Tony");
        cartPage.getCheckOutLastName().sendKeys("Stark");
        cartPage.getCheckOutZipCode().sendKeys("ABC1234567");
        cartPage.getCheckOutContinue().click();
        boolean isStep2 = wait.until(ExpectedConditions.textToBePresentInElement(cartPage.getCheckOutStep2Header(),"Checkout: Overview"));
        Assert.assertTrue(isStep2);
    }

    @Test(groups = "CheckOutStep1Test",dependsOnMethods = "dependenceToGroupTestCartPage",dataProvider = "CheckOutStep1Data",dataProviderClass = ProductProvider.class)
    public void verifyCheckOutWithInValidInputs(String firstName,String lastName, String postalCode, String errorMessage){
        CartPage cartPage = new CartPage(driver);

        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
        String currentURL = driver.getCurrentUrl();

        cartPage.clearInputsFieldCheckOut1();
        cartPage.getCheckOutFirstName().sendKeys(firstName);
        cartPage.getCheckOutLastName().sendKeys(lastName);
        cartPage.getCheckOutZipCode().sendKeys(postalCode);
        cartPage.getCheckOutContinue().click();

        // Verify if the application navigates to another page
        if (!currentURL.equals(driver.getCurrentUrl())) {
            // Check if the header text is null
            WebElement headerCheckOut2 = cartPage.getCheckOutStep2Header();
            if (headerCheckOut2 != null) {
                Assert.fail("User not supposed to be continue with invalid information");
            }
            return;
        }

        boolean isCorrectError = wait.until(ExpectedConditions.textToBePresentInElement(cartPage.getCheckOutErrorMessage(),errorMessage));
        Assert.assertTrue(isCorrectError,"Fail Expected" + errorMessage + "But get" + cartPage.getCheckOutErrorMessage());

    }






}

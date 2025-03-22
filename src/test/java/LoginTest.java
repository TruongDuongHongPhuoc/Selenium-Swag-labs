import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends baseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] getInvalidCredentials() {
        return new Object[][] {
                {"User1Admin", "Abc123@", "Epic sadface: Username and password do not match any user in this service"},
                {"", "Secret", "Epic sadface: Username is required"},
                {"User1Admin", "", "Epic sadface: Password is required"},
                {"locked_out_user","secret_sauce","Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void testInvalidLogin(String username, String password, String expectedError) {
        driver.navigate().refresh();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearInputUserNameAndPassword();
        loginPage.EnterUserName(username);
        loginPage.EnterPassword(password);
        loginPage.ClickLogin();

        Assert.assertEquals(loginPage.getErrorMessageAsText(), expectedError);
        loginPage.clearInputUserNameAndPassword();
    }

    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        driver.navigate().refresh();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearInputUserNameAndPassword();
        loginPage.EnterUserName(SuperSecret.ValidUserName);
        loginPage.EnterPassword(SuperSecret.ValidPassWord);
        loginPage.ClickLogin();

        verifyNavigateUserToProductPage();
    }

    public void verifyNavigateUserToProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product_label']"))
        );
        Assert.assertEquals(productLabel.getText(), "Products", "Product label text does not match!");
    }
}

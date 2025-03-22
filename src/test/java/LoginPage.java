import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait driverWait;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    LoginPage(WebDriver driver){
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        this.driver.get("https://www.saucedemo.com/v1/index.html");
    }

    public void EnterUserName(String userName){
        driver.findElement(usernameField).sendKeys(userName);
    }
    public void EnterPassword(String passWord){
        driver.findElement(passwordField).sendKeys(passWord);
    }
    public void ClickLogin(){
        driver.findElement(loginButton).click();
    }
    public boolean isErrorDisplay(){
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    public String getErrorMessageAsText() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        return driver.findElement(errorMessage).getText();
    }

    public void clearInputUserNameAndPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));

        usernameInput.clear();
        passwordInput.clear();
    }

}

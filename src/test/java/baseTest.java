import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class baseTest {
    protected WebDriver driver;
    protected String weblink = "https://www.saucedemo.com/v1/index.html";
    @Parameters("Browser")
    @BeforeClass
    public void setup(@Optional("chrome") String browser){
        driver = DriverManager.getDriver(browser);
        driver.get(weblink);
    }

    @AfterSuite
    public void teardown() {
        DriverManager.quitDriver();
    }
}

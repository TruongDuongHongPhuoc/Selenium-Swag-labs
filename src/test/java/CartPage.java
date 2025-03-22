import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/v1/cart.html");
    }

    private By productName = By.className("inventory_item_name");
    private By continueShoppingButton = By.linkText("Continue Shopping");
    private By checkOutButton = By.partialLinkText("CHECKO");
    private By header = By.className("subheader");

    //Check out step 1 attribute
    private By checkOutCancel = By.xpath("//a[@class='cart_cancel_link btn_secondary']");
    private By CheckOutcontinue = By.xpath("//input[@value='CONTINUE']");
    private By checkOutStep1Header = By.xpath("//div[@class='subheader']");
    private By checkOutFirstName = By.xpath("//input[@id='first-name']");
    private By checkOutLastName = By.xpath("//input[@id='last-name']");
    private By checkOutZipCode = By.xpath("//input[@id='postal-code']");
    private By checkOutErrorMessage = By.xpath("//h3[@data-test='error']");
    //Check out step 2 attribute
    private By checkOutStep2ProductsName = By.className("inventory_item_name");
    private By checkOutStep2ItemTotal = By.xpath("//div[@class='summary_subtotal_label']");
    private By checkOutStep2Tax = By.className("summary_tax_label");
    private By checkOutStep2Total = By.className("summary_total_label");
    private By checkOutStep2FinishButton = By.partialLinkText("FINI");
    private By checkOutStep2CancelButton = By.xpath("//a[@class='cart_cancel_link btn_secondary']");
    private By checkOutStep2Header = By.xpath("//div[@class='subheader']");

    public List<String> getProductsNameAsString(){
        List<String> productNames = new ArrayList<>();

        List<WebElement> elements = driver.findElements(productName);
        for(int i = 0; i < elements.size(); i++) {
            productNames.add(elements.get(i).getText());
        }
        return productNames;
    }

    public List<WebElement> getProductsNameAsElement(){
        return driver.findElements(productName);
    }

    public Float getPriceOfProductName(String productName){
        WebElement priceElement = driver.findElement(By.xpath("//div[text()='"+ productName + "']/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']"));
        if(priceElement != null){
            return Float.valueOf(priceElement.getText());
        }
        return 0F;
    }

    public Integer getQuantityOfProductName(String productName){
        WebElement quantityElement = driver.findElement(By.xpath("//div[text()='"+ productName + "']/ancestor::div[@class='cart_item']//div[@class='cart_quantity']"));
        if(quantityElement != null){
            return Integer.valueOf(quantityElement.getText());
        }
        return 0;
    }

    public WebElement getRemoveButtonOfProductName(String productName){
        return driver.findElement(By.xpath("//div[text()='"+ productName + "']/ancestor::div[@class='cart_item']//button[@class='btn_secondary cart_button']"));
    }

    public WebElement getContinueButton(){
        return driver.findElement(continueShoppingButton);
    }

    public WebElement getCheckOutButton(){
        return driver.findElement(checkOutButton);
    }

    public String getHeaderAsString(){
        return driver.findElement(header).getText();
    }

    //Check out step 1
    //
    //


    public WebElement getCheckOutCancel() {
        return driver.findElement(checkOutCancel);
    }
    public WebElement getCheckOutContinue(){
        return driver.findElement(CheckOutcontinue);
    }
    public WebElement getCheckOutFirstName(){
        return driver.findElement(checkOutFirstName);
    }
    public WebElement getCheckOutLastName(){
        return driver.findElement(checkOutLastName);
    }
    public WebElement getCheckOutZipCode(){
        return driver.findElement(checkOutZipCode);
    }
    public WebElement getCheckOutErrorMessage(){
        return driver.findElement(checkOutErrorMessage);
    }
    public WebElement getHeaderText(){
        return driver.findElement(checkOutStep1Header);
    }

    public void clearInputsFieldCheckOut1(){
        getCheckOutFirstName().clear();
        getCheckOutLastName().clear();
        getCheckOutZipCode().clear();
    }

    //Step2 check out attribute
    //
    //

    public List<WebElement> getCheckOutStep2ProductsName() {
        return driver.findElements(checkOutStep2ProductsName);
    }

    public List<String> getCheckOutStep2ProductsNameAsString(){
        List<WebElement> productsNameElements = getCheckOutStep2ProductsName();
        List<String> productsNameString = new ArrayList<>();
        for (int i = 0; i < productsNameElements.size(); i++) {
            productsNameString.add(productsNameElements.get(i).getText());
        }
        return productsNameString;
    }

    public WebElement getCheckOutStep2ItemTotal() {
        return driver.findElement(checkOutStep2ItemTotal);
    }

    public WebElement getCheckOutStep2Tax() {
        return driver.findElement(checkOutStep2Tax);
    }
    public Float getCheckOutStep2TaxAsFloat() {
        String taxString = driver.findElement(checkOutStep2Tax).getText();
        taxString = taxString.replace("$","");
        return Float.valueOf(taxString);
    }

    public WebElement getCheckOutStep2Total() {
        return driver.findElement(checkOutStep2Total);
    }

    public Float getCheckOutStep2TotalAsFloat() {
        String totalString = driver.findElement(checkOutStep2Tax).getText();
        totalString = totalString.replace("$","");
        return Float.valueOf(totalString);
    }

    public WebElement getCheckOutStep2FinishButton() {
        return driver.findElement(checkOutStep2FinishButton);
    }

    public WebElement getCheckOutStep2CancelButton() {
        return driver.findElement(checkOutStep2CancelButton);
    }

    public Float getCheckOutStep2PriceOfProductName(String productName){
        WebElement productPriceElement = driver.findElement(By.xpath("//div[text()='"+productName+"']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']"));
        String productPriceText = productPriceElement.getText();
        productPriceText = productPriceText.replace("$","");
        return Float.valueOf(productPriceText);
    }

    public Integer getCheckOutStep2QuantityOfProductName(String productName){
        WebElement productQuantityElement = driver.findElement(By.xpath("//div[text()='"+productName+"']/ancestor::div[@class='cart_item']//div[@class='summary_quantity']"));
        return Integer.valueOf(productQuantityElement.getText());
    }

    public String getCheckOutStep2HeaderAsString(){
        return driver.findElement(checkOutStep2Header).getText();
    }
    public WebElement getCheckOutStep2Header(){
        return driver.findElement(checkOutStep2Header);
    }


}

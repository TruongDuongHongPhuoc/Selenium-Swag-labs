import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductProvider {

    public static List<Map<String, Float>> productPriceMap = new ArrayList<>();
    public static List<Map<String,Integer>> productQuantityMap = new ArrayList<>();

    @DataProvider(name = "productData")
    public static Object[][] getProductData() {
        return new Object[][] {
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"},
                {"Sauce Labs Bike Light"}
        };
    }

    @DataProvider(name = "ProductPriceData")
    public static Object[][] getProductPriceData() {
        return new Object[][] {
                {"Sauce Labs Backpack","29.99"},
                {"Sauce Labs Bolt T-Shirt","15.99"},
                {"Sauce Labs Fleece Jacket","49.99"},
                {"Sauce Labs Bike Light","9.99"}
        };
    }
    // first name, last name, postal code, error message
    @DataProvider(name = "CheckOutStep1Data")
    public static Object[][] getCheckOutStep1Data() {
        return new Object[][] {
                {"","","","Error: First Name is required"},
                {"","Stark","ABC12345","Error: First Name is required"},
                {"Tony","","ABC12345","Error: Last Name is required"},
                {"Tony","Stark","","Error: Postal Code is required"},
                {"Tony@","Stark","ABC12345","Error: First name is invalid"},
                {"Tony123","Stark","ABC12345","Error: First name is invalid"},
                {"Tony","Stark@","ABC12345","Error: Last name is invalid"},
                {"Tony","Stark123","ABC12345","Error: Last name is invalid"},
                {"Tony","Stark123","ABC12345@#$","Error: Postal Code is invalid"}
        };
    }
}

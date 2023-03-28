package AutomationLearning;

import AutomationLearning.TestComponents.BaseTest;
import PageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName= "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"order","purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        String countryName="United States";
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalog.getProductsList();
        productCatalog.addProductToCart(input.get("productName"));// Selecting add to cart for ZARA coat

        CartPage cartPage = productCatalog.goToCart();
        Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.countrySelection(countryName);

        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmMessage = confirmationPage.confirmationMessage();
        Assert.assertEquals(confirmMessage,"THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest()
    {
        ProductCatalog productCatalog = landingPage.loginApplication("Saha@gmail.com","Saha@123");
        OrderPage orderPage=productCatalog.goToOrder();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

    }





    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","Saha@gmail.com");
//        map.put("password","Saha@123");
//        map.put("productName","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","samdev@gmail.com");
//        map1.put("password","Saha@123");
//        map1.put("productName","ADIDAS ORIGINAL");


        /*----  Optimized code   ----*/
        List<HashMap<String,String>> data= getJsondataToMap("C:\\akshata\\SeleniumFrameworkDesign\\src\\test\\java\\AutomationLearning\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}

package AutomationLearning.stepDefinition;

import AutomationLearning.TestComponents.BaseTest;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public ProductCatalog productCatalog;
    public  ConfirmationPage confirmationPage;
    String countryName="United States";

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        landingPage=launchApplicaiton();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String userName, String password){
        productCatalog = landingPage.loginApplication(userName, password);
    }

    @When("^I add product (.+) to Cart$")
    public void iAddProductProductNameToCart(String productName) {
        List<WebElement> products = productCatalog.getProductsList();
        productCatalog.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName){
        CartPage cartPage = productCatalog.goToCart();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.countrySelection(countryName);
        confirmationPage = checkoutPage.placeOrder();
    }

    @Then("{string} message is displayed on confirmation page.")
    public void messageIsDisplayedOnConfirmationPage(String string)  {
        String confirmMessage = confirmationPage.confirmationMessage();
        Assert.assertEquals(confirmMessage,string);
        driver.close();
    }


}

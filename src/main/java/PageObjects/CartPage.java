package PageObjects;

import AbstractComponent.AbstarctComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstarctComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartSection h3")
    public  List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkOut;

    By cartSection = By.cssSelector(".cartSection h3");

    public boolean VerifyProductDisplay(String productName){
        waitForElementToAppear(cartSection);
        Boolean match = cartProducts.stream().anyMatch( cartProduct->
                cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckout(){
        checkOut.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }


}

package PageObjects;

import AbstractComponent.AbstarctComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstarctComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".actions a")
    WebElement placeOrder;

    By getCountry  = By.cssSelector(".ta-results");

    public void countrySelection(String countryName){
        Actions action = new Actions(driver);
        action.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(getCountry);
        country.sendKeys(Keys.ARROW_DOWN);
        country.sendKeys(Keys.ENTER);
    }
    public ConfirmationPage placeOrder()
    {
        placeOrder.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

}

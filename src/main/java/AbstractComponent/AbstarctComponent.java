package AbstractComponent;

import PageObjects.CartPage;
import PageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstarctComponent {
    WebDriver driver;


    public AbstarctComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*=cart]")
    WebElement cartHeader;

    @FindBy(css = ".btn-custom[routerlink='/dashboard/myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }
    public void waitForElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }
    public CartPage goToCart(){
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrder(){
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;

    }


    public void waitForElementToDisappear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }
}

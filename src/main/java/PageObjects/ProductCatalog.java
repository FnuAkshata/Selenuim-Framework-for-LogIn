package PageObjects;

import AbstractComponent.AbstarctComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ProductCatalog extends AbstarctComponent {

    WebDriver driver;
    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
//    WebElement userEmail = driver.findElement(By.id("userName"));


    //  PageFactory Implementation
    @FindBy(css = ".mb-3")
    public List<WebElement> products;

    @FindBy(css = ".ng-animation")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addTOCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public List<WebElement> getProductsList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod = getProductsList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        return prod;
    }


    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addTOCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }


}

package PageObjects;

import AbstractComponent.AbstarctComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingObjects extends AbstarctComponent {

    WebDriver  driver;

    public LandingObjects(WebDriver driver){
        super(driver);

        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
//    WebElement userEmail = driver.findElement(By.id("userName"));


//  PageFactory Implementation
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement logIn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;


    public ProductCatalog loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        logIn.click();
        ProductCatalog productCatalog=new ProductCatalog(driver);
        return productCatalog;

    }
    public String getErrorMessage(){
        waitForElementToAppear(errorMessage);
        String message=errorMessage.getText();
        return message;
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }



}

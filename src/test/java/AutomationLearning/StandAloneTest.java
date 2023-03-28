package AutomationLearning;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        String productName= "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://rahulshettyacademy.com/client");


        driver.findElement(By.id("userEmail")).sendKeys("Saha@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Saha@123");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait5 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait5.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();// Selecting add to cart for ZARA coat

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animation")));
        driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch( cartProduct->
                cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
        Actions action = new Actions(driver);
        action.sendKeys(country,"United States").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));


        country.sendKeys(Keys.ARROW_DOWN);
        country.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector(".actions a")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMessage,"THANKYOU FOR THE ORDER.");

        driver.quit();



    }
}

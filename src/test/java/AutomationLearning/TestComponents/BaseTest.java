package AutomationLearning.TestComponents;

import PageObjects.LandingObjects;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingObjects landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis =new FileInputStream("C:\\akshata\\SeleniumFrameworkDesign\\src\\main\\java\\Resources\\GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
//        String browserName = prop.getProperty("browser");

        if(browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
//            if(browserName.contains("headless")){
//                options.addArguments("headless");
//            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge"))
        {
               WebDriverManager.edgedriver().setup();
               driver =new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver;
    }

    public List<HashMap<String, String>> getJsondataToMap(String filePath) throws IOException {

        // reading json to string
        String jsonData = FileUtils.readFileToString(new File(filePath),"UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data=mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public File getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
        return file;

        //    Extent reports
    }

    @BeforeMethod(alwaysRun = true)
    public LandingObjects launchApplicaiton() throws IOException {

        driver = initializeDriver();
        landingPage=new LandingObjects(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod
    public void  tearDown(){
        driver.quit();
    }
}

package AutomationLearning;

import AutomationLearning.Listeners.Retry;
import AutomationLearning.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidation extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void submitOrder() throws IOException {

        landingPage.loginApplication("Sah@gmail.com","Saha@123");
        Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage());
    }

}

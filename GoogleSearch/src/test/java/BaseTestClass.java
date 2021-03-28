import Web.Util.AppDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public abstract class BaseTestClass {
    public WebDriver driver;
    public SoftAssert softAssert;
    AppDriver testInit;

    @Attachment(value = "{0}", type = "text/plain")
    public static String Save_TestLog(String message) {
        return message;
    }

    @BeforeSuite
    void setUpTest() {
    }

    @BeforeClass
    @Parameters("browser")
    void beforeClass(@Optional("Chrome") String browser) throws Exception {
        AppDriver testInit = new AppDriver();
        driver = testInit.SeleniumDriverSetup(browser);
    }

    @BeforeMethod
    void beforeMethod() {
        softAssert = new SoftAssert();

    }

    @AfterClass
    void afterClass() {
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    void tearDownTest() throws IOException {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] Save_screenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("check that value : {0} equal to : {1} ")
    boolean DoAssert_equal(Object Actual, Object Expected, String message) {
        softAssert.assertEquals(Actual, Expected, message);
        if (String.valueOf(Expected) != String.valueOf(Actual)) {
            Save_screenshotPNG();
            Save_TestLog(message);
            return false;
        }

        return true;
    }
}

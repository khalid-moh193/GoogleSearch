package Web.Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AppDriver {
    public static boolean driverInit = true;

    WebDriver driver;

    public WebDriver SeleniumDriverSetup(String browser) throws Exception {
        //options.addArguments("user-data-dir=%LOCALAPPDATA%\\Google\\Chrome\\User Data");
//         Check if parameter passed fromאtestng.xml is 'firefox'
        if (browser.equalsIgnoreCase("firefox")) {
//            // Create firefox instance
            FirefoxOptions options = new FirefoxOptions();
//            // Download from https://github.com/mozilla/geckodriver/releases
            String exePath =   System.getProperty("user.dir") + File.separator +"src/main/java/Web/Util/"+ File.separator +"geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", exePath);
            driver = new FirefoxDriver(options);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver;

        }
        // Check if parameter passed from testng.xml is 'chrome'
        else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--profile-directory=Default");
            // Setting the webdriver.chrome.driver patch or simply add it to your system variables
            // Download from https://www.seleniumhq.org/download/
            String exePath;
//            if (Property_Reader.getOS().equalsIgnoreCase("os"))
//                exePath = System.getProperty("user.dir") + File.separator + "src/main/java/Web/Util/" + File.separator + "chromedriver";
//            else
            //   To be able to use it please download the selenium chrome driver and place it in util web package
            exePath = System.getProperty("user.dir") + File.separator + "src/main/java/Web/Util/" + File.separator + "chromedriver";
            System.setProperty("webdriver.chrome.driver", exePath);
            // Create chrome instance
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();


            return driver;
        } else {
            // If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
    }

    public void driverQuit() {
        driver.quit();
    }
}

package Web.Pages;

import Web.Util.AppDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


public class Page {
    static boolean URLFirstTime = AppDriver.driverInit;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String basicURL = "https://www.google.com/";

    public Page(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
        if (URLFirstTime) {
            driver.get(basicURL);
            URLFirstTime = false;
        }
    }

    protected void openURL(String url) {
        driver.navigate().to(url);
    }

    protected void visibilityWait(By element) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    @Step("-Go back")
    public Page goBack() {
        driver.navigate().back();
        return this;
    }

    @Step("-Scroll down")
    public void ScrollDownByPixel() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)", "");
    }

    @Step("-Scroll up")
    public void ScrollUpByPixel() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-350)", "");
    }

    protected void sendText(By element, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
    }

    @Step("-get Page Title")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean checkElementIsDisplayed(By element) {
        try {
            visibilityWait(element);
            driver.findElement(element).isDisplayed();
        } catch (Exception e) {
            System.out.println("Couldn't find element" + element);
            return false;
        }
        return true;
    }

}



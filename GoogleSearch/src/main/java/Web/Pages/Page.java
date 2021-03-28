package Web.Pages;

import Web.Util.AppDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


public class Page {
    static boolean URLFirstTime = AppDriver.driverInit;
    static Set<Cookie> seleniumCookies;
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

    protected void visibilityWait(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void visibilityWait(By element) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void alertWait() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    protected void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void sendText(WebElement element, String text) {
        visibilityWait(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getAlertMessage() {
        alertWait();
        return driver.switchTo().alert().getText();
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

    @Step("-Scroll by element")
    public void ScrollByVisibleElement(By Element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    @Step("-Scroll by element")
    public void ScrollByPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected void clickElementByText(String text) {
        clickElement(By.xpath("//*[@text= '" + text + "']"));

    }

    protected void clickOnElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

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

    protected boolean checkElementIsSelected(By element) {
        visibilityWait(element);
        return driver.findElement(element).isSelected();
    }

    public boolean checkElementIsEnabled(By element) {
        visibilityWait(element);
        return driver.findElement(element).isEnabled();
    }

    protected String getElementText(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element)).getText();
    }

    protected String getElementAttribute(By element, String attribute) {
        return driver.findElement(element).getAttribute(attribute);
    }

    protected boolean checkElementIsNotDisplayed(By element) {
        try {
            driver.findElement(element).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException NoSuchElementException) {
            NoSuchElementException.getSuppressed();
            System.out.println("Element [" + element + "]" + " is not displayed");
        }
        return false;
    }

    protected String checkElementIsChecked(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element)).getAttribute("checked");
    }


    protected boolean checkElementIsDisplayedByText(String text) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@text= '" + text + "']")))).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException NoSuchElementException) {
            NoSuchElementException.getSuppressed();
            System.out.println("Element [" + text + "]" + " is not displayed");
        }
        return false;
    }

    public boolean getElementTextByTextContains(String text){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'"+ text +"')]")))).getText();
        return true;
    }

}



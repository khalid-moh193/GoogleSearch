package Web.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultScreen extends Page {
    public By searchField = By.name("q");
    public By firstSearchResult = By.className("yuRUbf");
    public By instaBugLink = By.xpath("//a[@href='https://instabug.com/']");
    public By instaBugLink2 = By.xpath("//div[normalize-space()='instabug.com']");
    public By resultStats = By.id("result-stats");


    public SearchResultScreen(WebDriver driver) {
        super(driver);
    }

    @Step("-Write {0} in search field")
    public SearchResultScreen sendTextToSearchField(String Text) {
        sendText(searchField, Text);
        return this;
    }

    @Step("-click on the first link")
    public void clickOnFirstLink() {
        wait.until(ExpectedConditions.elementToBeClickable(firstSearchResult)).click();
    }

    @Step("-get text inside the Search field")
    public String getSearchText() {
        return driver.findElement(searchField).getAttribute("value");
    }

    @Step("-get text inside the Search field")
    public SearchResultScreen waitUntilSearchResultScreenIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        return this;
    }

}

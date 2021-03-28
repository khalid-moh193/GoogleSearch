package Web.Pages;

import Web.Util.URLs;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeScreen extends Page {
    public By searchField = By.name("q");
    public By searchButton = By.className("gNO89b");
    public By suggestionsList = By.className("sbl1");
    public By clearSearchButton = By.className("FqnKTc");
    public By englishLanguage = By.linkText("English");
    public By arabicLanguage = By.linkText("العربية");


    public HomeScreen(WebDriver driver) {
        super(driver);
    }

    @Step("-Navigate to Google")
    public HomeScreen navigateToGoogle() {
        openURL(URLs.Google.getValue());
        return this;
    }

    @Step("-Write {0} in search field")
    public HomeScreen sendTextToSearchField(String Text) {
        sendText(searchField, Text);
        return this;
    }

    @Step("-get text inside the Search field")
    public String getSearchText() {
        return wait.until(ExpectedConditions.elementToBeClickable(searchField)).getAttribute("value");
    }

    @Step("-Click on search button")
    public SearchResultScreen clickOnSearchButton() {
        clickElement(searchButton);
        return new SearchResultScreen(driver);
    }

    @Step("-Click on keyboard's enter button")
    public HomeScreen clickOnEnterButton() {
        driver.findElement(searchButton).sendKeys(Keys.ENTER);
        return this;
    }

    @Step("-Clear Text inside Search field")
    public HomeScreen clearSearch() {
        clickElement(clearSearchButton);
        return this;
    }

    @Step("-Get the size of suggestions lists")
    public int getSuggestionListSize() {
        return driver.findElements(suggestionsList).size();
    }

    @Step("-Get the search suggestion's text, number {0}")
    public String getSuggestionText(int index) {
        return driver.findElements(suggestionsList).get(index).getText().toLowerCase();
    }

    @Step("-change language to English")
    public HomeScreen chooseEnglishLanguage() { //ToDo: need te enhance this method as it slows down the execution
        if (checkElementIsDisplayed(englishLanguage))
            clickElement(englishLanguage);

        else {
            System.out.println("English language is already selected");
        }
        return this;
    }

    @Step("-change language to Arabic")
    public HomeScreen chooseArabicLanguage() {
        try {
            clickElement(arabicLanguage);
        } catch (org.openqa.selenium.NoSuchElementException NoSuchElementException) {
            System.out.println("Arabic language is already selected");
        }
        return this;
    }

    @Step("-get text inside the Search field")
    public HomeScreen waitUntilHomeScreenIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        return this;
    }

}

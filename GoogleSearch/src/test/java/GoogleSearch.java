import Web.Pages.HomeScreen;
import Web.Pages.SearchResultScreen;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Search Functionality")
@Feature("Search Functionality")
public class GoogleSearch extends BaseTestClass {
    HomeScreen homeScreen;
    SearchResultScreen searchResultScreen;
    String searchKeyword = "google";

    //region Test Cases
    /*

    1- Search results displayed should be relevant to search keyword ✓
    2- When user start typing word in text box it should suggest words that matches typed keyword ✓
    3- Search results should be cleared on clicking clear search button ✓
    4-Total number of search records/results should be displayed on page ✓
    5-User should be able to search when he enters the keyword and hits ‘Enter’ button on keyboard ✓
    6- % sign in search keyword should not redirect to 404 ERROR ✓
    7- Application should not crash if user inserted % in search field ✓
    8- When user clicks on any link from result and navigates back, then result should be maintained ✓

     */
    //endregion

    @Test(priority = 1, description = "search by any keyword, then check that it's existed in the search result ")
    public void verifySearchFunctionality() {
        homeScreen = new HomeScreen(driver);
        homeScreen.chooseEnglishLanguage()
                .sendTextToSearchField(searchKeyword)
                .clickOnSearchButton();

        searchResultScreen = new SearchResultScreen(driver);
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.firstSearchResult), true, "search result is not displayed");
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.googleLink), true, "google link is not displayed");
        DoAssert_equal(searchResultScreen.getSearchText(), searchKeyword, "search text is not existed");
        DoAssert_equal(searchResultScreen.getCurrentUrl().contains(searchKeyword), true, "URL doesn't contain the search keyword");
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.resultStats), true, "total records/results is not displayed"); // this verifies another test case, but i found that it can be included here


        softAssert.assertAll();
    }

    @Test(priority = 2, description = "search by any keyword, then check that suggestions contains the search keyword ")
    public void verifySuggestionsFunctionality() throws InterruptedException {
        homeScreen = new HomeScreen(driver);
        homeScreen.navigateToGoogle()
                .chooseEnglishLanguage()
                .sendTextToSearchField(searchKeyword);
        Thread.sleep(700);

        int suggestionsSize = homeScreen.getSuggestionListSize();
        System.out.println(suggestionsSize-1);

        for (int i = 1; i < suggestionsSize-1; i++) {
            System.out.println(homeScreen.getSuggestionText(i));
            DoAssert_equal(homeScreen.getSuggestionText(i).contains(searchKeyword), true, i + " keyword is not existed in the suggestions");
        }
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Write any keyword, then clear it and check that it was cleared successfully ")
    public void verifyClearSearchFunctionality() {
        homeScreen = new HomeScreen(driver);
        homeScreen.navigateToGoogle()
                .chooseEnglishLanguage()
                .sendTextToSearchField(searchKeyword)
                .clearSearch();

        DoAssert_equal(homeScreen.getSearchText(), "", "search field is not empty");

        softAssert.assertAll();
    }

    @Test(priority = 4, description = "search by any keyword, then click on keyboard's enter button, and check that user will get results ")
    public void verifyEnterButtonWillPerformTheSearch() {
        homeScreen = new HomeScreen(driver);
        homeScreen.chooseEnglishLanguage()
                .sendTextToSearchField(searchKeyword)
                .clickOnEnterButton();

        searchResultScreen = new SearchResultScreen(driver);
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.firstSearchResult), true, "search result is not displayed");
        DoAssert_equal(searchResultScreen.getCurrentUrl().contains(searchKeyword), true, "URL doesn't contain the search keyword");

        softAssert.assertAll();
    }

    @Test(priority = 5, description = "search by %, and check that results will return successfully ")
    public void verifySearchWithPercentageSymbol() {
        homeScreen = new HomeScreen(driver);
        homeScreen.navigateToGoogle()
                .chooseEnglishLanguage()
                .sendTextToSearchField(searchKeyword.concat("%"))
                .clickOnSearchButton();

        searchResultScreen = new SearchResultScreen(driver);
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.firstSearchResult), true, "search result is not displayed");
        DoAssert_equal(searchResultScreen.getCurrentUrl().contains(searchKeyword), true, "URL doesn't contain the search keyword");

        softAssert.assertAll();
    }

    @Test(priority = 6, description = "click on any search result, then navigate back, and check that search keyword and results are maintained ")
    public void verifyNavigationImpactOnSearch() {
        homeScreen = new HomeScreen(driver);
        homeScreen.navigateToGoogle()
                .chooseEnglishLanguage()
                .sendTextToSearchField(searchKeyword)
                .clickOnSearchButton()
                .clickOnFirstLink();

        searchResultScreen = new SearchResultScreen(driver);
        searchResultScreen.goBack();
        searchResultScreen.waitUntilSearchResultScreenIsLoaded();
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.firstSearchResult), true, "search result is not displayed");
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.googleLink), true, "google ling is not displayed");
        DoAssert_equal(searchResultScreen.getSearchText(), searchKeyword, "search text is not existed");
        DoAssert_equal(searchResultScreen.getCurrentUrl().contains(searchKeyword), true, "URL doesn't contain the search keyword");
        DoAssert_equal(searchResultScreen.checkElementIsDisplayed(searchResultScreen.resultStats), true, "total records/results is not displayed"); // this verifies another test case, but i found that it can be included here

        softAssert.assertAll();
    }

}

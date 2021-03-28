# GoogleSearch

Maven project using testNG and selenium Webdriver with POM design pattern and Allure reporting

for generating the report please run: allure-serve

Test Cases:

    1- Search results displayed should be relevant to search keyword
    2- When user start typing word in text box it should suggest words that matches typed keyword
    3- Search results should be cleared on clicking clear search button
    4- Total number of search records/results should be displayed on page
    5- User should be able to search when he enters the keyword and hits ‘Enter’ button on keyboard
    6- % sign in search keyword should not redirect to 404 ERROR
    7- Application should not crash if user inserted % in search field
    8- When user clicks on any link from result and navigates back, then result should be maintained
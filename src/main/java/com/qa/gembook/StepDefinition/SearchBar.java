package com.qa.gembook.StepDefinition;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.SearchBar_Locators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchBar extends DriverAction {

    @Given("Verify if search bar exists")
    // <!-- Function to check if search bar exists -->
    public void verifySearch() {
        if (isExist(By.id("searchInput"))) {
            click(By.id("searchInput"),"Search Input");
            GemTestReporter.addTestStep("Verify if search bar exists",
                    "Search bar exists as required", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify if search bar exists",
                    "Search bar doesn't exist", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Enter text in search bar {string}")
    // <!-- Function to enter text in search bar -->
    public void enterText(String search_text) {
        if (!isExist(SearchBar_Locators.searchbar)) {
            GemTestReporter.addTestStep("Verify if search bar exists","Search bar doesn't exist",STATUS.FAIL,takeSnapShot());
        }else{
            click(SearchBar_Locators.searchbar,"Search Bar");
            waitSec(2);
            typeText(SearchBar_Locators.searchbar, "Enter text to be searched in Search Bar", "Entered text to be searched in Search Bar: " + search_text, search_text);
            waitSec(3);
        }
    }

    @Then("Click on post filters")
    // <!-- Function to choose Post button in search filters -->
    public void clickPost() {
        try{
            if (isExist(SearchBar_Locators.searchbar) && isExist(SearchBar_Locators.searchBar_PostFilterBtn)) {
                click(SearchBar_Locators.searchbar,"Search Bar");
                waitSec(2);
                click(SearchBar_Locators.searchBar_PostFilterBtn, "Click on Post filters", "Clicked on Post filters");
            } }
        catch (Exception exception){
            GemTestReporter.addTestStep("Click on Post Filter","No post filter exists",STATUS.FAIL,takeSnapShot());
            throw exception;
        }
    }

    /*
     <!-- Function to search posts based on entered text and fetch the count
     and entire information of all related posts -->
    */
    @Then("Verify if related posts exists based on search text {string}")
    public void verifyRelatedPosts(String search_text) {
        waitSec(3);
        if (isExist(SearchBar_Locators.noRecordsFoundErr)) {
            GemTestReporter.addTestStep("Verify if any post is found",
                    "No posts are found for search text of: " + search_text, STATUS.PASS, takeSnapShot());
        }else{
            List<WebElement> relatedPosts = DriverAction.getElements(By.xpath("//div[@class='postContainer_postBody__90jsv']"));
            GemTestReporter.addTestStep("Verify if any post is found",
                    "Number of posts found with " + search_text + " are: " + relatedPosts.size(), STATUS.PASS, takeSnapShot());
            for (WebElement i : relatedPosts) {
                String data = getElementText(i);
                if (data.contains(search_text)) {
                    GemTestReporter.addTestStep("Verify the data found in related post",
                            "Entire data of the related post is - " + data, STATUS.PASS, takeSnapShot());
                }
            }
        }
    }

    @Then("Click on people filters")
    // <!-- Function to choose People button in search filters -->
    public void clickPeople() {
        try{
            if (isExist(SearchBar_Locators.searchbar) && isExist(SearchBar_Locators.searchBar_PeopleFilterBtn)) {
                click(SearchBar_Locators.searchbar,"Search Bar");
                click(SearchBar_Locators.searchBar_PeopleFilterBtn, "Click on People filters", "Clicked on People filters");
                waitSec(3);
            }}
        catch (Exception exception){
            GemTestReporter.addTestStep("Click on People Filter","No People filter exists",STATUS.FAIL,takeSnapShot());
            throw exception;
        }
    }

    /*
     <!-- Function to search people based on entered text and fetch the count
     and entire information of all related posts -->
    */
    @Then("Verify if related people exists based on search text {string}")
    public void verifyRelatedPeople(String search_text) {
          waitSec(3);
        if (isExist(SearchBar_Locators.noRecordsFoundErr)) {
            GemTestReporter.addTestStep("Verify if any person data exists",
                    "No person data exists with search text as: " + search_text, STATUS.PASS, takeSnapShot());
        }else{
            List<WebElement> relatedPeople = DriverAction.getElements(By.xpath("//div[@class='searched-people_media__3TNEy']"));
            GemTestReporter.addTestStep("Verify if any post is found",
                    "Number of posts found with " + search_text + " are: " + relatedPeople.size(), STATUS.PASS, takeSnapShot());
            for (WebElement i : relatedPeople) {
                String data = getElementText(i);
                if (data.contains(search_text)) {
                    GemTestReporter.addTestStep("Verify the data found in related post",
                            "Entire data of the related post is - " + data, STATUS.PASS, takeSnapShot());
                }
            }
        }
    }

    /*
<!-- Function to check if search dropdown contains filters tab in
which there exists Posts button and People button  -->
*/
    @Then("Verify if search dropdown is displayed with filters")
    public void searchDropdownDisplayed() {
        if (isExist(SearchBar_Locators.searchBar_FiltersTitle) && isExist(SearchBar_Locators.searchBar_PeopleFilterBtn)) {
            GemTestReporter.addTestStep("Verify if People button exists in filters dropdown of search",
                    "People button exists in filters dropdown of search", STATUS.PASS, takeSnapShot());
        }
        else {        GemTestReporter.addTestStep("Verify if People button exists in filters dropdown of search",
                "People button doesn't exist in filters dropdown of search", STATUS.FAIL, takeSnapShot());
        }
        if (isExist(SearchBar_Locators.searchBar_FiltersTitle) && isExist(SearchBar_Locators.searchBar_PostFilterBtn)) {
            GemTestReporter.addTestStep("Verify if Posts button exists in filters dropdown of search",
                    "Posts button exists in filters dropdown of search", STATUS.PASS, takeSnapShot());
        }
        else {        GemTestReporter.addTestStep("Verify if Posts button exists in filters dropdown of search",
                "Posts button doesn't exist in filters dropdown of search", STATUS.FAIL, takeSnapShot());
        }

    }

    @Then("Clear text entered")
    // <!-- Function to clear entered text -->
    public void clearText() {
        clearText(SearchBar_Locators.searchbar);
        waitSec(5);
        String enteredText = getElementText(SearchBar_Locators.searchbar);
        if(enteredText.length() == 0){
            GemTestReporter.addTestStep("Verify if entered data is cleared","Entered data is cleared",STATUS.PASS,takeSnapShot());
        }else {
            GemTestReporter.addTestStep("Verify if entered data is cleared","Entered data is not cleared",STATUS.FAIL,takeSnapShot());
        }
    }
}

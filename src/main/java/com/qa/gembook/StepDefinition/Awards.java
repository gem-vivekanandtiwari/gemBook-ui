package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;

import com.qa.gembook.GembookUtilities.OtherPortalsUtils;
import com.qa.gembook.Locators.Awards_Locators;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class Awards extends DriverAction {

    @Given("Check if Award section is present in News Feeds")
    public static void PresenceOfAwardSection() {
        waitSec(3);

        OtherPortalsUtils.isElementDisplayed("Verify if Awards grid is present in News Feeds", "Awards grid is present in News Feeds", "Awards grid is not present in News Feeds", Awards_Locators.awardSection);
        OtherPortalsUtils.isElementDisplayed("Verify if Awards Header icon is present", "Awards section header icon is present", "Awards section Header icon is not present", Awards_Locators.awardSectionIcon);
        OtherPortalsUtils.isElementDisplayed("Verify the presence of Year dropdown ", "Year dropdown is present", "Year dropdown is not present", Awards_Locators.awardSelectOption("year"));
        OtherPortalsUtils.isElementDisplayed("Verify the presence of Events dropdown", "Events dropdown is present", "Events dropdown is not present", Awards_Locators.awardSelectOption("award"));
    }

    @When("^User selects \"([^\"]*)\" and \"([^\"]*)\" from award section$")
    public void userSelectsFromAwardSection(String awardYear, String awardEvent) {
        dropDown(Awards_Locators.awardSelectOption("year"), awardYear);
        waitSec(2);
        dropDown(Awards_Locators.awardSectionEvent, awardEvent);
    }

    //--------function to verify list of winners is present for a particular event ---------
    @Then("^Verify if data is present in Awards Section for \"([^\"]*)\"$")
    public void verifyIfDataIsPresent(String awardEvent) {
        waitSec(4);
        List<String> awardsSectionWinners = getElementsText(Awards_Locators.awardFilteredDataList);
        if (awardsSectionWinners.size() > 0) {
            GemTestReporter.addTestStep("Verify if list of winners is visible in Awards Section", "List of winners visible for "+ awardEvent +" in Awards Section are " + awardsSectionWinners, STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify if list of winners is visible in Awards Section", "List of winners is not visible in Awards Section", STATUS.FAIL, takeSnapShot());
    }

    //-------function to scroll through the award section winners list----------
    @Then("^Scroll through the list of winners in Awards section for \"([^\"]*)\" and \"([^\"]*)\"$")
    public void scrollThroughTheListOfWinners(String awardYear, String awardEvent) {
        if (OtherPortalsUtils.isElementVisible(Awards_Locators.awardSectionContent)) {
            hoverOver(Awards_Locators.awardSectionContent);//to switch view to specific section
        }
        OtherPortalsUtils.scrollThroughSection(Awards_Locators.awardFilteredDataList, awardYear, awardEvent);
    }
}

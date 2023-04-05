package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.GembookUtilities.ClubsUtils;
import com.qa.gembook.Locators.Clubs_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static com.gemini.generic.ui.utils.DriverAction.*;


public class Clubs {

    //checking the existence of the Clubs button on side nav bar
    @When("the Clubs button exists")
    public void clubsExists() {
        if (isExist(Clubs_Locators.clubsBtn)) {
            GemTestReporter.addTestStep("Verify the Clubs button exists", "The Clubs btn exists", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the Clubs button exists", "The Clubs btn doesn't exists", STATUS.FAIL, takeSnapShot());
        }
    }

    //verifying the clicking functionality of the Clubs button
    @Then("Verify whether the Clubs btn is clickable or not")
    public void clubsBtnClickable() {
        ClubsUtils.verifyClubsBtnClickable();
    }

    //Validating the name mentioned on the Clubs button
    @Then("Verify the Clubs button name shows as {string}")
    public void clubsBtnName(String text) {
        String ClubsText = getElementText(Clubs_Locators.clubsBtn);
        try {
            if (ClubsText.equalsIgnoreCase(text)) {
                GemTestReporter.addTestStep("Verify the Clubs button exists", "The Clubs btn exists", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the Clubs button exists", "The Clubs btn doesn't exists", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify the Clubs button exists", "The clubs buttons doesn't exist", STATUS.FAIL, takeSnapShot());

        }
    }

    //validating the icon on the Clubs button
    @Then("Verify icon exists on the button")
    public void clubsIcon() {
        WebElement ClubIcon = getElement(Clubs_Locators.clubsIcon);
        String txt = ClubIcon.getAttribute("alt");
        try {
            if (txt.equals("club icon")) {
                GemTestReporter.addTestStep("Verify icon exists on the button", "The icon exists on the button", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify icon exists on the button", "The Clubs icon doesn't exists", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify icon exists on the button", "The club icon doesn't exist", STATUS.FAIL, takeSnapShot());

        }
    }

    //Clicking on the Clubs button
    @Given("^User clicks on the Clubs button$")
    public void clickOnClubs() {
        ClubsUtils.clickOnClubsBtn();
    }

    //Validating the presence of My Clubs and All Clubs button after clicking on Clubs button
    @Then("^Verify My Clubs and All Clubs buttons are displayed$")
    public void validateBothClubsBtn() {
        ClubsUtils.myClubsAndAllClubsBtn();
    }

    //Clicking on All Clubs button
    @When("^User Clicks on All Clubs Button$")
    public void clickOnAllClubs() {
        waitSec(5);
        try {
            if (isExist(Clubs_Locators.allClubs)) {
                waitSec(3);
                click(Clubs_Locators.allClubs);
            } else {
                GemTestReporter.addTestStep("User Clicks on All Clubs Button", "not clicked", STATUS.FAIL, takeSnapShot());

            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("User Clicks on All Clubs Button", "Unable to click on all clubs button", STATUS.FAIL, takeSnapShot());

        }

        if (isExist(Clubs_Locators.clubsCard)) {
            GemTestReporter.addTestStep("User Clicks on All Clubs Button", "Clicked on All Clubs Button successfully", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("User Clicks on All Clubs Button", "not clicked", STATUS.FAIL, takeSnapShot());
        }

        waitSec(20);
    }

    //Validating that All Clubs button is highlighted in green color after clicking on it
    @Then("Verify All Clubs Button is highlighted in {string} or not")
    public void allClubsIsHighlighted(String color) {
        ClubsUtils.colorOfHighlightedClubsBtn(color);
    }

    //Validating that after clicking on Clubs button My Clubs screen should be visible by default
    @Then("Verify My Clubs screen is displayed by default")
    public void homeScreen() {
        waitSec(10);
        String highlightedBtnText = getElementText(Clubs_Locators.selectedButton);
        String MyClubsBtnText = getElementText(Clubs_Locators.myClubs);

        if (highlightedBtnText.equalsIgnoreCase(MyClubsBtnText)) {
            GemTestReporter.addTestStep("Verify My Clubs screen is displayed by default", "My Clubs screen is displayed by default", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify My Clubs screen is displayed by default", "My Clubs screen is not displayed by default", STATUS.FAIL, takeSnapShot());
        }
    }

    //Validating that My Clubs button is highlighted in green color after clicking on it
    @Then("Verify My Clubs Button is highlighted in {string} or not")
    public void myClubsIsHighlighted(String color) {
        ClubsUtils.colorOfHighlightedClubsBtn(color);
    }

    //    Verifying all the clubs are displayed after clicking on  All clubs button
//    i.e. {Cultural Club,Entrepreneurship Club,CSR Club,Gemini-On-wheels,Sports Club,Technology Club}
    @Then("Verify All Clubs page is loaded with all the Clubs")
    public void allClubsLoaded() {
        String[] clubsName = {"Cultural Club", "Entrepreneurship Club", "CSR Club", "Gemini-On-wheels", "Sports Club", "Technology Club"};

        for (String s : clubsName) {
            if (isExist(Clubs_Locators.getClubsCard(s))) {
                GemTestReporter.addTestStep("Verify All Clubs page is loaded with all the Clubs", "All Clubs page is loaded with " + s, STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify All Clubs page is loaded with all the Clubs", "All Clubs page is not loaded with all the Clubs", STATUS.FAIL, takeSnapShot());
            }
        }

    }

    //verifying the logo is present on all the club's card
    @Then("Verify the {string} card is displayed with the logo")
    public void logoOfClubCard(String clubsLogo) {
        ClubsUtils.logoOfClubCard(clubsLogo);
    }

    //validating the name mentioned on each of the club
    @Then("Verify the {string} card has the {string}")
    public void nameOfClub(String name, String expectedName) {

        if (isExist(Clubs_Locators.getNameOfClub(name)) && getElementText(Clubs_Locators.getNameOfClub(name)).equalsIgnoreCase(expectedName)) {
            GemTestReporter.addTestStep("Verify the " + name + " card has correct name displayed", "The " + name + " card has the correct name of the club- " + expectedName, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the Clubs card has the correct name of the club", "The Clubs card does not have the correct name of the club", STATUS.FAIL, takeSnapShot());
        }

    }

    //Verifying that the Club's card is clickable or not
    @Then("Verify the {string} card is clickable")
    public void verifyTheClubCardIsClickable(String name) {
        ClubsUtils.clubsCardClickable(name);
    }

    //Validating the presence of Join Club or Leave Club button on the club's card
    @Then("Verify the {string} card has the Join Club or Leave Club button")
    public void validateJoinClubLeaveClubButton(String name) {
        String textOfBtn = getElementText(Clubs_Locators.getJoinOrLeaveBtn(name));

        if (isExist(Clubs_Locators.getJoinOrLeaveBtn(name))) {
            GemTestReporter.addTestStep("Verify the " + name + " card has the Join Club or Leave Club button", "The " + name + " card has " + textOfBtn, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the " + name + " card has the Join Club or Leave Club button", "The " + name + " card doesn't have Leave/Join button", STATUS.FAIL, takeSnapShot());
        }
    }

    //validating the presence of count of members enrolled in the club on the club's card
    @Then("Verify the {string} card has the count of members enrolled")
    public void countOfMembersEnrolled(String name) {

        if (isExist(Clubs_Locators.getCountOfMembers(name))) {
            GemTestReporter.addTestStep("Verify the " + name + " card has the count of members enrolled", "The count of members enrolled is " + getElementText(Clubs_Locators.getCountOfMembers(name)), STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the " + name + " card has the count of members enrolled", "The " + name + " card doesn't have the count of members enrolled", STATUS.FAIL, takeSnapShot());
        }
    }

    //validating the presence of date of creation of club on the club's card
    @Then("Verify the {string} card has the date of creation")
    public void validateDateOfCreation(String name) {
        if (isExist(Clubs_Locators.getDateOfCreation(name))) {
            GemTestReporter.addTestStep("Verify the " + name + " card has the date of creation", "The date of creation is " + getElementText(Clubs_Locators.getDateOfCreation(name)), STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the " + name + " card has the date of creation", "The " + name + " card doesn't have the date of creation", STATUS.FAIL, takeSnapShot());
        }
    }

    //Validating the Join Club button on the club's card is highlighted in green
    @Then("Verify the Join Club button of {string} card is highlighted in {string}")
    public void joinClubButtonColor(String name, String highlightedcolor) {
        ClubsUtils.highlightedColor(name, highlightedcolor);
    }

    //clicking on the Join Club button
    @And("User clicks on Join Club button of {string} card")
    public void joinClubButtonOfCard(String name) {
        ClubsUtils.joinOrLeaveClubBtnOnCard(name);
    }

    //validating that the notification arises after clicking on the Join Club
    // button through the club's card
    @Then("Verify the notification arises after clicking on the Join Club button")
    public void notificationAfterJoiningClub() {
        ClubsUtils.notificationVerification();
    }

    //Validating that the Join Club button changes to Leave club button after clicking on it
    @Then("Verify after clicking on Join Club button on {string} card it changes to Leave group button")
    public void leaveGroupButton(String name) {
        ClubsUtils.joinClubBtnChangesToLeaveBUtton(name, "Join", "#ec974b", "Leave Club");
    }

    //Validating the Leave Club button on the club's card is highlighted in orange
    @Then("Verify the Leave Club button of {string} card is highlighted in {string}")
    public void verifyTheLeaveClubButtonOfCardIsHighlightedIn(String name, String highlightedcolor) {
        ClubsUtils.highlightedColor(name, highlightedcolor);
    }

    //clicking on the Join Club button
    @And("User clicks on Leave Club button of {string} card")
    public void leaveClubButtonOfCard(String name) {
        ClubsUtils.joinOrLeaveClubBtnOnCard(name);
    }


    //validating that the notification arises after clicking on the Leave Club
    // button through the club's card
    @Then("Verify the notification arises after clicking on the Leave Club button")
    public void notificationOfTheLeaveClubButton() {
        ClubsUtils.notificationVerification();
    }

    //Validating that the Join Club button changes to Leave club button after clicking on it
    @Then("Verify after clicking on Leave Club button on {string} card it changes to Join group button")
    public void joinGroupButton(String name) {
        ClubsUtils.joinClubBtnChangesToLeaveBUtton(name, "Leave", "#38a3a5", "Join Club");
    }

    //when the user clicks on the club's card
    @When("User clicks on the {string} card")
    public void userClicksOnTheCard(String name) {
        ClubsUtils.clubsCardClickable(name);
        if (DriverAction.isExist(Clubs_Locators.getClubsCard(name))) {
            DriverAction.click(Clubs_Locators.getClubsCard(name));
            GemTestReporter.addTestStep("Verify user clicks on the " + name + " card", "Successfully clicked on " + name + " card", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify user clicks on the " + name + " card", "not clicked on " + name + " card", STATUS.FAIL, takeSnapShot());
        }
        waitSec(3);
    }


    //validating the navigation path from All Clubs to the detailed screen of the particular club
    @Then("Verify the user is navigated to All Clubs > {string}")
    public void verifyTheUserIsNavigatedToAllClubs(String nameOfClub) {
        ClubsUtils.navigationPath(nameOfClub, "All clubs");
    }

    //validating the navigation path from My Clubs to the detailed screen of the particular club
    @Then("Verify the user is navigated to My Clubs > {string}")
    public void verifyTheUserIsNavigatedToClubs(String nameOfClub) {
        ClubsUtils.navigationPath(nameOfClub, "My clubs");
    }


}

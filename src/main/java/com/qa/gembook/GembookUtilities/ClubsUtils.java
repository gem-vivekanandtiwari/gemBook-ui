package com.qa.gembook.GembookUtilities;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.Clubs_Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;

import static com.gemini.generic.ui.utils.DriverAction.*;

public class ClubsUtils {

    public static void clickOnClubsBtn() {
        try {
            waitUntilElementAppear(Clubs_Locators.clubsBtn, 10);
            click(Clubs_Locators.clubsBtn, "User clicks on the Clubs button", "Clicked on Clubs Button");
        } catch (Exception exception) {
            GemTestReporter.addTestStep("User clicks on the Clubs button", "Unable to click on Clubs Button", STATUS.FAIL, takeSnapShot());
        }
    }

    public static void myClubsAndAllClubsBtn() {
        try {
            if (isExist(Clubs_Locators.myClubs) && isExist(Clubs_Locators.allClubs)) {
                GemTestReporter.addTestStep("Verify My Clubs and All Clubs buttons are displayed", "All Clubs and My Clubs button are displayed", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify My Clubs and All Clubs buttons are displayed", "All Clubs and My Clubs button are not displayed", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify My Clubs and All Clubs buttons are displayed", "All Clubs and My Clubs button are not displayed", STATUS.FAIL, takeSnapShot());

        }
    }


    public static void colorOfHighlightedClubsBtn(String highlightedColor) {
        waitSec(5);
        String backgroundColor = Color.fromString(getCSSValue(Clubs_Locators.selectedButton, "background-color")).asHex();
        try {
            if (highlightedColor.equalsIgnoreCase(backgroundColor)) {
                GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.selectedButton) + " Button is highlighted in " + highlightedColor + " or not", "All Clubs Button is highlighted in " + backgroundColor, STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.selectedButton) + " Button is highlighted in " + highlightedColor + " or not", "All Clubs Button is not highlighted in " + backgroundColor, STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.selectedButton) + " Button is highlighted in " + highlightedColor + " or not", "All club buttons are not displayed", STATUS.PASS, takeSnapShot());

        }

    }


    public static void verifyClubsBtnClickable() {
        boolean validateClubCardClickable = getElement(Clubs_Locators.clubsBtn).isEnabled() && getElement(Clubs_Locators.clubsBtn).isDisplayed();
        try {
            if (validateClubCardClickable) {
                GemTestReporter.addTestStep("Verify the Clubs button is clickable", "The Clubs button is clickable", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the Clubs button is clickable", "The Clubs button is not clickable", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify the Clubs button is clickable", "The clubs button is not visible", STATUS.FAIL, takeSnapShot());

        }
    }

    public static void logoOfClubCard(String clubslogo) {
        By clubCardLogo = By.xpath("");

        if (clubslogo.equalsIgnoreCase("Cultural Club")) {
            clubCardLogo = By.xpath("(//*[@class='clubs_card__c37Tq card']//img[@alt='profile'])[1]");
        } else {
            for (int i = 2; i <= 6; i++) {
                clubCardLogo = By.xpath("(//*[@class='clubs_card__c37Tq card']//img[@alt='profile'])[" + i + "]");
            }
        }
        try {
            if (isExist(clubCardLogo)) {
                GemTestReporter.addTestStep("Verify the " + clubslogo + " card is displayed with the logo", "The Clubs card is displayed with the logo", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the " + clubslogo + " card is displayed with the logo", "The Clubs card is not displayed with the logo", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify the " + clubslogo + " card is displayed with the logo", "The Clubs card is not displayed with the logo", STATUS.FAIL, takeSnapShot());
        }
    }


    public static void clubsCardClickable(String name) {
        boolean validateClubCardClickable = getElement(Clubs_Locators.getClubsCard(name)).isEnabled() && getElement(Clubs_Locators.getClubsCard(name)).isDisplayed();
        try {
            if (validateClubCardClickable) {
                GemTestReporter.addTestStep("Verify the " + name + " card is clickable", "The " + name + " card is clickable", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify the " + name + " card is clickable", "The " + name + " card is not clickable", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify the " + name + " card is clickable", "The " + name + " card is not displayed", STATUS.FAIL, takeSnapShot());

        }
    }

    public static void highlightedColor(String name, String color) {
        waitSec(5);
        String highlightedColor = Color.fromString(getCSSValue(Clubs_Locators.getJoinClubBtnOnCard(name), "color")).asHex();

        if (color.equalsIgnoreCase(highlightedColor)) {
            GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.getJoinClubBtnOnCard(name)) + " Button of " + name + " card is highlighted in " + highlightedColor + " or not", "Join Clubs Button is highlighted in " + highlightedColor, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.getJoinClubBtnOnCard(name)) + " Button of " + name + "card is highlighted in " + highlightedColor + " or not", "Join Clubs Button is not highlighted in " + highlightedColor, STATUS.FAIL, takeSnapShot());
        }
    }

    public static void joinOrLeaveClubBtnOnCard(String name) {
        waitSec(5);
        try {
            if (isExist(Clubs_Locators.getJoinClubBtnOnCard(name))) {
                click(Clubs_Locators.getJoinClubBtnOnCard(name),name+" Club");
                GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.getJoinClubBtnOnCard(name)) + " Button of " + name + " card is clicked", "Successfully clicked", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.getJoinClubBtnOnCard(name)) + " Button of " + name + " card is clicked", "Failed to click", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify " + getElementText(Clubs_Locators.getJoinClubBtnOnCard(name)) + " Button of " + name + " card is clicked", "Failed to click", STATUS.FAIL, takeSnapShot());
        }
    }

    public static void notificationVerification() {

        if (isExist(Clubs_Locators.joinClubNotification)) {
            GemTestReporter.addTestStep("Verify the notification arises after clicking on the Join Club button", "Notification arises stating- " + getElementText(Clubs_Locators.joinClubNotification), STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the notification arises after clicking on the Join Club button", "No notification arises", STATUS.FAIL, takeSnapShot());
        }

    }


    public static void joinClubBtnChangesToLeaveBUtton(String name, String btnName, String color, String textOfLeaveBtn) {

        String highlightedColor = Color.fromString(getCSSValue(Clubs_Locators.leaveClubBtn, "color")).asHex();

        if (isExist(Clubs_Locators.leaveClubBtn) && getElementText(Clubs_Locators.leaveClubBtn).equalsIgnoreCase(textOfLeaveBtn) && highlightedColor.equalsIgnoreCase(color)) {
            GemTestReporter.addTestStep("Verify after clicking on " + btnName + " button on " + name + " card it changes to " + textOfLeaveBtn + " group button", "The " + name + " has " + getElementText(Clubs_Locators.leaveClubBtn), STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify after clicking on " + btnName + " Club button on " + name + " card it changes to " + textOfLeaveBtn + " group button", "The " + name + " has " + getElementText(Clubs_Locators.leaveClubBtn), STATUS.FAIL, takeSnapShot());
        }

    }

    public static void navigationPath(String nameOfClub, String nameOfPage) {
        waitSec(10);

        if (DriverAction.isExist(Clubs_Locators.pathFromMyClubToClub) && getElementText(Clubs_Locators.pathFromMyClubToClub).contains(nameOfClub) && getElementText(Clubs_Locators.pathFromMyClubToClub).contains(nameOfPage)) {
            GemTestReporter.addTestStep("Verify the user is navigated to " + nameOfPage + " > " + nameOfClub, "Successfully navigated-" + getElementText(Clubs_Locators.pathFromMyClubToClub), STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify the user is navigated to " + nameOfPage + " Clubs > " + nameOfClub, "Not navigated. Path show is- " + getElementText(Clubs_Locators.pathFromMyClubToClub), STATUS.FAIL, takeSnapShot());
        }

    }
}

package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.GembookUtilities.EventsUtils;
import com.qa.gembook.GembookUtilities.GembookUtils;
import com.qa.gembook.Locators.Events_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Events extends DriverAction {

    public static String ongoingTabData, pastTabData;

    // -------------------------- Visibility of Events Section on the Page--------------------------
    @Then("User checks if the Events section is present or not")
    public void eventsSection() {
        try {
            EventsUtils.waitForElement(Events_Locators.eventsSection, 20); //Wait for the presence of element on the screen
            GemTestReporter.addTestStep("Check the presence of Event section on the News Feed Page", "Events Section is present on the News Feed Page", STATUS.PASS, takeSnapShot());
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check the presence of Event section on the News Feed Page", "Events Section is not present on the News Feed Page", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    // ----------------Check for Necessary Elements inside Events Section-------------------------
    @And("User verifies the presence of necessary elements inside the Events section")
    public void eventsElements() {
        EventsUtils.isVisible(Events_Locators.eventsHeading, "Check presence of Events Header in Events Card", "Events Header is present", "Events Header is not present");
        EventsUtils.isVisible(Events_Locators.eventsImg, "Check presence of Events Image in Events Card", "Events Image is present", "Events Image is not present");
        EventsUtils.isVisible(Events_Locators.eventsTabs("Ongoing & Upcoming"), "Check presence of Ongoing & Upcoming tab in Events Card", "Ongoing & Upcoming tab is present", "Ongoing & Upcoming tab is not present");
        EventsUtils.isVisible(Events_Locators.eventsTabs("Past"), "Check presence of Past tab in Events Card", "Past tab is present", "Past tab is not present");
        EventsUtils.isVisible(Events_Locators.eventsData, "Check presence of data box in Events Card", "Data box is present", "Data box is not present");
    }

    // ---------------------------Toggle between tabs-------------------------------
    @Then("User switch to {string} tab inside Events section")
    public void toggleTabs(String tabName) {
        try {
            EventsUtils.waitForElement(Events_Locators.eventsTabs(tabName), 20);
            if (GembookUtils.isElementClickable(Events_Locators.eventsTabs(tabName), 20)) {
                click(Events_Locators.eventsTabs(tabName), tabName + " tab");
            } else {
                GemTestReporter.addTestStep("User Click on " + tabName + " tab", "User is enable to click on " + tabName + " tab", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check presence of " + tabName + " tab", tabName + " tab is not present", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    // -------------------------------- Get the Events List ----------------------------------
    @And("User gets the data inside the {string} tab of Events Section")
    public void getTabsData(String tabName) {
        try {
            EventsUtils.waitForElement(Events_Locators.eventsData, 5);
            GemTestReporter.addTestStep("Check if there is data inside " + tabName + " tab", "Data inside " + tabName + " tab is present", STATUS.PASS, takeSnapShot());
            switch (tabName) {
                case "Ongoing & Upcoming":
                    ongoingTabData = new String();
                    ongoingTabData = getElementText(Events_Locators.eventsData);
                    break;
                case "Past":
                    pastTabData = new String();
                    pastTabData = getElementText(Events_Locators.eventsData);
                    break;
                default:
                    System.out.println("Enter the correct tab Name in feature file");
                    break;
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check if there is data inside " + tabName + " tab", "Data inside " + tabName + " tab is not present", STATUS.FAIL, takeSnapShot());
        }
    }

    // ----------------------- Verify is user was able to toggle between tabs -----------------------------
    @And("User compares the data inside tabs to verify toggle")
    public void verifyToggle() {
        if (!ongoingTabData.equals(pastTabData)) {
            GemTestReporter.addTestStep("Check if toggle is working", "Toggling between tabs is working fine", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Check if toggle is working", "Toggling between tabs is not working", STATUS.FAIL, takeSnapShot());
        }
    }

    //------------------- Validate the Events appear inside tabs as per the Event date ------------------
    //-------- If the Date is previous than the current date then it should appear under Past tab -------
    //--------------------------- Else it should appear inside the Ongoing Tab---------------------------
    @And("User gets the data inside the {string} tab and validate the data")
    public void validateDate(String tabName) {
        try {
            EventsUtils.waitForElement(Events_Locators.eventsList("Event Date"), 10);
            for (int events = 0; events < getElements(Events_Locators.eventsList("Event Date")).size(); events++) {
                String date = getElementText(getElements(Events_Locators.eventsList("Event Date")).get(events));
                date = date.substring(date.indexOf(":") + 1).trim().replaceAll(", ", "/").replaceAll(" ", "/");
                if (EventsUtils.compareDate(date, tabName)) { // Event Date comparison with the current Date
                    GemTestReporter.addTestStep("Check if Event with " + getElementText(getElements(Events_Locators.eventsList("Name")).get(events)) + " is placed in " + tabName + " tab according to date", "Event with " + getElementText(getElements(Events_Locators.eventsList("Name")).get(events)) + " is placed in " + tabName + " tab according to date", STATUS.PASS, takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Check if Event with " + getElementText(getElements(Events_Locators.eventsList("Name")).get(events)) + " is placed in " + tabName + " tab according to date", "Event with " + getElementText(getElements(Events_Locators.eventsList("Name")).get(events)) + " is not placed in " + tabName + " tab according to date", STATUS.PASS, takeSnapShot());
                }
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check Events inside " + tabName + " tab", "No Events data is present", STATUS.PASS, takeSnapShot());
        }
    }

    // ----------------------- Verify Scroll Functionality of Event List --------------------------
    @And("Verify is user is able to scroll through the Events data inside {string} tab using {string}")
    public void verifyEventsScroll(String tabName, String method) throws AWTException {
        try {
            EventsUtils.waitForElement(Events_Locators.eventsList("Event Date"), 10);
            if (getElements(Events_Locators.eventsList("Event Date")).size() > 2) { // Scrollbar appears when the number of Events is more than 2
                Long beforeScroll = EventsUtils.getScrollPosEventList(Events_Locators.scrollEvents); // Position before Scrolling
                Long afterScroll = 0L;
                switch (method) {
                    //------------ Scroll using Keyboard up, Down Keys------------
                    case "Arrow Keys":
                        click(Events_Locators.eventsData, tabName + " data to switch focus"); // Switch focus on Events Card
                        for (int i = 0; i < 5; i++) {
                            EventsUtils.keyPress(KeyEvent.VK_DOWN);
                            waitSec(1);
                            EventsUtils.keyRelease(KeyEvent.VK_DOWN);
                        }
                        afterScroll = EventsUtils.getScrollPosEventList(Events_Locators.scrollEvents);
                        // --------------- Verify is scroll is successfull -------------
                        if (beforeScroll == 0L && afterScroll > beforeScroll) {
                            GemTestReporter.addTestStep("Verify if user is able to scroll through Event List in " + tabName + " tab using up, down keys", "User is able to scroll through Event List in " + tabName + " tab using up, down keys and Y offset is : " + afterScroll, STATUS.PASS, takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify if user is able to scroll through Event List in " + tabName + " tab using up, down keys", "User is not able to scroll through Event List in " + tabName + " tab using up, down keys", STATUS.FAIL, takeSnapShot());
                        }
                        break;

                    //------------ Scroll using Scrollbar(Javascript Executor) ------------
                    case "Scrollbar":
                        click(Events_Locators.eventsData, tabName + " data to switch focus");
                        waitSec(1);
                        EventsUtils.scrollInsideElement(Events_Locators.scrollEvents, 0, 200);
                        afterScroll = EventsUtils.getScrollPosEventList(Events_Locators.scrollEvents);
                        // --------------- Verify is scroll is successfull -------------
                        if (beforeScroll == 0L && afterScroll == 200L) {
                            GemTestReporter.addTestStep("Verify if user is able to scroll through Event List in " + tabName + " tab using scroll bar", "User is able to scroll through Event List in " + tabName + " tab using scroll bar and Y offset is : " + afterScroll, STATUS.PASS, takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep("Verify if user is able to scroll through Event List in " + tabName + " tab using scroll bar", "User is not able to scroll through Event List in " + tabName + " tab using scroll bar", STATUS.FAIL, takeSnapShot());
                        }
                        break;

                    default:
                        System.out.println("Enter from the above mentioned methods to scroll");
                        break;
                }

            } else {
                GemTestReporter.addTestStep("Check is there are enough events for scroll", "There are not enough events present", STATUS.PASS, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check Events inside " + tabName + " tab", "No Events data is present", STATUS.PASS, takeSnapShot());
            exception.printStackTrace();
        }
    }


}

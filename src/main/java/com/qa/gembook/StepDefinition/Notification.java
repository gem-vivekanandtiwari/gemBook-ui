package com.qa.gembook.StepDefinition;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.Notifications_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;


public class Notification extends DriverAction {

    @Then("Verify if Notifications tab is visible")
    // <!-- Function to check Notifications dropdown and its header -->
    public void verifyNotificationsTab() {
        if (isExist(Notifications_Locators.notificationDropDown)) {
            String notificationTabTitle = getElementText(Notifications_Locators.notificationDropDownTitle);
            if (notificationTabTitle.contains("Notifications")) {
                click(Notifications_Locators.notificationDropDownTitle, "Verify if Notification tab is displayed", "Notification tab is displayed with the title of: " + notificationTabTitle);
            }
        }
        else{
            GemTestReporter.addTestStep("Verify if Notifications tab is visible","Notifications tab is not visible",STATUS.FAIL,takeSnapShot());
        }
    }

    @Given("Click on Notifications icon")
    // <!-- Function to check if Notifications icon exists and click on it-->
    public void clickNotifications() {
        try{
            if (isExist(Notifications_Locators.notificationIcon)) {
                click(Notifications_Locators.notificationIcon, "Click on Notification icon", "Clicked on Notification icon");
            }
        } catch (Exception exception){
            GemTestReporter.addTestStep("Click on Notifications icon","Unable to click on Notifications icon",STATUS.FAIL,takeSnapShot());
            throw exception;
        }
    }

    @Then("Verify if any notifications are present")
    // <!-- Function to check if any Notifications are present in Notification dropdown -->
    public void verifyNotifications() {
        if (isExist(Notifications_Locators.notificationDropDown)) {
            String notificationTabMessage = getElementText(By.xpath("//p[@class='navbar_noNoti__3BsjK']"));
            if (notificationTabMessage.contains("No new notification")) {
                GemTestReporter.addTestStep("Verify if any notifications are available in Notifications tab", "No notifications are available in Notifications tab with message of: " + notificationTabMessage, STATUS.PASS, takeSnapShot());
            }
        }
        else {
            GemTestReporter.addTestStep("Verify if Notifications tab is visible","Notifications tab is not visible",STATUS.FAIL,takeSnapShot());
        }
    }

    @And("Click on close button of Notifications tab")
    // <!-- Function to close Notifications dropdown -->
    public void clickCloseButton() {
        if (isExist(Notifications_Locators.notificationDropDown) && isExist(Notifications_Locators.notificationCloseButton)) {
            click(Notifications_Locators.notificationCloseButton, "Click on close button in Notifications tab", "Clicked on close button in Notifications tab");
        }
        else {
            GemTestReporter.addTestStep("Verify if Notifications tab is visible","Notifications tab is not visible",STATUS.FAIL,takeSnapShot());
        }
    }


}

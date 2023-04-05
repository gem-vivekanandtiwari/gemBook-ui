package com.qa.gembook.GembookUtilities;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.Birthday_Locator;
import com.qa.gembook.Locators.Login_Locators;
import org.openqa.selenium.By;

public class BirthdayUtils extends DriverAction {


    // To check whether the element is visible or not.
    public static void isVisible(By locator, String title, String passDesc, String failDesc) {
        try {
            waitUntilElementAppear(locator, 10);
            GemTestReporter.addTestStep(title, passDesc, STATUS.PASS, takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep(title, failDesc, STATUS.FAIL, takeSnapShot());
        }
    }

    // To check the presence of gembook logo
    public static void userChecksThePresenceOfGembookLogo() {
        if ((isExist(Birthday_Locator.gembookLogo))) {
            GemTestReporter.addTestStep("Validate if home page is loaded successfully", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate if home page is loaded successfully", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }

    // To check the presence of signin button
    public static void verifyLogout() {
        waitUntilElementAppear(Login_Locators.signInBtn, 10);
        if (isExist(Login_Locators.signInBtn)) {
            GemTestReporter.addTestStep("Verify if User is logged out from the Gembook application", "User successfully logged out from the Gembook application", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify if User is logged out from the Gembook application", "Log out failed", STATUS.FAIL, takeSnapShot());

    }

    // To check the presence of validation fields
    public static void validateFields() {

        if (isExist(Birthday_Locator.validationFields("Skills")) && isExist(Birthday_Locator.validationFields("Achievement")) && isExist(Birthday_Locator.validationFields("Gemtalk")) && isExist(Birthday_Locator.validationFields("Project")) && isExist(Birthday_Locator.validationFields("Trainings"))) {
            GemTestReporter.addTestStep("Validate if data is loaded in a new tab", "Validation passed", STATUS.PASS, takeSnapShot());

        } else {
            GemTestReporter.addTestStep("Validate if data is loaded in a new tab", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }

    // To click on logout button
    public static void logout() {
        if (isExist(Login_Locators.moreBtn)) {
            click(Login_Locators.moreBtn,"More Button");
        }
        if (isExist(Login_Locators.logOut)) {
            click(Login_Locators.logOut,"Logout Button");
        }
    }
//Select the post type while creating the post
    public static void selectPostType(String filterName) {
        if (isExist(Birthday_Locator.postType)) {
            GemTestReporter.addTestStep("Validate if post type dropdown is present on UI", "Post type dropdown is present", STATUS.PASS, takeSnapShot());
            click(Birthday_Locator.postType,"Post Type Dropdown");

            if (isExist(Birthday_Locator.selectFilter(filterName))) {
                GemTestReporter.addTestStep("Validate if post type dropdown is present on UI", "Post type dropdown is present", STATUS.PASS, takeSnapShot());
                click(Birthday_Locator.selectFilter(filterName),filterName);
            } else {
                GemTestReporter.addTestStep("Validate post type dropdown is present on UI", "Post type is not present", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate post type indicator is present on UI", "Post type is not present", STATUS.FAIL, takeSnapShot());
        }
    }
    //Validate the post type
    public static void validatePostFilter(String filterName) {
        if (isExist(Birthday_Locator.validateFilter(filterName))) {
            GemTestReporter.addTestStep("Validate " + filterName + " is selected", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate " + filterName + " is selected", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }

}
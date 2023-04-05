package com.qa.gembook.GembookUtilities;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.Locators.Login_Locators;
import com.qa.gembook.Locators.SideBar_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GembookUtils extends DriverAction {

    /**
     * @author Deeksha Singh
     */
    @When("User clicks on signIn Button")
    public void signIn() {
        try {
            EventsUtils.waitForElement(Login_Locators.signInBtn, 20);
            if (isElementClickable(Login_Locators.signInBtn, 20)) {
                click(Login_Locators.signInBtn, "Click on Sign In Button", "Clicked Sign In Button"); // Click on Sign in Button
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check visibility of Sigin Button", "Sign in button is not present", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
        waitSec(3);
    }

    /**
     * @param credentialType Takes the string to choose between username or password field
     * @author Rahul tagra
     * @since 17th Feb,2023
     */
    @Then("User enters the {string}")
    public void enterCredentials(String credentialType) throws IOException {
        try {
            List<String> browserWindows = new ArrayList<>(getWindowHandles()); // Get all browser windows
            switchToWindow(browserWindows.get(0)); // Switch focus to 2nd browser window
            EventsUtils.waitForElement(Login_Locators.credentials(credentialType), 20);
            switch (credentialType) {
                case "username":
                    typeText(Login_Locators.credentials(credentialType), readProperties(credentialType)); // Enter Username
                    break;
                case "password":
                    typeText(Login_Locators.credentials(credentialType), "User enters the password", "Password Entered Successfully", readProperties(credentialType));
                    break;
                default:
                    System.out.println("Please enter a valid Input");
            }
            EventsUtils.waitForElement(Login_Locators.nextBtn, 10);
            click(Login_Locators.nextBtn, "Next Button");
            waitSec(5);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify if Login window appears on the screen", "Login window does not appear on the screen", STATUS.FAIL, takeSnapShot());
            throw e;
        }
    }

    /**
     * @author Deeksha Singh
     */
    @And("User logins into the application")
    public void login() {
        try {
            EventsUtils.waitForElement(Login_Locators.nextBtn, 10);
            getElement(Login_Locators.nextBtn).click(); // User clicks on yes Button
            List<String> browserWindows = new ArrayList<>(getWindowHandles());
            switchToWindow(browserWindows.get(0)); // Switch focus back to 1st window
            if (EventsUtils.isElementVisible(Login_Locators.loginError, 5)) {
                waitSec(5); // Necessary hardcoded Wait to first let the Login failed popup go
                click(Login_Locators.signInBtn, "Sign in Button");
                if (EventsUtils.isElementVisible(Login_Locators.loginError, 10)) {
                    click(Login_Locators.signInBtn, "Sign in Button");
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param property Takes the string to read the properties from Config File
     * @return returns String : Value of the fetched property
     * @author Rahul tagra
     * @since 17th Feb,2023
     */
    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader("src/main/resources/config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    /**
     * @author Deeksha Singh
     */
    @Then("Verify user is logged into the application or not")
    public void verifyLogin() {
        try {
            String expectedUrl = "https://gembook.geminisolutions.com/#/dashboard";
            EventsUtils.waitForElement(Login_Locators.Image("Logo"), 20);
            if (getCurrentURL().contains(expectedUrl) && isExist(Login_Locators.Image("Logo")) && isExist(Login_Locators.logoHeader) && isExist(Login_Locators.Image("Profile"))) {
                GemTestReporter.addTestStep("Verify if User is logged into the application", "User logins into the Gembook application", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify if User is logged into the application", "User is unable to login into the Gembook application", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify if User is logged into the application", "User is unable to login into the Gembook application", STATUS.FAIL, takeSnapShot());
            throw e;
        }
    }

    /**
     * // Function to encrypt password, by assigning password value to originalPassword
     *
     * @author Siva Puja Pasupulati
     * @since 20th February 2023
     */

    public void encryptString() {
        String originalPassword = ""; //Replace empty string with your password value
        byte[] encodedString = Base64.encodeBase64(originalPassword.getBytes());
        System.out.println(new String(encodedString)); //Get your encrypted password value

    }

    /**
     * -- Function to decrypt encrypted password value --
     *
     * @param password contains the encrypted value of originalPassword
     * @return returns String : Decrypted value of encrypted password
     * @author Siva Puja Pasupulati
     * @since 20th February 2023
     */

    public static String decryptString(String password) {
        if (password.length() != 0) {
            byte[] decodedPassword = Base64.decodeBase64(password); //enter your encrypted password value
            return (new String(decodedPassword));
        } else {
            GemTestReporter.addTestStep("Verify if encrypted password value is entered", "Encrypted password value is not entered- " + password, STATUS.ERR);
            return password;
        }
    }


    /**
     * @author Karan Singh Thakur
     */
    public void clickElementWithJS(By locator) {
        try {
            click(locator);
        } catch (NullPointerException e) {
            if (isExist(locator)) {
                JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
                executor.executeScript("arguments[0].click();", getElement(locator));
                GemTestReporter.addTestStep("Verify click on element is performed", "Click performed successfully", STATUS.PASS, takeSnapShot());
            } else
                GemTestReporter.addTestStep("Verify click on element is performed", "Click is not performed", STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * @author Karan Singh Thakur
     */
    public static boolean isElementClickable(By locator, int duration) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(duration));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            GemTestReporter.addTestStep("Verify element is clickable", "Validation passed. Element is clickable", STATUS.PASS, takeSnapShot());
            return true;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify element is clickable", "Validation failed.", STATUS.FAIL, takeSnapShot());
            return false;
        }
    }

    /**
     * @author Kartikay
     */
    @When("User navigates to tab {string}")
    public void navigateToScreen(String tabName) {
        if (isExist(SideBar_Locators.navigationTab(tabName))) {
            click(SideBar_Locators.navigationTab(tabName));
            GemTestReporter.addTestStep("Validate click on " + tabName + " is performed", "Clicked performed successfully", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate " + tabName + " is visible", tabName + " is not visible on UI", STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * -- Function to fetch xpath of credentialType from credentials function and to enter GitHubUserName
     * fetched from readProperties file and GitHubPassword from decryptString function. --
     *
     * @param credentialType Takes the string to choose between GitHubUsername or GitHubPassword
     * @author Siva Puja Pasupulati
     * @since 17th February 2023
     */

    @Then("User enters GitHub {string}")
    public void userEntersGitHub(String credentialType) throws IOException {
        if (credentialType.contains("GitHubUserName")) {
            click(Login_Locators.credentials(credentialType));
            typeText(Login_Locators.credentials(credentialType), readProperties(credentialType)); //enter GitHub username
        }
        if (credentialType.contains("GitHubPassword")) {
            click(Login_Locators.credentials(credentialType));
            String password = decryptString("TGFrc2h5cHV1KjY="); // edit it with your encrypted password value
            typeText(Login_Locators.credentials(credentialType), "User enters the password", "Password Entered Successfully", password);
        }
    }

}


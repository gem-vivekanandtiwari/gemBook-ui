package com.qa.gembook.StepDefinition;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.Login_Locators;
import com.qa.gembook.Locators.SignIn_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


public class SignIn extends DriverAction{


    @Then("Close Sign-in window")
    // <!-- Function to skip entering sign-in credentials by shifting to Gembook window and clicking on sign-in button -->
    public void closeWindow() {
        List<String> browserWindows = new ArrayList<>(getWindowHandles());
        switchToWindow(browserWindows.get(1));
        switchToWindow(browserWindows.get(0));
        click(SignIn_Locators.gembookSignInCoverImg);
        click(Login_Locators.signInBtn);
    }

    @And("Verify if Login Failed alert is displayed")
    // <!-- Function to check Login Failed alert -->
    public void loginFailedPopup() {
        if (isExist(SignIn_Locators.alertFailedPopup) && isExist(SignIn_Locators.gembookSignInLogo) && isExist(SignIn_Locators.gembookSignInCoverImg)) {
            String loginFailedAlertText = getElementText(SignIn_Locators.alertFailedPopup);
            GemTestReporter.addTestStep("Verify if Login Failed alert is displayed", "Login Failed alert is displayed with text as: " + loginFailedAlertText, STATUS.PASS, takeSnapShot());
            waitSec(3);
        }
        else{
            GemTestReporter.addTestStep("Verify if Login Failed alert is displayed", "Login Failed alert is not displayed", STATUS.FAIL, takeSnapShot());
        }
    }

    /*
  <!-- Function to click on sign-in options button in window
  where user enters login credentials and select GitHub sign-in option -->
 */
    @Then("Click on Sign-in options")
    public void useAnotherAccountOption() {
        List<String> browserWindows = new ArrayList<>(getWindowHandles()); // Get all browser windows
        switchToWindow(browserWindows.get(1)); // Switch focus to 2nd browser window
        waitSec(4);
        click(By.xpath("//div[@data-test-id='signinOptions']"), "Click on other Sign-in options", "Clicked on other Sign-in options");
        waitSec(3);
        click(By.xpath("//div[@data-test-cred-id='8']"), "Select GitHub Sign-in option", "GitHub Sign-in option selected");
        waitSec(5);

    }

    /*
     <!-- Function to accept that Microsoft can have read-only data access
     of GitHub username and mail -->
    */
    @Then("Provide GitHub access to Microsoft")
    public void gitHubAccessToMicrosoft() {
        click(By.xpath("//input[@type='submit']"), "Click on GitHub Sign in", "Clicked on GitHub Sign in");
        waitSec(5);
        if(isExist(By.xpath("//button[@data-octo-click='oauth_application_authorization']"))) {
            click(By.xpath("//button[@data-octo-click='oauth_application_authorization']"), "Provide GitHub access for Microsoft", "GitHub access for Microsoft is provided");}
        waitSec(7);
        if (isExist(By.xpath("//input[@type='submit']"))) //when entered GitHub credentials are of school or work account
        {
            click(By.xpath("//input[@type='submit']"), "Click on Next button if entered credentials are not of Personal GitHub account", "Entered GitHub credentials are of school or work account");
        }
        else {
            GemTestReporter.addTestStep("Entered GitHub credentials are of Personal account","Entered GitHub credentials are of Personal account only.",STATUS.INFO,takeSnapShot());
        }
    }




}

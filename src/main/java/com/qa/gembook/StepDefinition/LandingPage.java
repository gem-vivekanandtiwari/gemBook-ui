package com.qa.gembook.StepDefinition;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.SignIn_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

public class LandingPage extends DriverAction {

    @Given("Enter wrong url of Gembook {string}")
    // <!-- Function trying to load Gembook page by entering wrong url -->
    public void wrongUrl(String url) {
        waitSec(3);
        DriverAction.launchUrl(url);
//        if (getCurrentURL().contains(url)) {
//            GemTestReporter.addTestStep("Enter wrong url of Gembook", "Entered url of Gembook is: " + url, STATUS.INFO, takeSnapShot());
//        }
//        else{
//            GemTestReporter.addTestStep("Enter wrong url of Gembook", "Couldn't enter url of Gembook", STATUS.FAIL, takeSnapShot());
//        }
    }

    @Then("Verify if Gembook is accessible")
    // <!-- Function to check if Gembook site is loaded -->
    public void verifyGembookOpened() {

        if (isExist(SignIn_Locators.gembookSignInCoverImg) && isExist(SignIn_Locators.gembookSignInLogo)) {
            GemTestReporter.addTestStep("Verify if user is able to access Gembook signIn page", "User is able to access Gembook signIn page", STATUS.FAIL, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify if user is able to access Gembook signIn page", "User is unable to access Gembook signIn page", STATUS.PASS, takeSnapShot());
        }
    }

    @Then("Verify copyright of Landing Page")
    // <!-- Function to check copyrights issued on page-->
    public void verifyCopyright() {
        getCurrentURL();
        String copyrightStatus = getElementText(By.xpath("//div[@class='mt-auto text-center footer']"));
        if (copyrightStatus.contains("Gemini Solutions")) {
            GemTestReporter.addTestStep("Verify if Landing Page is copyrighted to Gemini Solutions", "Landing Page is copyrighted as:" + copyrightStatus, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify if Landing Page is copyrighted to Gemini Solutions", "Landing Page is not having Copyrights", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Verify logo of Landing Page")
    // <!-- Function to check page logo -->
    public void landingPageLogo() {
        if (isExist(SignIn_Locators.gembookSignInLogo) && isExist(SignIn_Locators.gembookSignInCoverImg)) {
            GemTestReporter.addTestStep("Verify logo of Landing Page", "Logo of Landing Page is of GemBook", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify logo of Landing Page", "Logo of Landing Page is not of GemBook", STATUS.FAIL, takeSnapShot());
        }

    }

    @And("Get tagline of Landing Page")
    // <!-- Function to check page tagline -->
    public void landingPageTagline() {
        String tagLineOfLandingPage = getElementText(By.xpath("//p[@class='info-2 text-gem']"));
        if (tagLineOfLandingPage.startsWith("Know") && tagLineOfLandingPage.endsWith("you")) {
            GemTestReporter.addTestStep("Verify if tagline is same as Gembook Landing Page tagline ", "Retrieved tagline is same as Gembook Landing Page tagline : " + tagLineOfLandingPage, STATUS.PASS, takeSnapShot());
        }
        else{
            GemTestReporter.addTestStep("Verify if tagline is same as Gembook Landing Page tagline ", "Retrieved tagline is not same as Gembook Landing Page ", STATUS.FAIL, takeSnapShot());
        }
    }


}

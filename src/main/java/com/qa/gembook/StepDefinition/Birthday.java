package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.GembookUtilities.BirthdayUtils;
import com.qa.gembook.Locators.Birthday_Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Birthday extends BirthdayUtils {


    @Given("User checks the presence of gembook logo")
    public static void userChecksThePresenceOfGembookLogo() {
        BirthdayUtils.userChecksThePresenceOfGembookLogo();
    }

    @Then("Birthday section is visible")
    public void validateBirthdayScreen() {
        if (isExist(Birthday_Locator.sectionName("Birthday"))) {
            GemTestReporter.addTestStep("Validate birthday section is loaded", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate birthday section is loaded", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }


    @Then("Click on any entry under birthday section")
    public void clickOnAnyEntryUnderBirthdaySection() {
        String BirthdayDateInBirthdaySection = getElementText(Birthday_Locator.dateRowAtScreen("Birthday", "1"));
        if (isExist(Birthday_Locator.rowAtScreen("Birthday", "1"))) {
            GemTestReporter.addTestStep("Verify entries are present under birthday section", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify entries are present under birthday section", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
        try {
            click(Birthday_Locator.rowAtScreen("Birthday", "1"));
        } catch (Exception exception) {
            throw exception;
        }

        waitSec(3);
        WebDriver driver = DriverManager.getWebDriver();
        Set<String> s1 = driver.getWindowHandles();

        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            i1.next();
            String ChildWindow = i1.next();
            driver.switchTo().window(ChildWindow);
            DriverAction.waitSec(4);

            GemTestReporter.addTestStep("Validate click is performed on a birthday section entry", "Validation passed", STATUS.PASS, takeSnapShot());

            String BirthdayDateOnNewWindow = getElementText(Birthday_Locator.fetchText("Birthday")).split(",")[0];


            if (BirthdayDateInBirthdaySection.equals(BirthdayDateOnNewWindow)) {
                GemTestReporter.addTestStep("Validate birthday date", "Validation passed. Birthday date in birthday section is " + BirthdayDateInBirthdaySection + " and Birthday date in new window is " + BirthdayDateOnNewWindow, STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate birthday date", "Validation failed. Birthday date in birthday section is " + BirthdayDateInBirthdaySection + " and Birthday date in new window is " + BirthdayDateOnNewWindow, STATUS.FAIL, takeSnapShot());
            }

        }
    }

    @Then("Check the URL is correct or not")
    public void checkTheURLIsCorrectOrNot() {
        String ActualURL = DriverManager.getWebDriver().getCurrentUrl();
        String email = getElementText(Birthday_Locator.validationFields("EmailID"));

        if (ActualURL.contains(email)) {
            GemTestReporter.addTestStep("Validate correct tab is opened", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate correct tab is opened", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }

    @And("Validate all fields are loaded successfully")
    public void validateAllFieldsAreLoadedSuccessfully() {
        BirthdayUtils.validateFields();
    }


    @When("User logs out from the Gembook application")
    public void userLogsOutFromTheGembookApplication() {
        BirthdayUtils.logout();
    }

    @Then("Validate if user is logged out from the application")
    public void validateIfUserIsLoggedOutFromTheApplication() {
        BirthdayUtils.verifyLogout();
    }


    @Then("Check birthday date of first entry")
    public void checkBirthdayDateOfFirstEntry() {
        WebDriver driver = DriverManager.getWebDriver();

        if (isExist(Birthday_Locator.dateMainScreen("Birthday", 1))) {
            GemTestReporter.addTestStep("Validate row number 1 visible in birthday section", "If row is visible click on it", STATUS.PASS, takeSnapShot());
            try {
                click(Birthday_Locator.dateMainScreen("Birthday", 1));
            } catch (Exception exception) {
                throw exception;
            }
        } else {
            GemTestReporter.addTestStep("Validate row number 1 is visible in birthday section", "Validation failed", STATUS.FAIL, takeSnapShot());
        }

        ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(list.get(1));
        String DOB_temp = getElementText(Birthday_Locator.dateNewTab("Birthday", 1));

        GemTestReporter.addTestStep("Check date of birth of selected member", "Date of birth is : " + DOB_temp, STATUS.PASS, takeSnapShot());
        DriverAction.waitSec(4);
        driver.switchTo().window(list.get(0));

    }

    @Then("Check the date of last entry")
    public void checkTheDateOfLastEntry() {
        WebDriver driver = DriverManager.getWebDriver();
        if (isExist(Birthday_Locator.screenName("Birthday"))) {
            GemTestReporter.addTestStep("Validate birthday screen loaded.", "Validation passed", STATUS.PASS, takeSnapShot());
            List<WebElement> BirthdayRows = driver.findElements(Birthday_Locator.screenName("Birthday"));
            int size = BirthdayRows.size() - 1;
            if (isExist(Birthday_Locator.lastEntry("Birthday", size))) {
                try {
                    click(Birthday_Locator.lastEntry("Birthday", size));
                } catch (Exception exception) {
                    throw exception;
                }
                GemTestReporter.addTestStep("Validate total number of entries present under birthday section and click on the last entry.", "Validation passed. Total entries are: " + size + " and click on " + size + "th entry is performed", STATUS.PASS, takeSnapShot());
                ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(list.get(0));
                DriverAction.waitSec(4);

                String birthdayDate = getElementText(Birthday_Locator.newTabOptions("Birthday"));

                GemTestReporter.addTestStep("Validate date of birth of last entry.", "Validation passed. Date of birth is :" + birthdayDate, STATUS.PASS, takeSnapShot());
                driver.switchTo().window(list.get(0));
            } else {
                GemTestReporter.addTestStep("Validate date of birth of last entry.", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate birthday screen loaded.", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Validate the date range of first entry and last entry inside birthday section")
    public void validateDateRangeInBirthdaySection() {
        WebDriver driver = DriverManager.getWebDriver();
        String DOB = getElementText(Birthday_Locator.dateMainScreen("Birthday", "1"));
        List<WebElement> BirthdayRows = driver.findElements(Birthday_Locator.screenName("Birthday"));
        int size = BirthdayRows.size();
        By date = By.xpath("(//div[contains(@class,'birthday')]/div[@class='date'])[1]");
        System.out.println(DriverAction.getElementsText(date));
        String dateValue = getAttributeName(date, "text");
        String dob = DOB.split(" ")[0];
        int FirstDate = Integer.parseInt(dob);
        int ExpectedDate = FirstDate + 7;
        System.out.println("Expected date " + ExpectedDate);
        int DateRange = ExpectedDate - FirstDate;
        System.out.println("Date range is " + DateRange);
        if (isExist(Birthday_Locator.screenName("Anniversary"))) {
            GemTestReporter.addTestStep("Validate the date range of loaded data in birthday screen", "Validation passed. Date range is: " + DateRange + " Days", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate the date range of loaded data in birthday screen", "Validation failed. Date range is coming out to be " + DateRange + " Days", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Work anniversary section is visible")
    public void workAnniversarySectionIsVisible() {

        if (isExist(Birthday_Locator.sectionName("Work Anniversary"))) {
            GemTestReporter.addTestStep("Validate work anniversary section is loaded", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate work anniversary section is loaded", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @When("Check work anniversary date of first entry")
    public void validtateWorkAnniversaryDate() throws ParseException {
        waitSec(5);
        if (isExist(Birthday_Locator.dateMainScreen("Anniversary", 1))) {
            GemTestReporter.addTestStep("Validate row number 1 visible in work anniversary section", "If row is visible. Click on first entry", STATUS.PASS, takeSnapShot());
            click(Birthday_Locator.dateMainScreen("Anniversary", 1));
        } else {
            GemTestReporter.addTestStep("Validate row number 1 is visible in work anniversary section", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
        waitSec(5);
        WebDriver driver = DriverManager.getWebDriver();
        ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(list.get(1));
        String DOB_temp = getElementText(Birthday_Locator.birthdayTabNewTab(1));
        GemTestReporter.addTestStep("Check date of joining of selected member", "Date of joining is : " + DOB_temp, STATUS.PASS, takeSnapShot());
        DriverAction.waitSec(4);
        String DOJ_temp = getElementText(Birthday_Locator.birthdayTabNewTab(2));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(DOJ_temp, formatter);
        DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date systemDate = new Date();
        String currentDate = simpleFormat.format(systemDate);
        Date sysDate = simpleFormat.parse(currentDate);
        Date joiningDate = simpleFormat.parse(String.valueOf(date));
        System.out.println((joiningDate.after(sysDate)));
        if (!sysDate.before(joiningDate)) {
            GemTestReporter.addTestStep("Validate joining date and system date", "Validation passed. Joining date is " + joiningDate + " and system date is " + sysDate, STATUS.PASS, takeSnapShot());
            driver.switchTo().window(list.get(0));
        } else {
            GemTestReporter.addTestStep("Validate joining date and system date", "Validation failed. Joining date is " + joiningDate + " and system date is " + sysDate, STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Check the joining date of last entry")
    public void dateOfJoiningOnLastEntry() {
        WebDriver driver = DriverManager.getWebDriver();
        List<WebElement> BirthdayRows = driver.findElements(Birthday_Locator.workAnniversaryEntries);
        int lastRowNumber = BirthdayRows.size() - 1;
        System.out.println("Total rows: " + lastRowNumber);
        if (isExist(Birthday_Locator.bithdayScreenRow(lastRowNumber))) {
            click(Birthday_Locator.bithdayScreenRow(lastRowNumber));
            GemTestReporter.addTestStep("Validate row number " + lastRowNumber + " is visible", "Row " + lastRowNumber + " is visible and clicked", STATUS.PASS, takeSnapShot());
            ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
            System.out.println("list size is: " + list.size());
            driver.switchTo().window(list.get(2));
            DriverAction.waitSec(4);
            String DOJ_temp = getElementText(Birthday_Locator.birthdayTabNewTab(2));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(DOJ_temp, formatter);
            System.out.println("testing date is: " + date);
        } else {
            GemTestReporter.addTestStep("Validate row number " + lastRowNumber + " is visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Validate the date range of first entry and last entry inside anniversary section")
    public void validateDataRangeAnniversay() {
        WebDriver driver = DriverManager.getWebDriver();
        String DOB = getElementText(Birthday_Locator.rowAtScreen("Anniversary", "1"));
        List<WebElement> BirthdayRows = driver.findElements(Birthday_Locator.screenName("Anniversary"));
        int size = BirthdayRows.size();
        By date = By.xpath("(//div[contains(@class,'birthday')]//div[@class='date'])[1]");
        System.out.println(DriverAction.getElementsText(date));
        String dateValue = getAttributeName(date, "text()");
        System.out.println("date value: " + dateValue);
        String dob = DOB.split(" ")[0];
        int FirstDate = Integer.parseInt(dob);
        int ExpectedDate = FirstDate + 7;
        System.out.println("Expected date " + ExpectedDate);
        int DateRange = ExpectedDate - FirstDate;
        System.out.println("Date range is " + DateRange);
        if (isExist(Birthday_Locator.screenName("Anniversary"))) {
            GemTestReporter.addTestStep("Validate the date range of loaded data in anniversary section is", "Validation passed. Date range is: " + DateRange + " Days", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate the date range of loaded data in anniversary section is", "Validation failed. Date range is coming out to be " + DateRange + " Days", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on any entry under work anniversary section")
    public void clickOnAnyEntryUnderWorkAnniversarySection() {
        String WorkAnniversaryDateHomePage = getElementText(Birthday_Locator.dateAtScreen("Anniversary", "1"));
        if (isExist(Birthday_Locator.dateAtScreen("Anniversary", "1"))) {
            GemTestReporter.addTestStep("Verify entries are present under work anniversary section", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify entries are present under work anniversary section", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
        try {
            click(Birthday_Locator.clickAnyEntry("Anniversary", "1"));

        } catch (Exception exception) {
            throw exception;
        }
        WebDriver driver = DriverManager.getWebDriver();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            i1.next();
            String ChildWindow = i1.next();
            driver.switchTo().window(ChildWindow);
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Validate click is performed on a work anniversary section entry", "Validation passed", STATUS.PASS, takeSnapShot());
            String WorkAnniversaryOnNewWindow = getElementText(Birthday_Locator.fetchText("Joining Date")).split(",")[0];
            if (WorkAnniversaryDateHomePage.contains(WorkAnniversaryOnNewWindow)) {
                GemTestReporter.addTestStep("Validate joining date", "Validation passed. Joining date on home page is " + WorkAnniversaryDateHomePage + " and Joining date  in new window is " + WorkAnniversaryOnNewWindow, STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate joining date", "Validation failed. Joining date on home page is " + WorkAnniversaryDateHomePage + " and Joining date  in new window is " + WorkAnniversaryOnNewWindow, STATUS.FAIL, takeSnapShot());
            }
        }
    }
    @Then("Check the presence of filter icon")
    public void checkThePresenceOfFilterIcon() {
        if (isExist(Birthday_Locator.screenName("Filter Button"))) {
            GemTestReporter.addTestStep("Validate the filter icon", "Validation passed.", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate the filter icon", "Validation failed.", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on filter icon")
    public void clickOnFilterIcon() {
        try {
            if (getElement(Birthday_Locator.screenName("Filter Button")).isEnabled() && getElement(Birthday_Locator.screenName("Filter Button")).isDisplayed()) {
                click(Birthday_Locator.screenName("Filter Button"));
                GemTestReporter.addTestStep("Validate click on filter button is performed successfully", "Validation passed", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate click on filter button is performed successfully", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            throw exception;
        }
    }
        @Then("Click on filter type")
        public void clickOnFilterType() {
            if (isExist(Birthday_Locator.filterSection)) {
                GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
                if (isExist(Birthday_Locator.filterType("Achievements"))) {
                    click(Birthday_Locator.filterType("Achievements"));
                    waitSec(5);
                    GemTestReporter.addTestStep("Validate achievement is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());

                } else {
                    GemTestReporter.addTestStep("Validate achievement is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        }
    @Then("New members section is visible")
    public void newMembersSectionIsVisible() {
        boolean NewMembersChild = isExist(By.className("member"));

        if (!NewMembersChild) {
            if (isExist(Birthday_Locator.sectionName("New Members"))) {
                GemTestReporter.addTestStep("Validate New member section is loaded", "Validation passed. New members has 0 entries", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate new member section is loaded", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            WebDriver driver = DriverManager.getWebDriver();
            List<WebElement> NewMembersRows = driver.findElements(Birthday_Locator.newMembersEntriesList);

            int size = NewMembersRows.size();

            if (size != 0) {
                GemTestReporter.addTestStep("Validate number of entries under new members section", "Validation passed. New members " + size + " entries", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate number of entries under new members section", "Validation passed." + size + " entries are present", STATUS.PASS, takeSnapShot());
            }
        }

    }
    @Then("Click on any entry under new members section")
    public void visitProfileNewMembers() {
        if (isExist(Birthday_Locator.rowsNewMembersSectionClick("1"))) {
            click(Birthday_Locator.rowsNewMembersSectionClick("1"));
            GemTestReporter.addTestStep("Validate click on row number one is performed", "Validation passed", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate click on row number one is performed", "Row doesn't exists", STATUS.PASS, takeSnapShot());
        }
        WebDriver driver = DriverManager.getWebDriver();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            i1.next();
            String ChildWindow = i1.next();
            driver.switchTo().window(ChildWindow);
            DriverAction.waitSec(4);
        }
    }
    @Then("Click on filter type announcements")
    public void clickOnFilterTypeAnnouncements() {
        if (isExist(Birthday_Locator.filterSection)) {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
            if (isExist(Birthday_Locator.filterType("Announcements"))) {
                click(Birthday_Locator.filterType("Announcements"));
                waitSec(5);
                GemTestReporter.addTestStep("Validate announcements is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate announcements is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Validates entries are filtered properly for applied filter")
    public void validatesEntriesAreFilteredProperlyForAppliedFilter() {
        if (isExist(Birthday_Locator.post) || isExist(Birthday_Locator.noEntry)) {
            GemTestReporter.addTestStep("Validate filter applied successfully", "Validation passed for applied filter type", STATUS.PASS, takeSnapShot());
        } else if (!isExist(Birthday_Locator.post)) {
            GemTestReporter.addTestStep("Validate filter applied successfully", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on filter type questions")
    public void clickOnFilterTypeQuestions() {
        if (isExist(Birthday_Locator.filterSection)) {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
            if (isExist(Birthday_Locator.filterType("Questions"))) {
                click(Birthday_Locator.filterType("Questions"));
                waitSec(5);
                GemTestReporter.addTestStep("Validate questions is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate questions is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on filter type generic")
    public void clickOnFilterTypeGeneric() {
        if (isExist(Birthday_Locator.filterSection)) {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
            if (isExist(Birthday_Locator.filterType("Generic"))) {
                click(Birthday_Locator.filterType("Generic"));
                waitSec(5);
                GemTestReporter.addTestStep("Validate generic is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate generic is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on filter type ideas")
    public void clickOnFilterTypeIdeas() {
        if (isExist(Birthday_Locator.filterSection)) {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
            if (isExist(Birthday_Locator.filterType("Ideas"))) {
                click(Birthday_Locator.filterType("Ideas"));
                waitSec(5);
                GemTestReporter.addTestStep("Validate ideas is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Validate ideas is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on filter type findings")
    public void clickOnFilterTypeFindings() {
        if (isExist(Birthday_Locator.filterSection)) {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
            if (isExist(Birthday_Locator.filterType("Findings"))) {
                click(Birthday_Locator.filterType("Findings"));
                waitSec(5);
                GemTestReporter.addTestStep("Validate findings is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Validate findings is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on filter type all")
    public void clickOnFilterTypeAll() {
        if (isExist(Birthday_Locator.filterSection)) {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation passed", STATUS.PASS, takeSnapShot());
            if (isExist(Birthday_Locator.filterType("All"))) {
                click(Birthday_Locator.filterType("All"));
                waitSec(5);
                GemTestReporter.addTestStep("Validate all is present in dropdown", "Validation passed", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate all is present in dropdown", "Validation failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate Filter options are visible", "Validation failed", STATUS.FAIL, takeSnapShot());
        }
    }
    @Then("Click on post type and select the achievement filter")
    public void clickOnPostTypeAndSelectTheFilter() {
        BirthdayUtils.selectPostType("Achievements");
    }
    @Then("Validates achievement filter is applied successfully")
    public void validatesFilterIsAppliedSuccessfully() {
        BirthdayUtils.validatePostFilter("Achievements");
    }

    @Then("Click on post type and select the announcement filter")
    public void clickOnPostTypeAndSelectTheAnnouncementFilter() {
        BirthdayUtils.selectPostType("Announcements");
    }

    @Then("Validates announcement filter is applied successfully")
    public void validatesAnnouncementFilterIsAppliedSuccessfully() {
        BirthdayUtils.validatePostFilter("Announcements");
    }

    @Then("Click on post type and select the question filter")
    public void clickOnPostTypeAndSelectTheQuestionFilter() {
        BirthdayUtils.selectPostType("Questions");
    }

    @Then("Validates question filter is applied successfully")
    public void validatesQuestionFilterIsAppliedSuccessfully() {
        BirthdayUtils.validatePostFilter("Questions");
    }

    @Then("Click on post type and select the generic filter")
    public void clickOnPostTypeAndSelectTheGenericFilter() {
        BirthdayUtils.selectPostType("Generic");
    }

    @Then("Validates generic filter is applied successfully")
    public void validatesGenericFilterIsAppliedSuccessfully() {
        BirthdayUtils.validatePostFilter("Generic");
    }

    @Then("Click on post type and select the ideas filter")
    public void clickOnPostTypeAndSelectTheIdeasFilter() {
         BirthdayUtils.selectPostType("Ideas");
    }

    @Then("Validates ideas filter is applied successfully")
    public void validatesIdeasFilterIsAppliedSuccessfully() {
        BirthdayUtils.validatePostFilter("Ideas");
    }

    @Then("Click on post type and select the findings filter")
    public void clickOnPostTypeAndSelectTheFindingsFilter() {
        BirthdayUtils.selectPostType("Findings");
    }

    @Then("Validates findings filter is applied successfully")
    public void validatesFindingsFilterIsAppliedSuccessfully() {
        BirthdayUtils.validatePostFilter("Findings");
    }
}
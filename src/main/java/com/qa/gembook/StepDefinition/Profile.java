package com.qa.gembook.StepDefinition;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.Login_Locators;
import com.qa.gembook.Locators.SignIn_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.qa.gembook.Locators.Profile_Locators;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;


public class Profile extends DriverAction {

    String userProfileData;
    String profileMenuData;

    @When("Click on ViewProfile icon")
    // <!-- Function to click on Profile Image icon -->
    public void viewProfile() {
        try{
            waitUntilElementAppear(Profile_Locators.viewProfile,5);
            click(Profile_Locators.viewProfile, "Click on View Profile icon", "Clicked on View Profile icon");
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Click on View Profile icon", "Unable to click on View Profile icon", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Click on Manager Profile")
    // <!-- Function to verify if Manager tab exists in user profile and clicking on it -->
    public void managerProfile() {
        try{
            if (isExist(Profile_Locators.managerProfile)) {
                GemTestReporter.addTestStep("Verify if Manager tab exists in View Profile page", "Manager tab exists in View Profile page", STATUS.PASS, takeSnapShot());
                click(Profile_Locators.managerProfile, "Click on Manager Profile", "Clicked on Manager Profile");
                waitSec(5);

            }
        }catch (Exception exception) {
            GemTestReporter.addTestStep("Click on Manager Profile","Unable to find Manager Profile",STATUS.FAIL,takeSnapShot());
            throw exception;
        }
    }

    /*
     <!-- Function to check if Manager profile is opening in new tab
     by counting the number of tabs opened in test window  -->
    */
    @Then("Verify if Manager Profile page is visible")
    public void verifyManagerProfile() {
        List<String> browserTabs = new ArrayList<>(getWindowHandles());
        switchToFrame(browserTabs.get(1)); // clicking on second tab in browser
        int count_of_tabs = 0;
        for (String iterator : browserTabs) {
            count_of_tabs += 1;
            System.out.println(iterator);
        }
        if (count_of_tabs == 2) {
            GemTestReporter.addTestStep("Verify if Manger profile is opened in next tab", "Manger profile is opened in next tab", STATUS.PASS, takeSnapShot());
        }
        else
        {
            GemTestReporter.addTestStep("Verify if Manger profile is opened in next tab", "Manger profile is not opened in next tab", STATUS.FAIL, takeSnapShot());
        }
    }


    @Then("Verify if Any {string} are existing")
    //<!-- Function to verify if any data already exists in Profile components-->
    public void profileComponents(String profileComponents) {
        try {
            switch (profileComponents) {
                case "Skills":
                    if (isExist(By.xpath("//i[contains(text(),'No Skills added.')] "))) {
                        GemTestReporter.addTestStep("Verify if any skills are added", "No skills are added", STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if any skills are added", "Skills are already added", STATUS.INFO, takeSnapShot());
                    }
                    break;
                case "Achievements":
                    if (isExist(By.xpath("//i[contains(text(),'No Achievement added.')]"))) {
                        GemTestReporter.addTestStep("Verify if any Achievements are added", "No Achievements are added", STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if any Achievements are added", "Achievements are already added", STATUS.INFO, takeSnapShot());
                    }
                    break;
                case "Trainings":
                    if (isExist(By.xpath("//i[contains(text(),'No Trainings added.')]"))) {
                        GemTestReporter.addTestStep("Verify if any Trainings are added", "No Trainings are added", STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if any Trainings are added", "Trainings are already added", STATUS.INFO, takeSnapShot());
                    }
                    break;
                case "GemTalk":
                    if (isExist(By.xpath("//i[contains(text(),'No Gemtalk added.')]"))) {
                        GemTestReporter.addTestStep("Verify if any GemTalk are added", "No GemTalk are added", STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if any GemTalk are added", "GemTalk are already added", STATUS.INFO, takeSnapShot());
                    }
                    break;

                case "Certification":
                    scrollToBottom();
                    if (isExist(By.xpath("//i[contains(text(),'No Certification added.')]"))) {
                        GemTestReporter.addTestStep("Verify if any Certification are added", "No Certification are added", STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if any Certification are added", "Certification are already added", STATUS.INFO, takeSnapShot());
                    }
                    break;
                case "Projects":
                    scrollToBottom();
                    if (isExist(By.xpath("//i[contains(text(),'No Projects added.')]"))) {
                        GemTestReporter.addTestStep("Verify if any Projects are added", "No Projects are added", STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if any Projects are added", "Projects are already added", STATUS.INFO, takeSnapShot());
                    }
                    break;
                default:
                    System.out.println("Valid profile component name is not available");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("Click on Add new {string}")
    //<!-- Function to write data into Profile components-->
    public void addData(String profileComponents) {
        try{
            switch (profileComponents) {
                case "Skills":
                    click(Profile_Locators.enterProfileComponent("Skills"), "Click on add Skills button", "Clicked on add Skills button");
                    typeText(By.id("skills"), "Selenium, UiPath");
                    break;

                case "Certification":
                    click(Profile_Locators.enterProfileComponent("Certification"), "Click on add Certification button", "Clicked on add Certification button");
                    break;

                case "Achievements":
                    click(Profile_Locators.enterProfileComponent("Achievement"), "Click on add Achievements button", "Clicked on add Achievements button");
                    typeText(By.id("achievement"), "Selenium, UiPath");
                    break;

                case "Trainings":
                    click(Profile_Locators.enterProfileComponent("Trainings"), "Click on add Trainings button", "Clicked on add Trainings button");
                    typeText(By.id("trainingsDone"),"Behavioural");
                    break;

                case "GemTalk":
                    click(Profile_Locators.enterProfileComponent("Gemtalk"), "Click on add GemTalk button", "Clicked on add GemTalk button");
                    typeText(By.id("gemTalkDelivered"),"UiPath");
                    break;

                case "Projects":
                    click(Profile_Locators.enterProfileComponent("Projects"), "Click on add Projects button", "Clicked on add Projects button");
                    break;

                default:
                    System.out.println("Valid profile component name is not available");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @And("Save added {string} information")
    //<!-- Function to save added data in Profile components-->
    public void saveInfo(String profileComponents) {
        try {
            switch (profileComponents) {
                case "Skills":
                    click(By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.profile_mainWrapper__2d8y4 > div > div.leftWrapper_leftWrapper__38HXT > div > div.leftBottom_desktopLeftBottom__1I0I2 > div > div > div:nth-child(1) > div > button:nth-child(2) > img"),"Click on save button","Clicked on save button");
                    waitSec(5);
                    break;
                case "Certification":
                    click(By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.profile_mainWrapper__2d8y4 > div > div.leftWrapper_leftWrapper__38HXT > div > div.leftBottom_desktopLeftBottom__1I0I2 > div > div > div.components_certiWrapper__2cjzs > div.components_heading__2c20_ > button:nth-child(2) > img"),"Click on save button","Clicked on save button");
                    break;
                case "Achievements":
                    click(By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.profile_mainWrapper__2d8y4 > div > div.leftWrapper_leftWrapper__38HXT > div > div.leftBottom_desktopLeftBottom__1I0I2 > div > div > div:nth-child(3) > div > button:nth-child(2) > img"),"Click on save button","Clicked on save button");
                    waitSec(5);
                    break;
                case "Trainings":
                    click(By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.profile_mainWrapper__2d8y4 > div > div.leftWrapper_leftWrapper__38HXT > div > div.leftBottom_desktopLeftBottom__1I0I2 > div > div > div:nth-child(4) > div > button:nth-child(2) > img"),"Click on save button","Clicked on save button");
                    waitSec(5);
                    break;
                case "GemTalk" :
                    click(By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.profile_mainWrapper__2d8y4 > div > div.leftWrapper_leftWrapper__38HXT > div > div.leftBottom_desktopLeftBottom__1I0I2 > div > div > div:nth-child(5) > div > button:nth-child(2) > img"),"Click on save button","Clicked on save button");
                    waitSec(5);
                    break;
                case "Projects":
                    click(By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.profile_mainWrapper__2d8y4 > div > div.leftWrapper_leftWrapper__38HXT > div > div.leftBottom_desktopLeftBottom__1I0I2 > div > div > div.components_projectWrapper__30vSL > div.components_heading__2c20_ > button:nth-child(2) > img"),"Click on save button","Clicked on save button");
                    break;
                default:
                    System.out.println("Valid profile component name is not available");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Then("Verify if {string} data is updated")
    //<!-- Function to verify if added data is updated in Profile components-->
    public void verifyData(String profileComponents) {
        try{
            switch (profileComponents) {
                case "Skills":
                    click(Profile_Locators.enterProfileComponent("Skills"),"Click on Skills component","Clicked on Skills component");
                    String skillsData = getAttributeName(By.id("skills"),"value");
                    if(skillsData.length()!=0){
                        GemTestReporter.addTestStep("Verify if entered Skills data is updated","Entered Skills data is updated as: "+skillsData,STATUS.PASS,takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if entered Skills data is updated","Entered Skills data is not updated.",STATUS.FAIL,takeSnapShot());
                    }
                    break;

                case "Achievements":
                    click(Profile_Locators.enterProfileComponent("Achievement"),"Click on Achievement component","Clicked on Achievement component");
                    String achievementsData = getAttributeName(By.id("achievement"),"value");
                    if(achievementsData.length() != 0){
                        GemTestReporter.addTestStep("Verify if entered Achievement data is updated","Entered Achievement data is updated as: "+achievementsData,STATUS.PASS,takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if entered Achievement data is updated","Entered Achievement data is not updated.",STATUS.FAIL,takeSnapShot());
                    }
                    break;

                case "Trainings":
                    click(Profile_Locators.enterProfileComponent("Trainings"),"Click on Trainings component","Clicked on Trainings component");
                    String trainingsData = getAttributeName(By.id("trainingsDone"),"value");
                    if(trainingsData.length() != 0){
                        GemTestReporter.addTestStep("Verify if entered Trainings data is updated","Entered Trainings data is updated as: "+trainingsData,STATUS.PASS,takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if entered Trainings data is updated","Entered Trainings data is not updated.",STATUS.FAIL,takeSnapShot());
                    }
                    break;

                case "GemTalk" :
                    click(Profile_Locators.enterProfileComponent("Gemtalk"),"Click on Gemtalk component","Clicked on Gemtalk component");
                    String gemTalkData = getAttributeName(By.id("trainingsDone"),"value");
                    if(gemTalkData.length() != 0){
                        GemTestReporter.addTestStep("Verify if entered Gemtalk data is updated","Entered Gemtalk data is updated as: "+ gemTalkData,STATUS.PASS,takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if entered Gemtalk data is updated","Entered Gemtalk data is not updated.",STATUS.FAIL,takeSnapShot());
                    }
                    break;

                case "Projects" :
                    if(isExist(Profile_Locators.enterProfileComponent("Projects"))){
                        GemTestReporter.addTestStep("Verify if entered Projects data is updated","Entered Projects data is updated.",STATUS.PASS,takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if entered Projects data is updated","Entered Projects data is not updated.",STATUS.FAIL,takeSnapShot());
                    }
                    break;

                case "Certification" :
                    if(isExist(Profile_Locators.enterProfileComponent("Certification"))){
                        GemTestReporter.addTestStep("Verify if entered Certification data is updated","Entered Certification data is updated.",STATUS.PASS,takeSnapShot());
                    }else{
                        GemTestReporter.addTestStep("Verify if entered Certification data is updated","Entered Certification data is not updated.",STATUS.FAIL,takeSnapShot());
                    }
                    break;

                default:
                    System.out.println("Valid profile component name is not available");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("Enter Certificate information")
    //<!-- Function to enter data in Certificate Profile component-->
    public void certificateInfo() {
        typeText(By.name("certificate"),"Gemini Cyber Security");
        typeText(By.name("provider"),"Gemini");
        typeText(By.name("issueDate"),"24-02-2023");
        fileUpload(By.name("certFile"),"C:\\Users\\pa.puja\\Pictures\\Camera Roll\\Gemini Certifications\\certification-Cyber-Security-Awareness-Training_2022-SivaPuja.Pasupulati@Geminisolutions.com.pdf");
    }

    @Then("Enter Projects information")
    //<!-- Function to enter Project and Project description data in Project Profile component-->
    public void projectsInfo() {
        typeText(By.name("project"),"Gembook");
        typeText(By.name("projectDescription"),"Mini facebook for Geminis");
    }

    @Then("Select Project Status as {string}")
    //<!-- Function to select a dropdown value for project status-->
    public void projectStatus(String projectStatus ) {
        click(By.name("projectStatus"), "Project Status");
        if(projectStatus.contains("OnGoing")){
            click(By.xpath("//option[text()='OnGoing']"),"Select Project status","Selected Project status as:"+projectStatus);
        }else if (projectStatus.contains("Completed")){
            click(By.xpath("//option[text()='Completed']"),"Select Project status","Selected Project status as:"+projectStatus);
        }else {
            GemTestReporter.addTestStep("Enter proper value from project status dropdown","Entered value doesn't exist in project status dropdown: "+projectStatus,STATUS.ERR,takeSnapShot());
        }
    }

    @Then("Select Project type as {string}")
    //<!-- Function to select a dropdown value for project type-->
    public void projectType(String projectType) {
        click(By.name("projectType"),"Click on project type dropdown","Clicked on project type dropdown");
        if(projectType.contains("Internal")){
            click(By.xpath("//option[text()='INTERNAL']"),"Select Project type","Selected Project type as:"+projectType);
        }else if(projectType.contains("Client")) {
            click(By.xpath("//option[text()='CLIENT']"),"Select Project type","Selected Project type as:"+projectType);
        }else {
            GemTestReporter.addTestStep("Enter proper value from project status dropdown","Entered value doesn't exist in project status dropdown: "+projectType,STATUS.ERR,takeSnapShot());
        }
    }

    @And("Verify if any Failed alert is displayed")
    // <!-- Function to check Failed alert popup -->
    public void alertFailed() {
            waitUntilElementAppear(By.xpath("//div[@class='Toastify__toast-body']"),10);
            String failedAlertText = getElementText(By.xpath("//div[@class='Toastify__toast-body']"));
            GemTestReporter.addTestStep("Verify if any Failed alert is displayed", "Failed alert is displayed with text as: " + failedAlertText, STATUS.PASS, takeSnapShot());
        }

    @Then("Enter wrong Certificate information")
    //<!-- Function to add incomplete information in Certificate Profile component-->
    public void wrongInformation() {
        typeText(By.name("certificate"),"Cyber Security");
        typeText(By.name("provider"),"ABC");
    }

    /*   <!-- Function to add multiple data in profile components without
         a comma and verify if they are displayed as multiple skills or not-->
     */
    @Then("Add multiple {string} without comma")
    public void addWithoutComma(String profileComponents) {
        try {
            switch (profileComponents) {
                case "Skills":
                    click(Profile_Locators.enterProfileComponent("Skills"), "Click on add Skills button", "Clicked on add Skills button");
                    typeText(By.id("skills"), "UiPath ; Selenium- Java | Rest API ");
                    break;

                case "Achievements":
                    click(Profile_Locators.enterProfileComponent("Achievement"), "Click on add Achievements button", "Clicked on add Achievements button");
                    typeText(By.id("achievement"), "You make a difference | You Rock | Keep it up");
                    break;

                case "Trainings":
                    click(Profile_Locators.enterProfileComponent("Trainings"), "Click on add Trainings button", "Clicked on add Trainings button");
                    typeText(By.id("trainingsDone"), "Behavioural . Functional ");
                    break;

                case "GemTalk":
                    click(Profile_Locators.enterProfileComponent("Gemtalk"), "Click on add GemTalk button", "Clicked on add GemTalk button");
                    typeText(By.id("gemTalkDelivered"), " 'UiPath' 'GitHub' ");
                    break;

                default:
                    System.out.println("Valid profile component name is not available");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("Get UserProfile data from ViewProfile page")
    //<!--Function to get data present in View Profile page of logged-in user>
    public void userProfileData() {
        waitSec(5);
        userProfileData = getElementText(By.xpath("//div[@class='leftWrapper_employeeName__1chtp']"));
        GemTestReporter.addTestStep("Get user data present in View Profile page of logged in user","User data present in View Profile page of logged in user is: "+userProfileData,STATUS.INFO,takeSnapShot());
    }

    @Then("Click on three vertical dots icon in Home page")
    //<!--Function to click on three vertical dots icon in home page>
    public void navigationMenu() {
        waitUntilElementAppear(Profile_Locators.threeVerticalDots,10);
        click(Profile_Locators.threeVerticalDots,"Click on three vertical dots icon in home page","Clicked on three vertical dots icon in home page");
    }

    @And("Get user data present on three vertical dots icon in home page")
    //<!--Function to get user data present on three vertical dots icon in home page>
    public void userData() {
        profileMenuData = getElementText(By.xpath("//div[@class='navbar_dropdownContent__27BhS']"));
        GemTestReporter.addTestStep("Get user data present on three vertical dots icon in home page","User data present on three vertical dots icon in home page is: "+profileMenuData,STATUS.INFO,takeSnapShot());
    }

    /*  <!--Function to verify if data in View profile page of user matches
        with data in three vertical dots icon in home page-->
     */
    @Then("Verify if ViewProfile page and Home page data matches")
    public void dataMatch() {
        if(userProfileData.contains(profileMenuData)){
            GemTestReporter.addTestStep("Verify if ViewProfile page and Home page data matches","ViewProfile page and Home page data doesn't match",STATUS.FAIL,takeSnapShot());
        }else {
            GemTestReporter.addTestStep("Verify if ViewProfile page and Home page data matches","ViewProfile page and Home page data matches",STATUS.PASS,takeSnapShot());
        }
    }

    @When("Click on edit certification icon")
    //<!--Function to click on edit post icon in Certification profile component-->
    public void editPost() {
        if(isExist(By.xpath("//img[@alt='editpost']"))){
            click(By.xpath("//img[@alt='editpost']"),"Click on edit certification","Clicked on edit certification");
        } else {
            GemTestReporter.addTestStep("Verify if any certification exist", "No certification data exist", STATUS.INFO, takeSnapShot());
        }
    }

    @Then("Click on delete icon and refresh page")
    //<!--Function to click on delete button in profile component and refresh entire page-->
    public void deleteIcon() {
        if (isExist(By.xpath("//img[@alt='delete']"))) {
            click(By.xpath("//img[@alt='delete']"),"Click on delete icon","Clicked on delete icon");
            waitSec(3);
            click(By.xpath("//button[text()='Yes']"),"Confirm deletion","Confirmed deletion");
            waitSec(2);
        } else {
            GemTestReporter.addTestStep("Verify if any data exist in profile component", "No data exist in profile component", STATUS.INFO, takeSnapShot());
        }
        String popupText =  getElementText(By.xpath("//div[@role='alert']"));
        GemTestReporter.addTestStep("Verify popup text","Text in popup is: "+popupText,STATUS.PASS,takeSnapShot());
        refresh(); // refresh page
    }


    @When("Click on edit projects icon")
    //<!--Function to click on edit projects icon in Projects profile component-->
    public void editProject() {
        if (isExist(By.xpath("//img[@alt='Edit button']"))) {
            click(By.xpath("//img[@alt='Edit button']"),"Click on edit projects","Clicked on edit projects");
        } else {
            GemTestReporter.addTestStep("Verify if any projects exist", "No projects data exist", STATUS.INFO, takeSnapShot());
        }
    }

    @When("Click on Navigation menu icon")
    //<!--Function to click on Navigation menu icon in home page-->
    public void navigationMenuIcon() {
        click(Profile_Locators.navigationMenuIcon,"Close Navigation Menu","Navigation Menu closed");
        waitSec(3);
        click(Profile_Locators.navigationMenuIcon,"Click on Navigation Menu icon","Clicked on Navigation Menu icon");
    }

    @Then("Verify if Navigation tab is opened")
    //<!--Function to verify if Navigation menu is opened-->
    public void navigationTab() {
        if(isExist(Login_Locators.logoHeader)){
            GemTestReporter.addTestStep("Verify if Navigation Menu is visible","Navigation menu is visible",STATUS.PASS,takeSnapShot());
        }else {
            GemTestReporter.addTestStep("Verify if Navigation Menu is visible","Navigation menu is not visible",STATUS.FAIL,takeSnapShot());
        }
    }

    @Then("Click on Expansion icon in Navigation menu")
    //<!--Function to select Other Portals expansion icon-->
    public void expansionIcon() {
        click(By.xpath("//img[@alt='arrow']"),"Click on expansion icon","Clicked on Expansion icon");
        waitSec(4);
        click(By.xpath("//img[@alt='arrow']"),"Expand Other Portals in Navigation menu","Expanded Other Portals in Navigation menu");
    }

    @And("Verify if Other Portals is expanded")
    //<!--Function to verify if Other Portals is expanded-->
    public void otherPortalsExpanded() {
        if(isExist(By.xpath("//img[@alt='menu icon']"))){
            GemTestReporter.addTestStep("Verify if Other Portals is expanded in Navigation menu","Other Portals is expanded in Navigation menu",STATUS.PASS,takeSnapShot());
        }else{
            GemTestReporter.addTestStep("Verify if Other Portals is expanded in Navigation menu","Other Portals is not expanded in Navigation menu",STATUS.FAIL,takeSnapShot());
        }
    }

    @Then("Fetch data of all Portals in Navigation Menu")
    //<!--Function to get all data in Navigation Menu-->
    public void fetchAllPortals() {
        List<WebElement> allPortals = DriverAction.getElements(By.xpath("//span[@class='sidebar_title__PeP6B']"));
        for(WebElement i : allPortals){
            String portalTitle = getElementText(i);
            GemTestReporter.addTestStep("Get the Portal title from Navigation Menu"," Portal title from Navigation Menu is: "+portalTitle,STATUS.INFO,takeSnapShot());
        }

    }

    @And("click on close button in {string}")
    //<!--Function to select close button in profile component-->
    public void closeButton(String profileComponent) {
         if(profileComponent.contains("Certification")){
            waitUntilElementAppear(By.xpath("//img[@alt='close']"),5);
            click(By.xpath("//img[@alt='close']"),"Click on close button in Certification of profile component","Clicked on close button in projects of Certification component");
        } else{
             waitUntilElementAppear(By.xpath("//img[@alt='closeButton']"),5);
             click(By.xpath("//img[@alt='closeButton']"),"Click on close button in projects of profile component","Clicked on close button in projects of profile component");
         }
    }

    @Then("Verify if any alert is displayed")
    //<!--Function to verify all fields required popup-->
    public void alertDisplayed() {
        waitUntilElementAppear(By.xpath("//div[@class='Toastify__toast-body']"),10);
        String failedAlertText = getElementText(By.xpath("//div[@class='Toastify__toast-body']"));
        GemTestReporter.addTestStep("Verify if any alert is displayed", "Failed alert is displayed as: "+failedAlertText, STATUS.PASS, takeSnapShot());
        waitSec(3);
    }
}



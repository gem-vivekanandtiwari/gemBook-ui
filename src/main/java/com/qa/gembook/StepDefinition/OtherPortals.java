package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.GembookUtilities.OtherPortalsUtils;
import com.qa.gembook.Locators.SideBar_Locators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class OtherPortals extends DriverAction {

    @Given("Check if Other Portals is present in side bar")
    public void isOtherPortalsPresent() {
        OtherPortalsUtils.isElementDisplayed("Verify presence of sidebar", "Sidebar is present", "Sidebar is not present", SideBar_Locators.sideBarIcon);
        OtherPortalsUtils.isElementDisplayed("Verify presence of Other Portals", "Other Portals is present in side bar", "Other Portals is not present in side bar", SideBar_Locators.otherPortals);
    }

    //---------Verify if all elements in OtherPortals are present-------
    @Then("Check if all sub items are present")
    public void checkIfAllSubItemsArePresent() {
        waitSec(3);
        String subItems = "";
        try {
            subItems = OtherPortalsUtils.getsubItemsList();//get list from config.properties
        } catch (Exception e) {
            GemTestReporter.addTestStep("Fetch list of items visible on screen", "Unable to fetch list", STATUS.FAIL, takeSnapShot());
        }
        String subItemsUI = OtherPortalsUtils.getsubItemsListUI();//get list from UI

        if (subItemsUI.equals(subItems)) { // check if elements in both array are equal
            GemTestReporter.addTestStep("Check for the elements of Other Portals", "All elements are present. List of elements present on ui contains " + subItems + " items.", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Check for the elements of Other Portals", "All elements are not present", STATUS.FAIL, takeSnapShot());
    }

    @Then("Check if sub menu items are hidden")
    public void checkIfSubMenuItemsAreGone() {
        OtherPortalsUtils.checkAllItems();//check for the presence of each sub menu item of Other Portals
    }

    //---------- click on given menu Item--------
    @Then("^User clicks on the \"([^\"]*)\" menu item$")
    public void userClicksOnTheMenuItem(String subItem) {
        OtherPortalsUtils.clickIfVisible(SideBar_Locators.navigationTab(subItem), "Click on " + subItem);
    }

    //----------Verify the position of arrow icon beside the OtherPortals----------
    @Then("^Verify the position of other Portals icon is \"([^\"]*)\"$")
    public void verifyThePositionOfOtherPortalsIconIs(String posOfIcon) {
        OtherPortalsUtils.verifyIconPosition(posOfIcon);//verify if arrow icon is pointing down or right
    }

    @Then("^Verify if user is navigated to \"([^\"]*)\" having \"([^\"]*)\" with \"([^\"]*)\"$")
    public void verifyIfUserIsNavigatedToWith(String page, String url, String title) {
        OtherPortalsUtils.verifyURL(url); // verify the url of navigated page
        OtherPortalsUtils.verifyTitle(page, title); //verify title of navigated page
        OtherPortalsUtils.verifyScreenItems(page);//verify items like header logged in credential
    }

    //-----------Verify the icon of given OtherPortalsItem-------
    @Then("^Check if \"([^\"]*)\" icon is present$")
    public void checkIfIconIsPresent(String sidebarItem) {
        if (OtherPortalsUtils.isElementVisible(SideBar_Locators.getIcon(sidebarItem))) {
            GemTestReporter.addTestStep("Verify the presence of " + sidebarItem + " icon", sidebarItem + " icon is present", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify the presence of " + sidebarItem + " icon", sidebarItem + " icon is not present", STATUS.FAIL, takeSnapShot());
    }

    //----------Hover over the given element in OtherPortals-------
    @Then("^Hover over the \"([^\"]*)\" in side bar$")
    public void hoverOverTheInSideBar(String subMenuItem) {
        if (OtherPortalsUtils.isElementVisible(SideBar_Locators.navigationTab(subMenuItem))) {
            hoverOver(SideBar_Locators.navigationTab(subMenuItem));
            GemTestReporter.addTestStep("Hover over the " + subMenuItem + " item", "Hover successfully", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Hover over the " + subMenuItem + " item", "Hover unsuccessful", STATUS.FAIL, takeSnapShot());
    }

    //----------Verify the url displayed at the bottom of the screen ----------
    @Then("^Verify the URL of \"([^\"]*)\" at the bottom of the screen \"([^\"]*)\"$")
    public void verifyTheURLOfAtTheBottomOfTheScreen(String subMenuItem, String urlOfItem) {
        String urlUI = getAttributeName(SideBar_Locators.navigationTabAnchor(subMenuItem), "href");
        if (urlUI.equalsIgnoreCase(urlOfItem)) {
            GemTestReporter.addTestStep("Verify the url displayed at the bottom of screen", "URL matched. Expected url: " + urlOfItem, STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify the url displayed at the bottom of scree", "Url not match. Expected url :" + urlOfItem + " and observed:" + urlUI, STATUS.FAIL, takeSnapShot());
    }
}

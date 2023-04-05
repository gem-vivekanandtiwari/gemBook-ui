package com.qa.gembook.GembookUtilities;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.Locators.SideBar_Locators;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OtherPortalsUtils extends DriverAction {
    /**
     * @param locator Takes xpath or locator of an element
     * @return boolean  returns whether the element is visible on screen or not
     * @author Karan Singh Thakur
     * @since 17th Feb, 2023
     */
    public static boolean isElementVisible(By locator) {
        try {
            return isExist(locator) && DriverManager.getWebDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify if element is visible", "Element is not visible.", STATUS.FAIL, takeSnapShot());
            return false;
        }

    }

    /**
     * @param title    title for the test step in reporting
     * @param failDesc fail description for test step in reporting
     * @param passDesc pass description for test step in reporting
     * @param locator  xpath or locator of the element
     * @author Karan Singh Thakur
     * @since created on 17th Feb, 2023
     */
    public static void isElementDisplayed(String title, String passDesc, String failDesc, By locator) {
        if (isElementVisible(locator)) {
            GemTestReporter.addTestStep(title, passDesc, STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep(title, failDesc, STATUS.FAIL, takeSnapShot());
    }

    /**
     * @param locator  Takes xpath or locator of an element
     * @param duration take duration for how much time we need to wait
     * @return returns boolean whether element is clickable in between this interval of time
     * @author Karan Singh Thakur
     * @since 17th Feb,2023
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
     * @param elemLocator takes xpath or locator of an element
     * @param title       takes title for the test step to add in reporting
     * @author Karan Singh Thakur
     * @since 23rd Feb, 2023
     */
    public static void clickIfVisible(By elemLocator, String title) {
        waitUntilElementAppear(elemLocator, 5);
        if (OtherPortalsUtils.isElementClickable(elemLocator, 5)) {
            click(elemLocator);
        } else
            GemTestReporter.addTestStep(title, "Click action is not performed", STATUS.FAIL, takeSnapShot());
    }

    /**
     * @return It returns void only reads property from config.properties file
     * @throws IOException it throws IOException
     * @author Karan Singh Thakur
     * @since 23rd Feb, 2023
     */
    public static String getsubItemsList() throws IOException {
        return GembookUtils.readProperties("otherPortalsItems");
    }

    /**
     * @return It returns String containing the list of subMenuItems present on UI
     * @author Karan Singh Thakur
     * @since 27th Feb,2023
     */
    public static String getsubItemsListUI() {
        List<WebElement> subItemsUI = getElements(SideBar_Locators.sidebarList);
        int startingItem = 0;
        int skipCount = 0;
        for (WebElement item : subItemsUI) {
            if (item.getText().equalsIgnoreCase("Other Portals")) {
                startingItem = skipCount;
                break;
            }
            skipCount++;
        }
        List<String> subItemsText = new ArrayList<>();
        for (int i = startingItem + 1; i < subItemsUI.size(); i++) {
            subItemsText.add(subItemsUI.get(i).getText());
        }
        return subItemsText.toString();
    }

    /**
     * @author Karan Singh Thakur
     * @description checks Whether the list in UI matches with List given and add test step
     * @since 27th Feb,2023
     */
    //----function to check whether all elements within otherPortals are present in ui----
    public static void checkAllItems() {
        String subItems = "";
        try {
            subItems = OtherPortalsUtils.getsubItemsList();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Fetch list of items visible on screen", "Unable to fetch list", STATUS.FAIL, takeSnapShot());
        }
        String[] subItemList = subItems.substring(1, subItems.length() - 1).split(",");
        boolean isPresent = true;
        for (String subItem : subItemList) {
            if (isExist(SideBar_Locators.navigationTab(subItem))) {
                GemTestReporter.addTestStep("Verify if " + subItem + " is hidden", subItem + " is not hidden", STATUS.FAIL, takeSnapShot());
                isPresent = false;
                break;
            }
        }
        if (isPresent)
            GemTestReporter.addTestStep("Verify if " + subItems + " are hidden in UI", subItems + " are hidden", STATUS.PASS, takeSnapShot());
    }

    /**
     * @author Karan Singh Thakur
     * @description Verify the positions of icon with respect to OtherPortals
     * @since 27th Feb,2023
     */
    public static void verifyIconPosition(String posOfIcon) {
        if (posOfIcon.equalsIgnoreCase("down") && SideBar_Locators.arrowIconDown.toString().contains("rotate-90")) {
            GemTestReporter.addTestStep("Position of Other Portals icon", "Other Portals icon is pointed down", STATUS.PASS, takeSnapShot());
        } else if (posOfIcon.equalsIgnoreCase("right") && isExist(SideBar_Locators.arrowIconRight)) {
            GemTestReporter.addTestStep("Position of Other Portals icon", "Other Portals icon is pointed right", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Position of Other Portals icon", "Position of icon is incorrect", STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * @return Verify if user is navigated to new page r tab by getting list of tabs and comparing the current one URL with previous tab URL
     * @author Karan Singh Thakur
     * @since 27th Feb,2023
     */
    //function to navigate to new tabs
    public static String verifyIfNavigatedToNewTab() {
        String prevPageUrl = "";
        String nextPageUrl = "";
        try {
            List<String> browserWindows = new ArrayList<>(getWindowHandles());
            switchToWindow(browserWindows.get(0));
            prevPageUrl = getCurrentURL();
            waitSec(4);
            switchToWindow(browserWindows.get(1));
            nextPageUrl = getCurrentURL();
        } catch (TimeoutException | NoSuchWindowException e) {
            GemTestReporter.addTestStep("Verify if user is navigated to new tab", "Unable to fetch new tab", STATUS.FAIL, takeSnapShot());
        }

        if (!prevPageUrl.equalsIgnoreCase(nextPageUrl)) {
            GemTestReporter.addTestStep("Verify if user is navigated to new tab", "User is navigated to new page", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify if user is navigated to new tab", "User in not navigated to new page", STATUS.FAIL, takeSnapShot());
        return nextPageUrl;
    }

    /**
     * @param url Takes URL of the page and compares it with previous after navigated to new page
     * @author Karan Singh Thakur
     * @since 27th Feb, 2023
     */

    //----------Verify url of the new page---------
    public static void verifyURL(String url) {
        waitSec(2);
        String nextPageUrl = verifyIfNavigatedToNewTab();
        if (nextPageUrl.equalsIgnoreCase(url)) {
            GemTestReporter.addTestStep("Verify if url matches the expected url " + url, "Url matched successfully", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify if url matches the expected url", "Url don't match, Expected:" + url + ". Observed " + nextPageUrl, STATUS.FAIL, takeSnapShot());
    }

    /**
     * @param title takes title to add in the step of
     * @param page  Takes the name of the page whose title has to be verified
     * @author Karan Singh Thakur
     * @since 27th Feb,2023
     */
    public static void verifyTitle(String page, String title) {
        waitSec(2);
        if (isExist(SideBar_Locators.getTitle(title)) && title.equalsIgnoreCase(getAttributeName(SideBar_Locators.getTitle(title), "text"))) {
            GemTestReporter.addTestStep("Verify title of " + page + " page", "Title matched expected title: " + title, STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify title of " + page + " page", "Title do not match, Expected:" + title + " .Observed:" + getAttributeName(SideBar_Locators.getTitle(page), "text"), STATUS.FAIL, takeSnapShot());
    }

    /**
     * @param page Takes name of the page of which we have to navigate and verify screen items
     * @author Karan Singh Thakur
     * @since 27th Feb,2023
     */
    public static void verifyScreenItems(String page) {
        switch (page) {
            case "Jenkins":
                verifyElemsOfPage(SideBar_Locators.jenkinsHeader, SideBar_Locators.jenkinsCred, page);
                break;
            case "Service Desk":
                verifyElemsOfPage(SideBar_Locators.serviceDeskIcon, SideBar_Locators.loginBtnViaSSO, page);
                break;
            case "Azure":
                verifyElemsOfPage(SideBar_Locators.azureAccountBtn, SideBar_Locators.azureAccountDetail, page);
                break;
            case "Contripoint":
                verifyElemsOfPage(SideBar_Locators.pageLogoHeader, SideBar_Locators.contriPointBtn, page);
                break;
            case "MIS":
                verifyElemsOfPage(SideBar_Locators.pageLogoHeader, SideBar_Locators.loginBtnViaSSO, page);
                break;
            case "GreytHR":
                verifyElemsOfPage(SideBar_Locators.greyTHrHeader, SideBar_Locators.pageLoginBtn, page);
                break;
            case "Github":
                verifyElemsOfPage(SideBar_Locators.gitHubGeminiHeader, SideBar_Locators.gitHubInput, page);
                break;
            case "Gem Wiki":
                verifyElemsOfPage(SideBar_Locators.gemWikiHeader, SideBar_Locators.gemWikiAzure, page);
                break;
            case "Athena":
                verifyElemsOfPage(SideBar_Locators.athenaHeader, SideBar_Locators.athenaLogin, page);
                break;
            case "LMS":
                verifyElemsOfPage(SideBar_Locators.lmsHeader, SideBar_Locators.lmsBtn, page);
                break;
            default:
                GemTestReporter.addTestStep("Verify the items on screen", "This page is not present", STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * @param headerLocator takes header of the page
     * @param page          take page name to which we need to navigate
     * @param loginLocator  take login credentials from which we have to Log in
     * @author Karan Singh Thakur
     * @since 27th Feb,2023
     */
    private static void verifyElemsOfPage(By headerLocator, By loginLocator, String page) {
        if (isExist(headerLocator) && isExist(loginLocator)) {
            GemTestReporter.addTestStep("Verify items of " + page + " page", "Header and login button are present in " + page + " page", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify items of " + page + " page", "Some items are missing in page", STATUS.FAIL, takeSnapShot());
    }

    /**
     * @param subMenuItem     takes subMenu items name of which we have to get tooltip
     * @param menuItemToolTip takes tooltip and matches with the tooltip on UI
     * @author Karan Singh Thakur
     * @since 27th Feb, 2023
     */

    @Then("^Verify the tooltip of \"([^\"]*)\" having \"([^\"]*)\" as tooltip$")
    public void verifyTheTooltipOfHavingAsTooltip(String subMenuItem, String menuItemToolTip) {
        String toolTipUI = OtherPortalsUtils.getToolTip(subMenuItem);//get tooltip from UI
        verifyToolTip(toolTipUI, menuItemToolTip, subMenuItem);//verify it with given tooltip
        waitSec(3);
    }

    /**
     * @param toolTipUI       gets the tooltip from UI
     * @param menuItemToolTip gets the tooltip given in scenario and checks whether they both are equal or not
     * @param subMenuItem     takes the name of sub menu item whose tooltip has to be verified
     * @author Karan Singh Thakur
     * @since 27th Feb, 2023
     */
    private void verifyToolTip(String toolTipUI, String menuItemToolTip, String subMenuItem) {
        if (toolTipUI.equalsIgnoreCase(menuItemToolTip)) {
            waitSec(1);
            GemTestReporter.addTestStep("Verify tooltip of " + subMenuItem + " sub menu item", "Tooltip Expected: " + menuItemToolTip + ". Tooltip Observed: " + toolTipUI, STATUS.PASS, takeSnapShot());
        } else {
            waitSec(1);
            GemTestReporter.addTestStep("Verify tooltip of " + subMenuItem + " sub menu item", "Tooltip Expected: " + menuItemToolTip + ". Tooltip Observed: " + toolTipUI, STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * @param subMenuItem takes the name of sub menu item whose tooltip has to be verified
     * @return It returns tooltip from Ui for a specific subMenu Item in the form of String
     * @author Karan Singh Thakur
     * @since 27th Feb, 2023
     */
    private static String getToolTip(String subMenuItem) {
        String tooltip = "";
        try {
            tooltip = DriverManager.getWebDriver().findElement(SideBar_Locators.navigationTab(subMenuItem, "/ancestor::a")).getAttribute("title");
        } catch (NoSuchElementException e) {
            GemTestReporter.addTestStep("Verify tooltip of " + subMenuItem + " element", "Element is not visible", STATUS.FAIL, takeSnapShot());
        }
        hoverOver(SideBar_Locators.navigationTab(subMenuItem));
        return tooltip;
    }

    //-----------Verify if user is Navigated to different tabs------------
    @Then("Verify if user is navigated to other tab")
    public void verifyUserIsNavigated() {
        String currentHandle = DriverManager.getWebDriver().getWindowHandle();
        try {
            for (String handle : DriverManager.getWebDriver().getWindowHandles()) {
                if (!handle.equals(currentHandle)) {
                    DriverManager.getWebDriver().switchTo().window(handle);
                    break;
                }
            }
            DriverManager.getWebDriver().switchTo().window(currentHandle);
        } catch (TimeoutException | NoSuchWindowException e) {
            GemTestReporter.addTestStep("Verify if user is navigated to new tab", "Unable to fetch new tab", STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * @param locator    takes xpath or locator of the element
     * @param awardYear  takes Year in which year award was given
     * @param awardEvent takes Event for which event award was given and scrolls through the list of winners for that event
     * @author Karan Singh Thakur
     * @since 27th Feb, 2023
     */

    //-------------function to scroll through the list of elements-----------
    public static void scrollThroughSection(By locator, String awardYear, String awardEvent) {
        waitSec(2);
        List<WebElement> listOfWebElements = getElements(locator);
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            if (listOfWebElements.size() > 2) {
                for (WebElement element : listOfWebElements) {
                    // Check if element is visible or not
                    if (!(Boolean) js.executeScript("var elem = arguments[0],                 "
                            + "  box = elem.getBoundingClientRect(), "
                            + "  cx = box.left + box.width / 2,         "
                            + "  cy = box.top + box.height / 2,         "
                            + "  e = document.elementFromPoint(cx, cy);"
                            + "for (; e; e = e.parentElement) {         "
                            + "  if (e === elem)                        "
                            + "    return true;                          "
                            + "}                                         "
                            + "return false;                             ", element)) {
                        // If element is not visible, scroll to it
                        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
                    }
                    waitSec(1);
                }
                //verify the last element of the list is displayed on screen
                if (listOfWebElements.get(listOfWebElements.size() - 1).isDisplayed()) { //if last element is displayed on screen return true
                    GemTestReporter.addTestStep("Scroll through the list of winners of year " + awardYear + " and event " + awardEvent, "Successfully scrolled through the list of winners", STATUS.PASS, takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Scroll through the list of winners of year " + awardYear + " and event " + awardEvent, "Unable to scroll through the list of elements", STATUS.FAIL, takeSnapShot());
            }
        } catch (NoSuchElementException e) {
            GemTestReporter.addTestStep("Scroll through the list of winners of year " + awardYear + " and event " + awardEvent, "Not able to find the list of winners for " + awardEvent, STATUS.FAIL, takeSnapShot());
        } catch (Exception e) {
            System.out.println("Unable to list of winners. Error is: " + e.getMessage());
        }
    }

}

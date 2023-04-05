package com.qa.gembook.GembookUtilities;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.Locators.Events_Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class EventsUtils extends DriverAction {

    /**
     * @param locator  Takes the xpath or any locator to perform the operation
     * @param title    Takes string for the title of the step
     * @param passDesc Takes string for the pass description of the step
     * @param failDesc Takes string for the fail description of the step
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    //---------------- Reporting added for visibility of Element ------------------------
    public static void isVisible(By locator, String title, String passDesc, String failDesc) {
        try {
            waitForElement(locator, 10);
            GemTestReporter.addTestStep(title, passDesc, STATUS.PASS, takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep(title, failDesc, STATUS.FAIL, takeSnapShot());
        }
    }

    /**
     * @param date    Takes the date as a string input to compare
     * @param tabName Takes the tabName of the Events Section
     * @return boolean : whether the date is before or after current date
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    // ------------------ Date Comparison for Events Tabs ------------------
    public static boolean compareDate(String date, String tabName) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
        Date now = new Date();
        Date eventDate = sdf.parse(date), todaysDate = sdf.parse(sdf.format(now));
        int result = todaysDate.compareTo(eventDate);
        if (tabName.contains("Ongoing")) {
            if (result <= 0) {
                return true;
            }
            return false;
        }
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param key Takes the key to be pressed
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    //------------------- Key Press using Robot Class ----------------
    public static void keyPress(int key) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(key);
    }

    /**
     * @param key Takes the key to be released
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    // ------------------ Release the pressed key --------------------
    public static void keyRelease(int key) throws AWTException {
        Robot robot = new Robot();
        robot.keyRelease(key);
    }

    /**
     * @param querySelector Take the query as a String on which scroll should take place
     * @param xOffset       Takes the x offset to scroll horizontally
     * @param yOffset       Takes the y offset to scroll vertically
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    // ------------------- Using scrollbar inside an elements ---------------
    public static void scrollInsideElement(String querySelector, int xOffset, int yOffset) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
        js.executeScript("document.querySelector('" + querySelector + "').scrollBy(" + xOffset + "," + yOffset + ")");
    }

    /**
     * @param querySelector Take the query as a String for which we want to find scroll position
     * @return Return Long: Y Offsetof the scroll position
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    // ----------------- Get the y offset after Scroll -------------------
    public static Long getScrollPosEventList(String querySelector) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
        Long yOffset = (Long) js.executeScript("return document.querySelector('" + querySelector + "').scrollTop;");
        return yOffset;
    }

    /**
     * @param locator  Takes the xpath or any locator to perform the operation
     * @param duration Takes the integer number to wait for the element to be visible
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    // ------------------ Wait until the element is visible -------------------------
    public static void waitForElement(By locator, int duration) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) duration));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * @param locator  Takes the xpath or any locator to perform the operation
     * @param duration Takes int to wait until the element becomes visible
     * @author Rahul tagra
     * @since 23th Feb,2023
     */
    //---------------- Reporting added for visibility of Element ------------------------
    public static boolean isElementVisible(By locator, int duration) {
        try {
            waitForElement(locator, duration);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param locator  Takes the xpath or any locator to perform the operation
     * @param elementLabel Takes string to enter into the description
     * @author Rahul tagra
     * @since 15th March,2023
     */
    public static void scrollIntoView(By locator, String elementLabel) {
        WebElement element = getElement(locator);
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
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
            if (element.isDisplayed()) {
                GemTestReporter.addTestStep("Scroll element into view : " + elementLabel, "Element successfully scrolled into view : " + elementLabel, STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Scroll element into view : " + elementLabel, "Element did not scroll into view : " + elementLabel, STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Scroll element into view : " + elementLabel, "Element did not scroll into view : " + elementLabel, STATUS.FAIL, takeSnapShot());
        }
    }
}

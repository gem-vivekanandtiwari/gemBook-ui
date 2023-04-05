package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.GembookUtilities.EventsUtils;
import com.qa.gembook.GembookUtilities.GembookUtils;
import com.qa.gembook.Locators.PostInteraction_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostInteraction extends DriverAction {

    Logger logger = LoggerFactory.getLogger(PostInteraction.class.getName());
    int beforeAction = 0, initialVisibleComments = 0;
    String username, activeImage;
    StringBuilder copyComment;
    int wait = 30;

    @Then("User checks the presence of posts on the News Feeds page")
    public void checkPostsPresence() {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.generalPostElements("Post Cards"), wait);
            GemTestReporter.addTestStep("Verify if posts are visible on the page", "Posts are visible on the page", STATUS.PASS, takeSnapShot());
        } catch (Exception exception) {
            if (isExist(PostInteraction_Locators.generalPostElements("No Post Message"))) {
                GemTestReporter.addTestStep("Verify if posts are visible on the page", "There are no posts available", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify if posts are visible on the page", "Posts are not visible on the page", STATUS.FAIL, takeSnapShot());
            }
            throw exception;

        }
    }

    @And("User verifies the presence of necessary elements of the posts")
    public void impPostElements() {
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Profile Picture"), "Verify if Profile Image of the user is visible inside the post", "Yes, the profile image is visible", "No, the profile image did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Profile Name"), "Verify if Profile Name of the user is visible inside the post", "Yes, the profile name is visible", "No, the profile name did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Post Content"), "Verify if post content is visible inside the post", "Yes, the post content is visible", "No, the post content did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Comment Button"), "Verify if Comment Button is visible inside the post", "Yes, the Comment button is visible", "No, the Comment button did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Like Button"), "Verify if like Button is visible inside the post", "Yes, the Like Button is visible", "No, the Like Button did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Like Count"), "Verify if like Count is visible inside the post", "Yes, the Like Count is visible", "No, the Like Count did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Comment Count"), "Verify if Comment count is visible inside the post", "Yes, the Comment count is visible", "No, the Comment count did not appear");
        EventsUtils.isVisible(PostInteraction_Locators.postTypeIcon, "Verify if post type icon is visible inside the post", "Yes, the post type icon is visible", "No, the post type icon did not appear");
    }

    @And("User checks if post filter dropdown is present or not")
    public void checkPostFilterPresence() {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.generalPostElements("Post Filter Dropdown"), wait);
            GemTestReporter.addTestStep("Verify is post filter dropdown is preset or not", "Post filter dropdown is present on the page", STATUS.PASS, takeSnapShot());
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify is post filter dropdown is preset or not", "Post filter dropdown is not present on the page", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    @When("User expands the post filtering dropdown")
    public void expandPostFilter() throws ElementNotInteractableException {
        if (GembookUtils.isElementClickable(PostInteraction_Locators.generalPostElements("Post Filter Dropdown"), wait)) {
            click(PostInteraction_Locators.generalPostElements("Post Filter Dropdown"), "Post Filter Dropdown");
        } else {
            throw new ElementNotInteractableException("Post Filter Dropdown is Not Clickable at the moment");
        }
    }

    @Then("User verifies if dropdown expands or not and selects a {string}")
    public void selectPostType(String postType) {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postFilterList, wait);
            GemTestReporter.addTestStep("Verify if Post Filter Dropdown is Expanded", "Post Filter dropdown expands successfully", STATUS.PASS, takeSnapShot());
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if Post Filter Dropdown is Expanded", "Post Filter dropdown is unable to expand", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
        if (GembookUtils.isElementClickable(PostInteraction_Locators.postFilterOptions(postType), wait)) {
            click(PostInteraction_Locators.postFilterOptions(postType), postType + " post type");
        } else {
            GemTestReporter.addTestStep("Select " + postType + " post type from dropdown", postType + " can't be selected", STATUS.FAIL, takeSnapShot());
            throw new ElementNotInteractableException(postType + " postype option is not clickable at that moment");
        }
    }

    @And("User verifies if the post type updated to the selected {string} in dropdown")
    public void postFilter(String postType) throws Exception {
        if (getElementText(PostInteraction_Locators.generalPostElements("Post Filter Dropdown")).contains(postType)) {
            GemTestReporter.addTestStep("Verify if post type is updated to " + postType, "Post type is updated to " + postType, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify if post type is updated to " + postType, "Post type is not updated to " + postType, STATUS.FAIL, takeSnapShot());
        }
    }

    @And("User verifies the updated posts appear according to the selected {string}")
    public void postFilterChanges(String postType) {
        checkPostsPresence();
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postTypeIcon, wait);
            if (getAttributeName(PostInteraction_Locators.postTypeIcon, "title").equals(postType)) {
                GemTestReporter.addTestStep("Verify if posts appeared are of selected post type: " + postType, "Posts appearing are of selected post type: " + postType, STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify if posts appeared are of selected post type: " + postType, "Posts appearing are of not of selected post type: " + postType, STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if post type icon appears inside the post", "Post type icon did not appear", STATUS.FAIL, takeSnapShot());
        }
    }

    @When("User hits the {string} of the post")
    public void hitButton(String btn) {
        try {
            // ------------------------ Get Like Count Before Hitting Like Button ---------------------------------
            switch (btn) {
                case "Commenter Username":
                case "Like User Profile":
                case "Profile Name":
                    username = new String();
                    username = getElementText(PostInteraction_Locators.postElements(btn));
                    break;
                case "Like Button":
                    beforeAction = Integer.parseInt(getElementText(PostInteraction_Locators.postElements("Like Count")));
                    break;
                case "Like Comment Button":
                    beforeAction = Integer.parseInt(getElementText(PostInteraction_Locators.postElements("Likes on Comment")));
                    break;
                default:
                    beforeAction = Integer.parseInt(getElementText(PostInteraction_Locators.postElements("Comment Count")));
                    break;
            }
            EventsUtils.waitForElement(PostInteraction_Locators.postElements(btn), wait);
            if (!btn.contains("Like User Profile"))
                EventsUtils.scrollIntoView(PostInteraction_Locators.postElements(btn), btn + " button");
            if (GembookUtils.isElementClickable(PostInteraction_Locators.postElements(btn), wait)) {
                click(PostInteraction_Locators.postElements(btn), btn + " button");
                waitSec(5);
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if " + btn + " is appearing in the post", btn + " is not present inside te post", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("User verifies if the {string} {string} or not")
    public void verifyEffect(String count, String effect) {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements(count), wait);
            // ------------------------ Get Like Count After Hitting Like Button ---------------------------------
            int afterAction = Integer.parseInt(getElementText(PostInteraction_Locators.postElements(count)));
            switch (effect) {
                case "increases":
                    if (afterAction == beforeAction + 1 && afterAction > beforeAction) {
                        GemTestReporter.addTestStep("Verify if " + count + " " + effect + " on hitting like Button", count + " before: " + beforeAction + " " + count + " after: " + afterAction, STATUS.PASS, takeSnapShot());
                        logger.info("Like Count Increases");
                    } else {
                        GemTestReporter.addTestStep("Verify if " + count + " " + effect + " on hitting like Button", count + " before: " + beforeAction + " " + count + " after: " + afterAction, STATUS.FAIL, takeSnapShot());
                    }
                    break;
                case "decreases":
                    if (afterAction == beforeAction - 1 && afterAction < beforeAction) {
                        GemTestReporter.addTestStep("Verify if " + count + " " + effect + " on hitting like Button", count + " before: " + beforeAction + " " + count + " after: " + afterAction, STATUS.PASS, takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if " + count + " " + effect + " on hitting like Button", count + " before: " + beforeAction + " " + count + " after: " + afterAction, STATUS.FAIL, takeSnapShot());
                    }
                    break;
                default:
                    logger.error("No Other Operations Takes Place");
                    break;
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if " + count + " is present or not", count + " is not present inside the post", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Verify if the comment box shows up or not")
    public void checkCommentBoxPresence() {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements("Comment Box"), wait);
            waitSec(3);
            GemTestReporter.addTestStep("Verify if Comment Box is Visible after hitting comment Button", "Yes, comment box is visible", STATUS.PASS, takeSnapShot());
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if Comment Box is Visible after hitting comment Button", "No, comment box is not visible", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    @Then("User hits the {string} button")
    public void viewComments(String btn) {
        try {
            // ------------------------ Get Count of Visible Comments Before Hitting Button ---------------------------------
            initialVisibleComments = getElements(PostInteraction_Locators.postElements("User Comments")).size();
            EventsUtils.waitForElement(PostInteraction_Locators.postElements(btn), wait);
            if (GembookUtils.isElementClickable(PostInteraction_Locators.postElements(btn), wait)) {
                click(PostInteraction_Locators.postElements(btn), btn + " button");
                waitSec(3);
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if " + btn + " button is appearing in the post", btn + " button is not present inside te post", STATUS.FAIL, takeSnapShot());
        }
    }

    @And("User checks if the visible comments {string} on hitting {string} button")
    public void checkNoOfVisibleComments(String effect, String btn) {
        // ------------------------ Get Count of Visible Comments After Hitting Button ---------------------------------
        int visibleCommentAfter = getElements(PostInteraction_Locators.postElements("User Comments")).size();
        switch (effect) {
            case "increases":
                if (visibleCommentAfter > initialVisibleComments) {
                    GemTestReporter.addTestStep("Verify if Visible Comment " + effect + " on hitting " + btn + " Button", "Visible Comment Before: " + initialVisibleComments + " Visible Comment After: " + visibleCommentAfter, STATUS.PASS, takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify if Visible Comment " + effect + " on hitting " + btn + " Button", "Visible Comment Before: " + initialVisibleComments + " Visible Comment After: " + visibleCommentAfter, STATUS.FAIL, takeSnapShot());
                }
                break;
            case "decreases":
                if (visibleCommentAfter < initialVisibleComments) {
                    GemTestReporter.addTestStep("Verify if Visible Comment " + effect + " on hitting " + btn + " Button", "Visible Comment Before: " + initialVisibleComments + " Visible Comment After: " + visibleCommentAfter, STATUS.PASS, takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify if Visible Comment " + effect + " on hitting " + btn + " Button", "Visible Comment Before: " + initialVisibleComments + " Visible Comment After: " + visibleCommentAfter, STATUS.FAIL, takeSnapShot());
                }
                break;
            default:
                logger.error("No Other Operations Takes Place");
                break;
        }
    }

    @Then("User types the {string} comment in the comment box")
    public void enterComment(String comment) {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements("Add Comment"), wait);
            typeText(PostInteraction_Locators.postElements("Add Comment"), comment);
            if (getAttributeName(PostInteraction_Locators.postElements("Add Comment"), "value").equals(comment)) {
                GemTestReporter.addTestStep("Verify if Comment is Typed inside the input Box", "Comment Typed Successfully", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify if Comment is Typed inside the input Box", "Comment not entered", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if Input Comment appears", "Comment input doesn't appear", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }


    @Then("Check if the profile of the clicked user opens up or not")
    public void checkUserProfile() {
        try {
            List<String> browserWindows = new ArrayList<>(getWindowHandles()); // Get all browser windows
            if (browserWindows.size() > 1) {
                GemTestReporter.addTestStep("Verify if new profile tab opens up or not", "Profile Tab Opens Up", STATUS.PASS, takeSnapShot());
                switchToWindow(browserWindows.get(1)); // Switch focus to 2nd browser window
                EventsUtils.waitForElement(PostInteraction_Locators.profileName, wait);
                if (username.equals(getElementText(PostInteraction_Locators.profileName))) {
                    GemTestReporter.addTestStep("Verify if the correct profile is opened or not", "Correct Profile is opened up", STATUS.PASS, takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify if the correct profile is opened or not", "Incorrect Profile opened", STATUS.FAIL, takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Verify if new profile tab opens up or not", "Profile Tab Not Opened", STATUS.FAIL, takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify if the correct profile is opened or not", "Incorrect Profile opened", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Verify if the emoji dialogue box appears or not")
    public void checkEmojiDialogueBoxPresence() {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements("Emoji Dialogue Box"), wait);
            if (isExist(PostInteraction_Locators.postElements("Emoji Dialogue Box"))) {
                GemTestReporter.addTestStep("Verify if the emoji dialogue box appears after clicking on emoji button", "Dialogue box appears on the screen", STATUS.PASS, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if the emoji dialogue box appears after clicking on emoji button", "Dialogue box did not appear on the screen", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    @Then("Verify if user is able to toggle between {string}")
    public void verifyToggleBetweenEmoji(String category) {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.emojiCategories(category), wait);
            if (GembookUtils.isElementClickable(PostInteraction_Locators.emojiCategories(category), wait)) {
                click(PostInteraction_Locators.emojiCategories(category), category + " emoji category");
                waitSec(3);
                checkEmojiDialogueBoxPresence();
                EventsUtils.isVisible(PostInteraction_Locators.emojiList(category), "Check if " + category + " Emoji category Comes in View", category + " category comes in view", category + " category did not come in view");
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check presence of " + category + " emoji category", category + " emoji category is not present", STATUS.FAIL, takeSnapShot());
        }
    }

    @When("User hover over the {string} icon")
    public void userHoverOverTheIcon(String icon) {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements(icon), wait);
            if (isExist(PostInteraction_Locators.postElements(icon))) {
                EventsUtils.scrollIntoView(PostInteraction_Locators.postElements(icon), "Like Count Icon");
                hoverOver(PostInteraction_Locators.postElements(icon));
                waitSec(3);
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check if Like Count icon appears on the screen", "Like Count icon did not appear", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    @Then("User checks if the dialogue box with users who like the post appears or not")
    public void checkLikeDialogueBoxPresence() {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements("Like Dialogue Box"), wait);
            EventsUtils.isVisible(PostInteraction_Locators.postElements("Like Dialogue Box"), "Verify if Like Dialogue Box appears on hovering over the like count", "Like Dialogue Box appears", "Like Dialogue box did not appear");
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Check if Like Dialogue Box Appears on the screen", "Like Dialogue Box Doesn't appear on the screen", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    @When("User copies the text from a {string}")
    public void userCopiesTheText(String path) {
        try {
            // Read the contents of the file
            BufferedReader reader = new BufferedReader(new FileReader(path));
            copyComment = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                copyComment.append(line);
            }
            reader.close();

            StringSelection selection = new StringSelection(copyComment.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);

            logger.info("File contents copied to clipboard.");
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage());
        }
    }

    @Then("User paste the text inside the comment box")
    public void pasteTextInCommentBox() throws AWTException {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements("Add Comment"), wait);
            if (GembookUtils.isElementClickable(PostInteraction_Locators.postElements("Add Comment"), wait)) {
                click(PostInteraction_Locators.postElements("Add Comment"), "Comment Box");
            }
            EventsUtils.keyPress(KeyEvent.VK_CONTROL);
            EventsUtils.keyPress(KeyEvent.VK_V);
            EventsUtils.keyRelease(KeyEvent.VK_CONTROL);
            EventsUtils.keyRelease(KeyEvent.VK_V);
            waitSec(3);
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if Input Comment appears", "Comment input doesn't appear", STATUS.FAIL, takeSnapShot());
            throw exception;
        }
    }

    @And("Verifies if the data is pasted inside the comment box or not")
    public void verifiesIfTheDataIsPasted() {
        if (getAttributeName(PostInteraction_Locators.postElements("Add Comment"), "value").equals(copyComment.toString())) {
            GemTestReporter.addTestStep("Verify if Copied Comment is pasted inside the input Box", "Copied Comment pasted Successfully", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify if Copied Comment is pasted inside the input Box", "Copied Comment not entered", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Verify if the other image comes in the view or not")
    public void verifyIfTheOtherImageComesInTheView() {
        try {
            EventsUtils.waitForElement(PostInteraction_Locators.postElements("Active Image"), wait);
            if (getAttributeName(PostInteraction_Locators.postElements("Active Image"), "src").equals(activeImage)) {
                GemTestReporter.addTestStep("Verify if the user is able to toggle multiple images inside the post", "User is unable to toggle images inside the post", STATUS.FAIL, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify if the user is able to toggle multiple images inside the post", "User is able to toggle images inside the post", STATUS.PASS, takeSnapShot());
            }
        } catch (Exception exception) {
            GemTestReporter.addTestStep("Verify if the user is able to toggle multiple images inside the post", "Image did not appear", STATUS.FAIL, takeSnapShot());
        }
    }

    @Then("Verify if the image gets expanded when user clicks on the image")
    public void imageExpands() {
        EventsUtils.isVisible(PostInteraction_Locators.postElements("Expand Image"), "Verify if image expands when user clicks on the image", "Image gets expanded when user clicks on image", "Image does not get expanded");
    }
}

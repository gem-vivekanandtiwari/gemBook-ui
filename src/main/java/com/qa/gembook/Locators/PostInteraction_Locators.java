package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class PostInteraction_Locators {

    public static By postFilterList         =       By.xpath("//div[@class='postContainer_dropDown__3OFc6 postContainer_filterDiv__2lCU- postContainer_active__2rlz4']//ul");

    public static By postTypeIcon           =       By.xpath("//img[@alt='postType']");

    public static By profileName            =       By.xpath("//div[contains(@class,'leftWrapper_employeeName')]//h4");

    public static By generalPostElements (String elementName) {
        By elementXpath = null;
        switch (elementName){
            case "Post Cards":
                elementXpath = By.xpath("//div[contains(@class,'postContainer_postBody')]");
                break;
            case "Post Filter Dropdown":
                elementXpath = By.xpath("//button[contains(@class,'postContainer_filterButton')]");
                break;
            case "No Post Message":
                elementXpath = By.xpath("//div[contains(@class,'postContainer_postError')]");
                break;
            default:
                System.out.println("No Such Element");
                break;
        }
        return elementXpath;
    }

    public static By postElements (String elementName) {
        String elementXpath = new String();

        switch (elementName){
            case "Profile Picture":
                elementXpath = "div[contains(@class,'postContainer_profilePicture')]";
                break;
            case "Profile Name":
                return By.xpath("//button[text()='Rahul Tagra']//parent::div[contains(@class,'postContainer_profileName')]//button[@class='postContainer_click__2nvKy']");
            case "Post Content":
                elementXpath = "div[contains(@class,'postContainer_postContent__9eiPM')]";
                break;
            case "Like Button":
                elementXpath = "button[contains(@class,'postContainer_postLikeBtn')]";
                break;
            case "Comment Button":
                elementXpath = "button[contains(@class,'postContainer_postCommentBtn')]";
                break;
            case "Like Count":
                elementXpath = "div[contains(@class,'postContainer_likeCount')]";
                break;
            case "Comment Count":
                elementXpath = "div[contains(@class,'postContainer_commentCount')]";
                break;
            case "Comment Box":
                elementXpath = "div[contains(@class,'postContainer_commentContainer')]";
                break;
            case "User Comments":
                elementXpath = "div[contains(@class,'postContainer_commentContainer')]//div[@class='d-flex py-2']";
                break;
            case "View More Comments":
                elementXpath = "div[contains(@class,'postContainer_commentContainer')]//b[contains(text(),'View More')]";
                break;
            case "View Less Comments":
                elementXpath = "div[contains(@class,'postContainer_commentContainer')]//b[contains(text(),'View Less')]";
                break;
            case "Add Comment":
                elementXpath = "input";
                break;
            case "Send Comment Button":
                elementXpath = "button[contains(@class,'postContainer_sendButton')]";
                break;
            case "Delete Comment Button":
                elementXpath = "span[contains(@class,'postContainer_commentDeleteBtn')]";
                break;
            case "Like Comment Button":
                elementXpath = "span[contains(@class,'postContainer_commentLikeBtn')]";
                break;
            case "Likes on Comment":
                elementXpath = "span[contains(@class,'postContainer_noOfLikes')]";
                break;
            case "Commenter Username":
                elementXpath = "div[contains(@class,'postContainer_commentBox')]//button[contains(@class,'postContainer_click')]";
                break;
            case "Emoji Button":
                elementXpath = "div[@id='button-addon1']";
                break;
            case "Like Dialogue Box":
                elementXpath = "div[contains(@class,'postContainer_dropdownContent')]";
                break;
            case "switchFocus":
                elementXpath = "span[text()='Like Reactions']";
                break;
            case "Like User Profile":
                elementXpath = "div[@class='postContainer_content__3Htqs']";
                break;
            case "Emoji Dialogue Box":
                elementXpath = "div[contains(@class,'postContainer_emojiContainer')]";
                break;
            case "Image Right Arrow Button":
                elementXpath = "a[@class='carousel-control-next']";
                break;
            case "Active Image":
                elementXpath = "div[@class='active carousel-item']//img";
                break;
            case "Expand Image":
                elementXpath = "div[@class='ImageModal_imageWrapper__22a58']//img";
                break;
            default:
                System.out.println("No Such Element");
                break;
        }


        return By.xpath("//button[text()='Rahul Tagra']//ancestor::div[contains(@class,'postContainer_postBody')]//" + elementXpath);
    }

    public static By postFilterOptions (String option) {
        return By.xpath("//div[@class='postContainer_dropDown__3OFc6 postContainer_filterDiv__2lCU- postContainer_active__2rlz4']//ul//li[text()='" + option + "']");
    }

    public static By emojiCategories (String category) {
        return By.xpath("//div[contains(@class,'postContainer_emojiContainer')]//nav[@class='emoji-categories']//button[contains(@class,'icn-"+category+"')]");
    }

    public static By emojiList (String category) {
        return By.xpath("//div[contains(@class,'postContainer_emojiContainer')]//ul[@class='emoji-group' and @data-name='"+category+"']");
    }

}

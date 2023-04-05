package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Birthday_Locator {
    public static By lastEntry(String screenName, Integer size) {
        if (screenName.contains("Birthday")) {
            return By.xpath("//div[@class='birthday-body']//child::div[@class='birthday-body-inner'][" + (size - 1) + "]//div[@class='date']");
        } else {
           return null;
        }
    }
    public static By birthdayTabNewTab(Integer row) {
        return By.xpath("(//div[@class='leftWrapper_detailDescription__3nzun'])[" + row + "]");
    }
    public static By newMembersEntriesList = By.xpath("//div[@class='member-body-inner']");
    public static By newTabOptions(String option) {
        if (option.contains("Birthday")) {
            return By.xpath("(//div[@class='birthday-body-inner'][1])");
        } else if (option.contains("Date of joining")) {
            return By.xpath("(//div[@class='birthday-body-inner'][2])");
        } else {
            return By.xpath("(//div[@class='birthday-body-inner'][3])");
        }
    }
    public static By rowsNewMembersSectionClick(String row) {
        return By.xpath("//div[@class='member-body-inner'][" + row + "]//*[@class='name']");
    }
    public static By bithdayScreenRow(Integer row) {
        return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]");
    }
    public static By dateMainScreen(String option, String row) {
        if (option.contains("Birthday")) {
            return By.xpath("//div[@class='birthday-body-inner'][" + row + "]//*[@class='date']");
        } else {
            return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]//*[@class='date']");
        }
    }
    public static By gembookLogo = By.xpath("//div[@class='logo_header__yvMXi']");
    public static By dateNewTab(String screen, Integer row) {
        if (screen.contains("Birthday")) {
            return By.xpath("(//div[@class='leftWrapper_detailDescription__3nzun'])[" + row + "]");
        } else {
            return null;
        }
    }
    public static By clickAnyEntry(String screenName,String row) {
        if (screenName.contains("Anniversary")){
            return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]//*[@class='name']");
        }
        else {
            return null;
        }
    }
//    public static By sectionName(String SectionName) {
//        if (SectionName.contains("Birthday")) {
//            return By.xpath("//span[text()='Birthday']");
//        } else if (SectionName.contains("Work Anniversary")) {
//            return By.xpath("//span[text()='Work Anniversary']");
//        } else {
//            return By.xpath("//span[text()='New Members']");
//        }
//    }
    public static By dateRowAtScreen(String option, String row) {
        if (option.contains("Birthday")) {
            return By.xpath("//div[@class='birthday-body-inner'][" + row + "]//*[@class='date']");
        } else {
            return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]//*[@class='date']");
        }
    }
    public static By rowAtScreen(String option, String row) {
        if (option.contains("Birthday")) {
            return By.xpath("//div[@class='birthday-body-inner'][" + row + "]//*[@class='name']");
        } else {
            return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]//*[@class='name']");
        }
    }
    public static By dateAtScreen(String option, String row) {
        if (option.contains("Birthday")) {
            return By.xpath("//div[@class='birthday-body-inner'][" + row + "]");
        } else {
            return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]");
        }
    }
    public static By screenName(String screen) {
        if (screen.contains("Birthday")) {
            return By.xpath("//div[@class='birthday-body']/div");
        } else if (screen.contains("Filter Button")) {
            return By.xpath("//img[@class='postContainer_img__1yUPy']");

        } else {
            return By.xpath("//div[@class='anniversary-body']/div");

        }
    }
//    public static By fetchText(String FieldName) {
//        if (FieldName.contains("Birthday")) {
//            return By.xpath("//*[contains(text(),'Birthday')]/following-sibling::div");
//        } else if (FieldName.contains("Joining Date")) {
//            return By.xpath("//*[contains(text(),'Joining Date')]/following-sibling::div");
//        } else {
//            return By.xpath("//*[contains(text(),'Hobbies')]/following-sibling::div");
//        }
//    }
    public static By validationFields(String section) {
        if (section.contains("EmailID")) {
            return By.xpath("//a[contains(text(), '@geminisolutions.com')]");
        } else if (section.contains("SKills")) {
            return By.xpath(" //h5[contains(text(), 'Skills')]");
        } else if (section.contains("Achievement")) {
            return By.xpath(" //h5[contains(text(), 'Achievement')]");
        } else if (section.contains("Gemtalk")) {
            return By.xpath("//h5[contains(text(), 'Gemtalk')]");
        } else if (section.contains("Project")) {
            return By.xpath("//h5[contains(text(), 'Project')]");
        } else {
            return By.xpath("//h5[contains(text(), 'Trainings')]");
        }
    }
    public static By filterType(String filterType) {
        if (filterType.contains("Achievements")) {
            return By.xpath("//div[@id='postContainer']/div[2]/ul/li[1]");
        } else if (filterType.contains("Announcements")) {
            return By.xpath("//div[@id='postContainer']/div[2]/ul/li[2]");
        } else if (filterType.contains("Questions")) {
            return By.xpath("//div[@id='postContainer']/div[2]/ul/li[3]");
        } else if (filterType.contains("Generic")) {
            return By.xpath("//div[@id='postContainer']/div[2]/ul/li[4]");
        } else if (filterType.contains("Ideas")) {
            return By.xpath("//div[@id='postContainer']/div[2]/ul/li[5]");
        } else if (filterType.contains("Findings")) {
            return By.xpath("//div[@id='postContainer']/div[2]/ul/li[6]");
        } else if (filterType.contains("All")) {
            return By.xpath("//li[contains(text(), 'All')]");
        }
        else {
            return null;
        }
    }
    public static By dateMainScreen(String screenName, Integer row) {
        if (screenName.contains("Birthday")) {
            return By.xpath("(//div[@class='birthday-body-inner'][" + row + "])");
        } else {
            return By.xpath("//div[@class='anniversary-body-inner'][" + row + "]");
        }
    }
    public static By workAnniversaryEntries = By.xpath("//div[@class='anniversary-body']/div");
    public static By filterSection = By.xpath("//div[@id='postContainer']/div[2]/ul");
    public static By noEntry = By.xpath("//div[contains(text(), 'No Post Found')]");
    public static By post = By.xpath("//div[@class='postContainer_postHeader__3aJRO']");
    public static By postType = By.xpath("//button[@class='tags_filterButton__185b5']");
    public static By selectFilter(String filterType) {
        if (filterType.contains("Achievements")) {
            return By.xpath("//div[@id='postContainer']/div[1]/div[1]/div[2]/div/div/div/ul//li[1]");
        } else if (filterType.contains("Announcements")) {
            return By.xpath("//div[@id='postContainer']/div[1]/div[1]/div[2]/div/div/div/ul//li[2]");
        } else if (filterType.contains("Questions")) {
            return By.xpath("//div[@id='postContainer']/div[1]/div[1]/div[2]/div/div/div/ul//li[3]");
        } else if (filterType.contains("Generic")) {
            return By.xpath("//div[@id='postContainer']/div[1]/div[1]/div[2]/div/div/div/ul//li[4]");
        } else if (filterType.contains("Ideas")) {
            return By.xpath("//div[@id='postContainer']/div[1]/div[1]/div[2]/div/div/div/ul//li[5]");
        } else if (filterType.contains("Findings")) {
            return By.xpath("//div[@id='postContainer']/div[1]/div[1]/div[2]/div/div/div/ul//li[6]");
        }
        else
            return null;
    }
    public static By validateFilter(String filterType) {
        if (filterType.contains("Achievements")) {
            return By.xpath("//button[contains(text(), 'Achievements')]");
        } else if (filterType.contains("Announcements")) {
            return By.xpath("//button[contains(text(), 'Announcements')]");
        } else if (filterType.contains("Questions")) {
            return By.xpath("//button[contains(text(), 'Questions')]");
        } else if (filterType.contains("Generic")) {
            return By.xpath("//button[contains(text(), 'Generic')]");
        } else if (filterType.contains("Ideas")) {
            return By.xpath("//button[contains(text(), 'Ideas')]");
        } else if (filterType.contains("Findings")) {
            return By.xpath("//button[contains(text(), 'Findings')]");
        }
        else
            return null;
    }
    public static By sectionName(String SectionName) {
        if (SectionName.contains("Birthday")) {
            return By.xpath("//span[text()='Birthday']");
        } else {
            return By.xpath("//span[text()='Work Anniversary']");
        }
    }
    public static By fetchText(String FieldName) {
        if (FieldName.contains("Birthday")) {
            return By.xpath("//*[contains(text(),'Birthday')]/following-sibling::div");
        } else if (FieldName.contains("Joining Date")) {
            return By.xpath("//*[contains(text(),'Joining Date')]/following-sibling::div");
        } else {
            return By.xpath("//*[contains(text(),'Hobbies')]/following-sibling::div");
        }
    }

}
package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Awards_Locators {
    public static By awardSection                =       By.xpath("//div[@class='award']");

    public static By awardSelectOption (String optionName)
    {
        return By.xpath("//select[@name='" +optionName +  "']");
    }

    public static By awardFilteredDataList       =       By.xpath("//div[@class='award']//ul//li");

    public static By awardSectionIcon            =       By.xpath("//img[@class='award-header']");

    public static By awardSectionContent         =       By.xpath("//div[contains(@class,'sidebar_description__1XG1m')]");
    public static By awardSectionEvent           =       By.xpath("//select[@class='w-40 ml-2']");
}
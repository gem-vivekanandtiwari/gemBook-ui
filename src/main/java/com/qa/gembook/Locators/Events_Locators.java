package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Events_Locators {

    public static By eventsSection          =       By.xpath("//div[@class='event']//parent::div[@class='card-body']");

    public static By eventsHeading          =       By.xpath("//span[@class='event-heading']");

    public static By eventsImg              =       By.xpath("//img[@class='event-header']");

    public static By eventsTabs(String tabName) {
        return By.xpath("//div[@class='sidebar_eventTabs__2hGpW']//div[contains(text(),'" + tabName + "')]");
    }

    public static By eventsData             =       By.xpath("//div[@class='container event-list']");

    public static By eventsList(String field) {
        return By.xpath("//div[@class='container event-list']//b[contains(text(),'" + field + "')]//parent::li");
    }

    public static String scrollEvents       =       "div[class*=\"container event-list\"]";
}


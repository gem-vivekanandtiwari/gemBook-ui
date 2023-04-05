package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Notifications_Locators {

    public static By notificationCloseButton = By.xpath("//strong[contains(text(),'Notifications')] /following-sibling :: img[@alt='cross']");

    public static By notificationIcon = By.cssSelector("#root > div > div.dashboard_dashboard__3MJQz > section > div.navbar_navWrapper__3cKcl > div > ul > li > svg");

    public static By notificationDropDown  = By.xpath("//strong[text()='Notifications'] //parent::div ");

    public static By notificationDropDownTitle = By.xpath("//strong[text()='Notifications']");

}

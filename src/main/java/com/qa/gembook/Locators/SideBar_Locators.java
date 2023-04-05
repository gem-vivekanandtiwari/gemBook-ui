package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class SideBar_Locators {
    public static By navigationTab(String tabName) {
        return By.xpath("//span[contains(text(),'" + tabName + "')]");
    }
    public static By navigationTabAnchor(String tabName)
    {
        return By.xpath("//span[contains(text(),'" + tabName + "')]//parent::a");
    }

    public static By navigationTab(String tabName, String dynamicParam) {
        return By.xpath("//span[contains(text(),'" + tabName + "')]" + dynamicParam);
    }

    public static By arrowIconDown         = By.xpath("//div[@class='expansion-icon']//img[contains(@class,'rotate-90')]");
    public static By arrowIconRight        = By.xpath("//div[@class='expansion-icon']//img[@class='']");
    public static By sidebarList           = By.xpath("//ul[contains(@class,'sidebar_menu')]//span");
    public static By sideBarIcon           = By.xpath("//span[contains(@class,'logo_header') and contains(text(),'Gembook')]");
    public static By otherPortals          = By.xpath("//span[contains(text(),'Other Portals')]");

    public static By iconPath(String param) {
        return By.xpath("//div[@class='expansion-icon']//img[contains(@class, " + param + ")]");
    }
    public static By loginBtnViaSSO        = By.xpath("//button[contains(text(),'Login') and contains(text(),'SSO')]");
    public static By pageLogoHeader        = By.xpath("//img[@class='logo']");

    public static By pageLoginBtn           = By.xpath("//button[@type='submit']");

    //-------get Title of submenuItem----------
    public static By getTitle(String subItem) {
        return By.xpath("//title[contains(text() ,'" + subItem + "')]");
    }

    //Jenkins
    public static By jenkinsHeader        = By.xpath("//img[@id='jenkins-head-icon']");
    public static By jenkinsCred          = By.xpath("//span[text()='Karan Singh Thakur']");

    //Service Desk
    public static By serviceDeskIcon      = By.xpath("//img[@alt='helpdesk']");

    //Azure
    public static By azureAccountBtn      = By.xpath("//div[@id='mectrl_headerPicture']");
    public static By azureAccountDetail   = By.xpath("//div[@id='mectrl_currentAccount_primary']");

    //Contripoint
    public static By contriPointBtn       = By.xpath("//button//span[contains(text(),'Login with Gemini mail')]");

    //greyTHR
    public static By greyTHrHeader        = By.xpath("(//img)[1]");

    //Github
    public static By gitHubGeminiHeader   = By.xpath("//div//h1[contains(text(),'Gemini Solutions')]");
    public static By gitHubInput          = By.xpath("//input[contains(@class,'header-search-input')]");

    //Athena
    public static By athenaHeader         = By.xpath("//div[contains(@class,'gem-image-logo')]");
    public static By athenaLogin          = By.xpath("//button//span[contains(text(),'LOGIN')]");

    //GemWiki
    public static By gemWikiHeader        = By.xpath("//div[text()='Gemini Wiki']");
    public static By gemWikiAzure         = By.xpath("//span[text()='Azure Active Directory']");
    //LMS
    public static By lmsHeader            = By.xpath("//img[contains(@class,'avatar-square')]");

    public static By lmsBtn               = By.xpath("//input[@type='submit']");

    //getIcon
    public static By getIcon(String sidebarItem) {
        return By.xpath("//span[text()='" + sidebarItem + "']//preceding-sibling::img");
    }
}


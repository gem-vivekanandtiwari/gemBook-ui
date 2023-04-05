package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Clubs_Locators {

    public static By clubsBtn = By.xpath("//div[@class='menu-item-detail' ]/span[text()='Clubs']");
    public static By myClubs = By.xpath("//div/p[text()='My Clubs']");
    public static By allClubs = By.xpath("//p[contains(text(),'All Clubs')]/parent::div");
    public static By clubsCard = By.xpath("//div[contains(text(),'Cultural Club' )]/parent::div/parent::div");
    public static By selectedButton = By.xpath("//div[contains(@class,'selected')]");
    public static By clubsIcon = By.xpath("//img[@alt='club icon']");
    public static By joinClubNotification = By.xpath("//div[@class='Toastify__toast-body']");
    public static By leaveClubBtn = By.xpath("//div[@class='topSection_icons__jk-TT']/button");

    public static By getClubsCard(String name) {
        return By.xpath("//div[contains(text(),'" + name + "')]/parent::div/parent::div");
    }

    public static By getNameOfClub(String name) {
        return By.xpath("//div[@class='clubs_title__1NO-n' and contains(text(),'" + name + "')]");
    }

    public static By getJoinOrLeaveBtn(String name) {
        return By.xpath("//div[contains(text(),'" + name + "')]//following::button");
    }

    public static By getCountOfMembers(String name) {
        return By.xpath("(//div[contains(text(),'" + name + "')]/following::div[@class='clubs_members__hrslg']/div)[1]");
    }

    public static By getDateOfCreation(String name) {
        return By.xpath("(//div[contains(text(),'" + name + "')]/following::div[@class='clubs_members__hrslg']/div)[2]");
    }

    public static By getJoinClubBtnOnCard(String name) {
        return By.xpath("//div/div[contains(text(),'" + name + "')]//following::button");
    }

    public static By pathFromMyClubToClub=By.xpath("//p[contains(@class,'clubPage_subHeading__3O5aV' )]");


}

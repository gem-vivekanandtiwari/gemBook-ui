package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Profile_Locators {

    public static By viewProfile = By.xpath("//img[@alt='profile image']");

    public static By managerProfile = By.xpath("//h5[@title='Click To Visit Profile']");

    public static By enterProfileComponent(String profileComponent){
        return By.xpath("//h5[text()='" + profileComponent + "'] //following:: img");
    }

    public static By navigationMenuIcon = By.xpath("//img[@alt='Menu Icon']");

    public static By threeVerticalDots = By.xpath("//img[@alt='dropdown icon']");


}
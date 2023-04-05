package com.qa.gembook.Locators;
import org.openqa.selenium.By;

public class SignIn_Locators {

    public static By gembookSignInLogo = By.xpath("//img[@alt='logo']");

    public static By gembookSignInCoverImg = By.xpath("//img[@alt='cover'] //parent ::div");

    public static By alertFailedPopup = By.xpath("//div[@role='alert']");


}
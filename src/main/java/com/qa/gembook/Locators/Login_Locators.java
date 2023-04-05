package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Login_Locators {

    public static By signInBtn = By.xpath("//button[text()='Sign in']");

    public static By loginError = By.xpath("//div[@class='Toastify__toast Toastify__toast--error']");

    public static By credentials(String credentialType) {
        if (credentialType.contains("username")) {
            return By.xpath("//input[@type='email']");

        } else if (credentialType.contains("GitHubUserName")) {
            return By.id("login_field");
        } else if (credentialType.contains("GitHubPassword")) {
            return By.id("password");
        } else {
            return By.xpath("//input[@type='password']");
        }
    }

    public static By nextBtn = By.id("idSIButton9");

    public static By Image(String type) {
        if (type.contains("Logo")) {
            return By.xpath("//img[@class='gembook-logo']");
        } else {
            return By.xpath("//img[@class='navbar_img__5LehJ']");
        }
    }

    public static By logoHeader = By.xpath("//span[@class='logo_headerTextHelper__1Z6Zl']");
    public static By moreBtn = By.xpath("//div[@class='navbar_dropdown__zoIJf nav-link']");
    public static By logOut = By.xpath("//*[text()='Logout']");
}


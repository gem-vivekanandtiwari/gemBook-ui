package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class SearchBar_Locators {

    public static By searchbar = By.id("searchInput");

    public static By noRecordsFoundErr = By.xpath("//*[contains(text(),'No ')]");

    public static By searchBar_FiltersTitle = By.xpath("//div[text()='Filters']");

    public static By searchBar_PostFilterBtn = By.xpath("//div[text()='Filters'] /following-sibling :: button[@title='Search for posts']");

    public static By searchBar_PeopleFilterBtn = By.xpath("//div[text()='Filters'] /following-sibling :: button[@title='Search for people']");

}

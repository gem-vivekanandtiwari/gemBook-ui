package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class PostCreation_Locators {

    public static By SectionName(String SectionName) {
        if (SectionName.contains("Birthday")) {
            return By.xpath("//span[text()='Birthday']");
        } else {
            return By.xpath("//span[text()='Work Anniversary']");
        }
    }

    public static By FetchText(String FieldName) {
        if (FieldName.contains("Birthday")) {
            return By.xpath("//*[contains(text(),'Birthday')]/following-sibling::div");
        } else if (FieldName.contains("Joining Date")) {
            return By.xpath("//*[contains(text(),'Joining Date')]/following-sibling::div");
        } else {
            return By.xpath("//*[contains(text(),'Hobbies')]/following-sibling::div");
        }
    }


}
package com.mytest.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmittedFormPage {
    private WebDriver driver;

    @FindBy(tagName = "pre")
    private WebElement successContent;

    public SubmittedFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getContent() {
        return successContent.getText();
    }


}

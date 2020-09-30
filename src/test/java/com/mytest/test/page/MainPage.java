package com.mytest.test.page;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private WebDriver driver;

    private static String PAGE_URL = "http://httpbin.org/";

    @FindBy(className = "opblock-tag-section")
    private List<WebElement> listOfTabs;

    @FindBy(linkText = "HTML form")
    private WebElement htmlForm;

    @FindBy(className = "opblock-summary-description")
    private List<WebElement> deleteElement;

    @FindBy(className = "is-open")
    public List<WebElement> listOfOpenTabs;

    @FindBy(id = "operations-tag-Status_codes")
    private WebElement statusCodesTab;

    @FindBy(xpath = "//*[@id=\"operations-Status codes-get_status__codes_\"]/div")
    private WebElement getStatusCodeBlock;

    @FindBy(className = "try-out")
    private WebElement tryItOutButton;

    @FindBy(xpath = "//*[@id=\"operations-Status codes-get_status__codes_\"]/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]/input")
    private WebElement codes;

    @FindBy(xpath = "//*[@id=\"operations-Status codes-get_status__codes_\"]/div[2]/div/div[2]/button[1]")
    private WebElement executeButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        PageFactory.initElements(this.driver, this);
    }

    public void expandAllTabs() throws InterruptedException {
        for (WebElement tab : listOfTabs) {
            tab.click();
            Thread.sleep(500);
        }
    }

    public void collapseAllTabs() throws InterruptedException {
        for (WebElement tab : listOfOpenTabs) {
            tab.click();
            Thread.sleep(500);
        }
    }

    public Boolean areAllElementsVisible() {
        for (WebElement element : deleteElement) {
            if(!element.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    public int getNumberOfAllElement() {
        return deleteElement.size();
    }

    public FormPage openHtmlForm() {
        htmlForm.click();
        return new FormPage(driver);
    }


    public void openStatusCodesTab() {
        statusCodesTab.click();
        getStatusCodeBlock.click();
        tryItOutButton.click();
        codes.clear();
        codes.sendKeys("200");
        executeButton.click();
    }
}

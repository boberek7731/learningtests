package com.mytest.test;

import com.mytest.test.page.FormPage;
import com.mytest.test.page.MainPage;
import com.mytest.test.page.SubmittedFormPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumTests {
    Logger log = LoggerFactory.getLogger(SeleniumTests.class);
    WebDriver driver;

    @Before
    public void setUp() {
       System.setProperty("webdriver.chrome.driver", "/Users/sg0221974/dev/mydev/seleniumtest/src/test/java/resources/chromedriver");
       driver = new ChromeDriver();
       log.info("Setting up test");
    }

    @After
    public void cleanUp() {
        driver.close();
    }

    @Test
    public void formFilledSuccessfully() {
        MainPage mainPage = new MainPage(driver);

        FormPage formPage = mainPage.openHtmlForm();
        formPage.setCustomerName("testName");
        formPage.setTelephone("123123123");
        formPage.setEmailAddress("test@test.com");
        formPage.setDeliveryInstructions("deliveryInstructions");
        formPage.setDeliveryTime("12:00");
        formPage.selectPizzaSize();
        formPage.selectPizzaTopping();

        SubmittedFormPage submittedFormPage = formPage.submitForm();

        String content = submittedFormPage.getContent();
        assertTrue(content.contains("Accept"));
        assertEquals("http://httpbin.org/post", submittedFormPage.getUrl());
    }

    @Test
    public void allTabsShouldBeExpanded() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.expandAllTabs();
        //TODO verify why is this not working as expected
//        mainPage.collapseAllTabs();
        assertTrue(mainPage.areAllElementsVisible());
    }

    @Test
    public void shouldInvoek200StatusCode() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openStatusCodesTab();
        String responseHeaders = driver.findElement(By.tagName("h5")).getText();
        assertEquals("Response headers", responseHeaders);
    }
}

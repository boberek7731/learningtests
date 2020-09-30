package com.mytest.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {
    private WebDriver driver;

    @FindBy(name = "custname")
    private WebElement customerName;

    @FindBy(name = "custtel")
    private WebElement telephone;

    @FindBy(name = "custemail")
    private WebElement emailAddress;

    @FindBy(xpath = "/html/body/form/fieldset[1]/p[2]/label/input")
    private WebElement pizzaSize;

    @FindBy(xpath = "/html/body/form/fieldset[2]/p[1]/label/input")
    private WebElement pizzaToppings;

    @FindBy(name = "delivery")
    private WebElement deliveryTime;

    @FindBy(name = "comments")
    private WebElement deliveryInstructions;

    @FindBy(xpath = "/html/body/form/p[6]/button")
    private WebElement submitButton;

    public void setCustomerName(String customerName) {
        this.customerName.clear();
        this.customerName.sendKeys(customerName);
    }

    public void setTelephone(String telephone) {
        this.telephone.clear();
        this.telephone.sendKeys(telephone);
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.clear();
        this.emailAddress.sendKeys(emailAddress);
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions.clear();
        this.deliveryInstructions.sendKeys(deliveryInstructions);
    }

    public SubmittedFormPage submitForm() {
        this.submitButton.click();
        return new SubmittedFormPage(this.driver);
    }

    public void selectPizzaSize() {
        this.pizzaSize.click();
    }

    public void selectPizzaTopping() {
        this.pizzaToppings.click();
    }

    public void setDeliveryTime(String time) {
        this.deliveryTime.clear();
        this.deliveryTime.sendKeys(time);
    }


    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}

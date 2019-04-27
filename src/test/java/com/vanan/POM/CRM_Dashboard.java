package com.vanan.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRM_Dashboard extends AccessingElement {

    private WebDriver driver;
    private JavascriptExecutor js;

    public CRM_Dashboard(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//button[@title='Easy Quote']")
    private WebElement easyQuoteBtn;

    public void clickEasyQuote() {
        clickElement(easyQuoteBtn);
    }
}

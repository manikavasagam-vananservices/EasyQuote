package com.vanan.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AccessingElement {


    public void enterTestBoxValuesWithClear(WebElement element, String content) {

        element.clear();
        element.sendKeys(content);
    }

    public void enterTestBoxValuesWOClear(WebElement element, String content) {

        element.sendKeys(content);
    }

    public void clearValue(WebElement element) {

        element.clear();
    }

    public void clickElement(WebElement element) {

        element.click();
    }

    public void clickJSElement(JavascriptExecutor javascriptExecutor, String element) {

        javascriptExecutor.executeScript("$('#" + element + "').click();");
    }

    public void selectDropDownByVText(WebElement element, String content) {

        Select dropDown = new Select(element);
        dropDown.selectByVisibleText(content);
    }

    public void selectDropDownByValue(WebElement element, String content) {

        Select dropDown = new Select(element);
        dropDown.selectByValue(content);
    }

    public void selectDropDownByIndex(WebElement element, int content) {

        Select dropDown = new Select(element);
        dropDown.selectByIndex(content);
    }

    public String getElementValues(WebElement element) {

        return element.getText();
    }

    public String getAttributeValues(WebElement element, String content) {

        return element.getAttribute(content);
    }

    public boolean isElementDisplayed(WebElement element) {

        return element.isDisplayed();
    }

    public boolean isElementEnabled(WebElement element) {

        return element.isEnabled();
    }

    public boolean isElementSelected(WebElement element) {

        return element.isSelected();
    }

    public void uploadFile(WebDriver driver, String fileName, String extenstion)
            throws IOException, AWTException, InterruptedException {

        fileName = fileName + extenstion;
        String filePath = System.getProperty("user.dir") + "/" + fileName;
        File file = new File(filePath);
        file.createNewFile();
        TimeUnit.SECONDS.sleep(10);
        WebElement fileUploadButton = driver.findElement(By.className("ui-upolad"));
        fileUploadButton.click();
        StringSelection selection = new StringSelection(fileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void pageRefresh(WebDriver driver) {

        driver.navigate().refresh();
    }


    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }
}

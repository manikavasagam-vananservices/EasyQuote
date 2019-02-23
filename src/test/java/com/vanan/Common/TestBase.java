package com.vanan.Common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase implements FilePaths, BrowserConfig, AppData, CredentialData {

    public WebDriver driver;

    private String browserName = "";
    private String operatingSystem = "";

    private void getDriverDetails() {
        //browserName = System.getProperty("browserName").toLowerCase();
        //operatingSystem = System.getProperty("operatingSystem").toLowerCase();
        browserName = "chrome";
        operatingSystem = "windows";
    }

    public void setDriver() {
        getDriverDetails();
        String browserOS = "";
        switch (browserName) {
            case CHROME:

                if (operatingSystem.equals("windows")) {
                    browserOS = CHROME_OS_WINDOWS;
                }
                if (operatingSystem.equals("linux")) {
                    browserOS = CHROME_OS_LINUX;
                }
                if (operatingSystem.equals("mac")) {
                    browserOS = CHROME_OS_MAC;
                }
                System.setProperty(CHROME_PROPERTY, browserOS);
                driver = new ChromeDriver();
                break;

            case FIREFOX:

                if (operatingSystem.equals("windows")) {
                    browserOS = FIREFOX_OS_WINDOWS;
                }
                if (operatingSystem.equals("linux")) {
                    browserOS = FIREFOX_OS_LINUX;
                }
                if (operatingSystem.equals("mac")) {
                    browserOS = FIREFOX_OS_MAC;
                }
                System.setProperty(FIREFOX_PROPERTY, browserOS);
                driver = new FirefoxDriver();
                break;

            case IE:

                System.setProperty(IE_PROPERTY, IE_OS_WINDOWS);
                driver = new InternetExplorerDriver();
                break;

            case SAFARI:
                driver = new SafariDriver();
                break;
        }
        setImplicitWaitingTime();
        setBrowserSizeMaximum();
    }

    private void setImplicitWaitingTime() {

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAITING_TIME, TimeUnit.SECONDS);
    }

    private void setBrowserSizeMaximum() {

        driver.manage().window().maximize();
    }

    public void takeSnapShot(WebDriver webdriver, String fileName) {

        try {

            TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(screenshotParentPath + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

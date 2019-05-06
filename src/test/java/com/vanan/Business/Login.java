package com.vanan.Business;

import com.vanan.Common.AppData;
import com.vanan.Common.TestBase;
import com.vanan.POM.CRM_Dashboard;
import com.vanan.POM.LoginPage;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Login extends TestBase implements AppData {

    @BeforeTest
    public void configureBrowser() {

        setDriver();
        String url = "";
        String username = "";
        String password = "";
        if(System.getProperty("env").equals(env[0])) {
            url = Live_URL;
            username = credentials[0];
            password = credentials [1];

        }
        else {
            url = Stage_URL;
            username = credentials[2];
            password = credentials [3];
        }

        driver.get(url);
        Cookie name = new Cookie("ignore-alert", "ignore-alert");
        driver.manage().addCookie(name);
        LoginPage login = new LoginPage(driver);
        login.signIn(username, password);
        CRM_Dashboard crm_dashboard = new CRM_Dashboard(driver);
        crm_dashboard.clickEasyQuote();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @Test
    public void test() {}

}

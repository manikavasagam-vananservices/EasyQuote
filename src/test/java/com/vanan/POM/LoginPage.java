package com.vanan.POM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AccessingElement {

	private WebDriver driver;
	private JavascriptExecutor js;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	@FindBy(id = "login_email")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement userPassword;
	
	@FindBy(xpath = "//button[text()='Sign In']")
	private WebElement signin;

	public void enterUserName(String username) {
		try {
			enterTestBoxValuesWithClear(userName, username);
		} catch (Exception e) {
			System.out.println("Unable to enter a user name " + e);
		}
	}

	public void enterPassword(String password) {
		try {
			enterTestBoxValuesWithClear(userPassword, password);
		} catch (Exception e) {
			System.out.println("Unable to enter a password " + e);
		}
	}
	
	private void clickSignIn() {
		
		clickElement(signin);
	}
	
	public void signIn(String userName, String pass) {
		
		enterUserName(userName);
		enterPassword(pass);
		clickSignIn();
	}	
}

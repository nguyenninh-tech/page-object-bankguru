package com.bankguru.account;

import commons.AbtractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;

public class Account_11_ShareClass_State_CloseBrowser_WebDriverManager extends AbtractTest {
    WebDriver driver;
    String email, username, password, loginPageUrl;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;

    // @Parameters("browser")
    @BeforeClass
    public void beforeClass() {
        //      driver = openMultiBrowser(browserName);
        driver = new FirefoxDriver();
        email = "itninhnt" + randomDataTest() + "@gmail.com";
        System.out.println("PRE-CONDITION- STEP 1. Open bankguru Application");
        driver.get(Constants.STAGING_URL);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        System.out.println("PRE-CONDITION- STEP 2. Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();
    }

    @Test
    public void TC_01_CheckShareClassState() {
        log.info("Login-01 verify login display");
        verifyTrue(loginPage.isLoginPageDisplayed());
        log.info("Login-02 input username and password");
        loginPage.inputToUserIDTextbox(Account_Common_01_RegisterToSystem.USER_NAME);
        loginPage.inputToPasswordTextbox(Account_Common_01_RegisterToSystem.PASS_WORD);
        log.info("Login-03 click to login button");
        homePage = loginPage.clickToLoginButton();
        log.info("Login-04 verify welcome message display");
        verifyTrue(homePage.isWelcomeMessageDisplayed("Welcome To Manager's Page of Guru99 Bank"));
        log.info("Login-05 verify username");
        verifyTrue(homePage.isUserIDDisplayed(Account_Common_01_RegisterToSystem.USER_NAME));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public int randomDataTest() {
        Random random = new Random();
        return random.nextInt(999999);
    }
}

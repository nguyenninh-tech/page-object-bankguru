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

public class Account_10_AssertVerify_Log_Report extends AbtractTest {
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
    public void TC_01_RegisterToSystem() {
        log.info("register-step1 verify login");
        System.out.println("Register - STEP: 1.Click to 'Here' link");
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        loginPageUrl = loginPage.getLoginPageUrl();
        registerPage = loginPage.clickToHereLink();
        System.out.println("Register - STEP: 2.Input to email textbox");
        registerPage.inputToEmailTextbox(email);
        System.out.println("Register - STEP: 3 submit button");
        registerPage.clickToSubmitButton();
        System.out.println("Register - STEP: 4 Get username pass info");
        username = registerPage.getUsernameInformation();
        password = registerPage.getPasswordInformation();
    }

    @Test
    public void TC_02_LoginToSystem() {
        log.info("login-step01 open login page again");
        System.out.println(" Login -STEP: 1.Open Login Page");
        loginPage = registerPage.openLoginPageUrl(loginPageUrl);
        Assert.assertFalse(loginPage.isLoginPageDisplayed());
        System.out.println(" Login -STEP: 2.Input userid & pass in textbox");
        loginPage.inputToUserIDTextbox(username);
        loginPage.inputToPasswordTextbox(password);
        System.out.println(" Login -STEP 3.Click Login");
        homePage = loginPage.clickToLoginButton();
        System.out.println(" Login -STEP 4: verify welcome mess display");
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed("Welcome To Manager's Page of Guru99 Bank"));
        System.out.println(" Login -STEP 5: Verify userid display");
        homePage.isUserIDDisplayed(username);
    }

    @Test
    public void TC_03_CheckAsssertAndVerify() {
        System.out.println(" Login -STEP: 1.Open Login Page");
        loginPage = registerPage.openLoginPageUrl(loginPageUrl);
        verifyFalse(loginPage.isLoginPageDisplayed());
        loginPage.inputToUserIDTextbox(username);
        loginPage.inputToPasswordTextbox(password);
        homePage = loginPage.clickToLoginButton();
        System.out.println(" Login -STEP 4: verify welcome mess display");
        verifyTrue(homePage.isWelcomeMessageDisplayed("Welcome To Manager's Page of Guru99 Bank"));
        System.out.println(" Login -STEP 5: Verify userid display");
        verifyTrue(homePage.isUserIDDisplayed(username));
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

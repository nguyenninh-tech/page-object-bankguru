package com.bankguru.account;

import commons.AbtractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;

public class Account_Common_01_RegisterToSystem extends AbtractTest {
    WebDriver driver;
    public static String USER_NAME, PASS_WORD;
    String email, username, password, loginPageUrl;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;

    // @Parameters("browser")
    @BeforeTest
    public void beforeTest() {
        //      driver = openMultiBrowser(browserName);
        driver = new FirefoxDriver();
        email = "itninhnt" + randomDataTest() + "@gmail.com";
        System.out.println("PRE-CONDITION- STEP 1. Open bankguru Application");
        driver.get(Constants.STAGING_URL);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        System.out.println("PRE-CONDITION- STEP 2. Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();
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
        USER_NAME = registerPage.getUsernameInformation();
        PASS_WORD = registerPage.getPasswordInformation();
        System.out.println("username la" + USER_NAME);
        System.out.println("password la" + PASS_WORD);
        driver.quit();
    }

    public int randomDataTest() {
        Random random = new Random();
        return random.nextInt(999999);
    }
}

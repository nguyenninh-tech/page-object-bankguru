package com.bankguru.account;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;

public class Account_06_RegisterAndLogin_MultiBrowser_Parallel {
    WebDriver driver;
    String email, username, password, loginPageUrl;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    HomePageObject homePage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        String rootFolder=System.getProperty("user.dir");
       // if(browserName.equals("")) //so sanh tuyet doi
        if(browserName.equalsIgnoreCase("firefox"))//so sanh khong phan biet hoa thuong fire=FIre
        {
            driver=new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("chrome"))
        {
            driver=new ChromeDriver();
        }
        else  if(browserName.equalsIgnoreCase("chromeheadless"))
        {
            ChromeOptions options=new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size-1920*1080");
            driver=new ChromeDriver(options);
        }
        else {
            System.out.println("Vui long chon browser name trong testng xml file");
        }
       // if(browserName.contains("")) //tim kiem tuong doi co phan biet hoa thuong chromeheadless=chrome        {}
        email = "itninhnt" + randomDataTest() + "@gmail.com";
//selenium api: xư ly triet de theo po trong bai multi browser
        System.out.println("PRE-CONDITION- STEP 1. Open bankguru Application");
        driver.get("http://demo.guru99.com/v4/");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        System.out.println("PRE-CONDITION- STEP 2. Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();
    }

    @Test
    public void TC_01_RegisterToSystem() {
        System.out.println("Register - STEP: 1.Click to 'Here' link");
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
        System.out.println(" Login -STEP: 1.Open Login Page");
        loginPage = registerPage.openLoginPageUrl(loginPageUrl);
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

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public int randomDataTest() {
        Random random = new Random();
        return random.nextInt(999999);
    }
}

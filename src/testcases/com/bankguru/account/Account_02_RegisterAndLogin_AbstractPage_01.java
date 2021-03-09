package com.bankguru.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import commons.AbstractPage;

public class Account_02_RegisterAndLogin_AbstractPage_01 {
    WebDriver driver;
    String email, username, password, loginPageUrl;
    AbstractPage abstractPage;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        email = "itninhnt" + randomDataTest() + "@gmail.com";
        abstractPage = new AbstractPage(); /*khoi tao*/
        abstractPage.openUrl(driver, "http://demo.guru99.com/v4/index.php");
//        driver.get("http://demo.guru99.com/v4/index.php");
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        loginPageUrl = driver.getCurrentUrl();
        loginPageUrl = abstractPage.getCurrentPageUrl(driver);
    }

    @Test
    public void TC_01_RegisterToSystem() {
        System.out.println("Register - STEP: 1.Click to 'Here' link");
//        driver.findElement(By.xpath("//a[text()='here']")).click();
        abstractPage.clickToElement(driver, "//a[text()='here']");
        System.out.println("Register - STEP: 2.Input to email textbox");
//        driver.findElement(By.name("emailid")).sendKeys(email);
        abstractPage.sendkeyToElement(driver, "//input[@name='emailid']", email);
        System.out.println("Register - STEP: 3 submit button");
//        driver.findElement(By.name("btnLogin")).click();
        abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
        System.out.println("Register - STEP: 4 Get username pass info");
//        username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
//        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
        username = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
        password = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
    }

    @Test
    public void TC_02_LoginToSystem() {
        System.out.println(" Login - STEP: 1.Open Login Page");
        driver.get(loginPageUrl);
        System.out.println(" Login -STEP: 2.Input userid & pass in textbox");
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        System.out.println(" Login -STEP 3.Click Login");
        driver.findElement(By.name("btnLogin")).click();
        System.out.println(" Login -STEP 4: verify welcome mess display");
        String welcomeMessage = driver.findElement(By.cssSelector("marquee")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome To Manager's Page of Guru99 Bank");
        System.out.println(" Login -STEP 5: Verify userid display");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + username + "']")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int randomDataTest() {
        Random random = new Random();
        return random.nextInt(999999);
    }
}

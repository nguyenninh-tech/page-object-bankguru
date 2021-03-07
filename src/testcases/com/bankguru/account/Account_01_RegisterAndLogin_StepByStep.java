package com.bankguru.account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Account_01_RegisterAndLogin_StepByStep {
    WebDriver driver;
    String email,username,password,loginPageUrl;

    @BeforeClass
    public void beforeClass()
    {
        driver=new FirefoxDriver();
        driver.get("http://demo.guru99.com/v4/index.php");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPageUrl = driver.getCurrentUrl();
        email="itninhnt" + randomDataTest() + "@gmail.com";
    }

    @Test
    public void TC_01_RegisterToSystem()
    {
        System.out.println("Register - STEP: 1.Click to 'Here' link");
        driver.findElement(By.xpath("//a[text()='here']")).click();
        System.out.println("Register - STEP: 2.Input to email textbox");
        driver.findElement(By.name("emailid")).sendKeys(email);
        System.out.println("Register - STEP: 3 submit button");
        driver.findElement(By.name("btnLogin")).click();
        System.out.println("Register - STEP: 4 Get username pass info");
        username=driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password=driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_LoginToSystem()
    {
        System.out.println(" Login - STEP: 1.Open Login Page");
        driver.get(loginPageUrl);
        System.out.println(" Login -STEP: 2.Input userid & pass in textbox");
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        System.out.println(" Login -STEP 3.Click Login");
        driver.findElement(By.name("btnLogin")).click();
        System.out.println(" Login -STEP 4: verify welcome mess display");
        String welcomeMessage=driver.findElement(By.cssSelector("marquee")).getText();
        Assert.assertEquals(welcomeMessage,"Welcome To Manager's Page of Guru99 Bank");
        System.out.println(" Login -STEP 5: Verify userid display");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + username + "']")).isDisplayed());

    }

    @AfterClass
    public void afterClass()
    {
    driver.quit();
    }

    public int randomDataTest()
    {
        Random random = new Random();
        return random.nextInt(999999);
    }
}
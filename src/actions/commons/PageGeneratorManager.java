package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.*;
import pageUIs.LoginPageUI;

public class PageGeneratorManager {
      public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }
    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }
    public static DepositPageObject getDeopsitPage(WebDriver driver){
        return new DepositPageObject(driver);
    }
    public static NewCustomerPageObject getNewCustomerPage(WebDriver driver){
        return new NewCustomerPageObject(driver);
    }
}

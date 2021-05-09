package pageObjects;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    //Ham khoi tao
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        System.out.println("Driver at LoginPage" + driver.toString());
    }

    public String getLoginPageUrl() {
        return getCurrentPageUrl(driver);
    }

    public RegisterPageObject clickToHereLink() {
        waitForElementVisible(driver, LoginPageUI.HERE_LINK);
        clickToElement(driver, LoginPageUI.HERE_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public void inputToUserIDTextbox(String username) {
        waitForElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERID_TEXTBOX, username);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public HomePageObject clickToLoginButton() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }

    public boolean isLoginPageDisplayed() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
        return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
    }


    public boolean isHomePageUnDisplayed() {
        waitForElementInVisible(driver, LoginPageUI.WELCOM_MESSAGE_TEXT);
        return isControlUnDisplayed(driver, LoginPageUI.WELCOM_MESSAGE_TEXT);
    }


}

package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    //Ham khoi tao
    public LoginPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
        System.out.println("Driver at LoginPage" + driver.toString());
    }

    public String getLoginPageUrl() {
        return getCurrentPageUrl(driver);
    }

    public void clickToHereLink() {
        waitForElementVisible(driver, LoginPageUI.HERE_LINK);
        clickToElement(driver, LoginPageUI.HERE_LINK);
    }

    public void inputToUserIDTextbox(String username) {
        waitForElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERID_TEXTBOX, username);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}

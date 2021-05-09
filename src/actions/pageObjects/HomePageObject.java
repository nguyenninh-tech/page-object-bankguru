package pageObjects;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
    WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWelcomeMessageDisplayed(String expectedText) {
        String actualText = getTextElement(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
        return actualText.equals(expectedText);
    }

    public boolean isUserIDDisplayed(String userID) {
        String actualText = getTextElement(driver, HomePageUI.USERID_TEXT);
        return actualText.contains(userID);
    }

}

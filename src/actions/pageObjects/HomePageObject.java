package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
    WebDriver driver;
    public HomePageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
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

package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.HomePageUI;

public class HomePageFactory {
    @FindBy(how = How.XPATH, using = "//marquee[@class='heading3']")
    private WebElement wellcomeMessageText;
    @FindBy(how = How.XPATH, using = "//tr[@class='heading3']/child::td")
    private WebElement userIDText;
    private WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isWelcomeMessageDisplayed(String expectedText) {
        String actualText = wellcomeMessageText.getText().trim();
        return actualText.equals(expectedText);
    }

    public boolean isUserIDDisplayed(String userID) {
        String actualText = userIDText.getText().trim();
        return actualText.contains(userID);
    }
}

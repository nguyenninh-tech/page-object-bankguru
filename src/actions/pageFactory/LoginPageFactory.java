package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.LoginPageUI;

public class LoginPageFactory {
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//input[@name='uid']")
    private WebElement userIDTextbox;
    @FindBy(how = How.NAME, using = "password")
    private WebElement passwordTextbox;
    @FindBy(how = How.NAME, using = "btnLogin")
    private WebElement loginButton;
    @FindBy(how = How.XPATH, using = "//a[text()='here']")
    private WebElement hereLink;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getLoginPageUrl() {

        return driver.getCurrentUrl();
    }

    public void clickToHereLink() {
        hereLink.click();
    }

    public void inputToUserIDTextbox(String username) {
        userIDTextbox.sendKeys(username);
    }

    public void inputToPasswordTextbox(String password) {
        passwordTextbox.sendKeys(password);
    }

    public void clickToLoginButton() {
        loginButton.click();
    }
}

package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.RegisterPageUI;

public class RegisterPageFactory {
    @FindBy(how = How.XPATH, using = "//input[@name='emailid']")
    private WebElement emailTextbox;
    @FindBy(how = How.NAME, using = "btnLogin")
    private WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//td[text()='User ID :']/following-sibling::td")
    private WebElement userIDText;
    @FindBy(how = How.XPATH, using = "//td[text()='Password :']/following-sibling::td")
    private WebElement passwordText;
    private WebDriver driver;

    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputToEmailTextbox(String email) {
        emailTextbox.sendKeys(email);
    }

    public void clickToSubmitButton() {
        submitButton.click();
    }

    public String getUsernameInformation() {
        return userIDText.getText();
    }

    public String getPasswordInformation() {
        return passwordText.getText();
    }

    public void openLoginPageUrl(String loginPageUrl) {
        driver.get(loginPageUrl);
    }

}

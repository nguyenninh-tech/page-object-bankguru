package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DepositPageObject extends AbstractPage {
    WebDriver driver;
    public DepositPageObject(WebDriver driver) {
        this.driver = driver;
    }

}

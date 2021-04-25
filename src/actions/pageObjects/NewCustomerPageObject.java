package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class NewCustomerPageObject extends AbstractPage {
    WebDriver driver;
    public NewCustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }

}

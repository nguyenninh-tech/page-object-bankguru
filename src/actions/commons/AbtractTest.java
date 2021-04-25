package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class AbtractTest {
    private WebDriver driver;

    public WebDriver openMultiBrowser(String browserName) {
        String rootFolder = System.getProperty("user.dir");
        // if(browserName.equals("")) //so sanh tuyet doi
        if (browserName.equalsIgnoreCase("firefox"))//so sanh khong phan biet hoa thuong fire=FIre
        {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("chromeheadless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size="+Constants.HEADLESS_RESOLUTION);
            driver = new ChromeDriver(options);
        } else {
            System.out.println("Vui long chon browser names trong testng xml file");
        }
        System.out.println("Driver ID at Abstract Test = " + driver.toString());
        driver.get(Constants.DEV_URL);
        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }
}

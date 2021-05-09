package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class AbtractTest {
    private WebDriver driver;
    protected final Log log;
    protected AbtractTest() {
        log = LogFactory.getLog(getClass());
    }

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
            options.addArguments("window-size=" + Constants.HEADLESS_RESOLUTION);
            driver = new ChromeDriver(options);
        } else {
            System.out.println("Vui long chon browser names trong testng xml file");
        }
        System.out.println("Driver ID at Abstract Test = " + driver.toString());
        driver.get(Constants.DEV_URL);
        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }


    private boolean checkPassed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true)
                log.info("===PASSED==");
            else
                log.info("===FAILED==");
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkPassed(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false)
                log.info("===PASSED===");
            else
                log.info("===FAILED===");
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        boolean status;
        try {
            if (actual instanceof String && expected instanceof String) {
                actual = actual.toString().trim();
                log.info("Actual = " + actual);
                expected = expected.toString().trim();
                log.info("Expected = " + expected);
                status = (actual.equals(expected));
            } else {
                status = (actual == expected);
            }

            log.info("Compare value = " + status);
            if (status) {
                log.info("===PASSED===");
            } else {
                log.info("===FAILED===");
            }
            Assert.assertEquals(actual, expected, "Value is not matching!");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }

}

package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.AbtractPageUI;

import java.util.List;
import java.util.Set;

public class AbstractPage {
    /*Web Browser*/
    public void openUrl(WebDriver driver, String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getCurrentPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void sendkeyToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    /*Web Element*/
    public void clickToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByVisibleText(itemText);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String allItemXpath,
                                           String expectedValueItem) throws Exception {
        element = driver.findElement(By.xpath(parentXpath));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click;", element);
        Thread.sleep(1000);

        waitExplicit = new WebDriverWait(driver, longTimeout);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

        elements = driver.findElements(By.xpath(allItemXpath));

        for (WebElement childElement : elements) {
            if (childElement.getText().contains(expectedValueItem)) {
                if (childElement.isDisplayed()) {
                    childElement.click();
                } else {
                    javascriptExecutor.executeScript("arguments[0].scrollIntroView(true);", childElement);
                    Thread.sleep(1000);
                    javascriptExecutor.executeScript("arguments[0].click();", childElement);
                }
                Thread.sleep(1000);
                break;
            }
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public int countElementsNumber(WebDriver driver, String locator) {
        elements = driver.findElements(By.xpath(locator));
        return elements.size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isControlDisplay(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();

    }

    public boolean isControlDisplay(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();

    }

    public boolean isControlSelected(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isSelected();

    }

    public boolean isControlEnabled(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public void switchToChildWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String runWindowID : allWindowIDs) {
            if (!runWindowID.equals(parentID)) {
                driver.switchTo().window(runWindowID);
                System.out.println("Child ID: " + runWindowID);
                break;
            }
        }
    }

    public void switchByTitle(WebDriver driver, String titleExpected) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String runTitle = driver.getTitle();
            if (runTitle.equals(titleExpected)) {
                break;
            }
        }
    }

    public boolean closeAllWindowWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String runWindowID : allWindowIDs) {
            if (!runWindowID.equals(parentID)) {
                driver.switchTo().window(runWindowID);
                driver.close();
            }
        }

        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public void hoverMuseToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.sendKeys(element, key).perform();
    }

//    public void uploadFiles(WebDriver driver, String locator) throws Exception {
//
//        for (String file : files) {
//            WebElement uploadFiles = driver.findElement(By.xpath(locator));
//            uploadFiles.sendKeys(file);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        driver.findElement(By.xpath(locator)).click();
//    }

    public Object executeForBrowser(WebDriver driver, String javaSript) {
        javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript(javaSript);
    }

    public Object scrollToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void scrollToBottomPage(WebDriver driver) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public boolean verifyTextInInnerText(WebDriver driver, String locator, String textExpected) {
        javascriptExecutor = (JavascriptExecutor) driver;
        String textActual = (String) javascriptExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        System.out.println("Text actual = " + textActual);
        return textActual.equals(textExpected);
    }

    public boolean checkAnyImageLoaded(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) javascriptExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        waitExplicit = new WebDriverWait(driver, longTimeout);
        element = driver.findElement(By.xpath(locator));
        By byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        waitExplicit = new WebDriverWait(driver, longTimeout);
        element = driver.findElement(By.xpath(locator));
        By byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public void waitForElementClickAble(WebDriver driver, String locator) {
        waitExplicit = new WebDriverWait(driver, longTimeout);
        element = driver.findElement(By.xpath(locator));
        By byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
    }

    public void waitForElementInVisible(WebDriver driver, String locator) {
        waitExplicit = new WebDriverWait(driver, longTimeout);
        element = driver.findElement(By.xpath(locator));
        By byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
    }

    //so luong<20 page
    public AbstractPage openMultiplePage(WebDriver driver, String pageName) {
        waitForElementVisible(driver, AbtractPageUI.DYNAMIC_MENU_LINK, pageName);
        clickToElement(driver, AbtractPageUI.DYNAMIC_MENU_LINK, pageName);
//        if (pageName.equals("Manager")) {
//            return PageGeneratorManager.getHomePage(driver);
//        } else if (pageName.equals("New Account")) {
//            return PageGeneratorManager.getRegisterPage(driver);
//        } else {
//            return PageGeneratorManager.getLoginPage(driver);
//
//        }
        switch (pageName) {
            case "New Customer":
                return PageGeneratorManager.getNewCustomerPage(driver);
            case "Deposit":
                return PageGeneratorManager.getDeopsitPage(driver);
            case "Manager":
                return PageGeneratorManager.getHomePage(driver);
            default:
                return PageGeneratorManager.getHomePage(driver);
        }
    }
    //so luong>20page
    public void openMultiplePages(WebDriver driver, String pageName) {
        waitForElementVisible(driver, AbtractPageUI.DYNAMIC_MENU_LINK, pageName);
        clickToElement(driver, AbtractPageUI.DYNAMIC_MENU_LINK, pageName);
    }

    private WebElement element;
    private Select select;
    private List<WebElement> elements;
    private JavascriptExecutor javascriptExecutor;
    private WebDriverWait waitExplicit;
    private Actions action;
    private By byLocator;
    private int shortTimeout=5;
    private int longTimeout=Constants.LONG_TIMEOUT;
}

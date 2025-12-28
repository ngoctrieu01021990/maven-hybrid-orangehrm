package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {

    public static BasePageFactory getInstance() {
        return new BasePageFactory();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTile(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwordToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(windowID)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByContainTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void closeAllExcepMain(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(windowID)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

    // Thao tác với element
    //PageFactory là define kiểu web element

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendkeyToElement(WebElement element, String keyToSend) {
        element.clear();
        element.sendKeys(keyToSend);
    }

    public void selectItemInDropDown(WebElement element, String valueItem) {
        new Select(element).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropdown(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebElement element) {
        return new Select(element).isMultiple();
    }

    public String getElementDOMAttribute(WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebElement element, String propertyName) {
        return element.getDomProperty(propertyName);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getElementCss(WebElement element, String propertyName) {
        return element.getCssValue(propertyName);
    }

    public String getHexaColorByRGBA(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public void checkToCheckbox(WebElement element) {
        if (!isElementSelected(element)) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebElement element) {
        if (isElementSelected(element)) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public boolean isElementEnable(WebElement element) {
        return element.isEnabled();
    }

    public void switchToFrame(WebDriver driver, WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    public void rightClick(WebDriver driver, WebElement element) {
        new Actions(driver).contextClick(element).perform();
    }

    public void moveToElement(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void dragAndDrop(WebDriver driver, WebElement elementSource, WebElement elementTaget) {
        new Actions(driver).dragAndDrop(elementSource, elementTaget).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, WebElement element, Keys keys) {
        new Actions(driver).sendKeys(element, keys).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void clickToElementByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", element);
    }

    public void scrollToElementOnTop(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElementOnDown(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public String getAttributeInDOM(WebDriver driver, WebElement element, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", element);
    }

    public String getElementValidationMessage(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public boolean isImageLoaded(WebDriver driver, WebElement element) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }

    public WebElement waitElementVisible(WebDriver driver, WebElement element) {
        WebElement until = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
        return until;
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitElementSelected(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(element));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitElementInvisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    private final int SHORT_TIMEOUT = 15;
    private final int LONG_TIMEOUT = 30;
}

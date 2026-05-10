package core;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.orangeHRM.LoginPageObject;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    // Hàm static có nhiệm vụ lấy ra đúng instance của chính class này
    // 1 biến static/hàm static có thể gọi ra trực tiếp từ phạm vi class
    public static BasePage getInstance() {
        return new BasePage();
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

    public Set<Cookie> getPageCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setPageCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
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

    public String getCurrentWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public void openUrlByNewTab(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public void openUrlByNewWindow(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
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


    private By getByXPath(String locator) {
        return By.xpath(locator);
    }

    private String castParameter(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    private By getByLocator(String locatorType) {
        System.out.println("Locator type =" + locatorType);

        if (locatorType == null || locatorType.trim().isEmpty()) {
            throw new IllegalArgumentException("Local type cannot be null or empty.");
        }

        String[] locatorArr = locatorType.split("=", 2);
        String locatorPrefix = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

        switch (locatorPrefix.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Locator type is not supported" + locatorType);
        }
    }

    protected WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected WebElement getWebElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElement(getByLocator(castParameter(locator, restValue)));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElements(getByLocator(castParameter(locator, restValue)));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, CharSequence keyToSend) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, CharSequence keyToSend, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).clear();
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(keyToSend);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String valueItem, String... restValue) {
        new Select(getWebElement(driver, castParameter(locator, restValue))).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        clickToElement(driver, parentLocator);
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem, String... restValue) {
        clickToElement(driver, castParameter(parentLocator, restValue));
        sleepInSecond(1);

        String formattedChildLocator = castParameter(childLocator, restValue);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(formattedChildLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getText();
    }

    public Dimension getElementSize(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getSize();
    }

    public Dimension getElementSize(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getSize();
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorByRGBA(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locator, String... restValue) {
        return getListElement(driver, castParameter(locator, restValue)).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckbox(WebDriver driver, String locator, String... restValue) {
        if (!isElementSelected(driver, castParameter(locator, restValue))) {
            getWebElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status = false;
        try {
            return getWebElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return status;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restValue) {
        boolean status = true;
        try {
            WebElement element = getWebElement(driver, castParameter(locator, restValue));
            if (element.isDisplayed()) {
                return status;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    private void overrideGlobalTimeout(WebDriver driver, long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));

    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locator);
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValue) {
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, castParameter(locator, restValue));
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).isSelected();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String tagetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, tagetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollDown(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,500);");
    }

    public void scrollToTopPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDOMAttribute(driver, locator, "style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restValue))));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitElementInvisibleNotInDOM(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, castParameter(locator, restValue))));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
        sleepInSecond(2);
    }

    // openCart
    public UserHomePO clickToLogoutLinkAtUserSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);

        waitElementClickable(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);

        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public AdminLoginPO clickToLogoutLinkAtAdminSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    public void openAdminSite(WebDriver driver, String adminURL) {
        openPageUrl(driver, adminURL);
    }

    public UserHomePO openUserSite(WebDriver driver, String userURL) {
        openPageUrl(driver, userURL);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public UserHomePO openHomeLogo(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_HOME_LOGO);
        clickToElement(driver, BasePageUI.USER_HOME_LOGO);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    //ORangeHRM
    @Step("Waiting for Loading Spinner undisplay")
    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    @Step("Enter to {0} textbox by label with value {1}")
    public void enterToTextboxByLabel(WebDriver driver, String textboxLabel, String valueToSendkey) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_LABEL, valueToSendkey, textboxLabel);
    }

    @Step("Enter to {0} textbox by label with value {1}")
    public void clearToTextboxByLabel(WebDriver driver, String textboxLabel) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_LABEL, Keys.chord(Keys.CONTROL, "a"), textboxLabel);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_LABEL, Keys.DELETE, textboxLabel);
    }

    @Step("Enter to {0} textbox by name with value {1}")
    public void enterToTextboxByName(WebDriver driver, String textboxNameAttribute, String valueToSendkey) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_NAME, valueToSendkey, textboxNameAttribute);
    }

    @Step("Click to {0} button by text")
    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public void clickToButtonByTextInMainTitle(WebDriver driver, String buttonText, String mainTitleName) {
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
    }

    public String getTextboxValueByName(WebDriver driver, String textboxNameAttribute) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_NAME, "value", textboxNameAttribute);
    }

    public String getTextboxValueByLabel(WebDriver driver, String textboxLabel) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_LABEL, "value", textboxLabel);
    }

    public String getDropdownSelectedValueByLabel(WebDriver driver, String labelName) {
        waitElementVisible(driver, BasePageUI.SELECTED_DROPDOWN_VALUE_BY_LABEL, labelName);
        return getElementText(driver, BasePageUI.SELECTED_DROPDOWN_VALUE_BY_LABEL, labelName).trim();
    }

    public boolean isRadioSelectedByLabel(WebDriver driver, String labelName) {
        waitElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        String classValue = getElementDOMAttribute(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, "class", labelName);
        return classValue.contains("oxd-radio-input--active");
    }

    public String getSelectedRadioValue(WebDriver driver, String... restValue) {
        for (String value : restValue) {
            if (isRadioSelectedByLabel(driver, value)) {
                return value;
            }
        }
        return null;
    }

    public boolean isTableinfoDisplayed(WebDriver driver, String fieldName, String fieldValue) {
        waitElementVisible(driver, BasePageUI.DYNAMIC_TABLE_INFO, fieldName, fieldValue);
        return isElementDisplayed(driver, BasePageUI.DYNAMIC_TABLE_INFO, fieldName, fieldValue);
    }

    @Step("Click to {0} module in Menu Item")
    public void clickToModuleByTextInMenuItem(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        clickToElement(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public boolean isModuleByTextInMenuItemDisplayed(WebDriver driver, String moduleName) {
        waitElementVisible(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementDisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public boolean isModuleByTextInMenuItemUndisplayed(WebDriver driver, String moduleName) {
        waitElementInvisibleNotInDOM(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementUndisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    @Step("Click to {0} Menu Item in header")
    public void clickToMenuItemHeader(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_HEADER, moduleName);
        clickToElement(driver, BasePageUI.MODULE_BY_TEXT_IN_HEADER, moduleName);
    }

//    @Step("Click to {0} button by text")
//    public void clickToButtonByGroupText(WebDriver driver, String groupText, String buttonText) {
//        waitElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_GROUP_LABEL, groupText, buttonText);
//        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_GROUP_LABEL, groupText, buttonText);
//    }

    public void selectDropdownByLabel(WebDriver driver, String labelName, String valueToSelect) {
        waitElementClickable(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInSelectableDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.CHILD_DROPDOWN_BY_LABEL, valueToSelect, labelName);
    }

    public void selectDropdownSubChildByLabel(WebDriver driver, String labelName, String valueToSelect) {
        waitElementClickable(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInSelectableDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.SUB_CHILD_DROPDOWN_BY_LABEL, valueToSelect, labelName);
    }

    public boolean isToastMessageDisplayed(WebDriver driver, String toastMessage) {
        waitElementVisible(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
        return isElementDisplayed(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
    }

    public void clickToRadioByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        clickToElement(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
        clickToElement(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
    }

    public LoginPageObject clickLogoutOnTopMenu(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_DROPDOWN);
        clickToElement(driver, BasePageUI.USER_DROPDOWN);

        waitElementClickable(driver, BasePageUI.LOGOUT_LINK);
        clickToElement(driver, BasePageUI.LOGOUT_LINK);

        return PageGenerator.getPage(LoginPageObject.class, driver);
    }

    public void uploadFileByLabel(WebDriver driver, String labelName, String... fileNames) {
        WebElement uploadElement = getWebElement(driver, BasePageUI.DYNAMIC_UPLOAD_FILE_BY_LABEL, labelName);
        // Check multiple upload support
        boolean isMultiple = uploadElement.getDomAttribute("multiple") != null;

        if (!isMultiple && fileNames.length > 1) {
            throw new RuntimeException("Upload field '" + labelName + "' does not support multiple files.");
        }

        StringBuilder fullFileName = new StringBuilder();

        for (String fileName : fileNames) {
            fullFileName.append(GlobalConstants.UPLOAD_PATH).append(fileName).append("\n");
        }
        uploadElement.sendKeys(fullFileName.toString().trim());
        sleepInSecond(2);
    }

    public boolean isFileUploadedByLabel(WebDriver driver, String labelName, String fileName) {
        try {
            waitElementVisible(driver, BasePageUI.DYNAMIC_FILE_NAME_BY_LABEL, labelName, fileName);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private int SHORT_TIMEOUT = GlobalConstants.SHORT_TIME;
    private int LONG_TIMEOUT = GlobalConstants.LONG_TIME;


}

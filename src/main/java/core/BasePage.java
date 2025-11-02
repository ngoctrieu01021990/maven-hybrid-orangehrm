package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    WebDriver driver;

    /**
     * get Web Element from Css locator
     * @param locator Css locator
     * @return WebElement
     * @throws
     * @author
     */
    public WebElement getWebElement(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }
}

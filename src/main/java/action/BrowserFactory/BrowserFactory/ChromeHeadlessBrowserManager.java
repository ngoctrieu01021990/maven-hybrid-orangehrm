package action.BrowserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        ChromeOptions chromeheadlessOptions = new ChromeOptions();
        chromeheadlessOptions.addArguments("--headless");
        chromeheadlessOptions.addArguments("window-size=1920x1080");
        return new ChromeDriver(chromeheadlessOptions);
    }
}

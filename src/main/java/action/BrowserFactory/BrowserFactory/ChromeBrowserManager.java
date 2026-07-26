package action.BrowserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //Config capability
        return new ChromeDriver(chromeOptions);
    }
}

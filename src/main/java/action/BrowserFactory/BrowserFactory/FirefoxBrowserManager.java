package action.BrowserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        FirefoxOptions ffObtions = new FirefoxOptions();
        //Config capability
        return  new FirefoxDriver(ffObtions);
    }
}

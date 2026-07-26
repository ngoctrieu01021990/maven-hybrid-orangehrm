package action.BrowserFactory.BrowserFactory;

import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariBrowserManager implements BrowserFactory {
    @Override
    public WebDriver getDriver() {
        if (!GlobalConstants.OS_NAME.toUpperCase().startsWith("MAC")) {
            throw new BrowserNotSupportedException("Safari is not supported on " + GlobalConstants.OS_NAME);
        }
        SafariOptions safariOptions = new SafariOptions();
        //Config capability
        return new SafariDriver(safariOptions);
    }
}

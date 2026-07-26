package action.BrowserFactory.BrowserFactory;

import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IEBrowserManager implements BrowserFactory {
    @Override
    public WebDriver getDriver() {
        if (!GlobalConstants.OS_NAME.toUpperCase().startsWith("WINDOWS")) {
            throw new BrowserNotSupportedException("Internet Explorer is not supported on " + GlobalConstants.OS_NAME);
        }
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.destructivelyEnsureCleanSession();
        ieOptions.ignoreZoomSettings();
        ieOptions.introduceFlakinessByIgnoringSecurityDomains();
        return new InternetExplorerDriver(ieOptions);
    }
}

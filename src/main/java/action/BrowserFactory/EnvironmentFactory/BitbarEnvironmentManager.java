package action.BrowserFactory.EnvironmentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BitbarEnvironmentManager implements EnvironmentFactory {
    private WebDriver driver;
    private String platformName, platformVersion, browserName, browserVersion;

    public BitbarEnvironmentManager(String platformName, String platformVersion, String browserName, String browserVersion) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDriver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bitbarOptions = new HashMap<String, String>();

        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);

        bitbarOptions.put("project", "orangeHRM");
        bitbarOptions.put("apiKey", GlobalConstants.BITBAR_AUTOMATE_KEY);
        bitbarOptions.put("osVersion", platformVersion);
        bitbarOptions.put("resolution", "1920x1080");
        bitbarOptions.put("seleniumVersion", "4");

        capabilities.setCapability("bitbar:options", bitbarOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BITBAR_EU_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

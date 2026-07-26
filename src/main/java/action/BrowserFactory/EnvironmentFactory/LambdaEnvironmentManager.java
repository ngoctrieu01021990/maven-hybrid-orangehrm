package action.BrowserFactory.EnvironmentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class LambdaEnvironmentManager implements EnvironmentFactory{
    private WebDriver driver;
    private String platformName, browserName, browserVersion;

    public LambdaEnvironmentManager(String platformName, String browserName, String browserVersion) {
        this.platformName = platformName;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDriver() {
        MutableCapabilities capability = null;
        browserName = browserName.toLowerCase();

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setPlatformName(platformName);
                fOptions.setBrowserVersion(browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setPlatformName(platformName);
                cOptions.setBrowserVersion(browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setPlatformName(platformName);
                eOptions.setBrowserVersion(browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setPlatformName(platformName);
                sOptions.setBrowserVersion(browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        HashMap<String, Object> lambdaOptions = new HashMap<String, Object>();
        lambdaOptions.put("username", GlobalConstants.LAMBDA_USERNAME);
        lambdaOptions.put("accessKey", GlobalConstants.LAMBDA_AUTOMATE_KEY);
        lambdaOptions.put("build", "orangeHRM lambdatest - " + formater.format(calendar.getTime()));
        lambdaOptions.put("project", "orangeHRM");
        lambdaOptions.put("name", "Run on " + platformName + " | " + browserName + " | " + browserVersion);
        lambdaOptions.put("w3c", true);
        lambdaOptions.put("selenium_version", "4.45.0");

        capability.setCapability("LT:Options", lambdaOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.LAMBDA_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

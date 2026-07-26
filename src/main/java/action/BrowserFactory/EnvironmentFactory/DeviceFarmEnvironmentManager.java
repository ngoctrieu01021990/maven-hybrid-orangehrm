package action.BrowserFactory.EnvironmentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

import java.net.MalformedURLException;
import java.net.URL;

public class DeviceFarmEnvironmentManager implements EnvironmentFactory {
    private WebDriver driver;
    private String platformName, browserName, browserVersion;

    public DeviceFarmEnvironmentManager(String platformName, String browserName, String browserVersion) {
        this.platformName = platformName;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDriver() {
        MutableCapabilities capability = null;
        browserName = browserName.toLowerCase();

        switch (browserName.toLowerCase()) {
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

        DeviceFarmClient client= DeviceFarmClient.builder().region(Region.US_WEST_2).build();
        CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300).projectArn(GlobalConstants.AWS_DEVICE_FARM).build();
        URL testGridUrl = null;

        try {
            CreateTestGridUrlResponse response = client.createTestGridUrl(request);
            testGridUrl = new URL(response.url());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new RemoteWebDriver(testGridUrl,capability);
        return driver;
    }
}

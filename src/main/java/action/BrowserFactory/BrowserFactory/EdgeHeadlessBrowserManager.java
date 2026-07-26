package action.BrowserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--headless");
        edgeOptions.addArguments("window-size=1920x1080");
        return new EdgeDriver(edgeOptions);
    }
}

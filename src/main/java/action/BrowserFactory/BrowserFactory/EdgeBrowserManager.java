package action.BrowserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        //Config capability
        edgeOptions.addArguments("");
        edgeOptions.addArguments("");
        return new EdgeDriver(edgeOptions);
    }
}

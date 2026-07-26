package action.BrowserFactory.EnvironmentFactory;

import org.openqa.selenium.WebDriver;

public interface EnvironmentFactory {
    public abstract WebDriver createDriver();
}

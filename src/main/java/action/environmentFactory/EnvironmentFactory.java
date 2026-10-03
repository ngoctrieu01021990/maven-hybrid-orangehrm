package action.environmentFactory;

import org.openqa.selenium.WebDriver;

public interface EnvironmentFactory {
    public abstract WebDriver createDriver();
}

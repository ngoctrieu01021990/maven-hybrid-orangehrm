package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class EmergencyContactPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public EmergencyContactPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}

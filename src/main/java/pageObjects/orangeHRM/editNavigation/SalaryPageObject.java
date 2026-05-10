package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class SalaryPageObject extends EditNavigatorPageObject {
    private WebDriver driver;
    public SalaryPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}

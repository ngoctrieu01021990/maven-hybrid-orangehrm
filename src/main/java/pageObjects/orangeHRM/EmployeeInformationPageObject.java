package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class EmployeeInformationPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeInformationPageObject(WebDriver driver) {
        this.driver = driver;
    }

}

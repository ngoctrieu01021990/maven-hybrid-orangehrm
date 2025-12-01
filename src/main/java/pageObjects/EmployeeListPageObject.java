package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAddEmployeeButton() {
        waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver,EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
    }
}

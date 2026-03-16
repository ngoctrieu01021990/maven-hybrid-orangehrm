package pageObjects.orangeHRM.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

public class PersonalDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get Firstname textbox attribute value")
    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMProperty(driver,PersonalDetailPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    @Step("Get Lastname textbox attribute value")
    public String getLastNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getElementDOMProperty(driver,PersonalDetailPageUI.LAST_NAME_TEXTBOX,"value");
    }

    @Step("Get EmployeeID textbox attribute value")
    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver,PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX,"value");
    }
}

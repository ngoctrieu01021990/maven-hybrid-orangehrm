package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.editNavigation.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter to Firstname textbox with value: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to Lastname textbox with value: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Get Employee ID from Add Employee page")
    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    @Step("Click to Save button and navigate to Personal page")
    public PersonalDetailPageObject clickToSaveButton() {
        waitElementVisible(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeePageUI.SPINNER_ICON);
        return PageGenerator.getPage(PersonalDetailPageObject.class,driver);
    }

}

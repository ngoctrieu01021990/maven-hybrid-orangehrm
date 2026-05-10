package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.editNavigation.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Add Employee and navigate to Add Employee page")
    public AddEmployeePageObject clickToAddEmployeeButton() {
        waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageGenerator.getPage(AddEmployeePageObject.class, driver);
    }

    @Step("Click to Edit button navigate to Personal detail page")
    public PersonalDetailPageObject clickToEditButton() {
        waitElementClickable(driver, EmployeeListPageUI.EDIT_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListPageUI.EDIT_EMPLOYEE_BUTTON);
        return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
    }



}

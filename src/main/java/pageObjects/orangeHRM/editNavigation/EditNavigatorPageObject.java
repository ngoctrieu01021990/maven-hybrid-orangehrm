package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
    WebDriver driver;
    String className;
    String extendVariable;

    public EditNavigatorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //C1: 10 hàm cho 10 page
    public JobPageObject openJobPage() {
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver, EditNavigatorPageUI.JOB_LINK);
        return PageGenerator.getPage(JobPageObject.class, driver);
    }

    public PersonalDetailPageObject openPersonalDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
    }

    public DependentsPageObject openDependentPage() {
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        clickToElement(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        return PageGenerator.getPage(DependentsPageObject.class, driver);
    }

    public ContactDetailPageObject openContactDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGenerator.getPage(ContactDetailPageObject.class, driver);
    }

    //C2: 1 hàm cho 10 page
    public EditNavigatorPageObject openEditNavigatorPageByName(String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

        switch (pageName) {
            case "Personal Details":
                return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            case "Job":
                return PageGenerator.getPage(JobPageObject.class, driver);
            case "Dependents":
                return PageGenerator.getPage(DependentsPageObject.class, driver);
            case "Contact Details":
                return PageGenerator.getPage(ContactDetailPageObject.class, driver);
            default:
                throw new IllegalArgumentException("Page name is invalid" + pageName);
        }
    }

    //C3: 1 hàm cho 10 page k ần switch case
    public void openEditNavigatorByName(String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

    }

    @Step("Click to {0} module in Menu Item profile")
    public void clickToMenuProfile(WebDriver driver, String moduleName) {
        waitElementClickable(driver, EditNavigatorPageUI.MODULE_BY_TEXT_IN_MENU_PROFILE_ITEM, moduleName);
        clickToElement(driver, EditNavigatorPageUI.MODULE_BY_TEXT_IN_MENU_PROFILE_ITEM, moduleName);
    }

    @Step("Click to {0} button by text")
    public void clickToButtonByGroupText(WebDriver driver, String groupText, String buttonText) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_BUTTON_BY_GROUP_LABEL, groupText, buttonText);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_BUTTON_BY_GROUP_LABEL, groupText, buttonText);
    }

    public boolean isTableinfoDisplayed(String fieldName, String fieldValue) {
        waitElementVisible(driver, EditNavigatorPageUI.DYNAMIC_TABLE_INFO, fieldName, fieldValue);
        return isElementDisplayed(driver, EditNavigatorPageUI.DYNAMIC_TABLE_INFO, fieldName, fieldValue);
    }

    @Step("Enter to {0} date picker by label with value {1}")
    public void enterToDatePickerByLabel(WebDriver driver, String dateLabel, String valueDate) {
        waitElementVisible(driver, EditNavigatorPageUI.DYNAMIC_DATE_PICKER_BY_LABEL, dateLabel);
        sendkeyToElement(driver, EditNavigatorPageUI.DYNAMIC_DATE_PICKER_BY_LABEL, valueDate, dateLabel);
    }

    public String getDatepickerValueByLabel(WebDriver driver, String dateValue) {
        waitElementVisible(driver, EditNavigatorPageUI.DYNAMIC_DATE_PICKER_BY_LABEL, dateValue);
        return getElementDOMProperty(driver, EditNavigatorPageUI.DYNAMIC_DATE_PICKER_BY_LABEL, "value", dateValue);
    }


    @Step("Enter to {0} textbox by label with value {1}")
    public void enterToTextByLabel(WebDriver driver, String textboxLabel, String index, String valueToSendkey) {
        waitElementVisible(driver, EditNavigatorPageUI.TEXTBOX_BY_LABEL, textboxLabel, index);
        sendkeyToElement(driver, EditNavigatorPageUI.TEXTBOX_BY_LABEL, valueToSendkey, textboxLabel, index);
    }

}

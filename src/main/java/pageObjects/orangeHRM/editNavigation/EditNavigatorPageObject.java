package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
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
}

package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
    WebDriver driver;

    public EditNavigatorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    // 9 page nằm trong edit employee
    public JobPageObject openJobPage() {
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver,EditNavigatorPageUI.JOB_LINK);
        return PageGenerator.getPage(JobPageObject.class,driver);
    }

    public PersonalDetailPageObject openPersonalDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver,EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        return PageGenerator.getPage(PersonalDetailPageObject.class,driver);
    }

    public DependentsPageObject openDependentPage() {
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        clickToElement(driver,EditNavigatorPageUI.DEPENDENT_LINK);
        return PageGenerator.getPage(DependentsPageObject.class,driver);
    }

    public ContactDetailPageObject openContactDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver,EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGenerator.getPage(ContactDetailPageObject.class,driver);
    }
}

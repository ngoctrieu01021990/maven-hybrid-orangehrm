package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to PIM module")
    public EmployeeListPageObject clickToPIMModule() {
        waitElementClickable(driver, DashboardPageUI.PIM_MODULE);
        clickToElement(driver, DashboardPageUI.PIM_MODULE);
        return PageGenerator.getPage(EmployeeListPageObject.class,driver);
    }
}

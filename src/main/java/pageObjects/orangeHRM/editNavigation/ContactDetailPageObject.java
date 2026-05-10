package pageObjects.orangeHRM.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.ContactDetailPageUI;

public class ContactDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public ContactDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}

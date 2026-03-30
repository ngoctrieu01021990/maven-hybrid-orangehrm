package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.LoginPageUI;

public class LoginPO extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPO clickCreateAnAccountLink() {
        waitElementClickable(driver, LoginPageUI.CREATE_ACCOUNT_BTN);
        clickToElement(driver,LoginPageUI.CREATE_ACCOUNT_BTN);
        return PageGenerator.getPage(RegisterPO.class, driver);
    }
}

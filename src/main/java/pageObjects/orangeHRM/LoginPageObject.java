package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter to Username textbox with: {0}")
    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, username);
    }

    @Step("Enter to Password textbox with: {0}")
    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click to Login button")
    public DashboardPageObject clickToLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(DashboardPageObject.class,driver);
    }
}

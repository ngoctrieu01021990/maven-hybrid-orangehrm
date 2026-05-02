package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.saucelab.LoginPageUI;

public class LoginPO extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }


    public ProductPO loginToSauce(String userName, String password) {
        waitElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);

        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(ProductPO.class, driver);
    }
}

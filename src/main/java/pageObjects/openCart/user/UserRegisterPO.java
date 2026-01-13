package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToFirtName(String firtname) {
        waitListElementVisible(driver, UserRegisterPageUI.FIRT_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.FIRT_NAME_TEXTBOX, firtname);
    }

    public void enterToLastName(String lastname) {
        waitListElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastname);
    }

    public void enterToEmail(String email) {
        waitListElementVisible(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX, email);
    }

    public void enterToPassword(String password) {
        waitListElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void acceptPrivacyCheckbox() {
        waitElementClickable(driver, UserRegisterPageUI.AGREE_CHECKBOX);
        checkToCheckbox(driver, UserRegisterPageUI.AGREE_CHECKBOX);
    }

    public void clickContinueButton() {
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
    }

    public boolean isSuccessMessageDisplayed() {
        waitListElementVisible(driver, UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
        return isElementDisplayed(driver,UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
    }
}

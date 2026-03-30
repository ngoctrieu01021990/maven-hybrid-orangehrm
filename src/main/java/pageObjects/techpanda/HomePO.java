package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.HomePageUI;

public class HomePO extends BasePage {
    private WebDriver driver;

    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPO openLoginPage() {
        waitElementClickable(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        return PageGenerator.getPage(LoginPO.class, driver);
    }
}

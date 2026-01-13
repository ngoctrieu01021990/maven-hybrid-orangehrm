package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGenerator {
  /*  public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }*/

    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            //Lấy contructor nhận Webdriver
            Constructor<T> contructor = pageClass.getConstructor(WebDriver.class);

            //Tạo instance mới của page class
            return contructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Can not init Page Object class:" + pageClass.getSimpleName(), e);
        }
    }
}
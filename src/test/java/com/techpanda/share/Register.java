package com.techpanda.share;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;


public class Register extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeTest
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        homePage = PageGenerator.getPage(HomePO.class, driver);

        loginPage = homePage.openLoginPage();
        registerPage = loginPage.clickCreateAnAccountLink();

        registerPage.enterToFirstName("Automation");
        registerPage.enterToLastName("FC");
        registerPage.enterToEmail("fc" + getRandomNumber() + "@hotmail.com");
        registerPage.enterToPassword("Automation@123");
        registerPage.enterToConfirmPassword("Automation@123");
        registerPage.clickToRegisterButton();
//        myAccountPage = registerPage.acceptContinueAlert();
        myAccountPage = PageGenerator.getPage(MyAccountPO.class, driver);

        verifyEquals(myAccountPage.getSuccessMessage(), "Thank you for registering with Main Website Store.");

        cookies = myAccountPage.getPageCookies(driver);

        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }

        closeBrowser();
    }

    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private RegisterPO registerPage;
    public static Set<Cookie> cookies;
}

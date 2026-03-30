package com.techpanda;

//import từ thư viện

import com.techpanda.share.Register;
import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;

import java.util.Set;


public class Level_20_Cookies extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        this.cookies = Register.cookies;

        homePage = PageGenerator.getPage(HomePO.class, driver);
        loginPage = homePage.openLoginPage();
        loginPage.setPageCookies(driver, this.cookies);

        loginPage.refreshToPage(driver);

        myAccountPage = PageGenerator.getPage(MyAccountPO.class, driver);
        verifyEquals(myAccountPage.getMyAccountPageTitle(),"My Dashboard");

        //--> Vào trang My Account
    }

    @Test
    public void Employee_01_CreateNewEmployee() {

    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private Set<Cookie> cookies;
}

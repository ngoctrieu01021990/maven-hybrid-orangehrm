package com.opencart;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;


public class Level_10_Multiple_Driver extends BaseTest {

    @Parameters({"userUrl", "adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String userURL, String adminURL, String browserName) {
        //gán dữ liệu
        this.userURL = userURL;
        this.adminURL = adminURL;

        adminUser = "automation";
        adminPassword = "Auto123$$##";

        userFirtname = "John";
        userLastname = "Terry";
        userEmailAddress = "johnterry" + getRandomNumber() + "@gmail.com";
        userPassword = "Auto123@@@";

        //mở browser lên là trang user
        userDriver = getBrowserDriver(userURL, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        adminDriver = getBrowserDriver(adminURL, browserName);
        adminLoginPage=PageGenerator.getPage(AdminLoginPO.class, adminDriver);
    }

    @Test
    public void OpenCart_01_Loggin_Without_Logout() {
        //firefox của user
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, userDriver);

        userRegisterPage = userLoginPage.clickContinueButton();

        userRegisterPage.enterToFirtName(userFirtname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //firefox của admin
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplay());

        //firefox của user
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, userDriver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser(userDriver);
        closeBrowser(adminDriver);
    }

    private WebDriver userDriver;
    private WebDriver adminDriver;
    private String userURL, adminURL;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserMyAccountPO userMyAccountPage;
    private UserLoginPO userLoginPage;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private String adminUser, adminPassword;
    private String userWindowID, adminWindowID;
    private String userFirtname, userLastname, userEmailAddress, userPassword;
}

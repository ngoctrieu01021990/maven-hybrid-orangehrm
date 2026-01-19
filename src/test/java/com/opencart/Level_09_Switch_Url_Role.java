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


public class Level_09_Switch_Url_Role extends BaseTest {

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
        driver = getBrowserDriver(userURL, browserName);

        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);
    }

    @Test(enabled = false)
    public void OpenCart_01_Logging_And_Logout() {
        /*Giống hành vi của user*/
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickContinueButton();

        userRegisterPage.enterToFirtName(userFirtname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        //user--> admin
        userHomePage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        // login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        //logout
        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //admin--> user
        userHomePage = adminLoginPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        // login
        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //user--> admin
        userMyAccountPage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        //admin--> user
        userHomePage = adminDashboardPage.openUserSite(driver, userURL);
    }

    @Test(enabled = false)
    public void OpenCart_02_Loggin_Without_Logout() {
        /*Tiện cho việc dev/test*/
        //user login vào đăng ký rồi mua hàng,...
        // user k log out
        // chuyển qua trang admin --> login 1 lần
        // admin verify đơn hàng
        // admin k log out
        // chuyển qua trang user
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickContinueButton();

        userRegisterPage.enterToFirtName(userFirtname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        //--> k logout nên vẫn ở trang register

        //user--> admin
        userHomePage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        // login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        // k logout nên vẫn ở trang customer

        //admin--> user
        userHomePage = adminLoginPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //user --> admin
        userMyAccountPage.openAdminSite(driver, adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class, driver);

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        // fail vì đang có bug
    }

    @Test
    public void OpenCart_03_Multiple_Tab() {
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickContinueButton();

        userRegisterPage.enterToFirtName(userFirtname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        userRegisterPage.openUrlByNewTab(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        // login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminWindowID = adminCustomerPage.getCurrentWindowID(driver);

        //admin--> user
        adminCustomerPage.switchToWindowByID(driver, adminWindowID);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class, driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //user --> admin
        userMyAccountPage.switchToWindowByID(driver, userWindowID);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class, driver);

        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplay());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
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

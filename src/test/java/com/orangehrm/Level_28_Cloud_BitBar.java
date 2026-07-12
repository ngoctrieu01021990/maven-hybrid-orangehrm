package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import unilities.IEnvironment;


public class Level_28_Cloud_BitBar extends BaseTest {

    IEnvironment environmentConfig;

    @Parameters({"environment", "platformName", "platformVersion", "browserName", "browserVersion"})
    @BeforeClass()
    public void beforeClass(String environment, String platformName, String platformVersion, String browserName, String browserVersion) {
        ConfigFactory.setProperty("environment", environment);
        environmentConfig = ConfigFactory.create(IEnvironment.class);

        driver = getBrowserDriverBitBar(environmentConfig.appUrl(), platformName, platformVersion, browserName, browserVersion);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        adminUserName = "Admin";
        adminPassword = "admin123";
        loginPage.enterToTextboxByLabel(driver, "Username", adminUserName);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));
    }

    @Test()
    public void Employee_01_NewEmployee() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private EmployeeListPageObject employeeListPage;
    private DashboardPageObject dashboardPage;
    private String adminUserName, adminPassword;
}

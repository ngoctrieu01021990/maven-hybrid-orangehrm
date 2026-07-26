package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import unilities.IEnvironment;


public class Level_29_EnvironmentFactory extends BaseTest {

    IEnvironment serverConfig;

    //String environmentName, String url, String osName, String osVersion, String browserName, String browserVersion
    @Parameters({"server", "environment", "osName", "osVersion", "browserName", "browserVersion", "ipAddress", "portNumber"})
    @BeforeClass()
    public void beforeClass(String serverName, @Optional("LOCAL")String environmentName,
                            @Optional("Windows")String osName, @Optional("11")String osVersion,
                            @Optional("chrome")String browserName, @Optional("lastest")String browserVersion,
                            @Optional("localhost")String ipAddress, @Optional("4444")String portNumber) {
        ConfigFactory.setProperty("environment", serverName);
        serverConfig = ConfigFactory.create(IEnvironment.class);

        driver = getBrowserDriver(environmentName, serverConfig.appUrl(), osName, osVersion, browserName, browserVersion, ipAddress, portNumber);

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

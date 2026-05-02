package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Level_23_Browser_Config extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        // selenium v3
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setVersion("144");

        // selenium v4
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("144");
        chromeOptions.setEnableDownloads(true);


        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBrowserVersion("144");
        firefoxOptions.setEnableDownloads(true);
        firefoxOptions.configureFromEnv();

        EdgeOptions edgeOptions =new EdgeOptions();
        edgeOptions.useWebView(true);
        edgeOptions.useWebView(true);

        adminUserName = "Admin";
        adminPassword = "admin123";

        employeeFirstName = "John";
        employeeLastName = "Terry";
        employeeUsername = "john" + getRandomNumber();
        employeePassword = "Auto222$$$";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUserName);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));
    }

    @Test
    public void Employee_01_NewEmployee() {

    }

    @Test
    public void Employee_02_UploadAvatar() {

    }

    @Test
    public void Employee_03_EditPersonalDetails() {
    }

    @Test
    public void Employee_04_ContactDetais() {
    }

    @Test
    public void Employee_05_EmergencyDetais() {
    }

    @Test
    public void Employee_06_Dependents() {
    }

    @Test
    public void Employee_07_Jobs() {
    }

    @Test
    public void Employee_08_Salary() {
    }

    @Test
    public void Employee_09_Tax() {
    }

    @Test
    public void Employee_10_Qualifications() {
    }

    @Test
    public void Employee_11_Search() {
    }

    @AfterClass
    public void afterClass() {
        //closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
    private String employeeUsername, employeePassword;
}

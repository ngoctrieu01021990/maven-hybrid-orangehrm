package com.orangehrm;

import com.aventstack.chaintest.plugins.ChainTestListener;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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


public class Level_15_ChainTest extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        this.browserName = browserName.toUpperCase();
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }

    @Test
    public void Employee_01_CreateEmployee() {
        ChainTestListener.log("Run on browser - " + browserName);
        ChainTestListener.log("Enter to Username and Password with info: " + adminUserName + " | " + adminPassword);

        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

        ChainTestListener.log("Navigate to Dashboard page");
        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        ChainTestListener.log("Navigate to Employee Search page");
        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
    }

    @Test
    public void Employee_02_ViewEmployee() {
        ChainTestListener.log("Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("Enter to FirstName and LastName with info: " + employeeFirstName + " | " + employeeLastName);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }

    @Test
    public void Employee_03_EditEmployee() {
        ChainTestListener.log("Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }

    @Test
    public void Employee_04_RemoveEmployee() {
        ChainTestListener.log("Verify FirstName is displayed: " + employeeLastName);
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);

        ChainTestListener.log("Verify LastName is displayed: " + employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);

        ChainTestListener.log("Verify EmployeeId is displayed: " + employeeID);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeFirstName);
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName, browserName;
}

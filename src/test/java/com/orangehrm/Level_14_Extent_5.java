package com.orangehrm;

import com.aventstack.extentreports.Status;
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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;


public class Level_14_Extent_5 extends BaseTest {

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
    public void Employee_01_CreateEmployee(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "Employee_01_CreateEmployee");
        ExtentManager.getTest().log(Status.INFO, "Enter to Username and Password with info: " + adminUserName + " | " + adminPassword);

        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

        ExtentManager.getTest().log(Status.INFO, "Navigate to Dashboard page");
        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        ExtentManager.getTest().log(Status.INFO, "Navigate to Employee Search page");
        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
    }

    @Test
    public void Employee_02_ViewEmployee(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "Employee_02_ViewEmployee");
        ExtentManager.getTest().log(Status.INFO, "Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ExtentManager.getTest().log(Status.INFO, "Enter to FirstName and LastName with info: " + employeeFirstName + " | " + employeeLastName);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }

    @Test
    public void Employee_03_EditEmployee(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "Employee_03_EditEmployee");
        ExtentManager.getTest().log(Status.INFO, "Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }

    @Test
    public void Employee_04_RemoveEmployee(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "Employee_04_RemoveEmployee");
        ExtentManager.getTest().log(Status.INFO, "Verify FirstName is displayed: " + employeeLastName);
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);

        ExtentManager.getTest().log(Status.INFO, "Verify LastName is displayed: " + employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);

        ExtentManager.getTest().log(Status.INFO, "Verify EmployeeId is displayed: " + employeeID);
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

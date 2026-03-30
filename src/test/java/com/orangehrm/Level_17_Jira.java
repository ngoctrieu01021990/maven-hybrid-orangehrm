package com.orangehrm;

import core.BaseTest;
import io.qameta.allure.*;
import jiraConfigs.JiraCreateIssue;
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


@Epic("Employee")
public class Level_17_Jira extends BaseTest {

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

    @Description("Create new an Employee")
    @Story("#IBP122 - Employee CRUD")
    @Severity(SeverityLevel.NORMAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_01_CreateEmployee() {

        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
    }

    @Description("View existing Employee")
    @Story("#IBP122 - Employee CRUD")
    @Severity(SeverityLevel.MINOR)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_02_ViewEmployee() {
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }

    @Description("Edit an Employee")
    @Story("#IBP122 - Employee CRUD")
    @Severity(SeverityLevel.CRITICAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_03_EditEmployee() {
        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }

    @Description("Remove an Employee")
    @Story("#IBP122 - Employee CRUD")
    @Severity(SeverityLevel.BLOCKER)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_04_RemoveEmployee() {
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);

        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);

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

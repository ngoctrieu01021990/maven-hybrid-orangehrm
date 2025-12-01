package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;


public class Login_04_Page_Object extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = new LoginPageObject(driver);
    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        // action của login
        loginPage.enterToUsernameTextbox("Admin");
        loginPage.enterToPasswordTextbox("admin123");
        loginPage.clickToLoginButton();

        //action cuả dashboard
        dashboardPage = new DashboardPageObject(driver);
        dashboardPage.clickToPIMModule();

        //action của employee list
        employeeListPage = new EmployeeListPageObject(driver);
        employeeListPage.clickToAddEmployeeButton();

        //action của add employee
        addEmployeePage = new AddEmployeePageObject(driver);
        addEmployeePage.enterToFirstNameTextbox("");
        addEmployeePage.enterToLastNameTextbox("");
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickToSaveButton();

        personalDetailPage = new PersonalDetailPageObject();
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }

    @Test
    public void Employee_02_EditEmployee() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID;
}

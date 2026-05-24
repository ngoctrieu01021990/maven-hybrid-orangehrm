package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.EditNavigatorPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import testData.orangehrm.EmployeeData;
import unilities.DataConfigNet;


public class Level_25_DataTest_III_Static extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass(groups = "employee")
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        dataConfigNet = DataConfigNet.getData();

        adminUserName = "Admin";
        adminPassword = "admin123";
        employeeID = String.valueOf(getRandomNumber());

        employeeFirstName = dataConfigNet.getFirstName();
        employeeLastName = dataConfigNet.getLastName();
        employeeUsername = dataConfigNet.getUsername();
        employeePassword = dataConfigNet.getPassword();

        editEmployeeFirstName = dataConfigNet.getFirstName();
        editEmployeeLastName = dataConfigNet.getLastName();
        eNationality = "Viet Nam";
        eBirth = "30-12-2002";

        loginPage.enterToTextboxByLabel(driver, "Username", EmployeeData.Admin.ADMIN_USERNAME);
        loginPage.enterToTextboxByLabel(driver, "Password", EmployeeData.Admin.ADMIN_PASSWORD);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));
    }

    @Test(groups = "employee")
    public void Employee_01_NewEmployee() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", EmployeeData.NewEmployee.FIRST_NAME);
        addEmployeePage.enterToTextboxByName(driver, "lastName", EmployeeData.NewEmployee.LAST_NAME);
        //addEmployeePage.enterToTextboxByName(driver, "Employee Id", employeeID);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", EmployeeData.NewEmployee.USER_NAME);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", EmployeeData.NewEmployee.PASSWORD);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", EmployeeData.NewEmployee.PASSWORD);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), EmployeeData.NewEmployee.FIRST_NAME);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), EmployeeData.NewEmployee.FIRST_NAME);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private EditNavigatorPageObject editNavigatorPage;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private DataConfigNet dataConfigNet;

    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
    private String employeeUsername, employeePassword;
    private String editEmployeeFirstName, editEmployeeLastName, eNationality, eBirth ;

}

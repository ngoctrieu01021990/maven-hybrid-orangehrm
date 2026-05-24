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
import testData.model.Employee;


public class Level_25_DataTest_V_JSON extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass()
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        employeeData = Employee.getEmployee();

        adminUsername = "Admin";
        adminPassword = "admin123";
        employeeID = String.valueOf(getRandomNumber());

        employeeUsername = employeeData.getUserName() + getRandomNumber();
        employeeEmail = employeeData.getEmployeeEmail() + getRandomNumber() + "@gmail.com";


        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
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

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeData.getEmployeeFirstName());
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeData.getEmployeeLastName());
        //addEmployeePage.enterToTextboxByName(driver, "Employee Id", employeeID);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeData.getUserName());
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employeeData.getPassword());
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeeData.getPassword());

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeData.getEmployeeFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeData.getEmployeeLastName());
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
    private Employee employeeData;

    private String employeeID, adminUsername, adminPassword, employeeUsername, employeePassword, employeeEmail;

}

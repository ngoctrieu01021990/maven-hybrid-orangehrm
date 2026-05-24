package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import core.GlobalConstants;
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
import testData.orangehrm.Employee_Data;
import unilities.DataConfigNet;


public class Level_25_DataTest_IV_POJO extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass(groups = "employee")
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        employeeData = Employee_Data.getEmployeeData();
        employeeID = String.valueOf(getRandomNumber());

        employeeData.setFirstName("John");
        employeeData.setLastName("Terry");
        employeeData.setUserName("john.terry");
        employeeData.setPassword("Auto@12345");

        loginPage.enterToTextboxByLabel(driver, "Username", GlobalConstants.ORANGEHRM_ADMIN_USERNAME);
        loginPage.enterToTextboxByLabel(driver, "Password", GlobalConstants.ORANGEHRM_ADMIN_PASSWORD);
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

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeData.getFirstName());
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeData.getLastName());
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

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeData.getFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeData.getLastName());
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
    private Employee_Data employeeData;

    private String employeeID;

}

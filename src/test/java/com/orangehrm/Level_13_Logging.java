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
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Level_13_Logging extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }

    @Test
    public void Employee_01_CreateNewEmployee() {
//        log.info("NewEmployee - STEP 01: Enter to Username and Password with info: " + adminUserName + " | " + adminPassword);
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

//        log.info("NewEmployee - STEP 02: Navigate to Dashboard page");
        dashboardPage = loginPage.clickToLoginButton();
        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

//        log.info("NewEmployee - STEP 03: Navigate to Employee Search page");
        employeeListPage = dashboardPage.clickToPIMModule();
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver)); //Failed

//        log.info("NewEmployee - STEP 04: Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

//        log.info("NewEmployee - STEP 05: Enter to FirstName and LastName with info: " + employeeFirstName + " | " + employeeLastName);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

//        log.info("NewEmployee - STEP 06: Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

//        log.info("NewEmployee - STEP 07: Verify FirstName is displayed: " + employeeLastName);
        verifyEquals(personalDetailPage.getFirstNameTextboxValue(), employeeLastName); //Failed

//        log.info("NewEmployee - STEP 08: Verify LastName is displayed: "  + employeeFirstName );
        verifyEquals(personalDetailPage.getLastNameTextboxValue(), employeeFirstName); //Failed

//        log.info("NewEmployee - STEP 09: Verify EmployeeId is displayed: " + employeeID);
        verifyEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);
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
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
}

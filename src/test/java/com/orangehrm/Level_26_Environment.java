package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.aeonbits.owner.ConfigFactory;
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
import testData.model.Employee;
import unilities.ExcelConfig;
import unilities.IEnvironment;
import unilities.PropertiesConfig;


public class Level_26_Environment extends BaseTest {
    IEnvironment environment;

    @Parameters({"browser"})
    @BeforeClass()
    public void beforeClass(String browserName) {
        String serverName=System.getProperty("env");
        ConfigFactory.setProperty("environment", serverName);
        environment = ConfigFactory.create(IEnvironment.class);

        driver = getBrowserDriver(environment.appUrl(), browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("employee");

        employeeID = String.valueOf(getRandomNumber());

        employeeUsername = excelConfig.getCellData("UserName", 1) + getRandomNumber();
        employeeEmail = excelConfig.getCellData("EmailAddress", 1) + getRandomNumber() + "@gmail.com";


        loginPage.enterToTextboxByLabel(driver, "Username", environment.appUser());
        loginPage.enterToTextboxByLabel(driver, "Password", environment.appPass());
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

        addEmployeePage.enterToTextboxByName(driver, "firstName", excelConfig.getCellData("FirstName", 1));
        addEmployeePage.enterToTextboxByName(driver, "lastName", excelConfig.getCellData("LastName", 1));
        //addEmployeePage.enterToTextboxByName(driver, "Employee Id", employeeID);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", excelConfig.getCellData("UserName", 1));
        addEmployeePage.enterToTextboxByLabel(driver, "Password", excelConfig.getCellData("Password", 1));
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", excelConfig.getCellData("Password", 1));

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), excelConfig.getCellData("FirstName", 1));
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), excelConfig.getCellData("LastName", 1));
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

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
    private Employee employeeData;
    private ExcelConfig excelConfig;
    private String employeeID, adminUsername, adminPassword, employeeUsername, employeePassword, employeeEmail;

    private PropertiesConfig propertiesConfig;

}

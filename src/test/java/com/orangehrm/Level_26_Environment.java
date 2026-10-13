package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
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
import utilities.ExcelConfig;
import utilities.IEnvironment;
import utilities.PropertiesConfig;


public class Level_26_Environment extends BaseTest {
    IEnvironment environment;

    @Parameters({"browser"})
    @BeforeClass()
    public void beforeClass(String browserName) {
        String serverName=System.getProperty("env");
        ConfigFactory.setProperty("environment", serverName);
        environment = ConfigFactory.create(IEnvironment.class);

        driver = getBrowserDriver(environment.appUrl(), browserName);

        String pageSource = driver.getPageSource();

        System.out.println("CURRENT URL: " + driver.getCurrentUrl());
        System.out.println("PAGE TITLE: " + driver.getTitle());
        System.out.println("PAGE SOURCE PREVIEW: "
                + pageSource.substring(0, Math.min(pageSource.length(), 3000)));

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

        System.out.println("========== AFTER LOGIN DEBUG ==========");
        System.out.println("URL = " + driver.getCurrentUrl());
        System.out.println("TITLE = " + driver.getTitle());

        System.out.println("Admin text count = "
                + driver.findElements(By.xpath("//*[normalize-space()='Admin']")).size());

        System.out.println("Admin span count = "
                + driver.findElements(By.xpath("//span[normalize-space()='Admin']")).size());

        System.out.println("Admin link count = "
                + driver.findElements(By.xpath("//a[.//span[normalize-space()='Admin']]")).size());

        System.out.println("Menu item count = "
                + driver.findElements(By.xpath("//*[contains(@class,'oxd-main-menu-item')]")).size());

        System.out.println("===== BODY TEXT =====");
        System.out.println(driver.findElement(By.tagName("body")).getText());

        System.out.println("========== END DEBUG ==========");

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

//        addEmployeePage.clickToButtonByText(driver, "Save");
//        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
//
//        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        addEmployeePage.clickToButtonByText(driver, "Save");

        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        personalDetailPage.sleepInSecond(2);

        System.out.println("===== AFTER CLICK SAVE =====");
        System.out.println("URL = " + driver.getCurrentUrl());
        System.out.println("TITLE = " + driver.getTitle());

        System.out.println("===== PAGE TEXT =====");
        System.out.println(driver.findElement(By.tagName("body")).getText());

        System.out.println("===== SUCCESS TOAST =====");
        System.out.println("Has Successfully Saved = "
                + driver.getPageSource().contains("Successfully Saved"));

        //verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

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

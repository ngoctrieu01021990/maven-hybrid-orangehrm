package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.Dimension;
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


public class Level_22_LiveCode extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "Admin";
        adminPassword = "admin123";

        employeeFirstName = "John";
        employeeLastName = "Terry";
        employeeUsername = "john" + getRandomNumber();
        employeePassword = "Auto222$$$";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUserName);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));
    }

    @Test
    public void Employee_01_NewEmployee() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        // Logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bằng quyền employee vừa tạo
        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        // Đến màn hình dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));

        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);
    }

    @Test
    public void Employee_02_UploadAvatar() {
        personalDetailPage.clickToProfileImage();
        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));

        //get size của hình trước khi upload
        Dimension oldProfileImageSize = personalDetailPage.getProfileNaturalImageSize();


        //Invalid file type
        personalDetailPage.uploadMultipleFiles(driver, "CT01.pdf");
        verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(), "File type not allowed");

        // maximum size
        personalDetailPage.uploadMultipleFiles(driver, "team.jpg");
        verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(), "Attachment Size Exceeded");

        // valid
        personalDetailPage.uploadMultipleFiles(driver, "flower.jpg");

        personalDetailPage.clickToButtonByText(driver, "Save");

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        // get size hình sau khi upload
        Dimension newProfileImageSize = personalDetailPage.getProfileNaturalImageSize();

        // verify 2 cái khác nhau
        verifyNotEquals(oldProfileImageSize, newProfileImageSize);
    }

    @Test
    public void Employee_03_EditPersonalDetails() {
    }

    @Test
    public void Employee_04_ContactDetais() {
    }

    @Test
    public void Employee_05_EmergencyDetais() {
    }

    @Test
    public void Employee_06_Dependents() {
    }

    @Test
    public void Employee_07_Jobs() {
    }

    @Test
    public void Employee_08_Salary() {
    }

    @Test
    public void Employee_09_Tax() {
    }

    @Test
    public void Employee_10_Qualifications() {
    }

    @Test
    public void Employee_11_Search() {
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
    private String employeeUsername, employeePassword;
}

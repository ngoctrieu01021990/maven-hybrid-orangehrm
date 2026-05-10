package com.orangehrm;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.*;


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
        eNationality ="American";
        eMaritalStatus="Single";
        eBirth="1995-02-03";
        eGender = "Female";

        contStreet = "8, MG Road";
        contCity = "Coimbatore";
        contProvince = "Tamil Nadu";
        contCountry = "India";
        contHome = "8408519729";
        contMobile = "8301333945";
        contWork = "8408519332";
        contWorkEmail = "johnterry" + getRandomNumber() + "@gmail.com";

        emerName = "Merry";
        emerRelationship = "wife";
        emerHomeTelephone = "8408519723";
        emerFileName = "flower.jpg";

        depName = "Bob";
        depRelationship = "Child";
        depBirthValue = "2002-14-05";
        depFileName = "moon.jpg";

        jJoinDate = "2026-02-03";
        jJobTitle = "Content Specialist";
        jJobCategory = "Operatives";
        jSubUnit = "Engineering";
        jLocation = "New York Sales Office";
        jEmploymentStatus = "Full-Time Contract";
        jContractStartDate = "2026-02-05";
        jContractDetails = "FILE01.pdf";
        jFileName = "tiger.jpg";

        sSalaryComponent = "Engineer";
        sPayGrade = "Grade 3";
        sPayFrequency = "Monthly";
        sCurrency = "United States Dollar";
        sAmount = "30000";
        sAccountNumber = "johnterry";
        sAccountType = "Checking";
        sRoutingNumber = "12321413";
        sDAmount = "55000";
        sFileName = "FILE01.pdf";

        qCompany = "USA";
        qJobTitle = "Engineer";
        qFrom = "2024-02-05";
        qTo = "2025-01-12";
        qLevel = "College Undergraduate";
        qGPA = "4.5/5";
        qYear = "2015";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUserName);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));
    }

    @Test(enabled = false)
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

    @Test(enabled = false)
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

        personalDetailPage.selectDropdownByLabel(driver, "Nationality", eNationality);
        personalDetailPage.selectDropdownByLabel(driver, "Marital Status", eMaritalStatus);
        personalDetailPage.enterToDatePickerByLabel(driver, "Date of Birth", eBirth);
        personalDetailPage.clickToRadioByLabel(driver, "Female");

        personalDetailPage.clickToButtonByGroupText(driver, "Personal Details", "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(3);

        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);
        verifyEquals(personalDetailPage.getDropdownSelectedValueByLabel(driver, "Nationality"), eNationality);
        verifyEquals(personalDetailPage.getDropdownSelectedValueByLabel(driver, "Marital Status"), eMaritalStatus);
        verifyEquals(personalDetailPage.getDatepickerValueByLabel(driver, "Date of Birth"), eBirth);
        verifyEquals(personalDetailPage.getSelectedRadioValue(driver, "Female"), eGender);

    }

    @Test
    public void Employee_04_ContactDetais() {
        personalDetailPage.clickToMenuProfile(driver, "Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);
        verifyTrue(contactDetailPage.isLoadingSpinnerDisappear(driver));
        contactDetailPage.sleepInSecond(2);

        contactDetailPage.enterToTextboxByLabel(driver, "Street 1", contStreet);
        contactDetailPage.enterToTextboxByLabel(driver, "City", contCity);
        contactDetailPage.enterToTextboxByLabel(driver, "State/Province", contProvince);
        contactDetailPage.selectDropdownByLabel(driver, "Country", contCountry);
        contactDetailPage.enterToTextboxByLabel(driver, "Home", contHome);
        contactDetailPage.enterToTextboxByLabel(driver, "Mobile", contMobile);
        contactDetailPage.enterToTextboxByLabel(driver, "Work", contWork);
        contactDetailPage.enterToTextboxByLabel(driver, "Work Email", contWorkEmail);

        contactDetailPage.clickToButtonByGroupText(driver, "Contact Details", "Save");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        verifyTrue(contactDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));

        verifyTrue(contactDetailPage.isLoadingSpinnerDisappear(driver));
        contactDetailPage.sleepInSecond(5);

        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Street 1"), contStreet);
        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "City"), contCity);
        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "State/Province"), contProvince);
        verifyEquals(personalDetailPage.getDropdownSelectedValueByLabel(driver, "Country"), "India");
        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Home"), contHome);
        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Mobile"), contMobile);
        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Work"), contWork);
        verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Work Email"), contWorkEmail);
    }

    @Test
    public void Employee_05_EmergencyDetais() {
        contactDetailPage.clickToMenuProfile(driver, "Emergency Contacts");
        emergencyContactPage = PageGenerator.getPage(EmergencyContactPageObject.class, driver);
        verifyTrue(emergencyContactPage.isLoadingSpinnerDisappear(driver));
        emergencyContactPage.sleepInSecond(3);

        emergencyContactPage.clickToButtonByGroupText(driver, "Assigned Emergency Contacts", "Add");

        emergencyContactPage.enterToTextboxByLabel(driver, "Name", emerName);
        emergencyContactPage.enterToTextboxByLabel(driver, "Relationship", emerRelationship);
        emergencyContactPage.enterToTextboxByLabel(driver, "Home Telephone", emerHomeTelephone);
        emergencyContactPage.enterToTextboxByLabel(driver, "Mobile", contMobile);

        emergencyContactPage.clickToButtonByGroupText(driver, "Save Emergency Contact", "Save");
        emergencyContactPage = PageGenerator.getPage(EmergencyContactPageObject.class, driver);

        verifyTrue(emergencyContactPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(emergencyContactPage.isLoadingSpinnerDisappear(driver));
        emergencyContactPage.sleepInSecond(3);

        emergencyContactPage.clickToButtonByGroupText(driver, "Attachments", "Add");

        emergencyContactPage.uploadMultipleFiles(driver, emerFileName);

        emergencyContactPage.clickToButtonByGroupText(driver, "Add Attachment", "Save");

        verifyTrue(emergencyContactPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(emergencyContactPage.isLoadingSpinnerDisappear(driver));
        emergencyContactPage.sleepInSecond(5);

        Assert.assertTrue(emergencyContactPage.isTableinfoDisplayed("Name", emerName));
        Assert.assertTrue(emergencyContactPage.isTableinfoDisplayed("Relationship", emerRelationship));
        Assert.assertTrue(emergencyContactPage.isTableinfoDisplayed("Home Telephone", emerHomeTelephone));
        Assert.assertTrue(emergencyContactPage.isTableinfoDisplayed("Mobile", contMobile));
        Assert.assertTrue(emergencyContactPage.isTableinfoDisplayed("File Name", emerFileName));
    }

    @Test
    public void Employee_06_Dependents() {
        emergencyContactPage.clickToMenuProfile(driver, "Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);
        verifyTrue(dependentsPage.isLoadingSpinnerDisappear(driver));
        dependentsPage.sleepInSecond(3);

        dependentsPage.clickToButtonByGroupText(driver, "Assigned Dependents", "Add");

        dependentsPage.enterToTextboxByLabel(driver, "Name", depName);
        dependentsPage.selectDropdownByLabel(driver, "Relationship", depRelationship);
        dependentsPage.enterToDatePickerByLabel(driver, "Date of Birth", depBirthValue);

        dependentsPage.clickToButtonByGroupText(driver, "Add Dependent", "Save");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);

        verifyTrue(dependentsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(dependentsPage.isLoadingSpinnerDisappear(driver));
        dependentsPage.sleepInSecond(3);

        dependentsPage.clickToButtonByGroupText(driver, "Attachments", "Add");
        dependentsPage.uploadMultipleFiles(driver, depFileName);

        dependentsPage.clickToButtonByGroupText(driver, "Add Attachment", "Save");

        verifyTrue(dependentsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(dependentsPage.isLoadingSpinnerDisappear(driver));
        dependentsPage.sleepInSecond(5);

        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Name", depName));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Relationship", depRelationship));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Date of Birth", depBirthValue));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("File Name", depFileName));
    }

    @Test
    public void Employee_07_Jobs() {
        dependentsPage.clickToMenuProfile(driver, "Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);
        verifyTrue(jobPage.isLoadingSpinnerDisappear(driver));
        jobPage.sleepInSecond(3);

        jobPage.enterToDatePickerByLabel(driver, "Joined Date", jJoinDate);
        jobPage.selectDropdownByLabel(driver, "Job Title", jJobTitle);
        jobPage.selectDropdownByLabel(driver, "Job Category", jJobCategory);
        jobPage.selectDropdownSubChildByLabel(driver, "Sub Unit", jSubUnit);
        jobPage.selectDropdownByLabel(driver, "Location", jLocation);
        jobPage.selectDropdownByLabel(driver, "Employment Status", jEmploymentStatus);
        jobPage.clickToCheckboxByLabel(driver, "Include Employment Contract Details");
        jobPage.enterToDatePickerByLabel(driver, "Contract Start Date", jContractStartDate);
        jobPage.uploadFileByLabel(driver, "Contract Details", jContractDetails);

        //jobPage.scrollElementToBottomByLabel(driver, "Contract Details");

        jobPage.clickToButtonByGroupText(driver, "Job Details", "Save");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        verifyTrue(jobPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(jobPage.isLoadingSpinnerDisappear(driver));
        jobPage.sleepInSecond(3);

        jobPage.clickToButtonByGroupText(driver, "Attachments", "Add");
        jobPage.uploadMultipleFiles(driver, jFileName);

        jobPage.clickToButtonByGroupText(driver, "Add Attachment", "Save");

        verifyTrue(jobPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(jobPage.isLoadingSpinnerDisappear(driver));
        jobPage.sleepInSecond(5);

        verifyEquals(jobPage.getDatepickerValueByLabel(driver, "Joined Date"), jJoinDate);
        verifyEquals(jobPage.getDropdownSelectedValueByLabel(driver, "Job Title"), jJobTitle);
        verifyEquals(jobPage.getDropdownSelectedValueByLabel(driver, "Job Category"), jJobCategory);
        verifyEquals(jobPage.getDropdownSelectedValueByLabel(driver, "Sub Unit"), jSubUnit);
        verifyEquals(jobPage.getDropdownSelectedValueByLabel(driver, "Location"), jLocation);
        verifyEquals(jobPage.getDropdownSelectedValueByLabel(driver, "Employment Status"), jEmploymentStatus);
        verifyEquals(jobPage.getDatepickerValueByLabel(driver, "Contract Start Date"), jContractStartDate);
        Assert.assertTrue(jobPage.isFileUploadedByLabel(driver, jContractDetails, "Contract Details"));
        Assert.assertTrue(jobPage.isTableinfoDisplayed("File Name", jFileName));
    }

    @Test
    public void Employee_08_Salary() {
        jobPage.scrollToBottomPage(driver);
        jobPage.clickToMenuProfile(driver, "Salary");
        salaryPage = PageGenerator.getPage(SalaryPageObject.class, driver);
        verifyTrue(salaryPage.isLoadingSpinnerDisappear(driver));
        salaryPage.sleepInSecond(3);

        salaryPage.clickToButtonByGroupText(driver, "Assigned Salary Components", "Add");

        salaryPage.enterToTextboxByLabel(driver, "Salary Component", sSalaryComponent);
        salaryPage.selectDropdownByLabel(driver, "Pay Grade", sPayGrade);
        salaryPage.sleepInSecond(2);
        salaryPage.selectDropdownByLabel(driver, "Pay Frequency", sPayFrequency);
        salaryPage.selectDropdownByLabel(driver, "Currency", sCurrency);
        salaryPage.enterToTextByLabel(driver, "Amount", "1", sAmount);
        salaryPage.clickToCheckboxByLabel(driver, "Include Direct Deposit Details");
        salaryPage.enterToTextboxByLabel(driver, "Account Number", sAccountNumber);
        salaryPage.selectDropdownByLabel(driver, "Account Type", sAccountType);
        salaryPage.enterToTextboxByLabel(driver, "Routing Number", sRoutingNumber);
        salaryPage.enterToTextByLabel(driver, "Amount", "2", sDAmount);

        salaryPage.scrollDown(driver);
        salaryPage.clickToButtonByGroupText(driver, " Add Salary Component ", "Save");

        verifyTrue(salaryPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(salaryPage.isLoadingSpinnerDisappear(driver));
        salaryPage.sleepInSecond(5);

        salaryPage.clickToButtonByGroupText(driver, "Attachments", "Add");
        salaryPage.uploadMultipleFiles(driver, sFileName);

        salaryPage.clickToButtonByGroupText(driver, "Add Attachment", "Save");

        verifyTrue(salaryPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(salaryPage.isLoadingSpinnerDisappear(driver));
        salaryPage.sleepInSecond(5);

        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Salary Component", sSalaryComponent));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Amount", sAmount));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Currency", sCurrency));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Pay Frequency", sPayFrequency));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("Direct Deposit Amount", sDAmount + ".00"));
        Assert.assertTrue(dependentsPage.isTableinfoDisplayed("File Name", sFileName));
    }

    @Test
    public void Employee_09_Tax() {
    }

    @Test
    public void Employee_10_Qualifications() {
        salaryPage.clickToMenuProfile(driver, "Qualifications");
        qualificationsPage = PageGenerator.getPage(QualificationsPageObject.class, driver);
        verifyTrue(qualificationsPage.isLoadingSpinnerDisappear(driver));
        qualificationsPage.sleepInSecond(3);

        qualificationsPage.clickToButtonByGroupText(driver, "Work Experience", "Add");

        qualificationsPage.enterToTextboxByLabel(driver, "Company", qCompany);
        qualificationsPage.enterToTextboxByLabel(driver, "Job Title", qJobTitle);
        qualificationsPage.enterToTextboxByLabel(driver, "From", qFrom);
        qualificationsPage.enterToTextboxByLabel(driver, "To", qTo);

        qualificationsPage.clickToButtonByGroupText(driver, "Add Work Experience", "Save");
        verifyTrue(qualificationsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(qualificationsPage.isLoadingSpinnerDisappear(driver));
        qualificationsPage.sleepInSecond(5);

        qualificationsPage.clickToButtonByGroupText(driver, "Education", "Add");

        qualificationsPage.selectDropdownByLabel(driver, "Level", qLevel);
        qualificationsPage.enterToTextboxByLabel(driver, "Year", qYear);
        qualificationsPage.enterToTextboxByLabel(driver, "GPA/Score", qGPA);

        qualificationsPage.scrollDown(driver);
        qualificationsPage.clickToButtonByGroupText(driver, "Add Education", "Save");
        verifyTrue(qualificationsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(qualificationsPage.isLoadingSpinnerDisappear(driver));
        qualificationsPage.sleepInSecond(5);

        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("Company", qCompany));
        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("Job Title", qJobTitle));
        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("From", qFrom));
        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("To", qTo));
        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("Level", qLevel));
        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("Year", qYear));
        Assert.assertTrue(qualificationsPage.isTableinfoDisplayed("GPA/Score", qGPA));
    }

    @Test
    public void Employee_11_Search() {
        qualificationsPage.clickToMenuItemHeader(driver, "Employee List");
        employeeInformationPage = PageGenerator.getPage(EmployeeInformationPageObject.class, driver);
        verifyTrue(employeeInformationPage.isLoadingSpinnerDisappear(driver));
        employeeInformationPage.sleepInSecond(3);

        employeeInformationPage.enterToTextboxByLabel(driver, "Employee Id", employeeID);
        employeeInformationPage.clickToButtonByText(driver, "Search");

        Assert.assertTrue(employeeInformationPage.isTableinfoDisplayed(driver,"Id", employeeID));
        Assert.assertTrue(employeeInformationPage.isTableinfoDisplayed(driver,"First (& Middle) Name", employeeFirstName));
        Assert.assertTrue(employeeInformationPage.isTableinfoDisplayed(driver,"Last Name", employeeLastName));
        Assert.assertTrue(employeeInformationPage.isTableinfoDisplayed(driver,"Job Title", jJobTitle));
        Assert.assertTrue(employeeInformationPage.isTableinfoDisplayed(driver,"Employment Status", jEmploymentStatus));
        Assert.assertTrue(employeeInformationPage.isTableinfoDisplayed(driver,"Sub Unit", jSubUnit));

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
    private ContactDetailPageObject contactDetailPage;
    private EmergencyContactPageObject emergencyContactPage;
    private DependentsPageObject dependentsPage;
    private JobPageObject jobPage;
    private SalaryPageObject salaryPage;
    private QualificationsPageObject qualificationsPage;
    private EmployeeInformationPageObject employeeInformationPage;

    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
    private String employeeUsername, employeePassword, eNationality, eMaritalStatus, eBirth, eGender;
    private String contStreet, contCity, contProvince, contCountry, contHome, contMobile, contWork, contWorkEmail;
    private String emerName, emerRelationship, emerHomeTelephone, emerFileName;
    private String depName, depRelationship, depBirthValue, depFileName;
    private String jJoinDate, jJobTitle, jJobCategory, jSubUnit, jLocation, jEmploymentStatus, jContractStartDate, jContractDetails, jFileName;
    private String sSalaryComponent, sPayGrade, sPayFrequency, sCurrency, sAccountNumber, sAccountType, sFileName, sAmount, sDAmount, sRoutingNumber;
    private String qCompany, qJobTitle, qFrom, qTo, qLevel, qGPA, qYear;
}

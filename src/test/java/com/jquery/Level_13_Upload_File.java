package com.jquery;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;


public class Level_13_Upload_File extends BaseTest {

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);

    }

    @Test()
    public void Table_01_Single() {

        homePage.uploadMultipleFiles(driver, flowerFileName);
        homePage.uploadMultipleFiles(driver, moonFileName);
        homePage.uploadMultipleFiles(driver, tigerFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(flowerFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(moonFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(tigerFileName));

        homePage.clickStartUpload();
        Assert.assertTrue(homePage.isFileUploadedSuccess(flowerFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(moonFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(tigerFileName));
    }

    @Test()
    public void Table_02_multiple() {
        homePage.refreshToPage(driver);

        homePage.uploadMultipleFiles(driver, flowerFileName, moonFileName, tigerFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(flowerFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(moonFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(tigerFileName));

        homePage.clickStartUpload();
        Assert.assertTrue(homePage.isFileUploadedSuccess(flowerFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(moonFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(tigerFileName));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
    String flowerFileName = "flower.jpg";
    String moonFileName = "moon.jpg";
    String tigerFileName = "tiger.jpg";
    String[] fileName = {flowerFileName, moonFileName, tigerFileName};
}

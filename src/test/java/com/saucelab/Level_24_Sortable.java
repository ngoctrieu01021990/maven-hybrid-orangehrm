package com.saucelab;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.saucelab.LoginPO;
import pageObjects.saucelab.ProductPO;


public class Level_24_Sortable extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        productPage = loginPage.loginToSauce("standard_user", "secret_sauce");
    }

    @Test
    public void Sort_01_Name() {
        productPage.sortBy("Name (A to Z)");
        verifyEquals(productPage.getSortItemSelected(), "Name (A to Z)");
        verifyTrue(productPage.isProductNameSortAscending());

        productPage.sortBy("Name (Z to A)");
        verifyEquals(productPage.getSortItemSelected(), "Name (Z to A)");
        verifyTrue(productPage.isProductNameSortDescending());
    }

    @Test
    public void Sort_02_Price() {
        productPage.sortBy("Price (low to high)");
        verifyEquals(productPage.getSortItemSelected(), "Price (low to high)");
        verifyTrue(productPage.isProductPriceSortAscending());

        productPage.sortBy("Price (high to low)");
        verifyEquals(productPage.getSortItemSelected(), "Price (high to low)");
        verifyTrue(productPage.isProductPriceSortDescending());
    }


    @AfterClass
    public void afterClass() {
        //closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private ProductPO productPage;

}

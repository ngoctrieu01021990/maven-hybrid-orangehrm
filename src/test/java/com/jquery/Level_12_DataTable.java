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

import java.util.List;


public class Level_12_DataTable extends BaseTest {

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);

    }

    @Test(enabled = false)
    public void Table_01_Paging() {
        //1. Mở ra 1 trang bất kì dựa vào số trang truyền vào
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumber("5"));

        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.openPageByNumber("14");
        Assert.assertTrue(homePage.isPageActiveByNumber("14"));

    }

    @Test (enabled = false)
    public void Table_02_Search() {
        //2. Search ở bất kỳ 1 header textbox nào dựa vào tên cột
        //3. Có thể verify bất kỳ 1 Country nào (Male/Country/Female/Total,...)
        homePage.enterToHeaderTextboxByName("Country", "Australia");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isPageinfoDisplayed("145412","Australia","154696","300109"));
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Famales","61394");
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isPageinfoDisplayed("61394","Azerbaijan","70542","131942"));
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Males","63391");
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isPageinfoDisplayed("60456","Belgium","63391","123847"));
    }

    @Test(enabled = false)
    public void Table_03_Action() {
        homePage.enterToHeaderTextboxByName("Country", "Bahrain");
        homePage.sleepInSecond(3);

        //4. Có thể xóa/edit bất kỳ 1 Country nào dựa vào tên Country
        homePage.clickToActionByCountryName("Bahrain","remove");
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Country", "Argentina");
        homePage.sleepInSecond(3);
        homePage.clickToActionByCountryName("Argentina","edit");

    }

    @Test(enabled = false)
    public void Table_04_Index() {
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();

        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","3","Putin");
        homePage.enterToTextboxByColumnNameAndRowIndex("Company","3","Russia");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","3","12");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","3","Hong Kong");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","3");
        homePage.actionToRowByRowIndex("3","Move Up");
        homePage.sleepInSecond(3);

        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","6","Trump");
        homePage.enterToTextboxByColumnNameAndRowIndex("Company","6","USA");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","6","19");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","6","United States");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","6");
        homePage.actionToRowByRowIndex("6","Insert");
        homePage.sleepInSecond(3);
    }

    @Test
    public void Table_05_Get_All_Value() {
        //UI
        List<String> countryActualValue =  homePage.getColumnAllValueByColumnName("Country");

        List<String> femaleActualValue =  homePage.getColumnAllValueByColumnName("Females");

        // Database/API/File

    }

    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
}

package com.orangehrm;

//import từ thư viện

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;


public class Login_02_BasePage_III_Extend extends BasePage {
    private WebDriver driver;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        switch (browserName) {
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Empty() {
        openPageUrl(driver, appUrl);

        sendkeyToElement(driver, "//input[@name='username']", "");
        sendkeyToElement(driver, "//input[@name='password']", "");
        clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver, "//input[@name='username']/parent::div/following-sibling::span"), "Required");
        Assert.assertEquals(getElementText(driver, "//input[@name='password']/parent::div/following-sibling::span"), "Required");
    }

    @Test
    public void TC_02_Invalid_UserName() {
        openPageUrl(driver, appUrl);

        sendkeyToElement(driver, "//input[@name='username']", "Jonh@gmail.com");
        sendkeyToElement(driver, "//input[@name='password']", "123456");
        clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver, "//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"), "Invalid credentials");
    }

    @Test
    public void TC_03_Invalid_Password() {
        openPageUrl(driver, appUrl);

        sendkeyToElement(driver, "//input[@name='username']", "Admin");
        sendkeyToElement(driver, "//input[@name='password']", "12345@");
        clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver, "//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"), "Invalid credentials");
    }

    @Test
    public void TC_04_Valid_User_Password() {
        openPageUrl(driver, appUrl);

        sendkeyToElement(driver, "//input[@name='username']", "Admin");
        sendkeyToElement(driver, "//input[@name='password']", "admin123");
        clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        Assert.assertEquals(getElementText(driver, "//div[@class='oxd-topbar-header-title h6']//h6"), "Dashboard");
    }

    public boolean isAllLoadingSpinnerInvisible() {
        return waitListElementInvisible(driver, "//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

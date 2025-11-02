package ui.orangehrm.user;

//import từ thư viện
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

//import class/interface từ package khác
import keywords.Topic_01_Keywords;


public class Login_01_DRY {
    private WebDriver driver;
    private Topic_01_Keywords topic01Keywords;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Empty() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username']/parent::div/following-sibling::span")).getText(),"Required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password']/parent::div/following-sibling::span")).getText(),"Required");
    }

    @Test
    public void TC_02_Invalid_UserName() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Jonh@gmail.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("123456");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.orangehrm-login-error p.oxd-alert-content-text")).getText(),"Invalid credentials");
    }

    @Test
    public void TC_03_Invalid_Password() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345@");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.orangehrm-login-error p.oxd-alert-content-text")).getText(),"Invalid credentials");
    }

    @Test
    public void TC_04_Valid_User_Password() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-topbar-header-title h6")).getText(),"Dashboard");
    }

    public boolean isAllLoadingSpinnerInvisible(){
        return explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

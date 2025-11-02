package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.awt.Color.white;

public class Topic_08 {

    //Phạm vi static là chia sẻ cho toàn bộ hệ thống sử dụng
    static String name = "Automation FC";

    // hằng số
    static final String AGE = "23";

    //non-static
    String address = "";

    @Test
    public void TC_01() throws InterruptedException {
        // thuộc phạm vi đối tượng là tp
        Topic_08 tp = new Topic_08();
        tp.address = "Ha noi";

        // thuộc phạm vi class
        Topic_08.name = "TEST";

        //Topic_08.AGE="34";

        String osName = "Windows 11";
        String separator = null;
        WebDriver driver;

        //Condition statement
        //if-else
        if (osName.contains("Windows")) {
            separator = "\\";
        } else {
            separator = "/";
        }

        String browserName = "Chrome";
        //switch - case
        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not support");
        }

        //Loop statement

        int studentNumber = 10;
        //Classic for
        //for
        for (int i = 0; i < studentNumber; i++) {
            if (i == 5) {
                System.out.println(i);
            }
        }

        List<String> stdName = new ArrayList<String>();
        // for-each
        for (String std : stdName) {
            System.out.println(std);
        }

        //white
        int x = 0;
        while (x < studentNumber) {
            System.out.println(x);
            x++;
        }

        //do-white
        int z = 10;
        do {
            System.out.println(z);
            z++;
        } while (z < studentNumber);


        // nếu element k đc tìm thấy thì cũng k bị lỗi
        try{
            //happy case
            driver.findElement(By.cssSelector("")).isDisplayed();
        } catch (NoSuchElementException exception){

            //edge case
            System.out.println(exception.getMessage());
        } finally {
            //close connection: DB/file/...
        }

        Thread.sleep(5000);
    }
}

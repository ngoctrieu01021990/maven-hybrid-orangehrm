package keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_09 {
    String name;
    String address;

    //constructor
    public Topic_09(String name) {
        this.name = name;
    }

    //khi chạy đa luồng và gọi đến hàm này thì bắt buộc phải chạy theo thứ tự
    public synchronized WebDriver getDriver() {

        WebDriver driver = null;
        if (driver instanceof FirefoxDriver){//instanceof: kiểm tra 1 biến thuộc kiểu dl gì?
            //action
            driver =new FirefoxDriver();
        }
        return driver;
    }
}

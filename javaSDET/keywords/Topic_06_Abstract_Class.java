package keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.Field;

public abstract class Topic_06_Abstract_Class {
    //có cả hàm abstract & non-abstract
    //k cho phép khởi tạo, chỉ cho kế thừa
    public abstract void clickToElement();//k có thân hàm

    public void enterToTextBox() { // có thân hàm

    }

    //hàm có kiểu trả về (hàm liên quan đến đấy dl ra <> void)
    public String getFullName() {
        WebDriver driver= new FirefoxDriver();
        driver.get("");
        return null;
    }

    //Action (void)
    public void setFullName(){

    }
}

package keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Class kế thừa class --> dùng extends
//class kế thừa interface --> dùng implements
public class Topic_01_Keywords extends Topic_01_Non_Abstract_Class implements Topic_02_Interface {
    //Data type
    char c = 'A';
    byte bNumber = 10;
    short sNumber = 15;
    int iNumber = 20;
    long lNumber = 123222;
    float fNumber = 13.33F;
    double dNumber = 12.33332D;
    boolean marialStatus = false;

    String fullName=null;

    //Access modifier
    private String studentNmae = "Hoa";
    String studentAddress = "";
    protected int studentAge = 30;
    public double studentPoint = 5.6;

    //Method
    // k có kiểu trả về: void (liên quan đến action)
    public void TC_01() {
        WebDriver driver = new FirefoxDriver();
        Topic_01_Keywords topic = new Topic_01_Keywords();
    }

    void TC_02() {

    }

    protected void TC_03() {

    }

    public void TC_04() {

    }

    @Override
    public void clearStudent() {
        //tự implement lại ở đây
    }
}

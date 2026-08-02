package JavaAbstraction;

import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSample {
    public static void main(String[] args) {
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("");
        firefoxDriver.getTitle();
    }
}

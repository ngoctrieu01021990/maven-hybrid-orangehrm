package com.orangehrm;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.Test;

public class FirefoxSessionDiagnosticTest {

    @Test
    public void testFirefoxSession() {
        FirefoxDriver driver = null;

        try {
            System.setProperty(
                    "webdriver.gecko.driver",
                    "C:\\Windows\\System32\\geckodriver.exe"
            );

            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(
                    "C:\\Program Files\\Mozilla Firefox\\firefox.exe"
            );

            // Test headless để loại trừ vấn đề giao diện Windows
            options.addArguments("-headless");

            GeckoDriverService service =
                    new GeckoDriverService.Builder()
                            .withLogOutput(System.out)
                            .build();

            System.out.println("STEP 1: Starting FirefoxDriver");
            System.out.println("Firefox options: " + options);

            driver = new FirefoxDriver(service, options);

            System.out.println("STEP 2: Firefox session created");
            System.out.println(
                    "Capabilities: " + driver.getCapabilities()
            );

            driver.get("about:blank");

            System.out.println("STEP 3: Opened about:blank");
            System.out.println("FIREFOX SESSION TEST PASSED");

        } catch (Exception e) {
            System.err.println("FIREFOX SESSION TEST FAILED");
            e.printStackTrace();

            throw new AssertionError(
                    "Cannot create Firefox WebDriver session", e
            );

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
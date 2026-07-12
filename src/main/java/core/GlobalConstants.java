package core;

import java.io.File;

public final class GlobalConstants {

    public static final String JIRA_SITE_URL = "https://ngoc-trieu.atlassian.net/";
    public static final String JIRA_USERNAME = "ngoctrieu01021990@gmail.com";
    public static final String JIRA_API_KEY = "";
    public static final String JIRA_PROJECT_KEY = "FRAMEWORK";

    private GlobalConstants() {

    }

    //System info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");

    //App info user
    public static final String DEV_USER_URL = "http://localhost:88/opencart/upload/";

    //App info admin
    public static final String DEV_ADMIN_URL = "http://localhost:88/opencart/upload/adm";

    public static final String ADMIN_USERNAME = "automation";
    public static final String ADMIN_PASSWORD = "Auto123$$##";

    public static final String ORANGEHRM_ADMIN_USERNAME = "Admin";
    public static final String ORANGEHRM_ADMIN_PASSWORD = "admin123";

    //wait info
    public static final int SHORT_TIME = 10;
    public static final int LONG_TIME = 30;

    //download/upload
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;

    //retry case failed
    public static final int RETRY_NUMBER = 3;

    //brower log/extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;

    // html report folder
    public static final String REPORTING_PATH = PROJECT_PATH + File.separator + "htmlReportNG" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "htmlExtent" + File.separator;
    public static final String ALLURE_PATH = PROJECT_PATH + File.separator + "htmlAllure" + File.separator;

    // Data test/environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + File.separator + "dataFile" + File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator + "environmentConfig" + File.separator;

    // Browser stack
    public static final String BROWSER_STACK_USERNAME = "ngoctrieu_NsY0ze";
    public static final String BROWSER_STACK_AUTOMATE_KEY = "xumSyU8JJoYvDEsjBrc3";
    public static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USERNAME + ":" + BROWSER_STACK_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    //SauceLab
    public static final String SAUCE_USERNAME = "oauth-ngoctrieu01021990-7cc09";
    public static final String SAUCE_AUTOMATE_KEY = "d63b3584-7085-4004-bcfa-2fbc250e287d";
    public static final String SAUCE_DATA_CENTER_ENDPOINT = "eu-central-1";
    public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand." + SAUCE_DATA_CENTER_ENDPOINT + ".saucelabs.com:443/wd/hub";

    //Bitbar
    public static final String BITBAR_AUTOMATE_KEY = "2uj8qEGhFy28N2MmNbWVyoMUBT2A2iQe";
    public static final String BITBAR_EU_URL = "https://eu-desktop-hub.bitbar.com/wd/hub";
    public static final String BITBAR_US_URL = "https://us-west-desktop-hub.bitbar.com/wd/hub";

    //Lambda
    public static final String LAMBDA_USERNAME = "ngoctrieu01021990";
    public static final String LAMBDA_AUTOMATE_KEY = "LT_2lZ3t9viabuL0O9gfg5bxj9vV5PKnAlUyXI3ZXOgVGJ4sYI";
    public static final String LAMBDA_URL = "https://hub.lambdatest.com/wd/hub";

    //Device Farm
    public static final String AWS_DEVICE_FARM = "anr:aws:devicefarm:us-west-2:4584428139224:testgrid-project:8d6e22db-2580-41d4-9569-0ecc10f2f55g";
}

package core;

public class GlobalConstants {
    //System info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    //App info user
    public static final String DEV_USER_URL = "http://localhost:88/opencart/upload/";

    //App info admin
    public static final String DEV_ADMIN_URL = "http://localhost:88/opencart/upload/adm";

    public static final String ADMIN_USERNAME = "automation";
    public static final String ADMIN_PASSWORD = "Auto123$$##";

    //wait info
    public static final int SHORT_TIME = 10;
    public static final int LONG_TIME = 30;

    //download/upload
    public static final String UPLOAD_PATH = PROJECT_PATH + "/uploadFiles/";
    public static final String DOWNLOAD_PATH = PROJECT_PATH + "/downloadFiles/";

    //retry case failed
    public static final int RETRY_NUMBER = 3;

    //brower log/extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + "/browserLogs/";
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + "/browserExtensions/";

    // html report folder
    public static final String REPORTING_PATH = PROJECT_PATH + "/htmlReportNG/";
    public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent/";
    public static final String ALLURE_PATH = PROJECT_PATH + "/htmlAllure/";

    // Data test/environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + "/dataTest/";
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + "/environmentConfig/";


}

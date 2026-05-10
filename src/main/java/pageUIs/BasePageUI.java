package pageUIs;

public class BasePageUI {
    //orangeHRM
    public static final String SPINNER_ICON = "css=div.oxd-loading-spinner";
    public static final String TEXTBOX_BY_LABEL = "xpath=//label[text()=\"%s\"]/parent::div/following-sibling::div//input";
    public static final String TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
    public static final String BUTTON_BY_TEXT = "xpath=//button[contains(string(),'%s')]";
    public static final String BUTTON_BY_TEXT_IN_MAIN_TITLE = "xpath=//h6[text()='%s']/following-sibling::form//button[contains(string(),'%s')]";
    public static final String MODULE_BY_TEXT_IN_MENU_ITEM = "xpath=//span[text()='%s']/parent::a[contains(@class,'oxd-main-menu-item')]";
    public static final String MODULE_BY_TEXT_IN_HEADER = "xpath=//a[text()='%s']";


    public static final String PARENT_DROPDOWN_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String CHILD_DROPDOWN_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span";
    public static final String SUB_CHILD_DROPDOWN_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-option']/following-sibling::div/span";
    public static final String SELECTED_DROPDOWN_VALUE_BY_LABEL ="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String TOAST_MESSAGE_BY_TEXT = "xpath=//p[contains(@class,'oxd-text--toast-message') and text()='%s']";
    public static final String RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/span";
    public static final String CHECKBOX_BY_LABEL = "xpath=//p[text()='%s']/following-sibling::div//span";
    public static final String USER_DROPDOWN = "css=p.oxd-userdropdown-name";
    public static final String LOGOUT_LINK = "xpath=//a[@class='oxd-userdropdown-link' and text()='Logout']";
    public static final String DYNAMIC_UPLOAD_FILE_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DYNAMIC_FILE_NAME_BY_LABEL = "xpath=//div[contains(@class,'orangehrm-file-preview')]//p[contains(@title,'%s')]";
    public static final String DYNAMIC_TABLE_INFO = "xpath=//div[text()='%s']/parent::div/parent::div/following-sibling::div//div[contains(text(),'%s')]";


    //openCart
    public static final String USER_MY_ACCOUNT_HEADER = "XPath=//nav[@id='top']//span[text()='My Account']";
    public static final String USER_LOGOUT_LINK_ITEM = "XPath=//a[@class='dropdown-item' and text()='Logout']";
    public static final String ADMIN_LOGOUT_LINK_ITEM = "XPath=//li[@id='nav-logout']//span[text()='Logout']";
    public static final String USER_HOME_LOGO = "css=div#logo>a";

    //JQuery
    public static final String UPLOAD_FILE_TYPE="css=input[type='file']";

}

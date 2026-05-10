package pageUIs.orangeHRM.editNavigation;

public class EditNavigatorPageUI {

    // 10 locator đại diện cho 10 page
    public static final String JOB_LINK="xpath=//a[text()='Job']";
    public static final String PERSONAL_DETAIL_LINK="xpath=//a[text()='Personal Details']";
    public static final String DEPENDENT_LINK="xpath=//a[text()='Dependents']";
    public static final String CONTACT_DETAIL_LINK="xpath=//a[text()='Contact Details']";

    // 1 locator đại diện cho 10 page
    public static final String DYNAMIC_LINK_BY_PAGE_NAME="xpath=//a[text()='%s']";

    public static final String MODULE_BY_TEXT_IN_MENU_PROFILE_ITEM = "xpath=//div/a[@class='orangehrm-tabs-item' and text()='%s']";
    public static final String DYNAMIC_BUTTON_BY_GROUP_LABEL = "xpath=//h6[text()='%s']/parent::div//button[normalize-space()='%s']";
    public static final String DYNAMIC_TABLE_INFO = "xpath=//div[text()='%s']/parent::div/parent::div/following-sibling::div//div[text()='%s']";
    public static final String DYNAMIC_DATE_PICKER_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String TEXTBOX_BY_LABEL = "xpath=(//label[text()='%s']/parent::div/following-sibling::div//input)[%s]";



}

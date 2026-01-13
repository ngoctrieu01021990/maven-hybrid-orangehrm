package pageFactory.orageHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailPageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(name = "firstName")
    private WebElement firtNameTextbox;

    @CacheLookup
    @FindBy(name = "lastName")
    private WebElement lastNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeIDTextbox;

    @CacheLookup
    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public PersonalDetailPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, firtNameTextbox);
        return getElementDOMProperty(firtNameTextbox, "value");
    }

    public String getLastNameTextboxValue() {
        waitElementVisible(driver, lastNameTextbox);
        return getElementDOMProperty(lastNameTextbox, "value");
    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, employeeIDTextbox);
        return getElementDOMProperty(employeeIDTextbox, "value");
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver,loadingSpinner);
    }
}

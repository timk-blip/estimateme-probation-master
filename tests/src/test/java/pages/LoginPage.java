package pages;

import helpers.PageObjectUtils;
import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Authorization page object.
 */
public class LoginPage {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Page url.
     */
    private static final String PAGE_URL = "/login";

    /**
     * Login input element.
     */
    @FindBy(css = "input[ng-model='vm.login']")
    private WebElement loginEditElement;

    /**
     * Password input element.
     */
    @FindBy(css = "input[ng-model='vm.password']")
    private WebElement passwordEditElement;

    /**
     * Login submit button.
     */
    @FindBy(css = "button[ng-click='vm.log()']")
    private WebElement loginButtonElement;

    /**
     * Page object constructor. Checks that page is open when created.
     * @param webDriver browser driver
     * @throws IOException when config file is not available
     * @throws IllegalStateException if page is not open now
     */
    public LoginPage(final WebDriver webDriver) throws IOException,
            IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
        if (!PageObjectUtils.checkPageIsPresentByUrl(webDriver, PAGE_URL)) {
            throw new IllegalStateException("Login page is not present");
        }
    }

    /**
     * Authorization method.
     * @param userName login to input
     * @param password password to input
     * @return page object with estimates list
     * @throws IOException when config file is not available
     */
    public final EstimatesPage login(final String userName,
                                     final String password)
            throws IOException {
        Waiters.waitUntilAngularReady(driver);
        loginEditElement.sendKeys(userName);
        passwordEditElement.sendKeys(password);
        loginButtonElement.click();
        return new EstimatesPage(driver);
    }
}

package pages;

import helpers.PageObjectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Estimates list page object.
 */
public class EstimatesPage {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Page Url.
     */
    private static final String PAGE_URL = "/estimates";

    /**
     * Page object constructor. Checks that page is open when created.
     * @param webDriver browser driver
     * @throws IOException when config file is not available
     * @throws IllegalStateException if page is not open now
     */
    public EstimatesPage(final WebDriver webDriver) throws IOException,
            IllegalStateException {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
        if (!PageObjectUtils.checkPageIsPresentByUrl(driver, PAGE_URL)) {
            throw new IllegalStateException("Estimates page is not present");
        }
    }


}

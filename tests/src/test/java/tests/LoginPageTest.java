package tests;


import java.io.IOException;

import helpers.DriverFactory;
import helpers.ParametersProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Test suite for authorization page.
 */
public class LoginPageTest {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Suite setup.
     * @throws IOException when config file is not available
     */
    @BeforeClass
    public final void setEnvironment() throws IOException {
        this.driver = DriverFactory.createDriver();
        String webUrl = ParametersProvider.getProperty("webUrl");
        driver.get(webUrl);
    }

    /**
     * Test case EST-1.
     * @throws IOException when config file is not available
     */
    @Test(description = "EST-1:Авторизация с корректным логином и паролем")
    public final void checkCorrectLoginPassAuth() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        String adminLogin = ParametersProvider.getProperty("adminLogin");
        String adminPassword = ParametersProvider.getProperty("adminPassword");

        Boolean testResult;
        try {
            loginPage.login(adminLogin, adminPassword);
            testResult = true;
        } catch (IllegalStateException e) {
            testResult = false;
        }
        Assert.assertTrue(testResult, "Not authorized");
    }

    /**
     * Suite teardown.
     */
    @AfterClass
    public final void tearDown() {
        driver.quit();
    }
}

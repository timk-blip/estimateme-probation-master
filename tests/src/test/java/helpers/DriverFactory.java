package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Browser driver factory.
 */
public final class DriverFactory {
    /**
     * Not called.
     */
    private DriverFactory() {
    }

    /**
     * Sets driver path if it possible.
     * @param browserName browser name
     * @throws IOException when config file is not available
     */
    private static void trySetDriverPath(final String browserName)
            throws IOException {
        String driverPath = ParametersProvider.getProperty("driverPath");
        if (driverPath != null && !driverPath.isEmpty()) {
            System.setProperty("webdriver." + browserName + ".driver",
                    driverPath);
        }
    }

    /**
     * Creates a browser driver using configuration.
     * @return browser driver
     * @throws IOException when config file is not available
     * @throws IllegalStateException when unsupported browser chosen
     */
    public static WebDriver createDriver() throws IOException,
            IllegalStateException {
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String browserName = ParametersProvider.getProperty("browserName");
        boolean remote = Boolean.valueOf(ParametersProvider
                .getProperty("remote"));
        String seleniumUrl = ParametersProvider.getProperty("seleniumUrl");
        int pageLoadTimeout = Integer.valueOf(ParametersProvider
                .getProperty("pageLoadTimeout"));

        if (remote) {
            capabilities.setBrowserName(browserName);
            driver = new RemoteWebDriver(new URL(seleniumUrl), capabilities);
        } else {
            trySetDriverPath(browserName);

            if ("chrome".equals(browserName) || "opera".equals(browserName)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browserName)) {
                driver = new FirefoxDriver();
            } else if ("edge".equals(browserName)) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalStateException(
                        "Chosen browser not supported");
            }
        }
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout,
                TimeUnit.SECONDS);
        return driver;
    }
}

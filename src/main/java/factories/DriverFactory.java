package factories;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;
import static utils.LogHandler.*;
import static utils.PropertiesHandler.getPropertyValue;

/**
 * Created by ggiorgi on 4/25/2017.
 */
public class DriverFactory {

    private static DriverFactory Instance;
    protected WebDriver driver = null;
    static String chromeURL = DriverFactory.class.getClassLoader().getResource("drivers/chromedriver.exe").getFile();
    static String firefoxURL = DriverFactory.class.getClassLoader().getResource("drivers/geckodriver.exe").getFile();
    static String IExploreURL = DriverFactory.class.getClassLoader().getResource("drivers/IEDriverServer.exe").getFile();
    Logger logger;

    public static DriverFactory getIntance() {
        if (Instance == null) {
            Instance = new DriverFactory();
        }

        return Instance;
    }

    protected DriverFactory() {

    }

    public WebDriver startbrowser() {

        logger = initLogging();
        String browserType = getPropertyValue("browserType");
        String connectionType = getPropertyValue("connectionType");

        switch (browserType) {
            case "chrome":
                switch (connectionType) {

                    case "local": {
                        logger.info("==== Using Local Chrome ==== ");
                       driver = asLocalChrome(driver);

                        return driver;
                    }
                    case "remote": {
                        logger.info("==== Using Remote Chrome ==== ");
                       driver= asRemoteChrome(driver);
                        return driver;
                    }
                }
            case "firefox":
                switch (connectionType) {

                    case "local": {

                        logger.info("==== Using Local Firefox ==== ");
                        driver = asLocalFirefox(driver);
                        return driver;

                    }

                    case "remote": {

                        logger.info("==== Using Remote Firefox ==== ");
                        driver = asRemoteFirefox(driver);
                        return driver;

                    }
                }
                return null;

            case "iexplore":
                switch (connectionType) {

                    case "local": {
                        logger.info("==== Using Local IExplorer ==== ");
                        driver = asLocalIExplore(driver);
                        return driver;

                    }
                    case "remote": {
                        logger.info("==== Using Local IExplorer ==== ");
                       driver = asRemoteIExplore(driver);
                        return driver;
                    }
                }


        } return driver;

    }


    public  WebDriver asLocalChrome(WebDriver driver){
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, chromeURL);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        options.addArguments("--js-flags=--expose-gc");
        options.addArguments("--enable-precise-memory-info");
        options.addArguments("no-sandbox");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("test-type=browser");
        options.addArguments("disable-infobars");
        options.addArguments("disable-extensions");
        driver = new ChromeDriver(options);
        return driver;
    }
    public static WebDriver asRemoteChrome(WebDriver driver) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        try {
            driver = new RemoteWebDriver(new URL(getPropertyValue("urlRemote")), desiredCapabilities);
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        return driver;
    }

    public static WebDriver asLocalFirefox(WebDriver driver) {
        System.setProperty("webdriver.gecko.driver", firefoxURL);
        driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver asRemoteFirefox(WebDriver driver) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        try {
            driver = new RemoteWebDriver(new URL(getPropertyValue("urlRemote")), desiredCapabilities);

        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        return driver;
    }

    public static WebDriver asLocalIExplore(WebDriver driver) {
        System.setProperty("webdriver.ie.driver", IExploreURL);
        driver = new InternetExplorerDriver();
        return driver;
    }

    public static WebDriver asRemoteIExplore(WebDriver driver) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
        try {
            driver = new RemoteWebDriver(new URL(getPropertyValue("urlRemote")), desiredCapabilities);
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        return driver;
    }

    public WebDriver getDriver(){
        return driver;
    }


}

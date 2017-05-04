package utils;


import factories.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static utils.LogHandler.initLogging;


/**
 * Created by ggiorgi on 5/3/2017.
 */
public class BaseClass {

    WebDriver driver;
    String homeURL = PropertiesHandler.getPropertyValue("homeURL");
    Logger logger;

    @BeforeClass
    public void setupApplication(){

        logger = initLogging();
        logger.info("===== Starting Browser Session =====");
        driver = DriverFactory.getIntance().startbrowser();
        driver.get(homeURL);
        logger.info("===== Application Started =====");
    }


    @AfterClass
    public void closeApp(){
        driver.quit();
        logger.info("===== Browser Session End =====");
    }
}

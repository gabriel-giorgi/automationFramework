package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Created by ggiorgi on 4/25/2017.
 */
public class LogHandler {

    public static String log4jConfigURL = LogHandler.class.getClassLoader().getResource("ConfigFiles/log4jConfig.properties").getFile();

    public static Logger initLogging () {
        Logger logger = Logger.getLogger("LogHandler");
        PropertyConfigurator.configure(log4jConfigURL);
        return logger;
    }

}

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ggiorgi on 4/25/2017.
 */
public class PropertiesHandler {

    public static final String PROPERTY_PATH = PropertiesHandler.class.getClassLoader().getResource("ConfigFiles/config.properties").getFile();

    public static String getPropertyValue ( final String key){
        Properties prop = new Properties();
        File file = new File(PROPERTY_PATH);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            prop.load(fis);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        finally {
            if(fis != null){
                try {
                    fis.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        return prop.getProperty(key);
    }
}

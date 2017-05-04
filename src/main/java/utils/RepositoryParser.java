package utils;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ggiorgi on 4/27/2017.
 */
public class RepositoryParser {

    private FileInputStream stream;
    private String RepositoryFile;
    private Properties propertyFile = new Properties();

    public RepositoryParser(String fileName) throws IOException
    {
        this.RepositoryFile = fileName;
        stream = new FileInputStream(RepositoryFile);
        propertyFile.load(stream);
    }

    public By getLocator(String locatorName)
    {
        String locatorProperty = propertyFile.getProperty(locatorName);
        System.out.println(locatorProperty.toString());
        String locatorType = locatorProperty.split(":")[0];
        String locatorValue = locatorProperty.split(":")[1];

        By locator = null;
        switch(locatorType)
        {
            case "Id":
                locator = By.id(locatorValue);
                break;
            case "Name":
                locator = By.name(locatorValue);
                break;
            case "CssSelector":
                locator = By.cssSelector(locatorValue);
                break;
            case "LinkText":
                locator = By.linkText(locatorValue);
                break;
            case "PartialLinkText":
                locator = By.partialLinkText(locatorValue);
                break;
            case "TagName":
                locator = By.tagName(locatorValue);
                break;
            case "ClassName":
                locator = By.className(locatorValue);
                break;
            case "Xpath":
                locator = By.xpath(locatorValue);
                break;
        }
        return locator;
    }
}



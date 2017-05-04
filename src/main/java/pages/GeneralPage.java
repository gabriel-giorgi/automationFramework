package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ggiorgi on 4/27/2017.
 */
public class GeneralPage {

    protected WebDriverWait wait;
    protected WebDriver driver;
    protected Actions action;

    public GeneralPage(WebDriver driver){

        this.driver= driver;
        this.action= new Actions(driver);
        this.wait= new WebDriverWait(driver, 15);

    }

    public static void navigateTo(WebDriver driver,String url){
        driver.get(url);
    }

}

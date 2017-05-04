package utils;

        import java.io.File;
        import factories.DriverFactory;
        import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;

        import org.testng.ITestResult;
        import org.testng.TestListenerAdapter;
/**
 * Created by ggiorgi on 4/27/2017.
 */
public class TestListener extends TestListenerAdapter {
//Add this listener to your custom testng xml //
    WebDriver driver;
    private static String fileSeperator = System.getProperty("file.separator");

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("***** Error " + result.getName() + " test has failed *****");

        driver = DriverFactory.getIntance().getDriver();

        String testClassName = getTestClassName(result.getInstanceName()).trim();

        String testMethodName = result.getName().toString().trim();
        String screenShotName = testMethodName + ".png";

        if (driver != null) {
            String imagePath = ".." + fileSeperator + "Screenshots"
                    + fileSeperator + "Results" + fileSeperator + testClassName
                    + fileSeperator
                    + takeScreenShot(driver, screenShotName, testClassName);
            System.out.println("Screenshot can be found : " + imagePath);
        }
    }

    public static String takeScreenShot(WebDriver driver,
                                        String screenShotName, String testName) {
        try {
            File file = new File("Screenshots" + fileSeperator + "Results");
            if (!file.exists()) {
                System.out.println("File created " + file);
                file.mkdir();
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //Here set the path to your screenshots Folder , In this case is "src/main/Execution Reports/Failed Case Screnshots"
            File targetFile = new File("src/main/Execution Reports/Failed Case Screnshots" + fileSeperator + "Results" + fileSeperator + testName, screenShotName);
            FileUtils.copyFile(screenshotFile, targetFile);

            return screenShotName;
        } catch (Exception e) {
            System.out.println("An exception occured while taking screenshot " + e.getCause());
            return null;
        }
    }

    public String getTestClassName(String testName) {
        String[] reqTestClassname = testName.split("\\.");
        int i = reqTestClassname.length - 1;
        System.out.println("Required Test Name : " + reqTestClassname[i]);
        return reqTestClassname[i];
    }


}

package pageFiles;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;


public class TestBase {


	public static WebDriver driver;
    protected static Properties prop = null;

    public static ExtentReports extent;
    public static ExtentTest test;
//	public Page page;


public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com"); // Replace with your actual URL
        return driver;
    }

    
    /*------------------*/
    public TestBase() {
        System.out.println("TestBase constructor called");
        if (prop == null) {
            prop = new Properties();
        try {
            prop = new Properties();
            // Load from classpath (works in Maven, Eclipse, etc.)
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                if (inputStream == null) {
                    throw new RuntimeException("config.properties not found in classpath!");
                }
                prop.load(inputStream);
               // logger.info("Properties loaded from config.properties in classpath");
            }
        } catch (IOException e) {
          //  logger.error("Failed to load config.properties file.", e);
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }
    }
    
    
    
    
    
    
    
    
    
//    public TestBase() {
//    	 System.out.println("TestBase constructor called");
//        try {
//            prop = new Properties();
//            String configPath = System.getProperty("config.path", "src/main/resources/config.properties");
//            FileInputStream fis = new FileInputStream(configPath);
//            prop.load(fis);
//            logger.info("Properties loaded from: " + configPath);
//        } catch (IOException e) {
//            logger.error("Failed to load config.properties file.", e);
//            throw new RuntimeException("Failed to load config.properties file.");
//        }
//    }

    
    
    public static void setupExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("DPW Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Deepa A Ambannavar");
    }

    public static void flushExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }

  
public static String captureScreenshot(String testName) {

if (driver == null) {
        System.out.println("WebDriver is null. Cannot capture screenshot.");
        return null;
    }

    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String screenshotPath = "test-output/screenshots/" + testName + ".png";
    try {
        FileHandler.copy(srcFile, new File(screenshotPath));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return screenshotPath;
}

    
    
    
    public static WebDriver initialization() {
        String browserName = prop.getProperty("browser");

        if (browserName == null) {
            throw new RuntimeException("Browser name is not specified in config.properties");
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}

package Utilities;

import PageObjectClasses.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ReusableMethodsTestCases {
    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\GlobalValues.properties";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);

        String browserName = properties.getProperty("browserName");

        if (browserName.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ionut\\Downloads\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.contains("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        return driver;
    }

    public LoginPage launchApp() throws IOException {
        driver = initializeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return loginPage;
    }

    @AfterMethod
    public void exitApp() {
        driver.quit();
    }

    public List<HashMap<String, String>> jsonDataToHashMap(String filePath) throws IOException {
        String stringData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(stringData, new TypeReference<>() {
        });
        return data;
    }

    public String getScreenshot(WebDriver driver, String testCaseName) throws IOException {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileHandler.copy(sourceFile, destinationFile);
        String screenshotPath = destinationFile.toString().split("reports")[1];
        return ".\\" + screenshotPath;
    }
}

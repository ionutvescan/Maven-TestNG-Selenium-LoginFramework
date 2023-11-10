package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableMethodsPages {
    WebDriver driver;
    public ReusableMethodsPages(WebDriver driver){
        this.driver = driver;
    }
    public void waitForElementToAppear(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public void waitForElementToDisappear(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
}

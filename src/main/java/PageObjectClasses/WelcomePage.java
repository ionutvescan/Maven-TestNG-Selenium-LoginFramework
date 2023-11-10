package PageObjectClasses;

import Utilities.ReusableMethodsPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends ReusableMethodsPages {
    WebDriver driver;
    public WelcomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".login-container p")
    private WebElement welcomeMessage;
    @FindBy(className = "logout-btn")
    private WebElement logoutButton;

    public String getWelcomeMessage(){
        waitForElementToAppear(welcomeMessage);
        return welcomeMessage.getText();
    }
    public void logout(){
        logoutButton.click();
    }
}



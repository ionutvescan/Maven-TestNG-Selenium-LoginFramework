package PageObjectClasses;

import Utilities.ReusableMethodsPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ReusableMethodsPages {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Username']")
    private WebElement userName;
    @FindBy(css = "input[placeholder='Password']")
    private WebElement password;
    @FindBy(css = ".submit")
    public WebElement submit;
    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPassword;
    @FindBy(css = "div p")
    private WebElement errorMessage;
    //this locator identifies errorMessage on LoginPage and also welcomeMessage on WelcomePage -> better assertion

    public void goToLoginPage(){
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
    }
    public void insertUserName(String username){
        userName.sendKeys(username);
    }
    public void insertPassword(String pwd){
        password.sendKeys(pwd);
    }
    public WelcomePage pressSignInButton(){
        submit.click();
        return new WelcomePage(driver);
    }
    public ForgotPasswordPage goToForgotPassword(){
        forgotPassword.click();
        return new ForgotPasswordPage(driver);
    }
    public String getErrorMessage(){
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}

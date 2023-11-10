package PageObjectClasses;

import Utilities.ReusableMethodsPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends ReusableMethodsPages {
    WebDriver driver;
    public ForgotPasswordPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Name']")
    private WebElement name;
    @FindBy(css = "input[placeholder='Email']")
    private WebElement email;
    @FindBy(xpath = "//input[@placeholder='Phone Number']")
    private WebElement phoneNumber;
    @FindBy(css = "button.go-to-login-btn")
    private WebElement goToLoginPageButton;
    @FindBy(css = "button.reset-pwd-btn")
    private WebElement resetPasswordButton;
    @FindBy(css = "p.infoMsg")
    private  WebElement temporaryPasswordMessage;
    @FindBy(css = "form h2")
    private  WebElement forgotPasswordMessage;

    public void insertName(String nameUser){
        name.sendKeys(nameUser);
    }
    public void insertEmail(String emailUser){
        email.sendKeys(emailUser);
    }
    public void insertPhoneNumber(String phone){
        phoneNumber.sendKeys(phone);
    }
    public void goToLoginPage(){
        waitForElementToAppear(goToLoginPageButton);
        goToLoginPageButton.click();
        waitForElementToDisappear(goToLoginPageButton);
    }
    public void resetPassword(){
        resetPasswordButton.click();
    }
    public String getTemporaryPassword(){
        return temporaryPasswordMessage.getText().split("'")[1].split("'")[0];
    }
    public String getForgotPasswordMessage(){
        waitForElementToAppear(forgotPasswordMessage);
        return forgotPasswordMessage.getText();
    }

}

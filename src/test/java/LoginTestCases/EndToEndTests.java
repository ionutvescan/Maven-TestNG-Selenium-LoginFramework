package LoginTestCases;

import PageObjectClasses.ForgotPasswordPage;
import PageObjectClasses.LoginPage;
import PageObjectClasses.WelcomePage;
import Utilities.ReusableMethodsTestCases;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EndToEndTests extends ReusableMethodsTestCases {
    @Test(dataProvider = "getData")
    public void end2endTest(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertPassword(input.get("wrongPassword"));
        loginPage.pressSignInButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"* Incorrect username or password");
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPassword();
        Assert.assertEquals(forgotPasswordPage.getForgotPasswordMessage(), "Forgot password");
        forgotPasswordPage.insertName(input.get("userName"));
        forgotPasswordPage.insertEmail(input.get("email"));
        forgotPasswordPage.insertPhoneNumber(input.get("phone"));
        forgotPasswordPage.resetPassword();
        String tempPassword = forgotPasswordPage.getTemporaryPassword();
        forgotPasswordPage.goToLoginPage();
        loginPage.insertUserName(input.get("userName"));
        loginPage.insertPassword(tempPassword);
        WelcomePage welcomePage = loginPage.pressSignInButton();
        loginPage.waitForElementToDisappear(loginPage.submit);
        Assert.assertEquals(welcomePage.getWelcomeMessage(), "You are successfully logged in.");
        welcomePage.logout();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = jsonDataToHashMap(
                System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\EndToEndTestData.json");
        return new Object[][] {{data.get(0)}};
    }
}

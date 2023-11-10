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

public class SuccessfulLoginTests extends ReusableMethodsTestCases {

    @Test(dataProvider = "getData")
    public void successfulLogin(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertUserName(input.get("userName"));
        loginPage.insertPassword(input.get("password"));
        WelcomePage welcomePage = loginPage.pressSignInButton();
        Assert.assertEquals(welcomePage.getWelcomeMessage(), "You are successfully logged in.");
        welcomePage.logout();
    }

    @Test(dataProvider = "getData")
    public void successfulLogin2(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertUserName(input.get("userName"));;
        loginPage.insertPassword(input.get("password"));
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPassword();
        forgotPasswordPage.goToLoginPage();
        WelcomePage welcomePage = loginPage.pressSignInButton();
        Assert.assertEquals(welcomePage.getWelcomeMessage(), "You are successfully logged in.");
        welcomePage.logout();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = jsonDataToHashMap(
                System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\TestData.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}

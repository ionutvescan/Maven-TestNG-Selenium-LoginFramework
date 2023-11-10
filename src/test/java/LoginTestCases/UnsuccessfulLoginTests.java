package LoginTestCases;

import PageObjectClasses.LoginPage;
import Utilities.ReusableMethodsTestCases;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UnsuccessfulLoginTests extends ReusableMethodsTestCases {

    @Test(dataProvider = "getData")
    public void wrongUserNameLogin(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertUserName("userName");
        loginPage.insertPassword(input.get("password"));
        loginPage.pressSignInButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"* Incorrect username or password");
    }

    @Test (dataProvider = "getData")
    public void wrongPasswordLogin(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertUserName(input.get("userName"));
        loginPage.insertPassword("password");
        loginPage.pressSignInButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"* Incorrect username or password");
    }

    @Test(dataProvider = "getData")
    public void emptyUserNameField(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertPassword(input.get("password"));
        loginPage.pressSignInButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"* Incorrect username or password");
    }

    @Test(dataProvider = "getData")
    public void emptyPasswordField(HashMap<String,String> input) throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.insertUserName(input.get("userName"));
        loginPage.pressSignInButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"* Incorrect username or password");
    }

    @Test
    public void emptyUserNameAndPasswordFields() throws IOException {
        LoginPage loginPage = launchApp();
        loginPage.pressSignInButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"* Incorrect username or password");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = jsonDataToHashMap(
                System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\TestData.json");
        return new Object[][] {{data.get(0)}};
    }
}

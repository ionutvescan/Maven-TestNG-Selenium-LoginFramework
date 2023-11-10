package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends ReusableMethodsTestCases implements ITestListener {

    ExtentReports extentReports = ExtentReporter.getExtentReportsObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());

        try{
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());
        } catch (Exception e){
            e.printStackTrace();
        }

        String filePath = null;
        try{
            filePath = getScreenshot(driver, result.getMethod().getMethodName());
        } catch (Exception e1){
            e1.printStackTrace();
        }
        threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }
    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}

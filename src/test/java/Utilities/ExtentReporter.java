package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    public static ExtentReports getExtentReportsObject(){
        String filePath = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setReportName("Selenium Web Automation");
        reporter.config().setDocumentTitle("Tests Result");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Ionut");
        return extentReports;
    }
}

package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "John Doe");
        extent.setSystemInfo("Project Name", "API Automation");
        spark.config().setDocumentTitle("API Test Report");
        spark.config().setReportName("API Test Automation Report");
        spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

    }
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.pass("Test Passed");
    }
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.fail(result.getThrowable());
    }
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.skip(result.getThrowable());
    }
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
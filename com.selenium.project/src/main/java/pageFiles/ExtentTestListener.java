//package pageFiles;
//
//
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//
//
//
//	public class ExtentTestListener implements ITestListener {
//
//	    private static ExtentReports extent;
//	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//	    
//
//	    @Override
//	    public void onStart(ITestContext context) {
//	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
//	        sparkReporter.config().setReportName("Automation Test Report");
//	        sparkReporter.config().setDocumentTitle("DPW Test Results");
//
//	        extent = new ExtentReports();
//	        extent.attachReporter(sparkReporter);
//	        extent.setSystemInfo("Tester", "Deepa A Ambannavar");
//	    }
//
//	    
//	    
//	    @Override
//	    public void onTestStart(ITestResult result) {
//	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//	        test.set(extentTest);
//	    }
//
//	    @Override
//	    public void onTestSuccess(ITestResult result) {
//	        test.get().pass("Test passed");
//	    }
//
//	    
////	    @Override
////	    public void onTestFailure(ITestResult result) {
////	        String testName = result.getMethod().getMethodName();
////	        String screenshotPath = TestBase.captureScreenshot(testName);
////	        test.get().fail(result.getThrowable());
////	        test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
////	    }
//	    
//	    @Override
//	    public void onTestFailure(ITestResult result) {
//	        String testName = result.getMethod().getMethodName();
//
//	        // Get the Page object from the test class
//	        Object testInstance = result.getInstance();
//	        Page page = ((TestBase) testInstance).page;
//
//	        String screenshotPath = TestBase.capturePlaywrightScreenshot(testName, page);
//
//	        test.get().fail(result.getThrowable());
//	        if (screenshotPath != null) {
//	            test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
//	        }
//	    }
//
//
//
//	    @Override
//	    public void onTestSkipped(ITestResult result) {
//	        test.get().skip("Test skipped");
//	    }
//
//	    @Override
//	    public void onFinish(ITestContext context) {
//	        extent.flush();
//	    }
//	}
//
//
//}
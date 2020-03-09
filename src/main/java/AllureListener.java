import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener extends BaseClass implements ITestListener  {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment
    public byte[] saveFailureScreenshot(WebDriver driver) {
        System.out.println(" I am in saveFailureScreenshot method");
         return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method onStart " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", BaseClass.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method onFinish " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("I am in method onTestStart " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("I am in method onTestSuccess " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("I am in onTestFailure method onTestFailure " + getTestMethodName(result));
        Object testClass = result.getInstance();
        WebDriver driver = BaseClass.getDriver();
        if (driver != null) {
            System.out.println("Screenshot captured for test case : "+ getTestMethodName(result));
            saveFailureScreenshot(driver);
        }else{
            System.out.println(" Driver is null, so unable to capture screenshot");
        }
        saveTextLog(getTestMethodName(result) + " is failed and screenshot is taken");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("I am in onTestSkipped  method onTestSkipped " + getTestMethodName(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("I am in method onTestFailedButWithinSuccessPercentage " + getTestMethodName(result));
    }
    public void onTestFailedWithTimeout(ITestResult result){
        System.out.println("I am in method onTestFailedWithTimeout " + getTestMethodName(result));

    }


}

import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;


//@Test(suiteName = "TestNgQuickTests",testName = "QuickTests")
@Listeners({AllureListener.class})
public class QuickTests extends BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        BaseClass bs=new BaseClass();
        driver=bs.initialize_driver();


    }
    @Description(" Taking sreenshot of a page method")
    @Severity(SeverityLevel.TRIVIAL)
    @Epic("Ep001")
    @Feature("Feature01: page sceenshot")
    @Story("story: take page screenshot")
    @Test(groups = "now", description = "Selenium features taking screenshot of a page")
    public void screenshotOfPage() throws IOException {

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        TakesScreenshot ts= (TakesScreenshot) driver;
        File srcfile = ts.getScreenshotAs(OutputType.FILE);
        File desFile= new File("target/pagescreen.png");
        FileUtils.copyFile(srcfile,desFile);

    }


    @Test(groups = "now")
    @Description(" Taking sreenshot of a page method")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Ep001")
    @Feature("Feature01: page sceenshot")
    @Story("story: take page screenshot")
    public void screenshotOfaSectionOnAPage() throws IOException {
       // WebDriverManager.chromedriver().setup();
       // driver=new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        WebElement pageSection = driver.findElement(By.xpath("//div[@class='product-grid home-page-product-grid']"));
        highlightElement(pageSection,driver);
        File srcfile = pageSection.getScreenshotAs(OutputType.FILE);
        File desfile=new File("target/pageSection.png");

        FileUtils.copyFile(srcfile,desfile);


    }
    void highlightElement(WebElement we,WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='4px solid red'",we);

    }
    @Test(priority = 5)
    @Description ("To check failed test case")
    @Severity(SeverityLevel.MINOR)
    void CheckingfailedTest(){
        Assert.fail("Expected to fail`");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
        System.out.println("Successfully closed the driver");
    }
}

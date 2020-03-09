import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> tdriver=new ThreadLocal<WebDriver>();

    public WebDriver initialize_driver(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        tdriver.set(driver);
        return getDriver();
    }

    public static synchronized WebDriver getDriver(){
        return tdriver.get();
    }

}

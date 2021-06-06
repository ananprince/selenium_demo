package seleniumDay05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * @Project: seleniumDemo
 * @Author: 54540
 * @Create: 2021-05-12 14:40
 * @Desc：
 **/
public class TestRobotDemo {
    public static void main(String[] args) {
        WebDriver driver = openBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.get("http://rms.utry-robot.com/#/");
        //输入账号
        driver.findElement(By.xpath("//input[@placeholder='请输入用户名']")).sendKeys("pingxiang");
        //输入密码
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys("Utry1234");
        //选择租户
        driver.findElement(By.className("ivu-select-selected-value")).click();
        driver.findElement(By.xpath("//li[text()='太原火车站']")).click();
    }
    public static WebDriver openBrowser(String browser){
        if("chrome".equalsIgnoreCase(browser)){
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
            return new ChromeDriver();
        }else if("ie".equalsIgnoreCase(browser)){
            System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
            return new InternetExplorerDriver(desiredCapabilities);
        }else if("firefox".equalsIgnoreCase(browser)){
            System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
            return new FirefoxDriver();
        }else {
            System.out.println("浏览器不对，只支持火狐、谷歌、IE");
            return null;
        }
    }
}

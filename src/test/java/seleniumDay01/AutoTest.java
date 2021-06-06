package seleniumDay01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

/**
 * @Project: seleniumDemo
 * @Author: 54540
 * @Create: 2021-04-25 11:41
 * @Desc：
 **/
public class AutoTest {
    public static void main(String[] args) throws Exception {
        WebDriver webDriver = openBrowser("chrome");
        webDriver.get("http://testingpai.com/");
        //webDriver.manage().window().maximize();
        //登录
        webDriver.findElement(By.xpath("//span[text()='登录']")).click();

        //输入用户名
        webDriver.findElement(By.xpath("//input[@placeholder='用户名/邮箱/手机号']")).sendKeys("13989830373");

        //输入密码
        webDriver.findElement(By.xpath("//input[@placeholder='密码']")).sendKeys("lca1990714");
        //点击登录按钮
        webDriver.findElement(By.xpath("//button[text()='登录']")).click();
        //点击首页
        webDriver.findElement(By.cssSelector(".nav__item--current")).click();
        //点击领域
        webDriver.findElement(By.xpath("//nav/a[2]")).click();
        //点击问答
        webDriver.findElement(By.xpath("//nav/a[3]")).click();
        //点击关注
        webDriver.findElement(By.xpath("//nav/a[4]")).click();
        //排行榜
        webDriver.findElement(By.xpath("//nav/a[5]")).click();
        //最新
        webDriver.findElement(By.xpath("//nav/a[6]")).click();
        //测试


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

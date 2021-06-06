package seleniumDay02;

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
 * @Create: 2021-04-30 11:09
 * @Desc：
 **/
public class ElementProject {
    public static void main(String[] args) throws Exception {
        WebDriver driver = openBrowser("chrome");
        //窗口最大化
        driver.manage().window().maximize();
        //隐式等待10秒
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://8.129.91.152:8765/Admin/Index/main.html");
        driver.findElement(By.name("admin_name")).sendKeys("lemon7");
        Thread.sleep(1000);
        driver.findElement(By.name("admin_pwd")).sendKeys("lemonbest");
        driver.findElement(By.name("code")).sendKeys("hapi");
        driver.findElement(By.xpath("//button[text()='登陆后台']")).click();
        //点击进入广告管理
        driver.findElement(By.id("_easyui_tree_24")).click();
        driver.switchTo().frame("mainFrame");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='添加']")).click();
        driver.findElement(By.xpath("//td[text()='说明:']/following-sibling::td/input")).sendKeys("机器人管理平台");
        driver.findElement(By.xpath("//td[text()='链接地址:']/following-sibling::td/input")).sendKeys("http://rms.utry-robot.com/#/");
        driver.findElement(By.xpath("//input[@type='button' and @value='提交']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[text()='机器人管理平台']")).click();
        driver.findElement(By.xpath("//span[text()='删除']")).click();
        driver.findElement(By.xpath("//span[text()='确认']")).click();
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

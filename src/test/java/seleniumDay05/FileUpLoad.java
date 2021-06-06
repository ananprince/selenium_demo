package seleniumDay05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

/**
 * @Project: seleniumDemo
 * @Author: 54540
 * @Create: 2021-05-08 11:28
 * @Desc：
 **/
public class FileUpLoad {
    public static void main(String[] args) {
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.layui.com/demo/upload.html");
        driver.findElement(By.id("test1")).click();
        //Java运行时对象
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("src/test/resources/test.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

package seleniumDay02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Project: seleniumDemo
 * @Author: 54540
 * @Create: 2021-04-29 09:21
 * @Desc：
 **/
public class ElementWaitDemo {
    public static void main(String[] args) throws Exception {
        jbTest("渣渣辉");
        Thread.sleep(2000);
        tbTest(" 渣渣辉");
    }
    //投标
    public static void tbTest(String tbName) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
        //打开前程贷页面，进行投标操作
        driver.get("http://8.129.91.152:8765/Index/index.html");
        driver.findElement(By.xpath("//span/a[text()='登录']")).click();
        driver.findElement(By.name("phone")).sendKeys("13323234545");
        driver.findElement(By.name("password")).sendKeys("lemon123456");
        driver.findElement(By.xpath("//button[text()='登录']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='"+tbName+"']/parent::div/parent::a/following-sibling::div[1]//a")).click();
        driver.findElement(By.xpath("//input[contains(@class,'unit-investinput')]")).sendKeys("1200000");
        driver.findElement(By.xpath("//button[text()='投标']")).click();
    }
    //加标
    public static void jbTest(String jbname) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.get("http://8.129.91.152:8765//Admin/Index/login.html");
        driver.findElement(By.name("admin_name")).sendKeys("lemon7");
        Thread.sleep(1000);
        driver.findElement(By.name("admin_pwd")).sendKeys("lemonbest");
        driver.findElement(By.name("code")).sendKeys("hapi");
        driver.findElement(By.xpath("//button[text()='登陆后台']")).click();
        driver.findElement(By.xpath("//div[@id='_easyui_tree_15']/span[text()='借款管理']")).click();
        //定位到iframe页面中
        driver.switchTo().frame("mainFrame");
        driver.findElement(By.xpath("//span[contains(text(),'加标')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='输入手机号码进行查找']")).sendKeys("13323234444");
        driver.findElement(By.xpath("//tr[@id='datagrid-row-r2-2-0']/td/div[text()='自动化测试帐号2']")).click();
        //输入标题
        driver.findElement(By.xpath("//td[text()='贷款标题:']/following-sibling::td/input")).sendKeys(jbname);
        driver.findElement(By.xpath("//td[text()='年利率利息:']/following-sibling::td/input")).sendKeys("5");
        driver.findElement(By.xpath("//td[text()='借款期限:']/following-sibling::td/input")).sendKeys("20");
        driver.findElement(By.xpath("//td[text()='借款额度:']/following-sibling::td/input")).sendKeys("5000000");
        driver.findElement(By.xpath("//td[text()='竞标期限:']/following-sibling::td/input")).sendKeys("100");
        driver.findElement(By.xpath("//span[text()='风控评测']")).click();
        driver.findElement(By.name("EvaluAmount")).sendKeys("200000");
        driver.findElement(By.xpath("//span[contains(text(),'录入')]")).click();
        driver.findElement(By.name("Native")).sendKeys("杭州");
        driver.findElement(By.xpath("//td[text()='职业:']/following-sibling::td/input")).sendKeys("IT");
        driver.findElement(By.name("Age")).sendKeys("22");
        driver.findElement(By.xpath("//span[text()='提交']")).click();
        Thread.sleep(1000);
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            driver.findElement(By.xpath("//td[@field='Title']/div[text()='"+jbname+"']")).click();
            driver.findElement(By.xpath("//span[text()='审核']")).click();
            driver.findElement(By.xpath("//span[text()='审核通过']")).click();
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

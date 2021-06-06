package seleniumDay04;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Project: seleniumDemo
 * @Author: 54540
 * @Create: 2021-05-06 13:12
 * @Desc：
 **/
public class WindowsDemo {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        open12306();
        Thread.sleep(2000);
        windowsSwitch();
        Thread.sleep(2000);
        scrollerTest();
    }
    /**
     * 公共窗口切换方法
     * @param title 标题
     */
    public static void switchWindow(String title){
        Set<String> allHandles = driver.getWindowHandles();
        for (String handle:allHandles){
            //判断是不是对应的句柄（根据什么来判断）
            if(driver.getTitle().equals(title)){
                //符合，跳出循环
                break;
            }else {
                //切换窗口
                driver.switchTo().window(handle);
            }
        }
    }
    //3、window窗口切换
    public static void windowsSwitch() throws InterruptedException {
        //句柄

        driver = openBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.get("https://www.baidu.com");
        //打开千千音乐之前获取所有的窗口句柄
        //Set<String> handles1 = driver.getWindowHandles();
        //System.out.println("打开千千音乐之前获取所有的窗口句柄："+handles1);
        //点击【更多】
        driver.findElement(By.xpath("//div[@class='mnav s-top-more-btn']/a[text()='更多']")).click();
        //使用javascript滚动页面到最底下
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //打开新闻之后获取所有的窗口句柄
        Set<String> handles2 = driver.getWindowHandles();
        System.out.println("打开千千音乐之后获取所有的窗口句柄："+handles2);
        //循环遍历所有的窗口句柄
        for (String handle:handles2){
            //判断是不是对应的句柄（根据什么来判断）
            if(driver.getTitle().equals("百度产品大全")){
                //符合，跳出循环
                break;
            }else {
                //切换窗口
                driver.switchTo().window(handle);
            }
        }
        switchWindow("百度产品大全");
        //点击【千千音乐】
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='千千音乐']")).click();
    }

//2、滚动操作
    public static void scrollerTest(){
        driver = openBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.treejs.cn/v3/demo/cn/exedit/drag.html");

        //鼠标拖拽
        WebElement webElement1 =  driver.findElement(By.id("treeDemo_2_span"));
        WebElement webElement2 = driver.findElement(By.id("treeDemo_1_span"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(webElement2).moveToElement(webElement1).release().perform();
    }
    //1、特殊元素操作、打开12306网站
    public static void open12306() throws InterruptedException {
        driver = openBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.12306.cn/index/");
        Thread.sleep(1000);
        driver.findElement(By.id("fromStationText")).click();
        driver.findElement(By.xpath("//li[@title='杭州']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("toStationText")).sendKeys("北京北");
        driver.findElement(By.id("toStationText")).sendKeys(Keys.ENTER);
        //移除只读属性
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("document.getElementById('train_date').removeAttribute('readonly')");
        //清除输入框信息
        driver.findElement(By.id("train_date")).clear();
        //输入日期
        driver.findElement(By.id("train_date")).sendKeys("2021-05-07");
        driver.findElement(By.id("train_date")).click();
        driver.findElement(By.xpath("//a/i[@class='icon icon-dancheng']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("search_one")).click();
    }
    //打开浏览器
    public static WebDriver openBrowser(String browserName){

            if("chrome".equalsIgnoreCase(browserName)){
                //执行打开chrome的代码
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
                return new ChromeDriver();
            }else if("firefox".equalsIgnoreCase(browserName)){
                //执行打开firefox的代码
                System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
                return new FirefoxDriver();
            }else if("ie".equalsIgnoreCase(browserName)){
                //执行打开ie的代码
                //取消IE安全设置（忽略IE的Protected Mode的设置）
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                //忽略掉浏览器缩放设置问题
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                //表示让我们脚本使用对应的驱动程序iedriver.exe
                System.setProperty("webdriver.ie.driver","src\\test\\resources\\IEDriverServer.exe");
                return new InternetExplorerDriver(capabilities);
            }else {
                System.out.println("浏览器不支持，请确认你的浏览器名是否正确");
                return null;
            }
        }
    }


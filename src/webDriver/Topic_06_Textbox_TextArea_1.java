package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Textbox_TextArea_1 {
    WebDriver driver;
    WebDriverWait explicitWait;
    String firstName = "Thu", lastName = "Xinh", employeeID, userName = "thuxinhgai", password = "Nguyenthilethu@2001", number = "0364697180", comment = "Nguyen Thi Le Thu";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //Mở trang PIM
        driver.findElement(By.xpath("//span[text()='PIM']//ancestor::li")).click();

        //Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']//parent::li")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //nhập dữ liệu
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        driver.findElement(By.xpath("//span[contains(@class, 'oxd-switch-input')]")).click();
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //verify dữ liệu
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

        //Nhấn sang tab Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //nhấn btn Add
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

        //nhap dữ liệu
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //nhấn btn edit
        driver.findElement(By.xpath("//i[contains(@class, 'bi-pencil-fill')]//parent::button")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //verify dữ liệu
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);

        //User => Logout
        driver.findElement(By.cssSelector(".oxd-userdropdown-tab")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //Login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //Mở trang My Info
        driver.findElement(By.xpath("//span[text()='My Info']//ancestor::li")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //verify dữ liệu
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

        //Nhấn sang tab Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //nhấn btn edit
        driver.findElement(By.xpath("//i[contains(@class, 'bi-pencil-fill')]//parent::button")).click();

        //đợi cho icon Loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));

        //verify dữ liệu
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

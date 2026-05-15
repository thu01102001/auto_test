package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Invalid_Email() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        Thread.sleep(3_000);
        driver.findElement(By.cssSelector("#txtEmail")).sendKeys("123@456@789");
        driver.findElement(By.cssSelector("#txtCEmail")).sendKeys("123@456@789");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#txtCPassword")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#txtPhone")).sendKeys("0368516970");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
    }

    @Test
    public void TC_03_Invalid_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("#txtEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtCEmail")).sendKeys("lethy@gmail.com");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#txtCPassword")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#txtPhone")).sendKeys("0368516970");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Invalid_Pasword() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("#txtEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("#txtCPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("#txtPhone")).sendKeys("0368516970");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.cssSelector("#txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Invalid_Confirm_Pasword() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("#txtEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123123123123");
        driver.findElement(By.cssSelector("#txtCPassword")).sendKeys("123123123133");
        driver.findElement(By.cssSelector("#txtPhone")).sendKeys("0368516970");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Invalid_Phone_Number_01() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("#txtEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123123123123");
        driver.findElement(By.cssSelector("#txtCPassword")).sendKeys("123123123123");
        driver.findElement(By.cssSelector("#txtPhone")).sendKeys("036851697078478");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
    }

    @Test
    public void TC_07_Invalid_Phone_Number_02() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.cssSelector("#txtEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtCEmail")).sendKeys("lethu2001@gmail.com");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123123123123");
        driver.findElement(By.cssSelector("#txtCPassword")).sendKeys("123123123123");
        driver.findElement(By.cssSelector("#txtPhone")).sendKeys("5412698740");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

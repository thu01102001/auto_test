package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_Login {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Empty() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(), "This is a required field.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("#email")).sendKeys("123434234@123123123123");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Invalid_Pass() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Incorrect() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123123123");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

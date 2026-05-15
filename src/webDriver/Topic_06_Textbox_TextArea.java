package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Textbox_TextArea {
    WebDriver driver;
    String firstName = "Nguyễn", lastName = "THu", emailAdress = "thu011000@gmail.com";
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_TechPanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("#password")).sendKeys("12345678");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("12345678");
        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Change Password']/parent::p")).getText().contains(firstName +" "+ lastName));
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Change Password']/parent::p")).getText().contains(emailAdress));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.cssSelector("input[value='5']")).click();
        driver.findElement(By.cssSelector("#review_field")).sendKeys("Good application");
        driver.findElement(By.cssSelector("#summary_field")).sendKeys("Best phone");
        driver.findElement(By.cssSelector("#review_field")).sendKeys("Good application");
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Your review has been accepted for moderation.");


    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

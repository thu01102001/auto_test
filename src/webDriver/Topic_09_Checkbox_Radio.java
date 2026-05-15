package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]//preceding-sibling::span/input")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]//preceding-sibling::span/input")).isSelected());
        driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]//preceding-sibling::span/input")).click();
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]//preceding-sibling::span/input")).isSelected());


    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

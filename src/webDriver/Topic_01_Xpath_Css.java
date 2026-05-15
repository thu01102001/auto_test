package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_01_Xpath_Css {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Html_Css() {
        driver.get("https://www.w3schools.com/");
        driver.findElement(By.xpath("//a[@id='tnb-user-profile']/following-sibling::a[4]")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

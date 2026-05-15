package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        By registerButton = By.cssSelector("button.fhs-btn-register");

        //Clickable
        //chờ cho 1 element ko được phép click
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                not(ExpectedConditions.elementToBeClickable(registerButton)));

        //Text hiển thị đúng
        Assert.assertEquals(driver.findElement(By.cssSelector("button.fhs-btn-register span")).getText().trim(), "Đăng ký");

        //Background màu gì
        System.out.println(driver.findElement(registerButton).getCssValue("background-color"));
        Assert.assertEquals(Color.fromString(driver.findElement(registerButton).getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        //Disable / Enable
        Assert.assertFalse(driver.findElement(registerButton).isEnabled());
    }

    @Test
    public void TC_02_Button_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("ul#popup-login-tab_list li")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(), "#000000");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0364675619");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Nguyenthilethu@2001");
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

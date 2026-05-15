package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(driver.findElement(By.cssSelector("#mail")).isDisplayed()) {
            System.out.println("Email is displayed");
        } else {
            System.out.println("Email is not displayed");
        }

        if(driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed()) {
            System.out.println("Age under 18 is displayed");
        } else {
            System.out.println("Age under 18 is not displayed");
        }

        if(driver.findElement(By.cssSelector("#edu")).isDisplayed()) {
            System.out.println("Edu is displayed");
        } else {
            System.out.println("Edu is not displayed");
        }

        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Name user 5 is displayed");
        } else {
            System.out.println("Name user 5 is not displayed");
        }
    }

    @Test
    public void TC_02_isEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.cssSelector("#mail")).isEnabled()) {
            System.out.println("Email is enabled");
        } else {
            System.out.println("Email is disabled");
        }

        if(driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled()) {
            System.out.println("Age under 18 is enabled");
        } else {
            System.out.println("Age under 18 is disabled");
        }

        if(driver.findElement(By.cssSelector("#edu")).isEnabled()) {
            System.out.println("Edu is enabled");
        } else {
            System.out.println("Edu is disabled");
        }

        if(driver.findElement(By.cssSelector("#job1")).isEnabled()) {
            System.out.println("job1 is enabled");
        } else {
            System.out.println("job1 is disabled");
        }

        if(driver.findElement(By.cssSelector("#job2")).isEnabled()) {
            System.out.println("job2 is enabled");
        } else {
            System.out.println("job2 is disabled");
        }

        if(driver.findElement(By.cssSelector("#development")).isEnabled()) {
            System.out.println("Development checkbox is enabled");
        } else {
            System.out.println("Development checkbox is disabled");
        }

        if(driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled()) {
            System.out.println("Slider 1 is enabled");
        } else {
            System.out.println("Slider 1 is disabled");
        }

        if(driver.findElement(By.cssSelector("#disable_password")).isEnabled()) {
            System.out.println("Password is enabled");
        } else {
            System.out.println("Password checkbox is disabled");
        }

        if(driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled()) {
            System.out.println("Radio button is enabled");
        } else {
            System.out.println("Radio button is disabled");
        }

        if(driver.findElement(By.cssSelector("#bio")).isEnabled()) {
            System.out.println("Bio is enabled");
        } else {
            System.out.println("Bio checkbox is disabled");
        }

        if(driver.findElement(By.cssSelector("#job3")).isEnabled()) {
            System.out.println("job3 is enabled");
        } else {
            System.out.println("job3 checkbox is disabled");
        }

        if(driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled()) {
            System.out.println("Checkbox is enabled");
        } else {
            System.out.println("Checkbox is disabled");
        }

        if(driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled()) {

            System.out.println("Slider 2 is enabled");
        } else {
            System.out.println("Slider 2 is disabled");
        }
    }

    @Test
    public void TC_03_isSelected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(!driver.findElement(By.xpath("//input[@id='under_18']")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='under_18']")).click();
            System.out.println("Age under 18 is selected");
        } else {
            System.out.println("Age under 19 is de-selected");
        }

        if(!driver.findElement(By.cssSelector("#java")).isSelected()) {
            driver.findElement(By.cssSelector("#java")).click();
            System.out.println("Language Java is selected");
        } else {
            System.out.println("Language Java is de-selected");
        }

        if(driver.findElement(By.cssSelector("#java")).isSelected()) {
            driver.findElement(By.cssSelector("#java")).click();
            System.out.println("Language Java is de-selected");
        } else {
            System.out.println("Language Java is selected");
        }
    }

    @Test
    public void TC_04_MailChimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("#email")).sendKeys("nguyenthilethu2001vnn@gmail.com");

        //nhập số
        driver.findElement(By.cssSelector("#new_password")).sendKeys("123");
        Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //chữ thường
        driver.findElement(By.cssSelector("#new_password")).clear();
        driver.findElement(By.cssSelector("#new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //chữ hoa
        driver.findElement(By.cssSelector("#new_password")).clear();
        driver.findElement(By.cssSelector("#new_password")).sendKeys("ABC");
        Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //chữ hoa
        driver.findElement(By.cssSelector("#new_password")).clear();
        driver.findElement(By.cssSelector("#new_password")).sendKeys("#$%");
        Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //hơn 8 ký tự
        driver.findElement(By.cssSelector("#new_password")).clear();
        driver.findElement(By.cssSelector("#new_password")).sendKeys("aaaaaaaaaaaaaaaaaa");
        Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //Hợp lệ
        driver.findElement(By.cssSelector("#new_password")).clear();
        driver.findElement(By.cssSelector("#new_password")).sendKeys("nguyenThile@2001");
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.cssSelector(".lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

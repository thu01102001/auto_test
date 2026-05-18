package webDriver;

import org.openqa.selenium.Alert;
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

public class Topic_10_Alert {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //Chờ cho 1 alert được xuất hiện trong HTML + sau đó switch vào
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        //Chờ cho 1 alert được xuất hiện trong HTML + sau đó switch vào
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_02_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        //Chờ cho 1 alert được xuất hiện trong HTML + sau đó switch vào
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys("thu");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: thu");
    }

    @Test
    public void TC_03_Authentication_Alert() throws InterruptedException {
        String username = "admin";
        String password = "admin";

        //1 - truyền thẳng U / P vào Url
        driver.get("http://" +username+ ":" +password+ "@" + "the-internet.herokuapp.com/basic_auth");
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_04_Authentication_Alert() throws InterruptedException {
        String username = "admin";
        String password = "admin";

        //1 - truyền thẳng U / P vào Url
        driver.get("http://the-internet.herokuapp.com/");
        Thread.sleep(2000);

        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");

        driver.get(passUserToAuthenLink(authenLink, username, password));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    public String passUserToAuthenLink (String authenLink, String username, String password) {
        String [] text = authenLink.split("//");
        return text[0] + "//" + username + ":" + password + "@" + text[1];
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}

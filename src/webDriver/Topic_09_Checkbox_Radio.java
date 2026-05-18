package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_09_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        // 1 - Browser mở ra nhưng ko maximize
        // 2 - Browser maximize nhưng loading icon chưa biến mất
        // 3 - Browser có maximize va loading icon bien mat nhung kich thước màn hình nhỏ
        // 4 - Nếu mở page ra cái element cần tương tác nó đã được chọn rồi / chưa chọn
    }

    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(2000);

        if(!driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        if(driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

    }

    @Test
    public void TC_02_Radio() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By carEngine = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(2000);

        if(!driver.findElement(carEngine).isSelected()) {
            driver.findElement(carEngine).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(carEngine).isSelected());

    }

    @Test
    public void TC_03_Checkbox_01() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");

        if (!driver.findElement(By.xpath("//label[text()='Summer']//parent::div/div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Summer']//parent::div/div/input")).click();
        }
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Summer']//parent::div/div/input")).isSelected());
    }

    @Test
    public void TC_04_Radio_01() throws InterruptedException {
        driver.get("https://material.angular.dev/components/checkbox/examples");

        if(!driver.findElement(By.xpath("//label[text()='Checked']//parent::div/div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Checked']//parent::div/div/input")).click();
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']//parent::div/div/input")).isSelected());

        if(driver.findElement(By.xpath("//label[text()='Checked']//parent::div/div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Checked']//parent::div/div/input")).click();
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']//parent::div/div/input")).isSelected());


        if(!driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div/div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div/div/input")).click();
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div/div/input")).isSelected());

        if(driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div/div/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div/div/input")).click();
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div/div/input")).isSelected());
    }

    @Test
    public void TC_05_SelectAll() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@class='form-single-column']//input[@type='checkbox']"));

        for(WebElement checkbox : allCheckboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for(WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        //deselect
        for(WebElement checkbox : allCheckboxes) {
            if(checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for(WebElement checkbox : allCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        for(WebElement checkbox : allCheckboxes) {
            if (checkbox.getDomAttribute("value").equals("Fainting Spells")) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }

        for(WebElement checkbox : allCheckboxes) {
            if (checkbox.getDomAttribute("value").equals("Fainting Spells")) {
                Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_06_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        if (!driver.findElement(By.cssSelector("input#id_new_user")).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#id_new_user")));
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

        if(!driver.findElement(By.cssSelector("input#id_accept_tos")).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#id_accept_tos")));
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_accept_tos")).isSelected());
    }

    @Test
    public void TC_07_Google_Form() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(2000);

        By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");

        driver.findElement(canthoRadio).click();
        Thread.sleep(2000);

        //Verify = các hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());

        //Verify lấy thuộc tính
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}

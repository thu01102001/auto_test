package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        //Selenium sẽ init browser lên với config mặc định
        // Tham số để nhận điện dang chạy browser bằng automation test
        // driver = new EdgeDriver();

        //Nó chạy với 1 browser có profile đã được confic / đã được manual test (end user)
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Microsoft/Edge/User Data/");
//        edgeOptions.addArguments("--profile-directory=Profile 3");
//        driver = new EdgeDriver(edgeOptions);

        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("-profile");
        options.addArguments("C:\\Users\\Admin\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\nybx4vjg.default-release-1");

        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).pause(Duration.ofSeconds(3)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_HoverToElement() {
        driver.get("http://www.myntra.com/");

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"))).pause(Duration.ofSeconds(3)).perform();
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_HoverToElement() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']//parent::div"))).pause(Duration.ofSeconds(3)).perform();
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']//ancestor::li"))).pause(Duration.ofSeconds(4)).perform();
        List<WebElement> allSubMenu = driver.findElements(By.cssSelector("li.aligned-left span.menu-title"));
        for(WebElement subMenu : allSubMenu) {
            System.out.println(subMenu.getText());
        }
        actions.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='My Little Pony']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "MY LITTLE PONY");
    }

    @Test
    public void TC_04_ClickAndHold_Fix() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Click chọn 1 số đầu tiên
        // Di chuột đến 1 số cuối cùng
        // Nhả chuột
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));

        actions.clickAndHold(numbers.get(4)).moveToElement(numbers.get(11)).release().perform();

        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));

        Assert.assertEquals(numbersSelected.size(), 8);

    }


    @Test
    public void TC_05_ClickAndHold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));

        actions.keyDown(Keys.CONTROL).perform();
        actions.click(numbers.get(3))
                .click(numbers.get(5))
                .click(numbers.get(11))
                .click(numbers.get(13))
                .click(numbers.get(19))
                .perform();
        actions.keyUp(Keys.CONTROL).perform();

        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));

        Assert.assertEquals(numbersSelected.size(), 5);

    }


    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }


    @Test
    public void TC_07_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.xpath("//span[contains(text(),'right click me')]"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']//parent::li")).isDisplayed());

        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']//parent::li"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        actions.click(driver.findElement(By.xpath("//span[text()='Quit']//parent::li"))).pause(Duration.ofSeconds(3)).perform();
        driver.switchTo().alert().accept();
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

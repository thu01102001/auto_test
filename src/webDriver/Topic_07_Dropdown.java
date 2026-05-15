package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_07_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() {
        driver.get("https://rode.com/en-au/support/where-to-buy");
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("#country"))).isMultiple());
        new Select(driver.findElement(By.cssSelector("#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div/div//h4"));
        int numberDealers = driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div/div")).size();
        Assert.assertEquals(numberDealers, 16);
        for(WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }
    }
    @Test
    public void TC_02_jQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("#speed-button", "#speed-menu li", "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button .ui-selectmenu-text")).getText(), "Medium");
        Thread.sleep(3000);

        selectItemInDropdown("#speed-button", "#speed-menu li", "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button .ui-selectmenu-text")).getText(), "Faster");
        Thread.sleep(3000);

        selectItemInDropdown("#speed-button", "#speed-menu li", "Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button .ui-selectmenu-text")).getText(), "Slower");
        Thread.sleep(3000);
    }

    private void selectItemInDropdown(String parentLocator, String childLocator, String textItem) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(3000);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        for(WebElement item : allItems) {
            if(item.getText().equals(textItem)) {
                item.click();
                Thread.sleep(3000);

                break;
            }
        }
    }

    @Test
    public void TC_03_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInDropdown("div.ui.fluid.selection.dropdown", "div.visible.menu.transition div span", "Christian");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown div")).getText(), "Christian");
    }

    @Test
    public void TC_04_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");

        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
    }

    @Test
    public void TC_05_Multiple() throws InterruptedException {
        driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

        //chọn 3
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']")).click();
        Thread.sleep(3000);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='ms-drop bottom']//li")));

        driver.findElement(By.xpath("//div[@class='ms-drop bottom']//li//span[text()='January']")).click();
        driver.findElement(By.xpath("//div[@class='ms-drop bottom']//li//span[text()='February']")).click();
        driver.findElement(By.xpath("//div[@class='ms-drop bottom']//li//span[text()='March']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']/button/span")).getText(), "January, February, March");

        driver.findElement(By.xpath("//div[@class='ms-drop bottom']//li//span[text()='April']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']/button/span")).getText(), "4 of 12 selected");

        driver.findElement(By.xpath("//div[@class='ms-drop bottom']//li//span[text()='All selected']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']/button/span")).getText(), "All selected");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

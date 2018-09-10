import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new SeleniumConfig().getDriver();
        driver.navigate().to("http://pecunia2.zaw.uni-heidelberg.de/AFE_HD/coin_search_detailed");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void websiteReachable(){
        assertEquals("AFE Web", driver.getTitle());
    }

    @Test
    public void emptyFormular(){
        WebElement searchBox = driver.findElement(By.name("detailedSearch"));
        searchBox.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMSG = driver.switchTo().alert().getText();
        assertTrue(alertMSG.startsWith("You need"));
    }

    @Test
    public void dropDownMenu(){
        WebElement function = driver.findElement(By.name("function"));
        Select select = new Select(function);
        //List<String> list = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        select.selectByVisibleText("Casting mould (Gußform)");
        assertEquals("Casting mould (Gußform)",select.getFirstSelectedOption().getText());
        select.selectByVisibleText("");
        assertNotEquals("Casting mould (Gußform)",select.getFirstSelectedOption().getText());
        function.click();
        function.sendKeys("c");
        assertEquals("Casting mould (Gußform)",select.getFirstSelectedOption().getText());
    }

    @Test
    public void invalidWeight() {
        WebElement weight = driver.findElement(By.name("weight"));
        weight.sendKeys("not a weight");
        assertEquals("",weight.getAttribute("style"));
        driver.findElement(By.name("detailedSearch")).click();
        assertEquals("background-color: red; color: white;",weight.getAttribute("style"));
    }

    @Test
    public void mintClickX(){
        WebElement delete_mint = driver.findElement(By.id("delete_mint"));
        WebElement autocomplete_mint = driver.findElement(By.id("autocomplete_mint"));
        assertEquals("",autocomplete_mint.getAttribute("value"));
        delete_mint.click();
        autocomplete_mint.sendKeys("mint text");
        assertEquals("mint text",autocomplete_mint.getAttribute("value"));
        delete_mint.click();
        assertEquals("",autocomplete_mint.getAttribute("value"));
    }

    @Test
    public void date_from(){
        WebElement date_from = driver.findElement(By.id("date_from"));
        date_from.sendKeys("1990");
        List<WebElement> submit = driver.findElements(By.xpath("//button[@type='submit']"));
        assertTrue(submit.size() > 0);
        submit.get(0).click();
        assertNotNull(driver.findElement(By.id("coinTable")));
    }

    @Test
    public void date_from_wrong_date(){
        WebElement date_from = driver.findElement(By.id("date_from"));
        date_from.sendKeys("TEXT");
        List<WebElement> submit = driver.findElements(By.xpath("//button[@type='submit']"));
        assertTrue(submit.size() > 0);
        submit.get(0).click();
        assertEquals(0, driver.findElements(By.id("coinTable")).size());
        WebElement activeElement = driver.switchTo().activeElement();
        assertEquals("TEXT",activeElement.getAttribute("value"));
    }

    @Test
    public void date_from_after_to(){
        WebElement date_from = driver.findElement(By.id("date_from"));
        WebElement date_to = driver.findElement(By.id("date_to"));
        date_from.sendKeys("2000");
        date_to.sendKeys("1000");
        List<WebElement> submit = driver.findElements(By.xpath("//button[@type='submit']"));
        assertTrue(submit.size() > 0);
        submit.get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("problem - from: 2000 to: 1000",driver.switchTo().alert().getText());
    }

    @Test
    public void autocomplete(){
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.id("autocomplete_mint"));
        WebElement parent = element.findElement(By.xpath(".."));
        element.sendKeys("vi");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until((webDriver -> parent.findElements(By.xpath("//li[@class='li_class']")).size()!=0));
        WebElement child = parent.findElement(By.xpath("//li[@id='160']"));
        child.click();
        assertEquals("Viminacium (null)",element.getAttribute("value"));
    }
}
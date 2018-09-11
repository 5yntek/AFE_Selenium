import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ChromeDriverTest extends AbstractGeneralTests {

    @BeforeEach
    public void setUp() {
        driver = new SeleniumConfig().getChromeDriver();
        driver.navigate().to("http://pecunia2.zaw.uni-heidelberg.de/AFE_HD/coin_search_detailed");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        //Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void dropDownMenu(){
        WebElement function = driver.findElement(By.name("function"));
        Select select = new Select(function);
        //List<String> list = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        select.selectByVisibleText("Casting mould (Gußform)");
        assertEquals("7",
                select.getFirstSelectedOption()
                        .getAttribute("value"),
                "First Select");
        select.selectByVisibleText("");
        assertNotEquals("Casting mould (Gußform)",
                select.getFirstSelectedOption()
                        .getAttribute("value"),
                "clear select");
        function.click();
        function.sendKeys("c");
        assertEquals("7",
                select.getAllSelectedOptions().stream().map((x)->x.getAttribute("value")).filter((x)->!x.isEmpty()).findFirst().get(),
                "Second select");
        select.selectByVisibleText("");
        assertNotEquals("7",
                select.getFirstSelectedOption().
                        getAttribute("value"),
                "clear select");
        function.click();
        function.sendKeys("cc");
        assertEquals("13",
                select.getFirstSelectedOption()
                        .getAttribute("value")
                ,"Third select");
    }
}

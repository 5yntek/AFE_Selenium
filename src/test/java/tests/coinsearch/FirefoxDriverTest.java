package tests.coinsearch;

import org.junit.jupiter.api.*;
import tests.SeleniumConfig;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class FirefoxDriverTest extends AbstractGeneralTests {


    @BeforeEach
    public void setUp() throws IOException {
        driver = new SeleniumConfig().getFireFoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.navigate().to("http://pecunia2.zaw.uni-heidelberg.de/AFE_HD/coin_search_detailed");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

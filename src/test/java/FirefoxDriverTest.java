import org.junit.jupiter.api.*;

public class FirefoxDriverTest extends AbstractGeneralTests {


    @BeforeAll
    public static void setUp() {
        driver = new SeleniumConfig().getFireFoxDriver();
    }

    @BeforeEach
    public void navigate(){
        try{
            driver.navigate().to("http://pecunia2.zaw.uni-heidelberg.de/AFE_HD/coin_search_detailed");
            driver.navigate().refresh();
        }catch(Throwable ex){
            setUp();
            driver.navigate().to("http://pecunia2.zaw.uni-heidelberg.de/AFE_HD/coin_search_detailed");
        }

    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        //Thread.sleep(2000);
    }

    @AfterAll
    public static void quit(){
        driver.quit();
    }
}

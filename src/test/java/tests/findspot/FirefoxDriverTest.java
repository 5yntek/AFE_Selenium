package tests.findspot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.firefox.FirefoxDriver;
import tests.SeleniumConfig;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class FirefoxDriverTest extends AbstractGeneralTests {


    @BeforeEach
    public void setUp() throws IOException {
        driver = new SeleniumConfig().getFireFoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Assumptions.assumeTrue(Objects.nonNull(driver),"FirefoxDriver could not be created");
        super.login();
    }



}

package tests.findspot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.SeleniumConfig;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class ChromeDriverTest extends AbstractGeneralTests {

    @BeforeEach
    public void setUp() throws IOException {
        driver = new SeleniumConfig().getChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Assumptions.assumeTrue(Objects.nonNull(driver),"ChromeDriver could not be created");
        super.login();
       }

}
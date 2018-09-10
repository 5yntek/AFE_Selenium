import com.google.common.collect.Lists;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    private WebDriver driver;
    private static List<String> drivers = List.of("E:\\Java\\geckodriver.exe");

    public SeleniumConfig() {
        Optional<String> d = drivers.stream().filter((x) -> Files.exists(Paths.get(x))).findFirst();
        assert d.isPresent();
        System.setProperty("webdriver.gecko.driver", d.get());
        Capabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
}

    public WebDriver getDriver() {
        return driver;
    }
}

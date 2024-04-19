import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pogorelov.pages.YandexBefore;

import java.time.Duration;


public class YandexTests {

    private WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    @Test
    public void testYandex(){
        YandexBefore before = new YandexBefore(driver);
        before.openWebsite("https://market.yandex.ru/","Яндекс Маркет");
        before.openCatalog();
        before.indicateElectronics();
        before.openLaptop();
        before.validateLaptop();
        before.installPrice(10000,30000);
        before.selectManufacture();
        before.saveTitleLaptops();
        before.savePrice();
        Assertions.assertTrue(1==1);
    }
}


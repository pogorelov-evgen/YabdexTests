import io.qameta.allure.Step;
import io.qameta.allure.aspects.StepsAspects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pogorelov.pages.YandexBefore;

import static ru.pogorelov.pages.YandexStepsAll.*;

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
    public void testYandex() throws Exception {
        openWebsite("https://market.yandex.ru/",driver,"Яндекс Маркет");
        openCatalog();
        indicateElectronics();
        openLaptop();
        validateLaptop();
        installPrice(10000,30000);
        selectManufacture();
        checkingQuantityProduct();
        checkingFilterProduct();
        firstLaptop();
        inputFirstLaptop();
        pressSearch();
        Assertions.assertTrue( validLaptop());
    }
    @AfterEach
    public void afterEach(){
        driver.quit();
    }
}


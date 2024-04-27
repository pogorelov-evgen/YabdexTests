import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.aspects.StepsAspects;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pogorelov.helpers.TestsProperties;
import ru.pogorelov.pages.YandexBefore;

import static ru.pogorelov.helpers.Properties.properties;
import static ru.pogorelov.pages.YandexStepsAll.*;

import java.time.Duration;

/**
 * @author  Погорелов Денис
 * Класс отвечающий за тестирование Яндекс маркета
 * */
public class YandexTests {
    /**
     * Драйвер для работы с хромом
     */
    private WebDriver driver;

    /**
     * Методы вызываемый перед каждым тестом
     * */
    @BeforeEach
    public void beforeEach(){
        System.setProperty(properties.driver(), System.getenv("CHROME_DRIVER"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }
    /**
     * Метод тестрирования Яндекс маркета
     * */
    @Feature("Проверка Яндекс Маркета")
    @DisplayName("Проверка результатов поиска ноутбуков")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @CsvSource({"10000,30000"})
    public void testYandex(int minPrice, int maxPrice) {
        openWebsite(properties.yandexMarket(),driver,"Яндекс Маркет");
        openCatalog();
        indicateElectronics();
        openLaptop();
        validateLaptop();
        installPrice(minPrice,maxPrice);
        selectManufacture();
        checkingQuantityProduct();
        checkingFilterProduct();
        firstLaptop();
        inputFirstLaptop();
        pressSearch();
        Assertions.assertTrue(validLaptop(),"В результате поиска не найдено данного нотбука");
    }
    /**
     * Методы вызываемый после каждого теста
     * */
    @AfterEach
    public void afterEach(){
        driver.quit();
    }
}


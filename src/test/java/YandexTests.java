import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pogorelov.pages.YandexStepsAll;

import static ru.pogorelov.helpers.Properties.properties;

import java.time.Duration;
import java.util.List;

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
        System.setProperty(properties.driver(), properties.chromeDriver());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }
    /**
     * Метод тестрирования Яндекс маркета
     * @param title Данные для проверки названия веб страницы на которую перешли
     * @param  minPrice Устанавливаем минимальную цену на товар
     * @param maxPrice Устанавливаем максимальную цену на товар
     * @param manufacturersList Задаем список производителей
     * @param quantity задаем минимальное количества товара на странице которое мы проверяем
     * */
    @Feature("Проверка Яндекс Маркета")
    @DisplayName("Проверка результатов поиска ноутбуков")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @MethodSource("ru.pogorelov.helpers.DataProvider#provider")
    public void testYandex(String title, int minPrice, int maxPrice, List<String> manufacturersList,int quantity) {
        YandexStepsAll step = new YandexStepsAll(driver);
        step.openWebsite(properties.yandexMarket(),title);
        step.openCatalog();
        step.indicateElectronics();
        step.openLaptop();
        step.validateLaptop();
        step.installPrice(minPrice,maxPrice);
        step.selectManufacture(manufacturersList);
        step.checkingQuantityProduct(quantity);
        step.checkingFilterProduct(minPrice,maxPrice,manufacturersList);
        step.firstLaptop();
        step.inputFirstLaptop();
        step.pressSearch();
        Assertions.assertTrue(step.validLaptop(),"В результате поиска не найдено данного нотбука");
    }
    /**
     * Методы вызываемый после каждого теста
     * */
    @AfterEach
    public void afterEach(){
        driver.quit();
    }
}


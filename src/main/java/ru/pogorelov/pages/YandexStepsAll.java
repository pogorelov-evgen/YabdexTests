package ru.pogorelov.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexStepsAll {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static YandexBefore before;

    @Step("Переход на сайт {url}")
    public static void openWebsite(String url, WebDriver chromedriver, String title){
        driver = chromedriver;
        driver.get(url);
    }
    @Step("Переход в раздел каталог")
    public static void openCatalog(){
        before = new YandexBefore(driver);
        before.openCatalog();
    }
    @Step("Наводим курсор на раздел ноутбуки и компьютеры")
    public static void indicateElectronics(){
        before.indicateElectronics();
    }
    @Step("Переходим в раздел ноутбуки")
    public static void openLaptop(){
        before.openLaptop();
    }
    @Step("Проверка что перешли в раздел ноутбуки")
    public static void validateLaptop(){
        before.validateLaptop();
    }
    @Step("Задаем цену от {min} до {max}")
    public static void installPrice(int min, int max){
        before.installPrice(min, max);
    }
    @Step("Выбрать производителя HP и Lenovo")
    public static void selectManufacture(){
        before.selectManufacture();
    }
    @Step("Проверяем, что на первой странице отображается более 12 элементов товаров")
    public static void checkingQuantityProduct() throws Exception {
       before.addLaptopsFirstPage();
       before.checkCountLaptopsFirstPage();
    }
    @Step("Проверка, что на всех страницах предложения соответсвуют фильтру")
    public static void checkingFilterProduct() throws InterruptedException {
        before.addLaptopsAllPage();
        before.checkProductsAllPage("Lenovo", "HP", 30000, 10000);
    }
    @Step("Возвращаемся и запоминаем перввое наименование ноутбука")
    public static void firstLaptop(){
        before.firstLaptopInAllPage();
    }
    @Step("Вводим название нотбука, который находился первым в предыдущем списке")
    public static void inputFirstLaptop(){
        before.inputFirstNameLaptop();
    }
    @Step("Нажимаем кнопку найти")
    public static void pressSearch(){
        before.pressSearch();
    }
    @Step("Проверка что в результате поиска есть искомый товар")
    public static Boolean validLaptop(){
        before.addLaptopsInSearchPage();
        return before.validNameLaptopInSearchPage();
    }

}


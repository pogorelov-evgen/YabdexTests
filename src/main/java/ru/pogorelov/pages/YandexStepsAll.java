package ru.pogorelov.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * @author  Погорелов Денис
 * Класс отвечающий за пошаговые проверки нашей программы
 * */
public class YandexStepsAll {
    /**
      * Драйвер для работы с хромом
     */
    private WebDriver driver;
    /**
     * Переменная для работы с классом YandexBefore
     */
    private YandexBefore before;

    /**
     * Конструктор для создания класса YandexStepsAll
     * */
    public YandexStepsAll(WebDriver driver){
        this.driver = driver;
    }
    /**
     * Метод для проверки одного шага нашей программы
     * @param  url ссылка на страницу для перехода
     * @param title данные для проверки названия страницы на которую перешли
     */
    @Step("Переход на сайт {url}")
    public void openWebsite(String url, String title){
        before = new YandexBefore(driver);
        before.openWebsite(url, title);
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Переход в раздел каталог")
    public void openCatalog(){
        before.openCatalog();
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Наводим курсор на раздел ноутбуки и компьютеры")
    public void indicateElectronics(){
        before.indicateElectronics();
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Переходим в раздел ноутбуки")
    public void openLaptop(){
        before.openLaptop();
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Проверка что перешли в раздел ноутбуки")
    public void validateLaptop(){
        before.validateLaptop();
    }
    /**
     * Метод для проверки одного шага нашей программы
     * @param min Минимальная цена при выборе диапазона цен
     * @param max Максимальная цена при выборе диапазона цен
     */
    @Step("Задаем цену от {min} до {max}")
    public void installPrice(int min, int max){
        before.installPrice(min, max);
    }
    /**
     * Метод для проверки одного шага нашей программы
     * @param manufacturers список производителей
     */
    @Step("Выбираем производителей")
    public void selectManufacture(List<String>manufacturers){
        for (String manufacturer : manufacturers) {
            before.selectManufacture(manufacturer);
        }
    }
    /**
     * Метод для проверки одного шага нашей программы
     * @param quantity устанавливаем минимальное количество товара на первой странице, которые мы проверяем
     */
    @Step("Проверяем, что на первой странице отображается более {quantity} элементов товаров")
    public void checkingQuantityProduct(int quantity) {
       before.addLaptopsFirstPage();
       before.checkCountLaptopsFirstPage(quantity);
    }
    /**
     * Метод для проверки одного шага нашей программы
     * @param minPrice Минимальная цена при выборе диапазона цен
     * @param maxPrice Максимальная цена при выборе диапазона цен
     * @param manufacturersList устанавливаем минимальное количество товара на первой странице, которые мы проверяем
     */
    @Step("Проверка, что на всех страницах предложения соответсвуют фильтру")
    public void checkingFilterProduct(int minPrice, int maxPrice, List<String> manufacturersList)  {
        before.addLaptopsAllPage();
        before.checkProductsAllPage(minPrice, maxPrice, manufacturersList);
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Возвращаемся и запоминаем перввое наименование ноутбука")
    public void firstLaptop(){
        before.firstLaptopInAllPage();
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Вводим название нотбука, который находился первым в предыдущем списке")
    public void inputFirstLaptop(){
        before.inputFirstNameLaptop();
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Нажимаем кнопку найти")
    public void pressSearch(){
        before.pressSearch();
    }
    /**
     * Метод для проверки одного шага нашей программы
     */
    @Step("Проверка что в результате поиска есть искомый товар")
    public Boolean validLaptop(){
        before.addLaptopsInSearchPage();
        return before.validNameLaptopInSearchPage();
    }

}


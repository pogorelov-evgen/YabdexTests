package ru.pogorelov.pages;

import ru.pogorelov.helpers.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 * @author  Погорелов Денис
 * Класс отвечающий за страницу Яндекс Маркета
 * */
public class YandexBefore {

    /**
     * Драйвер для работы с хромом
     */
    private WebDriver driver;
    /**
     * Драйвер для установления явных ожиданий
     */
    private Wait<WebDriver> wait;
    /**
     * Элемент указывающий на кнопку выбора каталогов товара в Яндекс Маркете
     */
    private WebElement catalog;
    /**
     * Элемент указывающий на тип товара "электроника" в каталоге товаров
     */
    private WebElement electronics;
    /**
     * Элемент указывающий на тип товара "электроника" в подкаталоге электроники
     */
    private WebElement laptop;
    /**
     * Элемент области поиска Яндекс Маркета в разделе ноутбуков, где задается минимальная цена
     * */
    private WebElement minPrice;
    /**
     * Элемент области поиска Яндекс Маркета в разделе ноутбуков, где задается максимальная цена
     * */
    private WebElement maxPrice;
    /**
     * Список названий ноутбуков
     * */
    private List<WebElement> titleLaptops;
    /**
     * Список цен ноутбуков
     * */
    private List<WebElement> priceLaptops;
    /**
     * Список цен ноутбуков переведенных в значения типа int
     * */
    private List<Integer> priceInteger;
    /**
     * Название первого выданного в поиске ноутбука
     * */
    private String firstLaptopsInPages;
    /**
     * Элемент отвечающий за общее поле поиска Яндекс Маркета
     * */
    private WebElement searchVoid;
    /**
     * Элемент отвечающий за кнопку общего поиска
     * */
    private WebElement searchButton;
    /**
     * Список названий ноутбуков при общем поиске по названию
     * */
    private List<WebElement> titleSearch;
    /**
     * Элемент отвечающий за кнопку "Показать еще" в конце поисковой страницы
     * */
    private WebElement buttonShowMore;

    /**
     * Конструктор для создания класса для работы со страницей Яндекс Маркета
     * @param chromeDriver передается вызывающим
     * Создается драйвер для явного ожидания с максимальным временным ожиданием - 20 секунд
     * */
    public YandexBefore(WebDriver chromeDriver) {
        this.driver = chromeDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    /**
     * Метод для перехода по переданной ссылке
     * @param  url ссылка на страницу для перехода
     * @param title данные для проверки названия страницы на которую перешли
     * */
    public void openWebsite(String url, String title){
        driver.get(url);
        Assertions.assertTrue(driver.getTitle().contains(title),"Название: "+ driver.getTitle()+" не соответсвует заданному: "+title);
    }
    /**
     * Метод открытия каталога товаров
     * */
    public void openCatalog(){
        this.catalog = driver.findElement(By.xpath("//span[text()='Каталог']"));
        catalog.click();
    }
    /**
     * Метод для наведения курсора на раздел - электроника
     * */
    public void indicateElectronics() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li/a/span[text()='Электроника']")));
        this.electronics = driver.findElement(By.xpath("//li/a/span[text()='Электроника']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).perform();
    }
    /**
     * Метод для открытия вкладки - ноутбуки
     * */
    public void openLaptop(){
        this.laptop = driver.findElement(By.xpath("//a[text()='Ноутбуки']"));
        laptop.click();
    }
    /**
     * Метод для проверки перехода в раздел - ноутбуки
     * */
    public void validateLaptop() {
        Assertions.assertTrue(driver.getTitle().contains("Ноутбуки"),"Раздел в который перешли: "+ driver.getTitle()+" не содержит поискового слова 'Ноутбуки'");
    }
    /**
     * Метод для установки диапазона цен на ноутбуки
     * @param min Минимальная цена при выборе диапазона цен
     * @param max Максимальная цена при выборе диапазона цен
     * */
    public void installPrice(int min, int max) {
        this.minPrice = driver.findElement(By.xpath("//div[@data-auto = 'filter-range-glprice']//span[@data-auto ='filter-range-min']//input"));
        this.maxPrice = driver.findElement(By.xpath("//div[@data-auto = 'filter-range-glprice']//span[@data-auto ='filter-range-max']//input"));
        minPrice.click();
        minPrice.sendKeys(String.valueOf(min));
        maxPrice.click();
        maxPrice.sendKeys(String.valueOf(max));
    }
    /**
     * Метод для выбора производителей ноутбуков
     * @param name название производителя которого мы выбираем
     * */
    public void selectManufacture(String name) {
        driver.findElement(By.xpath("//span[text()='"+name+"']/../span/span")).click();
    }
    /**
     * Метод сохранения названий ноутбуков выданных при поиске
     * */
    public void saveTitleLaptops() {
        this.titleLaptops = driver.findElements(By.xpath("//div[@data-auto-themename='listDetailed']//div[@data-baobab-name='title']//h3"));
        Assertions.assertTrue(!titleLaptops.isEmpty(),"Список названий ноутбуков пуст");
    }
    /**
     * Метод сохранения цен ноутбуков выданных при поиске
     * */
    public void savePriceLaptops() {
        this.priceLaptops = driver.findElements(By.xpath("//div[@data-auto-themename='listDetailed']//span[@data-auto='snippet-price-current']/span[1]"));
        Assertions.assertTrue(!priceLaptops.isEmpty(),"Список с ценами на ноутбуки пуст");
    }
    /**
     * Метод сохранения данных(цена и название) о ноутбуках выданных при поиске
     * */
    public void addLaptopsFirstPage() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-auto='SerpStatic-loader']//div[contains(@class, 'position_center')]//span[@role='progressbar']")));
        saveTitleLaptops();
        savePriceLaptops();
    }
    /**
     * Метод проверки количества выдаваемых ноутбуков на первой странице
     * @param quantity задаем минимальное количество товаров на странице, которое мы проверяем
     * */
    public void checkCountLaptopsFirstPage(int quantity) {
        Assertions.assertTrue(titleLaptops.size()>quantity, "Количество выдаваемых нотбуков на первой странице не больше "+quantity);

    }
    /**
     * Метод сохранения всех выдаваемых ноутбуков на всех страницах
     * */
    public void addLaptopsAllPage() {
        Actions actions = new Actions(driver);
        WebElement buttonShowMore = driver.findElement(By.xpath("//button[@data-auto = 'pager-more']/span"));
        actions.scrollToElement(buttonShowMore).perform();

        if (buttonShowMore.isDisplayed()) {
            buttonShowMore.click();
        }
        List<WebElement> allPages = driver.findElements(By.xpath("//div[@data-auto='SerpList']//h3[@data-auto='snippet-title']"));
        int countProducts = 0;
        List<WebElement> numberPages;
        int maxNumber;
        for(int i = 2; i < 50; i++) {
            countProducts = allPages.size();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-current-page='"+i+"']//button[@data-auto='pager-more']"))));
            actions.scrollToElement(buttonShowMore).perform();
            if (buttonShowMore.isDisplayed()) {
                buttonShowMore.click();
            }
            if (driver.findElement(By.xpath("//div[@data-auto=\"pagination-page\"]")).isDisplayed()){
                numberPages = driver.findElements(By.xpath("//div[@data-auto=\"pagination-page\"]"));

                HashSet<Integer> set = new HashSet<>();
                numberPages.forEach(x->set.add(Integer.parseInt(x.getText())));
                maxNumber = set.stream().max(Integer::compare).get();
                if ((maxNumber-1)==(i)){
                    break;
                }
            }
            allPages = driver.findElements(By.xpath("//div[@data-auto='SerpList']//h3[@data-auto='snippet-title']"));
            if (countProducts== allPages.size()) break;
        }
        saveTitleLaptops();
        savePriceLaptops();
        
    }
    /**
     * Метод проверки соответсвия всех выданных ноутбуков условиям поиска
     * @param manufacturersList Список с названиями производителей
     * @param  minPrice Минимальная цена при выборе диапазона цен
     * @param maxPrice максимальная цена при выборе диапазона цен
     * */
    public void checkProductsAllPage(int minPrice, int maxPrice, List<String> manufacturersList) {
        Assertions.assertTrue(titleLaptops.stream().allMatch(x->manufacturersList.contains(x.getText()))
                ,"Название ноутбуков не соответсвуют условиям поиска");

        convertPriceToInteger(priceLaptops);
        Assertions.assertTrue(priceInteger.stream().allMatch(x->(x>=minPrice && x<=maxPrice))
               ,"Цены ноутбуков не соответсвуют условиям поиска, не входят в диапозон: от "+minPrice+" до "+maxPrice);
    }
    /**
     * Метод для перевода цен ноутбуков в общем списке ноутбуков к значению выраженных в int
     * @param prices список цен ноутбуков выраженных в типе данных WebElement
     * */
    private void convertPriceToInteger(List<WebElement> prices) {
        for (WebElement price : prices) {
            char[]newPrice=price.getText().toCharArray();
            priceInteger = new ArrayList<>();
            priceInteger.add(Character.getNumericValue(newPrice[0])*10000 + Character.getNumericValue(newPrice[1])*1000 + Character.getNumericValue(newPrice[3])*100 + Character.getNumericValue(newPrice[4])*10 + Character.getNumericValue(newPrice[5]));
        }
    }
    /**
     * Метод для определения и сохранения назвавания первого ноутбука на первой странице поиска
     * */
    public void firstLaptopInAllPage() {
        firstLaptopsInPages = titleLaptops.get(0).getText();
    }
    /**
     * Метод для ввода в поисковую строку названия первого найденого ноутбука на первой странице
     * */
    public void inputFirstNameLaptop() {
        searchVoid = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchVoid.click();
        searchVoid.sendKeys(firstLaptopsInPages);
    }
    /**
     * Метод нажатия на кнопку поиска
     * */
    public void pressSearch() {
        searchButton = driver.findElement(By.xpath("//button[@data-auto='search-button']"));
        searchButton.click();
    }
    /**
     * Метод проверки названий найденных ноутбуков на соотношение с названием первого найденого ноутбука на первой странице
     * */
    public Boolean validNameLaptopInSearchPage() {
        addLaptopsInSearchPage();
        for(WebElement element:titleSearch){
            if(element.getText().equals(firstLaptopsInPages)) return true;
        }
        return false;
    }
    /**
     * Метод сохранения названий ноутбуков при поиске по названию
     * */
    public void addLaptopsInSearchPage() {
        titleSearch = driver.findElements(By.xpath("//div[@data-auto='SerpList']//h3[@data-auto='snippet-title']/.."));
    }
}

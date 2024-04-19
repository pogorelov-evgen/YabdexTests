package ru.pogorelov.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class YandexBefore {


    private WebDriver driver;

    private WebElement catalog;
    private WebElement electronics;
    private WebElement laptop;
    private WebElement minPrice;
    private WebElement maxPrice;
    private WebElement manufacturerHP;
    private WebElement manufacturerLenovo;
    private List<WebElement> titleLaptops;

    public YandexBefore(WebDriver chromeDriver) {
        this.driver = chromeDriver;
        /*this.catalog = driver.findElement(By.xpath("//span[text()='Каталог']"));
        this.electronics = driver.findElement(By.xpath("//li/a[contains(@href,'elektronika')]"));
        this.laptop = driver.findElement(By.xpath("//ul[@data-autotest-id = 'subItems']/li//a[contains(@href,'noutbuki') and not (contains(@href,'igrovye'))]"));
        this.minPrice = driver.findElement(By.xpath("//input[@id='range-filter-field-glprice_b9uigjk381n_min']"));
        this.maxPrice = driver.findElement(By.xpath("//input[@id='range-filter-field-glprice_b9uigjk381n_max']"));
        this.manufacturerHP = driver.findElement(By.xpath("//span[./span[text()='HP']]/span/span"));
        this.manufacturerLenovo = driver.findElement(By.xpath("//span[./span[text()='Lenovo']]/span/span"));*/
    }
    public void openWebsite(String url, String title){
        driver.get(url);
        System.out.println(driver.getTitle().contains(title));
    }

    public void openCatalog(){
        this.catalog = driver.findElement(By.xpath("//span[text()='Каталог']"));
        catalog.click();
        System.out.println("Catalog");
    }

    public void indicateElectronics() {
        this.electronics = driver.findElement(By.xpath("//li/a/span[text()='Электроника']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).perform();
        System.out.println("indicateElectronics");
    }

    public void openLaptop(){
        this.laptop = driver.findElement(By.xpath("//a[text()='Ноутбуки']"));
        laptop.click();
        System.out.println("openLaptop");
    }

    public void validateLaptop() {
        System.out.println(driver.getTitle().contains("Ноутбуки"));
    }

    public void installPrice(int min, int max) {
        this.minPrice = driver.findElement(By.xpath("//div[@data-auto = 'filter-range-glprice']//span[@data-auto ='filter-range-min']//input"));
        this.maxPrice = driver.findElement(By.xpath("//div[@data-auto = 'filter-range-glprice']//span[@data-auto ='filter-range-max']//input"));
        minPrice.click();
        minPrice.sendKeys(String.valueOf(min));
        maxPrice.click();
        maxPrice.sendKeys(String.valueOf(max));
        System.out.println("Цена установлена");
    }

    public void selectManufacture() {
        this.manufacturerHP = driver.findElement(By.xpath("//span[text()='HP']/../span/span"));
        this.manufacturerLenovo = driver.findElement(By.xpath("//span[text()='Lenovo']/../span/span"));
        manufacturerLenovo.click();
        manufacturerHP.click();
        System.out.println("manufacturer");
    }

    public void saveTitleLaptops() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        this.titleLaptops = driver.findElements(By.xpath("//div[@data-auto='SerpList']//h3[@data-auto='snippet-title']"));
        titleLaptops.forEach(x -> System.out.println(x.getText()));
    }

    public void savePrice() {
    }
}

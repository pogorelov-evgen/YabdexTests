package ru.pogorelov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        Main main = new Main();
        main.openWebsite(driver,"https://market.yandex.ru/", "Яндекс Маркет");
        driver.findElement(By.xpath("//span[text()='Каталог']")).click();
    }
    public void openWebsite(WebDriver driver, String url, String title){
        driver.get(url);
        System.out.println(driver.getTitle().contains(title));
    }
}
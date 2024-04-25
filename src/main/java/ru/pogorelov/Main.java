package ru.pogorelov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        driver.get("https://market.yandex.ru/catalog--noutbuki/26895412/list?hid=91013&rs=eJwz6mUOYDzKyHAgzRZIMoRYA8mEpzZAsuHJbpD4JSsQ2QcSWfABJOuwFiSuUAtiKxwEiTt0gMgE6T1A8kEASPbBc5DIgQ0g9oJdIDaDDkiWYTJY7zUQe4E3iFQIAtnroAQ2vxksexzM3goiFXwsQbp-gdkbweZ8Adn7oHo_2HyQbIMh2PZYsJtXg032AJv5DSTygAPETmgGkQo2YFlGELtBD2xXN9gXbWDXfgWbcBnso3UgWYY5YPY5UAg4rAG74SZY5A5Y1xOwvQpgUnYvyGQnsBomsO3cYNMWgt0sBXbJfbDtDWC2I4jt8A5kS0Km9SlGjmRTQ0MLS2Mjp05mLmkuDg5GAQUJLgUuATYpzpTUtMTSnJJ4YwUGDQa4JKMCI7KkIVhSDyzJAZYUkJJISizOTI5PTizKLy1OzYkvyEnMzIsvyS9QmLOCS-Pjy2wuM7B6brBNAlIKyYnFpYk5CA3G8ZklqbnF8cWpiUXJGfFGYH2dy9K4jKH2QPTJYrUHRdPOpWlcJlDLWBRYgJrk8FumsPlftcbRPylclmBdQlCrlNF1FaUWF-TnFWeWpSIsfLuVR2PlrgwuW7BWGaiF6rn5KalIOsszSzLiyzJTUvPjkxLz8lKLYDZvfc2j8bcxTYARABPh_P4%2C&pricefrom=10000&priceto=30000&glfilter=7893318%3A152981%2C152722");

        List<WebElement> titles = driver.findElements(By.xpath("//div[@data-auto='SerpList']//h3[@data-auto='snippet-title']/.."));
        List<WebElement> prices = driver.findElements(By.xpath("//span[@data-auto='snippet-price-current']/span[1]"));

        for (WebElement t : prices) {
            System.out.print(t.getText()+"; ");
        }
        System.out.println();
        ArrayList<Integer> newPriceList = new ArrayList<Integer>();
        for (WebElement t : prices) {
            char[] price = t.getText().toCharArray();
            newPriceList.add(Character.getNumericValue(price[0])*10+ Character.getNumericValue(price[1]));
        }
        newPriceList.forEach(System.out::println);



        driver.quit();
    }

}
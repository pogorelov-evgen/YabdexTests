package ru.pogorelov.helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author  Погорелов Денис
 * Класс отвечающий за задание аргументов для тестиорования
 * */
public class DataProvider {

    /**
    * Метод для задания аргумнтов для тестирования
     * titleMarket переменной задаем название странице с которой сравниваем
     * minPrice переменной устанавливаем минимальную цену товара при выборе диапазона
     * maxPrice переменной устанавливаем максимальную цену товара при выборе диапазона
     * manufacturersList задаем список производителей
     * quantity устанавливаем число с которым сравниваем количество выдаваемой продукции на первой страницы
    */
    public static Stream<Arguments> provider(){
        String titleMarket = "Яндекс Маркет";
        int minPrice = 10000;
        int maxPrice = 30000;
        List<String> manufacturersList = new ArrayList<String>();
        manufacturersList.add("HP");
        manufacturersList.add("Lenovo");
        int quantity = 13;
        return Stream.of(
                Arguments.of(titleMarket, minPrice, maxPrice, manufacturersList, quantity)
        );
    }
}

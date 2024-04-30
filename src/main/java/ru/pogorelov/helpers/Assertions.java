package ru.pogorelov.helpers;

import io.qameta.allure.Step;
/**
 * @author  Погорелов Денис
 * Класс для отображения удачно пройденых тестов
 * */
public class Assertions {
    /**
     * Метод для отображения всех шагов, в том числе и удачно пройденных
     * */
    @Step("Проверяем что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}

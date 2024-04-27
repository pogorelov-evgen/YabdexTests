package ru.pogorelov.helpers;

import io.qameta.allure.Step;
/**
 * Класс для отображения удачно пройденых тестов
 * */
public class Assertions {
    @Step("Проверяем что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}

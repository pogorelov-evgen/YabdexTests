package ru.pogorelov.helpers;

import org.aeonbits.owner.ConfigFactory;
/**
 * @author  Погорелов Денис
 * Класс отвечающий за установление зависимостей переменных
 * */
public class Properties {

    /**
     * Задаем статическую переменную для использовагния переменных
     */
    public static TestsProperties properties = ConfigFactory.create(TestsProperties.class);
}

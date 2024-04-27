package ru.pogorelov.helpers;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"
})
public interface TestsProperties extends Config{

    @Config.Key("yandexMarket.url")
    String yandexMarket();

    @Config.Key("driver")
    String driver();

}

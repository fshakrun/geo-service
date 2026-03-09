package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeoServiceImplTest {

    // Тесты для проверки определения локации по ip. Мокито в итоге не нужно

    @Test
    void shouldReturnRussiaForRussianIp() {

        GeoService geoService = new GeoServiceImpl();
        Location moscow = new Location("Moscow", Country.RUSSIA, null, 0);

        Location result = geoService.byIp("172.0.32.11");

        // Сравнивание страны: совпадает ли страна в полученном объекте с той, что мы ожидаем
        assertEquals(Country.RUSSIA, result.getCountry());
    }

    @Test
    void shouldReturnUSAForUSAIp() {

        GeoService geoService = new GeoServiceImpl();
        Location newYork = new Location("New York", Country.USA, null, 0);

        Location result = geoService.byIp("96.44.183.149");

        assertEquals(Country.USA, result.getCountry());
    }

    @Test
    void shouldReturnLocalHostForLocalHostIp() {

        GeoService geoService = new GeoServiceImpl();
        Location localHost = new Location(null, null, null, 0);

        Location result = geoService.byIp("127.0.0.1");

        assertNull(result.getCountry());
    }


}
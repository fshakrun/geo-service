package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GeoServiceImplTest {

    @Mock
    GeoService geoService;

    // Тесты для проверки определения локации по ip

    @Test
    void shouldReturnRussiaForRussianIp() {

        // Создаем объект локации
        Location moscow = new Location("Moscow", Country.RUSSIA, null, 0);
        // Когда Ip российский то нужно вернуть объект локации
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(moscow);

        // Вызываем метод. Сохраняем Ip в переменную
        Location result = geoService.byIp("172.0.32.11");

        // Сравнивание страны: совпадает ли страна в полученном объекте с той, что мы ожидаем
        assertEquals(Country.RUSSIA, result.getCountry());
        verify(geoService).byIp("172.0.32.11");
    }

    @Test
    void shouldReturnUSAForUSAIp() {
        Location newYork = new Location("New York", Country.USA, null, 0);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(newYork);

        Location result = geoService.byIp("96.44.183.149");

        assertEquals(Country.USA, result.getCountry());
        verify(geoService).byIp(anyString());
    }

    @Test
    void shouldReturnLocalHostForLocalHostIp() {
        Location localHost = new Location(null, null, null, 0);
        Mockito.when(geoService.byIp("127.0.0.1")).thenReturn(localHost);

        Location result = geoService.byIp("127.0.0.1");

        assertNull(result.getCountry());
        verify(geoService).byIp(anyString());
    }



}
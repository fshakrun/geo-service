package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    @Mock
    GeoService geoService;
    @Mock
    LocalizationService localizationService;
    @InjectMocks
    MessageSenderImpl messageSender;

    @Test
    void testSendRussianMessage() {

        // Передаем заголовки
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "172.0.32.11");

        // Создаем объект локации
        Location locationRussia = new Location("Moscow", Country.RUSSIA, null, 0);

        // Когда Ip российский, то нужно вернуть заданный ранее объект локации
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(locationRussia);

        // Когда передана страна Россия, то сообщение должно быть на русском
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Засылаем заголовки и сохраняем результат в переменную
        String result = messageSender.send(headers);

        // Проверяем что сообщение на русском
        assertEquals("Добро пожаловать", result);

    }

    @Test
    void testSendAmericanMessage() {

        // Передаем заголовки
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "96.44.183.149");

        // Создаем объект локации
        Location locationUSA = new Location("New York", Country.USA, null, 0);

        // Когда Ip американский, то нужно вернуть заданный ранее объект локации
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(locationUSA);

        // Когда передана страна США, то сообщение должно быть на американском
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        // Засылаем заголовки и сохраняем результат в переменную
        String result = messageSender.send(headers);

        // Проверяем что сообщение на американском, если передана страна - США и ip американский
        assertEquals("Welcome", result);

    }
}
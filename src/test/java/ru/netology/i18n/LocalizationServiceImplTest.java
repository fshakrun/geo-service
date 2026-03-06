package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LocalizationServiceImplTest {

    @Mock
    LocalizationService localizationService;

    @Test
    void shouldReturnRussianForRussia() {
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    void shouldReturnEnglishForUSA() {
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String result = localizationService.locale(Country.USA);

        assertEquals("Welcome", result);
    }


}
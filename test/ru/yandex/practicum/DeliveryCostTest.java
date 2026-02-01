package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {
    @Test
    void shouldCalculateCorrectCost() {
        StandardParcel standard = new StandardParcel(10, 1, "Книги", "Москва");
        FragileParcel fragile = new FragileParcel(5, 1, "Ваза", "Питер");

        assertEquals(20, standard.calculateDeliveryCost(), "Стандартная посылка считается неверно");

        assertEquals(20, fragile.calculateDeliveryCost(), "Хрупкая посылка считается неверно");
    }
}

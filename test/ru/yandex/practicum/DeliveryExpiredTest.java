package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryExpiredTest {
    @Test
    void testIsExpired() {
        // Отправлена в 5 день живет 3 дня. Годна до дня 8 включительно
        PerishableParcel pp = new PerishableParcel(2, 5, "Молоко",
                "Адрес", 3);

        assertFalse(pp.isExpired(6), "Должна быть годна на 6-й день");

        // Граничный сценарий последний день срока
        assertFalse(pp.isExpired(8), "Должна быть годна в последний день срока (8)");

        // Просрочк
        assertTrue(pp.isExpired(9), "Должна быть испорчена на 9-й день");
    }
}

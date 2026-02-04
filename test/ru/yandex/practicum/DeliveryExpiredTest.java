package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryExpiredTest {

    private final PerishableParcel parcel = new PerishableParcel(2, 5,
            "Молоко", "Адрес", 3);

    @Test
    void shouldNotBeExpiredBeforeDeadline() {
        assertFalse(parcel.isExpired(7),
                "Посылка должна быть свежей на 7-й день");
    }

    @Test
    void shouldNotBeExpiredOnDeadline() {
        assertFalse(parcel.isExpired(8),
                "Посылка должна быть свежей в 8-й день (день окончания срока)");
    }

    @Test
    void shouldBeExpiredAfterDeadline() {
        assertTrue(parcel.isExpired(9),
                "Посылка должна считаться испорченной на 9-й день");
    }
}
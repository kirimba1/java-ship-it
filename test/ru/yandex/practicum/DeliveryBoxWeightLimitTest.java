package ru.yandex.practicum;


import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryBoxWeightLimitTest {
    @Test
    void testParcelBoxWeightLimits() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel p1 = new StandardParcel(5, 1, "Посылка 1", "Адрес");
        StandardParcel p2 = new StandardParcel(5, 1, "Посылка 2", "Адрес");
        StandardParcel p3 = new StandardParcel(1, 1, "Лишняя", "Адрес");

        // стандартный сценарий
        box.addParcel(p1);
        assertEquals(1, box.getAllParcels().size());
        assertEquals(5, box.getCurrentWeight());

        // заполнение ровно до лимита
        box.addParcel(p2);
        assertEquals(2 , box.getAllParcels().size());
        assertEquals(10, box.getCurrentWeight());

        // превышение лимита
        box.addParcel(p3);
        assertEquals(2, box.getAllParcels().size(), "Посылка не должна была добавиться в полную коробку");
        assertEquals(10, box.getCurrentWeight());
    }
}

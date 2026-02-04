package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryBoxWeightLimitTest {

    private ParcelBox<StandardParcel> box;
    private StandardParcel parcel5kg;
    private StandardParcel anotherParcel5kg;
    private StandardParcel parcel1kg;

    @BeforeEach
    void setUp() {
        box = new ParcelBox<>(10);
        parcel5kg = new StandardParcel(5, 1, "Посылка 5кг", "Адрес");
        anotherParcel5kg = new StandardParcel(5, 1, "Посылка 5кг", "Адрес");
        parcel1kg = new StandardParcel(1, 1, "Посылка 1кг", "Адрес");
    }

    @Test
    void shouldAddParcelWhenSpaceAvailable() {
        box.addParcel(parcel5kg);

        assertEquals(1, box.getAllParcels().size(), "В коробке должна быть 1 посылка");
        assertEquals(5, box.getCurrentWeight(), "Вес коробки должен быть 5 кг");
    }

    @Test
    void shouldAddParcelWhenFillingExactlyToLimit() {
        box.addParcel(parcel5kg);
        box.addParcel(anotherParcel5kg);

        assertEquals(2, box.getAllParcels().size(), "В коробке должно быть 2 посылки");
        assertEquals(10, box.getCurrentWeight(), "Вес коробки должен быть равен лимиту (10 кг)");
    }

    @Test
    void shouldNotAddParcelWhenLimitExceeded() {
        box.addParcel(parcel5kg);
        box.addParcel(anotherParcel5kg);

        box.addParcel(parcel1kg);

        assertEquals(2, box.getAllParcels().size(), "Лишняя посылка не должна была добавиться");
        assertEquals(10, box.getCurrentWeight(), "Вес не должен превышать лимит");
    }
}
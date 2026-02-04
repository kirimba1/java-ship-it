package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {

    @Test
    void shouldCalculateCostForStandardParcel() {
        StandardParcel standard = new StandardParcel(10, 1, "Книги", "Таганрог");

        int actualCost = standard.calculateDeliveryCost();

        assertEquals(20, actualCost, "Стоимость стандартной посылки считается неверно");
    }

    @Test
    void shouldCalculateCostForFragileParcel() {
        FragileParcel fragile = new FragileParcel(5, 1, "Реликвия", "Ростов");

        int actualCost = fragile.calculateDeliveryCost();

        assertEquals(20, actualCost, "Стоимость хрупкой посылки считается неверно");
    }

    @Test
    void shouldCalculateCostForPerishableParcel() {
        PerishableParcel perishable = new PerishableParcel(10, 1, "Молоко",
                "Осташков", 3);

        int actualCost = perishable.calculateDeliveryCost();

        assertEquals(30, actualCost, "Стоимость скоропортящейся посылки считается неверно");
    }

    @Test
    void shouldCalculateZeroCostForZeroWeight() {
        StandardParcel zeroWeightParcel = new StandardParcel(0, 1,
                "Воздух", "Небо");

        int actualCost = zeroWeightParcel.calculateDeliveryCost();

        assertEquals(0, actualCost, "Посылка с нулевым весом должна стоить 0");
    }
}
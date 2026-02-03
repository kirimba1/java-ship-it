package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private int currentWeight;
    private final List<T> parcels;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Ошибка: Превышен максимальный вес коробки! Посылка "
                    + parcel.getDescription() + " не добавлена.");
        } else {
            parcels.add(parcel);
            currentWeight += parcel.getWeight();
            System.out.println("Посылка добавлена в коробку. Свободно: " + (maxWeight - currentWeight) + " кг.");
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }

    public void clearBox() {
        parcels.clear();
        currentWeight = 0;
    }
}

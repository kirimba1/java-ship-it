package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {
    private static final int BASE_COST = 4;

    public FragileParcel(int weight, int sendDay, String description, String deliveryAddress) {
        super(weight, sendDay, description, deliveryAddress);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    protected int getDeliveryCost() {
        return BASE_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }
}

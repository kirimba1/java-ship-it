package ru.yandex.practicum.delivery;

public class StandardParcel extends  Parcel {
    private static final int BASE_COST = 2;

    public StandardParcel(int weight, int sendDay, String description, String deliveryAddress) {
        super(weight, sendDay, description, deliveryAddress);
    }


    @Override
    protected int getConstant() {
        return BASE_COST;
    }
}

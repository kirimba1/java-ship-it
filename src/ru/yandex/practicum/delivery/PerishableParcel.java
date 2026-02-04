package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private static final int BASE_COST = 3;
    private final int timeToLive;

    public PerishableParcel(int weight, int sendDay, String description, String deliveryAddress, int timeToLive) {
        super(weight, sendDay, description, deliveryAddress);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return getSendDay() + getTimeToLive() < currentDay;
    }

    @Override
    protected int getDeliveryCost() {
        return BASE_COST;
    }

    @Override
    public boolean isAvailableForSend(int currentDay) {
        return !isExpired(currentDay);
    }
}

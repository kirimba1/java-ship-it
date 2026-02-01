package ru.yandex.practicum.delivery;

public abstract class Parcel {
    private int weight;
    private int sendDay;
    private String description;
    private String deliveryAddress;

    public Parcel(int weight, int sendDay, String description, String deliveryAddress) {
        this.weight = weight;
        this.sendDay = sendDay;
        this.description = description;
        this.deliveryAddress = deliveryAddress;
    }

    public int getWeight() {
        return weight;
    }

    public int getSendDay() {
        return sendDay;
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка "+ description +  " доставлена по адресу " + deliveryAddress);

    }

    public int calculateDeliveryCost() {
        return getWeight() * getConstant();
    }

    protected abstract int getConstant();

    public boolean isAvailableForSend(int currentDay) {
        return true;
    }
}

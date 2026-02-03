package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(30);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(20);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(15);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackParcels();
                    break;
                case 5:
                    showBoxContent();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылку");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static int askCurrentDay() {
        System.out.print("Введите текущий день месяца: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        return day;
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Хрупкая посылка");
        System.out.println("3 — Скоропортящаяся посылка");

        int parcelType = scanner.nextInt();

        System.out.print("Введите вес: ");
        int weight = scanner.nextInt();
        System.out.print("Введите день отправления: ");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите описание: ");
        String description = scanner.nextLine();
        System.out.print("Введите адрес доставки: ");
        String address = scanner.nextLine();

        Parcel parcel;

        switch (parcelType) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(weight, sendDay, description, address);
                standardBox.addParcel(standardParcel);
                parcel = standardParcel;
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(weight, sendDay, description, address);
                fragileBox.addParcel(fragileParcel);
                trackableParcels.add(fragileParcel);
                parcel = fragileParcel;
                break;
            case 3:
                System.out.print("Введите срок годности (дней): ");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                PerishableParcel perishableParcel = new PerishableParcel(weight, sendDay,
                        description, address, timeToLive);
                perishableBox.addParcel(perishableParcel);
                parcel = perishableParcel;
                break;
            default:
                System.out.println("Ошибка: неизвестный тип посылки.");
                return;
        }

        allParcels.add(parcel);

    }

    private static void showBoxContent() {
        System.out.println("Какую коробку хотите посмотреть?");
        System.out.println("1 — Стандартная, 2 — Хрупкая, 3 — Скоропортящаяся ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<? extends Parcel> parcels;
        String boxName;

        switch (choice) {
            case 1:
                parcels = standardBox.getAllParcels();
                boxName = "стандартных";
                break;
            case 2:
                parcels = fragileBox.getAllParcels();
                boxName = "хрупких";
                break;
            case 3:
                parcels = perishableBox.getAllParcels();
                boxName = "скоропортящихся";
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        System.out.println("Содержимое коробки для " + boxName + " посылок:");
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
        } else {
            for (Parcel parcel : parcels) {
                System.out.println("Посылка: " + parcel.getDescription() +
                        " (Вес: " + parcel.getWeight() + " кг)");
            }
        }
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Список посылок пуст.");
            return;
        }

        int currentDay = askCurrentDay();

        for (Parcel parcel : allParcels) {
            if (!parcel.isAvailableForSend(currentDay)) {
                System.out.println("Посылка " + parcel.getDescription() + " испортилась.");
                continue;
            }
            parcel.packageItem();
            parcel.deliver();
        }
        allParcels.clear();
        trackableParcels.clear();
        standardBox.clearBox();
        fragileBox.clearBox();
        perishableBox.clearBox();
    }

    private static void calculateCosts() {
        int totalCost = 0;

        for (Parcel parcel : allParcels) {
                totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость доставки посылок: " + totalCost);
    }

    private static void trackParcels() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок для отслеживания.");
            return;
        }

        System.out.println("Всего посылок в трекинге: " + trackableParcels.size());
        System.out.print("Введите новое местоположение: ");
        String location = scanner.nextLine();

        for (Trackable trackable : trackableParcels) {
            trackable.reportStatus(location);
        }
    }
}



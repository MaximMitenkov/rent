package org.mitenkov.menu;

import org.mitenkov.dao.Storage;
import net.datafaker.Faker;
import org.mitenkov.entity.RentalPoint;
import org.mitenkov.entity.VehicleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Menu {

    public static final Logger log = LoggerFactory.getLogger(Menu.class);

    protected static final String INPUT_DATE_FORMAT = "dd MM yyyy";
    protected static final String OUTPUT_DATE_FORMAT = "dd-MM-yyyy";
    private int budget, numberOfPassengers, distance;
    private final Storage storage;

    protected VehicleList vehicles = new VehicleList();
    private final VehicleManager vehicleManager = new VehicleManager();

    public Menu(Storage storage) {
        this.storage = storage;
    }

    public void start() {

        chooseRentalPoint();

        Scanner in = new Scanner(System.in);
        boolean doCycle = true;

        fillInTravelData();

        Faker faker = new Faker();
        String randomName = faker.name().fullName().toUpperCase();
        String randomCar = faker.vehicle().model();

        while (doCycle) {
            System.out.printf("""
                    Прошлый красавчик %s
                    Уже забрал свой %s
                    Выбирай и ты!%n
                    """, randomName, randomCar);
            System.out.println("\n\n\nВыберите нужную функцию");
            System.out.println("""
                    1) Показать список всех автомобилей
                    2) Рассчитать оптимальный вариант для поездки
                    3) Показать список доступных автомобилей для поездки
                    4) Обновить данные о поездке
                    5) Взять автомобиль в аренду на определенное время
                    6) Выбор другой точки
                    
                    0) Выйти из программы
                    """);


            int choice = Integer.parseInt(in.nextLine());

            try {
                switch (choice) {
                    case 1 -> {
                        vehicles.showWithId();
                        in.nextLine();
                    }
                    case 2 -> {
                        vehicles.printBestVariant(numberOfPassengers, budget, distance);
                        System.out.println();
                    }
                    case 3 -> {
                        vehicles.show(vehicles.sortPossibleVehicles(numberOfPassengers, budget, distance));
                        in.nextLine();
                    }
                    case 4 -> {
                        fillInTravelData();
                        in.nextLine();
                    }
                    case 5 -> vehicleManager.holdVehicle(vehicleManager.chooseVehicle()
                            .orElseThrow(ArrayIndexOutOfBoundsException::new));
                    case 6 -> vehicles = chooseRentalPoint().getVehicles();
                    case 0 -> doCycle = false;
                    default -> System.out.println("You entered incorrect number");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error("Invalid input", e);
            }
        }
    }

    //TODO Вынести в класс авторизации
    private void fillInTravelData() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите количество человек для поездки");
        numberOfPassengers = Integer.parseInt(in.nextLine());

        System.out.println("Введите ваш бюджет");
        budget = Integer.parseInt(in.nextLine());

        System.out.println("Введите предполагаемую длину пути");
        distance = Integer.parseInt(in.nextLine());

        System.out.println("Данные успешно записаны");
    }

    private RentalPoint chooseRentalPoint() {
        System.out.println("Выберите ближайшую к вам точку аренды, чтобы посмотреть доступный транспорт");
        storage.getRentalPoints().ifPresent(i -> i.forEach((a, b) -> b.showAddress(a)));

        Scanner in = new Scanner(System.in);
        int i = Integer.parseInt(in.nextLine());

        storage.getRentalPointById(i).ifPresent(point -> vehicles = point.getVehicles());
        vehicleManager.setVehicles(vehicles);
        return storage.getRentalPointById(i).orElseThrow();
    }
}

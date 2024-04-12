package org.mitenkov.menu;

import org.mitenkov.Main;
import org.mitenkov.entity.base.Vehicle;
import org.mitenkov.entity.VehicleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import static org.mitenkov.menu.Menu.INPUT_DATE_FORMAT;
import static org.mitenkov.menu.Menu.OUTPUT_DATE_FORMAT;

public class VehicleManager {

    private static final Logger log = LoggerFactory.getLogger(VehicleManager.class);

    protected VehicleList vehicles;

    protected VehicleManager(VehicleList vehicles) {
        this.vehicles = vehicles;
    }

    public VehicleManager() {

    }

    protected void holdVehicle(Vehicle vehicle) {

        System.out.println("""
                1) Указать дату до которой планируется взять т/с в аренду
                2) Взять т/с на сутки
                3) Взять т/с на 12 часов
                4) Указать количество часов, на которое планируется взять т/с
                """);
        Scanner in = new Scanner(System.in);
        switch (Integer.parseInt(in.nextLine())) {
            case 1:
                System.out.println("Введите дату, формат ввода даты 'DD mm YYYY'");
                LocalDate date = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
                int price = (int) vehicle.getTotalPrice(date);
                System.out.printf("В таком случае, вы заплатите за аренду %d денег%n", price);
                System.out.println(date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)));
                break;
            case 2:
                System.out.println("Это будет вам стоить " +
                        vehicle.getOneHourRentPrice() * 16 + vehicle.getMomentRentPrice());
                break;
            case 3:
                System.out.println("Это будет вам стоить " +
                        (vehicle.getOneHourRentPrice() * 12 + vehicle.getMomentRentPrice()));
                break;
            case 4:
                System.out.println("Введите количество часов для аренды");
                double priceHours = vehicle.calculatePriceAsHours(Duration.ofHours(Integer.parseInt(in.nextLine())));
                System.out.println("Вам будет это стоить " + priceHours);
            default: break;
        }
    }

    protected Optional<Vehicle> chooseVehicle() {
        Scanner in = new Scanner(System.in);
        vehicles.showWithId();
        System.out.println("Напишите номер выбранного вами транспорта");

        try {
            return Optional.ofNullable(vehicles.getVehicleById(Integer.parseInt(in.nextLine())));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            log.error("Picked number out of bounds", e);
            return Optional.empty();
        }
    }

    public void setVehicles(VehicleList vehicles) {
        this.vehicles = vehicles;
    }
}

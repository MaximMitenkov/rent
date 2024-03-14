package org.mitenkov.entity;

import org.mitenkov.entity.base.FuelableVehicle;

public class Car extends FuelableVehicle {
    public Car(String model,
               double rentPrice, //цена аренды за раз
               double oneHourRentPrice,
               int maxNumberOfPassengers,
               double maxSpeed,
               double fuelPricePerKilometer) {
        super(model, rentPrice,oneHourRentPrice, maxNumberOfPassengers, maxSpeed, fuelPricePerKilometer);
    }
}

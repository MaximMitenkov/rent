package org.mitenkov.entity;

import org.mitenkov.entity.base.FuelableVehicle;

public class Motobike extends FuelableVehicle {
    public Motobike(String model,
                    double rentPrice,
                    double oneHourRentPrice,
                    int maxNumberOfPassengers,
                    double maxSpeed,
                    double fuelPricePerKilometer) {
        super(model, rentPrice, oneHourRentPrice, maxNumberOfPassengers, maxSpeed, fuelPricePerKilometer);
    }
}

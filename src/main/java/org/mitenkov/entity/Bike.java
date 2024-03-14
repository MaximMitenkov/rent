package org.mitenkov.entity;

import org.mitenkov.entity.base.Vehicle;

public class Bike extends Vehicle {
    public Bike(String model, double rentPrice, double oneHourRentPrice, int maxNumberOfPassengers, double maxSpeed) {
        super(model, rentPrice, oneHourRentPrice, maxNumberOfPassengers, maxSpeed);
    }
}

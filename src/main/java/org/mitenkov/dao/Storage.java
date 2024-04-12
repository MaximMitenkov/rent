package org.mitenkov.dao;

import org.mitenkov.entity.RentalPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Storage {

    private static final Logger log = LoggerFactory.getLogger(Storage.class);

    private final Map<Integer, RentalPoint> rentalPoints = new HashMap<>();
    private int counter;

    public Storage(ArrayList<RentalPoint> rentalPoints) {
        this.counter = 0;
        rentalPoints.forEach(a -> {
            this.rentalPoints.put(counter++, a);
            if (a.getVehicles().getLength() < 1) {
                log.warn("Added empty rental point");
            }
        });
    }

    public Optional<RentalPoint> getRentalPointById(int index) {
        return Optional.ofNullable(rentalPoints.get(index));
    }

    //TODO нужно убрать из DAO всё, кроме операций с данными
    public Optional<Map<Integer, RentalPoint>> getRentalPoints() {
        return Optional.of(rentalPoints);
    }
}

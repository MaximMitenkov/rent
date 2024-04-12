package org.mitenkov;

import org.mitenkov.dao.Storage;
import org.mitenkov.generator.VehicleGenerator;
import org.mitenkov.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Project started");
        VehicleGenerator generator = new VehicleGenerator();
        Storage storage = new Storage(generator.generateRentalPointList(3));

        log.info("Storage generated");
        Menu menu = new Menu(storage);
        menu.start(); //запустить программу
    }

}


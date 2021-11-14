package application.controller;

import application.model.Conference;
import application.model.Excursion;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Controller {

    public static void init () {
        Controller.initStorage();
    }

    private static void initStorage () {
        Conference c1 = new Conference("Hav og himmel", "Odense Universitet", 1000,
                LocalDateTime.of(2021, 12, 18, 8, 0),
                LocalDateTime.of(2021, 12, 20, 18, 0),
                LocalDateTime.of(2021, 12, 15, 23, 59));
        Conference c2 = new Conference("Some name", "Aarhus Universitet", 1200,
                LocalDateTime.of(2022, 1, 10, 8, 0),
                LocalDateTime.of(2022, 1, 12, 18, 0),
                LocalDateTime.of(2022, 1, 7, 23, 59));

        Storage.addConferences(c1, c2);

        Excursion e1 = new Excursion("Byrundtur", "Tag på sightseeing i Odense", "Odense",
                LocalDateTime.of(2021, 12, 18, 10, 0), 125, true);
        Excursion e2 = new Excursion("Egeskov", "Gå en tur rundt i Egeskoven", "Egeskov",
                LocalDateTime.of(2021, 12, 19, 10, 0),  75, false);
        Excursion e3 = new Excursion("Trapholt Museum", "Se de mange fantastiske udstillinger i Trapholt Museum", "Kolding",
                LocalDateTime.of(2021, 12, 20, 11, 0), 200, true);

        Storage.addExcursions(e1, e2, e3);
        c1.addExcursions(e1, e2, e3);
    }

    // --------------------------------------------------------------

    public static ArrayList<Conference> getConferences () {
        return Storage.getConferences();
    }

    public static ArrayList<Excursion> getExcursions () {
        return Storage.getExcursions();
    }
}

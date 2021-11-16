package application.controller;

import application.model.*;
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

        Hotel h1 = new Hotel("Den Hvide Svane", "Odense", 1050, 1250);
        h1.createAddOn("bad", 0);
        h1.createAddOn("WIFI", 50);
        Hotel h2 = new Hotel("Hotel Phønix", "Odense", 700, 800);
        h2.createAddOn("bad", 200);
        h2.createAddOn("WIFI", 75);
        Hotel h3 = new Hotel("Pension Tusindfryd", "Odense", 500, 600);
        h3.createAddOn("morgenmad", 100);

        Storage.addHotels(h1, h2, h3);
        c1.addHotels(h1, h2, h3);
    }

    // --------------------------------------------------------------

    public static ArrayList<Conference> getConferences () {
        return Storage.getConferences();
    }

    // --------------------------------------------------------------

    public static ArrayList<Excursion> getExcursions () {
        return Storage.getExcursions();
    }

    // --------------------------------------------------------------

    public static Participant createParticipant (String name, String telephone, String address, String country, String city) {
        Participant participant = new Participant(name, telephone, address, country, city);
        Storage.addParticipant(participant);
        return participant;
    }
}

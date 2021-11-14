package storage;

import application.model.Conference;
import application.model.Excursion;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Storage {

    private static final ArrayList<Conference> conferences = new ArrayList<>();
    private static final ArrayList<Excursion> excursions = new ArrayList<>();

    // --------------------------------------------------------------

    public static void addConference (Conference conference) {
        Storage.conferences.add(conference);
    }

    public static void addConferences (Conference... conferences) {
        Storage.conferences.addAll(Arrays.asList(conferences));
    }

    public static void removeConference (Conference conference) {
        Storage.conferences.remove(conference);
    }

    public static ArrayList<Conference> getConferences () {
        return new ArrayList<>(Storage.conferences);
    }

    // --------------------------------------------------------------

    public static void addExcursion (Excursion excursion) {
        Storage.excursions.add(excursion);
    }

    public static void addExcursions (Excursion... excursions) {
        Storage.excursions.addAll(Arrays.asList(excursions));
    }

    public static void removeExcursion (Excursion excursion) {
        Storage.excursions.remove(excursion);
    }

    public static ArrayList<Excursion> getExcursions () {
        return new ArrayList<>(Storage.excursions);
    }
}

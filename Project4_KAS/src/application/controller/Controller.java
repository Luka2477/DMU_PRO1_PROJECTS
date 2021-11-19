package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Controller {

    public static void init () {
        Controller.initStorage();
    }

    private static void initStorage () {
        Conference c1 = Controller.createConference("Hav og himmel", "Odense Universitet", 1500,
                LocalDateTime.of(2021, 12, 18, 8, 0),
                LocalDateTime.of(2021, 12, 20, 18, 0),
                LocalDateTime.of(2021, 12, 15, 23, 59));
        Conference c2 = Controller.createConference("Some name", "Aarhus Universitet", 1200,
                LocalDateTime.of(2022, 1, 10, 8, 0),
                LocalDateTime.of(2022, 1, 12, 18, 0),
                LocalDateTime.of(2022, 1, 7, 23, 59));

        Excursion e1 = Controller.createExcursion("Byrundtur", "Tag på sightseeing i Odense", "Odense",
                LocalDateTime.of(2021, 12, 18, 10, 0), 125, true);
        Excursion e2 = Controller.createExcursion("Egeskov", "Gå en tur rundt i Egeskoven", "Egeskov",
                LocalDateTime.of(2021, 12, 19, 10, 0),  75, false);
        Excursion e3 = Controller.createExcursion("Trapholt Museum", "Se de mange fantastiske udstillinger i Trapholt Museum", "Kolding",
                LocalDateTime.of(2021, 12, 20, 11, 0), 200, true);

        c1.addExcursions(e1, e2, e3);

        Hotel h1 = Controller.createHotel("Den Hvide Svane", "Odense", 1050, 1250);
        h1.createAddOn("bad", 0);
        h1.createAddOn("WIFI", 50);
        Hotel h2 = Controller.createHotel("Hotel Phønix", "Odense", 700, 800);
        h2.createAddOn("bad", 200);
        h2.createAddOn("WIFI", 75);
        Hotel h3 = Controller.createHotel("Pension Tusindfryd", "Odense", 500, 600);
        h3.createAddOn("morgenmad", 100);

        c1.addHotels(h1, h2, h3);

        Participant p1 = Controller.createParticipant("Finn Madsen", "12345678", "abc 12", "Danmark", "Viby J");
        Registration r1 = p1.createRegistration("", "", LocalDate.of(2021, 12, 18), LocalDate.of(2021, 12, 20), false, c1);

        Participant p2 = Controller.createParticipant("Niels Petersen", "12345678", "abc 12", "Danmark", "Viby J");
        Registration r2 = p2.createRegistration("", "", LocalDate.of(2021, 12, 18), LocalDate.of(2021, 12, 20), false, c1);
        r2.setHotelRoom(h1.createHotelRoom(true));

        Participant p3 = Controller.createParticipant("Peter Sommer", "12345678", "abc 12", "Danmark", "Viby J");
        Registration r3 = p3.createRegistration("", "", LocalDate.of(2021, 12, 18), LocalDate.of(2021, 12, 20), false, c1);
        Companion cp1 = r3.createCompanion("Mie Sommer");
        cp1.addExcursion(e2);
        cp1.addExcursion(e3);
        HotelRoom hr1 = h1.createHotelRoom(false);
        hr1.addAddOn(h1.getAddOns().get(0));
        hr1.addAddOn(h1.getAddOns().get(1));
        r3.setHotelRoom(hr1);

        Participant p4 = Controller.createParticipant("Lone Jensen", "12345678", "abc 12", "Danmark", "Viby J");
        Registration r4 = p4.createRegistration("", "", LocalDate.of(2021, 12, 18), LocalDate.of(2021, 12, 20), true, c1);
        Companion cp2 = r4.createCompanion("Jan Madsen");
        cp2.addExcursion(e1);
        cp2.addExcursion(e2);
        HotelRoom hr2 = h1.createHotelRoom(false);
        hr2.addAddOn(h1.getAddOns().get(0));
        hr2.addAddOn(h1.getAddOns().get(1));
        r4.setHotelRoom(hr2);
    }

    // --------------------------------------------------------------

    public static Conference createConference (String name, String address, int dailyPrice, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime deadline) {
        Conference conference = new Conference(name, address, dailyPrice, startDate, endDate, deadline);
        Storage.addConference(conference);
        return conference;
    }

    public static void removeConference (Conference conference) {
        Storage.removeConference(conference);
    }

    public static ArrayList<Conference> getConferences () {
        return Storage.getConferences();
    }

    // --------------------------------------------------------------

    public static Excursion createExcursion (String name, String description, String destination, LocalDateTime dateTime, int price, boolean lunchIncluded) {
        Excursion excursion = new Excursion(name, description, destination, dateTime, price, lunchIncluded);
        Storage.addExcursion(excursion);
        return excursion;
    }

    public static void removeExcursion (Excursion excursion) {
        Storage.removeExcursion(excursion);
    }

    public static ArrayList<Excursion> getExcursions () {
        return Storage.getExcursions();
    }

    // --------------------------------------------------------------

    public static Participant createParticipant (String name, String telephone, String address, String country, String city) {
        Participant participant = new Participant(name, telephone, address, country, city);
        Storage.addParticipant(participant);
        return participant;
    }

    public static void removeParticipant (Participant participant) {
        Storage.removeParticipant(participant);
    }

    public static ArrayList<Participant> getParticipants () {
        return Storage.getParticipants();
    }

    // --------------------------------------------------------------

    public static void addRegistration (Registration registration) {
        Storage.addRegistration(registration);
    }

    public static void removeRegistration (Registration registration) {
        registration.getParticipant().removeRegistration(registration);
        Storage.removeRegistration(registration);
    }

    public static ArrayList<Registration> getRegistrations () {
        return Storage.getRegistrations();
    }

    // --------------------------------------------------------------

    public static Hotel createHotel (String name, String address, int singlePrice, int doublePrice) {
        Hotel hotel = new Hotel(name, address, singlePrice, doublePrice);
        Storage.addHotel(hotel);
        return hotel;
    }

    public static void removeHotel (Hotel hotel) {
        for (Conference conference : hotel.getConferences()) {
            conference.removeHotel(hotel);
        }

        for (Registration registration : Storage.getRegistrations()) {
            if (registration.getHotelRoom().getHotel() == hotel) {
                registration.setHotelRoom(null);
            }
        }

        Storage.removeHotel(hotel);
    }

    public static ArrayList<Hotel> getHotels () {
        return Storage.getHotels();
    }

    // --------------------------------------------------------------

    public static AddOn createAddOn (Hotel hotel, String name, int price) {
        return hotel.createAddOn(name, price);
    }
}

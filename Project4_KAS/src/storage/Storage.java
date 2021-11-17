package storage;

import application.model.*;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Storage {

    private static final ArrayList<Conference> conferences = new ArrayList<>();
    private static final ArrayList<Excursion> excursions = new ArrayList<>();
    private static final ArrayList<Hotel> hotels = new ArrayList<>();
    private static final ArrayList<Participant> participants = new ArrayList<>();
    private static final ArrayList<Registration> registrations = new ArrayList<>();

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

    // --------------------------------------------------------------

    public static void addHotel (Hotel hotel) {
        Storage.hotels.add(hotel);
    }

    public static void addHotels (Hotel... hotels) {
        Storage.hotels.addAll(Arrays.asList(hotels));
    }

    public static void removeHotel (Hotel hotel) {
        Storage.hotels.remove(hotel);
    }

    public static ArrayList<Hotel> getHotels () {
        return new ArrayList<>(Storage.hotels);
    }

    // --------------------------------------------------------------

    public static void addParticipant (Participant participant) {
        Storage.participants.add(participant);
    }

    public static void addParticipants (Participant... participants) {
        Storage.participants.addAll(Arrays.asList(participants));
    }

    public static void removeParticipant (Participant participant) {
        Storage.participants.remove(participant);
    }

    public static ArrayList<Participant> getParticipants () {
        return new ArrayList<>(Storage.participants);
    }

    // --------------------------------------------------------------

    public static void addRegistration (Registration registration) {
        Storage.registrations.add(registration);
    }

    public static void addRegistrations (Registration... registrations) {
        Storage.registrations.addAll(Arrays.asList(registrations));
    }

    public static void removeRegistration (Registration registration) {
        Storage.registrations.remove(registration);
    }

    public static ArrayList<Registration> getRegistrations () {
        return new ArrayList<>(Storage.registrations);
    }
}

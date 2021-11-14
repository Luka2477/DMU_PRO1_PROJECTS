package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conference {

    private String name;
    private String address;
    private int dailyPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;

    private final ArrayList<Registration> registrations = new ArrayList<>();
    private final ArrayList<Hotel> hotels = new ArrayList<>();
    private final ArrayList<Excursion> excursions = new ArrayList<>();

    public Conference (String name, String address, int dailyPrice, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime deadline) {
        this.name = name;
        this.address = address;
        this.dailyPrice = dailyPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
    }

    // ------------------------------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDailyPrice() {
        return this.dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    // ------------------------------------------------------------------------------

    public void addRegistration (Registration registration) {
        if (!this.registrations.contains(registration)) {
            this.registrations.add(registration);
            registration.setConference(this);
        }
    }

    public void removeRegistration (Registration registration) {
        if (this.registrations.contains(registration)) {
            this.registrations.remove(registration);
            registration.setConference(null);
        }
    }

    public ArrayList<Registration> getRegistrations () {
        return new ArrayList<>(this.registrations);
    }

    // ------------------------------------------------------------------------------

    public void addHotel (Hotel hotel) {
        if (!this.hotels.contains(hotel)) {
            this.hotels.add(hotel);
            hotel.addConference(this);
        }
    }

    public void removeHotel (Hotel hotel) {
        if (this.hotels.contains(hotel)) {
            this.hotels.remove(hotel);
            hotel.removeConference(this);
        }
    }

    public ArrayList<Hotel> getHotels () {
        return new ArrayList<>(this.hotels);
    }

    // ------------------------------------------------------------------------------

    public void addExcursion (Excursion excursion) {
        if (!this.excursions.contains(excursion)) {
            this.excursions.add(excursion);
        }
    }

    public void removeExcursion (Excursion excursion) {
        this.excursions.remove(excursion);
    }

    public ArrayList<Excursion> getExcursions () {
        return new ArrayList<>(this.excursions);
    }
}

package application.model;

import java.time.LocalDateTime;

public class Excursion {

    private String name;
    private String description;
    private String destination;
    private LocalDateTime dateTime;
    private int price;
    private boolean lunchIncluded;

    public Excursion (String name, String description, String destination,
                      LocalDateTime dateTime, int price, boolean lunchIncluded) {
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.dateTime = dateTime;
        this.price = price;
        this.lunchIncluded = lunchIncluded;
    }

    // ------------------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isLunchIncluded() {
        return this.lunchIncluded;
    }

    public void setLunchIncluded(boolean lunchIncluded) {
        this.lunchIncluded = lunchIncluded;
    }
}

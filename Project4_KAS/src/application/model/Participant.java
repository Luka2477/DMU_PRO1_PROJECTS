package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Participant extends Person {

    private String email;
    private String address;
    private String country;
    private String city;

    private ArrayList<Registration> registrations = new ArrayList<>();

    public Participant (String name, int age, String telephone, String email,
                        String address, String country, String city) {
        super(name, age, telephone);

        this.email = email;
        this.address = address;
        this.country = country;
        this.city = city;
    }

    // ------------------------------------------------------------------------------

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // ------------------------------------------------------------------------------

    public Registration createRegistration (String companyName, String companyTelephone, LocalDateTime arrivalDate,
                                            LocalDateTime departureDate, boolean speaker, Conference conference) {
        Registration registration = new Registration(
                this, companyName, companyTelephone, arrivalDate, departureDate, speaker, conference);
        this.registrations.add(registration);
        return registration;
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
}

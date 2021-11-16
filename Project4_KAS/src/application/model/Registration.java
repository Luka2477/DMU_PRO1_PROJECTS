package application.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Registration {

    private String companyName;
    private String companyTelephone;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private boolean speaker;

    private HotelRoom hotelRoom;
    private final Participant participant;
    private Companion companion;
    private Conference conference;

    public Registration (Participant participant, String companyName,
                         String companyTelephone, LocalDate arrivalDate,
                         LocalDate departureDate, boolean speaker, Conference conference) {
        this.participant = participant;
        this.companyName = companyName;
        this.companyTelephone = companyTelephone;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.speaker = speaker;

        this.setConference(conference);
    }

    // ------------------------------------------------------------------------------

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTelephone() {
        return this.companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(boolean speaker) {
        this.speaker = speaker;
    }

    // ------------------------------------------------------------------------------

    public void setConference(Conference conference) {
        if (this.conference != conference) {
            if (this.conference != null) {
                this.conference.removeRegistration(this);
            }

            if (conference != null) {
                conference.addRegistration(this);
            }

            this.conference = conference;
        }
    }

    public Conference getConference() {
        return this.conference;
    }

    // ------------------------------------------------------------------------------

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public HotelRoom getHotelRoom() {
        return this.hotelRoom;
    }

    // ------------------------------------------------------------------------------

    public Participant getParticipant() {
        return this.participant;
    }

    // ------------------------------------------------------------------------------

    public Companion createCompanion (String name) {
        Companion companion = new Companion(name);
        this.companion = companion;
        return companion;
    }

    public Companion getCompanion() {
        return this.companion;
    }

    // ------------------------------------------------------------------------------

    public double calulateTotalPrice () {
        int daysOfStay = (int) ChronoUnit.DAYS.between(this.arrivalDate, this.departureDate);
        double sum = (this.hotelRoom.getPrice() + this.conference.getDailyPrice() + this.hotelRoom.calculateAddOnPrice()) * daysOfStay;

        if (this.companion != null) {
            sum += this.companion.calculateExcursionsPrice();
        }

        return sum;
    }
}

package application.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Registration {

    private String companyName;
    private String companyTelephone;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private boolean speaker;

    private HotelRoom hotelRoom;
    private final Participant participant;
    private Companion companion;
    private Conference conference;

    public Registration (Participant participant, String companyName,
                         String companyTelephone, LocalDateTime arrivalDate,
                         LocalDateTime departureDate, boolean speaker, Conference conference) {
        this.participant = participant;
        this.companyName = companyName;
        this.companyTelephone = companyTelephone;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.speaker = speaker;
        this.conference = conference;
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

    public LocalDateTime getArrivalDate() {
        return this.arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
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

    public Companion createCompanion (String name, int age, String telephone) {
        Companion companion = new Companion(name, age, telephone);
        this.companion = companion;
        return companion;
    }

    public Companion getCompanion() {
        return this.companion;
    }

    // ------------------------------------------------------------------------------

    public double calulateTotalPrice () {
        int daysOfStay = (int) ChronoUnit.DAYS.between(this.arrivalDate, this.departureDate);
        double sum = (this.hotelRoom.getPrice() + this.conference.getDailyPrice()) * daysOfStay + this.hotelRoom.calculateAddOnPrice();

        if (this.companion != null) {
            sum += this.companion.calculateExcursionsPrice();
        }

        return sum;
    }
}

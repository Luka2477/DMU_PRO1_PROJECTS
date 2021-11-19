package gui;

import application.model.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminGetCodeWindow extends Stage {

    private final Object object;

    private TextArea txaCode;

    AdminGetCodeWindow(Object object) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Tilføj udflugt - KAS");

        this.object = object;

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private void initContent (GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // -------------------------------------------------------------------------

        this.txaCode = new TextArea();
        this.txaCode.setPrefSize(500, 300);
        pane.add(this.txaCode, 0, 0);

        // -------------------------------------------------------------------------

        this.initCode();
    }

    // -------------------------------------------------------------------------

    private void initCode () {
        StringBuilder code = new StringBuilder();

        if (this.object.getClass() == Participant.class) {
            Participant participant = (Participant) this.object;
            code = new StringBuilder(String.format("Participant p = Controller.createParticipant(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\");",
                    participant.getName(), participant.getTelephone(), participant.getAddress(),
                    participant.getCountry(), participant.getCity()));
        } else if (this.object.getClass() == Registration.class) {
            Registration registration = (Registration) this.object;
            code = new StringBuilder(String.format("Registration r = p.createRegistration(\"%s\", \"%s\", LocalDate.of(%d, %d, %d), LocalDate.of(%d, %d, %d), %s, CONFERENCE_%s);%n",
                    registration.getCompanyName(), registration.getCompanyTelephone(),
                    registration.getArrivalDate().getYear(),
                    registration.getArrivalDate().getMonthValue(),
                    registration.getArrivalDate().getDayOfMonth(),
                    registration.getDepartureDate().getYear(),
                    registration.getDepartureDate().getMonthValue(),
                    registration.getDepartureDate().getDayOfMonth(),
                    (registration.isSpeaker()) ? "true" : "false",
                    registration.getConference().getName()));

            if (registration.getCompanion() != null) {
                code.append(String.format("Companion cp = r.createCompanion(\"%s\");%n", registration.getCompanion().getName()));

                for (Excursion excursion : registration.getCompanion().getExcursions()) {
                    code.append(String.format("cp.addExcursion(EXCURSION_%s);%n", excursion.getName()));
                }
            }

            if (registration.getHotelRoom() != null) {
                code.append(String.format("HotelRoom hr = HOTEL_%s.createHotelRoom(%s);%n",
                        registration.getHotelRoom().getHotel().getName(),
                        (registration.getCompanion() != null) ? "false" : "true"));

                for (AddOn addOn : registration.getHotelRoom().getAddOns()) {
                    code.append(String.format("hr.createAddon(\"%s\", %d);%n", addOn.getName(), addOn.getPrice()));
                }

                code.append("r.setHotel(hr);");
            }
        } else if (this.object.getClass() == Conference.class) {
            Conference conference = (Conference) this.object;
            code = new StringBuilder(String.format("Conference c = Controller.createConference(\"%s\", \"%s\", %d, LocalDateTime.of(%d, %d, %d, %d, %d), LocalDateTime.of(%d, %d, %d, %d, %d), LocalDateTime.of(%d, %d, %d, %d, %d));%n",
                    conference.getName(), conference.getAddress(), conference.getDailyPrice(),
                    conference.getStartDate().getYear(),
                    conference.getStartDate().getMonthValue(),
                    conference.getStartDate().getDayOfMonth(),
                    conference.getStartDate().getHour(),
                    conference.getStartDate().getMinute(),
                    conference.getEndDate().getYear(),
                    conference.getEndDate().getMonthValue(),
                    conference.getEndDate().getDayOfMonth(),
                    conference.getEndDate().getHour(),
                    conference.getEndDate().getMinute(),
                    conference.getDeadline().getYear(),
                    conference.getDeadline().getMonthValue(),
                    conference.getDeadline().getDayOfMonth(),
                    conference.getDeadline().getHour(),
                    conference.getDeadline().getMinute()));

            if (!conference.getExcursions().isEmpty()) {
                for (Excursion excursion : conference.getExcursions()) {
                    code.append(String.format("c.addExcursion(EXCURSION_%s);%n", excursion.getName()));
                }
            }

            if (!conference.getHotels().isEmpty()) {
                for (Hotel hotel : conference.getHotels()) {
                    code.append(String.format("c.addHotel(HOTEL_%s);%n", hotel.getName()));
                }
            }
        } else if (this.object.getClass() == Hotel.class) {
            Hotel hotel = (Hotel) this.object;
            code = new StringBuilder(String.format("Hotel h = Controller.createHotel(\"%s\", \"%s\", %d, %d);%n",
                    hotel.getName(), hotel.getAddress(), hotel.getSinglePrice(), hotel.getDoublePrice()));

            if (!hotel.getAddOns().isEmpty()) {
                for (AddOn addOn : hotel.getAddOns()) {
                    code.append(String.format("h.createAddon(\"%s\", %d);%n", addOn.getName(), addOn.getPrice()));
                }
            }
        } else if (this.object.getClass() == Excursion.class) {
            Excursion excursion = (Excursion) this.object;
            code = new StringBuilder(String.format("Excursion e = Controller.createExcursion(\"%s\", \"%s\", \"%s\", LocalDateTime.of(%d, %d, %d, %d, %d), %d, %s);",
                    excursion.getName(), excursion.getDescription(), excursion.getDestination(),
                    excursion.getDateTime().getYear(),
                    excursion.getDateTime().getMonthValue(),
                    excursion.getDateTime().getDayOfMonth(),
                    excursion.getDateTime().getHour(),
                    excursion.getDateTime().getMinute(),
                    excursion.getPrice(),
                    (excursion.isLunchIncluded()) ? "true" : "false"));
        }

        this.txaCode.setText(code.toString());
    }

}

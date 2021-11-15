package gui;

import application.controller.Controller;
import application.model.AddOn;
import application.model.Conference;
import application.model.Excursion;
import application.model.Hotel;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class RegistrationPane extends GridPane {

    private Conference conference;

    private final GridPane conferencesGridPane, participantGridPane, companionGridPane, hotelGridPane;

    private final ListView<Conference> lvwConferences;
    private final ListView<Excursion> lvwExcursions;
    private final ListView<Hotel> lvwHotels;
    private final ListView<AddOn> lvwAddOns;
    private final TextField txfName, txfAddress, txfCountry, txfCity, txfTelephone, txfCompanyName, txfCompanyTelephone, txfCompanionName;
    private final CheckBox chbSpeaker, chbCompanion, chbHotel;
    private final DatePicker dtpStart, dtpEnd;
    private final Label lblCompanionName, lblExcursions, lblHotels, lblAddOns, lblDouble;

    public RegistrationPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.conferencesGridPane = new GridPane();
        this.conferencesGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.add(this.conferencesGridPane, 0, 0, 2, 1);

        Label lblConferenceHeader = new Label("Vælg konference");
        lblConferenceHeader.setFont(new Font(25));
        GridPane.setHalignment(lblConferenceHeader, HPos.CENTER);
        this.conferencesGridPane.add(lblConferenceHeader, 0, 0);

        this.lvwConferences = new ListView<>();
        this.lvwConferences.getItems().setAll(Controller.getConferences());
        this.lvwConferences.setPrefSize(1000, 200);
        this.conferencesGridPane.add(this.lvwConferences, 0, 1);

        ChangeListener<Conference> listener = (ov, oldConference, newConference) -> this.selectedConferenceChanged(newConference);
        this.lvwConferences.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        this.participantGridPane = new GridPane();
        this.participantGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.participantGridPane.setDisable(true);
        this.add(this.participantGridPane, 0, 1);

        Label lblHeader = new Label("Deltager");
        lblHeader.setFont(new Font(25));
        GridPane.setHalignment(lblHeader, HPos.CENTER);
        this.participantGridPane.add(lblHeader, 0, 0, 4, 1);

        Label lblName = new Label("Navn:");
        this.participantGridPane.add(lblName, 0, 1);

        this.txfName = new TextField();
        this.participantGridPane.add(this.txfName, 1, 1);

        Label lblSpeaker = new Label("Foredragsholder");
        this.chbSpeaker = new CheckBox();
        lblSpeaker.setGraphic(this.chbSpeaker);
        lblSpeaker.setContentDisplay(ContentDisplay.RIGHT);
        this.participantGridPane.add(lblSpeaker, 2, 1, 2, 1);

        Label lblAddress = new Label("Adresse:");
        this.participantGridPane.add(lblAddress, 0, 2);

        this.txfAddress = new TextField();
        this.participantGridPane.add(this.txfAddress, 1, 2);

        Label lblCity = new Label("By:");
        this.participantGridPane.add(lblCity, 2, 2);

        this.txfCity = new TextField();
        this.participantGridPane.add(this.txfCity, 3, 2);

        Label lblCountry = new Label("Land:");
        this.participantGridPane.add(lblCountry, 0, 3);

        this.txfCountry = new TextField();
        this.participantGridPane.add(this.txfCountry, 1, 3);

        Label lblTelephone = new Label("Tlf.nr.");
        this.participantGridPane.add(lblTelephone, 2, 3);

        this.txfTelephone = new TextField();
        this.participantGridPane.add(this.txfTelephone, 3, 3);

        Label lblStartDate = new Label("Ankomstdato:");
        this.participantGridPane.add(lblStartDate, 0, 4);

        this.dtpStart = new DatePicker();
        this.participantGridPane.add(this.dtpStart, 1, 4);

        Label lblEndDate = new Label("Afrejsedato:");
        this.participantGridPane.add(lblEndDate, 2, 4);

        this.dtpEnd = new DatePicker();
        this.participantGridPane.add(this.dtpEnd, 3, 4);

        Label lblCompany = new Label("Deltager du i forbindelse med arbejde, indtast venligst nedenstående");
        lblCompany.setFont(new Font(15));
        this.participantGridPane.add(lblCompany, 0, 5, 4, 1);

        Label lblCompanyName = new Label("Firmanavn:");
        this.participantGridPane.add(lblCompanyName, 0, 6);

        this.txfCompanyName = new TextField();
        this.participantGridPane.add(this.txfCompanyName, 1, 6);

        Label lblCompanyTelephone = new Label("Firma tlf.nr.");
        this.participantGridPane.add(lblCompanyTelephone, 2, 6);

        this.txfCompanyTelephone = new TextField();
        this.participantGridPane.add(this.txfCompanyTelephone, 3, 6);

        // --------------------------------------------------------------

        this.companionGridPane = new GridPane();
        this.companionGridPane.setPrefWidth(500);
        this.companionGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.companionGridPane.setDisable(true);
        this.add(this.companionGridPane, 1, 1);

        Label lblCompanionHeader = new Label("Ledsager");
        lblCompanionHeader.setFont(new Font(25));
        GridPane.setHalignment(lblCompanionHeader, HPos.CENTER);
        this.companionGridPane.add(lblCompanionHeader, 0, 0, 4, 1);

        Label lblCompanion = new Label("Medbring ledsager");
        this.chbCompanion = new CheckBox();
        this.chbCompanion.setOnAction(event -> this.checkBoxCompanionAction());
        lblCompanion.setGraphic(this.chbCompanion);
        lblCompanion.setContentDisplay(ContentDisplay.RIGHT);
        this.companionGridPane.add(lblCompanion, 0, 1, 2, 1);

        this.lblCompanionName = new Label("Navn:");
        this.lblCompanionName.setDisable(true);
        this.companionGridPane.add(this.lblCompanionName, 2, 1);

        this.txfCompanionName = new TextField();
        this.txfCompanionName.setDisable(true);
        this.companionGridPane.add(this.txfCompanionName, 3, 1);

        this.lblExcursions = new Label("Udflugter");
        this.lblExcursions.setFont(new Font(15));
        this.lblExcursions.setDisable(true);
        this.companionGridPane.add(this.lblExcursions, 0, 2, 4, 1);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(500, 200);
        this.lvwExcursions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lvwExcursions.setDisable(true);
        this.companionGridPane.add(this.lvwExcursions, 0, 3, 4, 1);

        // --------------------------------------------------------------

        this.hotelGridPane = new GridPane();
        this.hotelGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.hotelGridPane.setDisable(true);
        this.add(this.hotelGridPane, 0, 2, 2, 1);

        Label lblHotelHeader = new Label("Overnatningsønsker");
        lblHotelHeader.setFont(new Font(25));
        GridPane.setHalignment(lblHotelHeader, HPos.CENTER);
        this.hotelGridPane.add(lblHotelHeader, 0, 0, 2, 1);

        Label lblHotel = new Label("Vil du booke hotel igennem os");
        this.chbHotel = new CheckBox();
        this.chbHotel.setOnAction(event -> this.checkBoxHotelAction());
        lblHotel.setGraphic(this.chbHotel);
        lblHotel.setContentDisplay(ContentDisplay.RIGHT);
        GridPane.setHalignment(lblHotel, HPos.CENTER);
        this.hotelGridPane.add(lblHotel, 0, 1, 2, 1);

        this.lblHotels = new Label("Hoteler");
        this.lblHotels.setFont(new Font(15));
        this.lblHotels.setDisable(true);
        this.hotelGridPane.add(this.lblHotels, 0, 2);

        this.lvwHotels = new ListView<>();
        this.lvwHotels.setPrefSize(700, 200);
        this.lvwHotels.setDisable(true);
        this.hotelGridPane.add(this.lvwHotels, 0, 3);

        ChangeListener<Hotel> listenerHotels = (ov, oldHotel, newHotel) -> this.selectedHotelChanged(newHotel);
        this.lvwHotels.getSelectionModel().selectedItemProperty().addListener(listenerHotels);

        this.lblAddOns = new Label("Tillæg");
        this.lblAddOns.setFont(new Font(15));
        this.lblAddOns.setDisable(true);
        this.hotelGridPane.add(this.lblAddOns, 1, 2);

        this.lvwAddOns = new ListView<>();
        this.lvwAddOns.setPrefSize(300, 200);
        this.lvwAddOns.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lvwAddOns.setDisable(true);
        this.hotelGridPane.add(this.lvwAddOns, 1, 3);

        this.lblDouble = new Label("Bemærk at såfremt du medbringer en ledsager tildeles i automatisk et dobbeltværelse");
        this.lblDouble.setWrapText(true);
        this.lblDouble.setDisable(true);
        this.hotelGridPane.add(this.lblDouble, 0, 4, 2, 1);

        // --------------------------------------------------------------


    }

    // --------------------------------------------------------------

    private void selectedConferenceChanged (Conference newConference) {
        if (this.participantGridPane.isDisable()) {
            this.participantGridPane.setDisable(false);
            this.companionGridPane.setDisable(false);
            this.hotelGridPane.setDisable(false);
        }

        this.conference = newConference;
        this.updateControls();
    }

    private void selectedHotelChanged (Hotel newHotel) {
        this.lvwAddOns.getItems().setAll(newHotel.getAddOns());
    }

    private void checkBoxCompanionAction () {
        boolean checked = this.chbCompanion.isSelected();

        this.lblCompanionName.setDisable(!checked);
        this.txfCompanionName.setDisable(!checked);
        this.lblExcursions.setDisable(!checked);
        this.lvwExcursions.setDisable(!checked);
    }

    private void checkBoxHotelAction () {
        boolean checked = this.chbHotel.isSelected();

        this.lblDouble.setDisable(!checked);
        this.lvwHotels.setDisable(!checked);
        this.lblHotels.setDisable(!checked);
        this.lblAddOns.setDisable(!checked);
        this.lvwAddOns.setDisable(!checked);
    }

    // --------------------------------------------------------------

    public void updateControls () {
        this.dtpStart.setValue(this.conference.getStartDate().toLocalDate());
        this.dtpEnd.setValue(this.conference.getEndDate().toLocalDate());
        this.restrictDatePicker(this.dtpStart, this.conference.getStartDate().toLocalDate(), this.conference.getEndDate().toLocalDate());
        this.restrictDatePicker(this.dtpEnd, this.conference.getStartDate().toLocalDate(), this.conference.getEndDate().toLocalDate());

        this.lvwExcursions.getItems().setAll(this.conference.getExcursions());
        this.lvwHotels.getItems().setAll(this.conference.getHotels());
    }

    // --------------------------------------------------------------

    private void restrictDatePicker (DatePicker datePicker, LocalDate startDate, LocalDate endDate) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem (LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
            }
        });
    }

}

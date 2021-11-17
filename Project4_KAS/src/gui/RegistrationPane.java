package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class RegistrationPane extends GridPane {

    private Conference conference;
    private Hotel hotel;

    private final GridPane conferencesGridPane, participantGridPane, companionGridPane, hotelGridPane;

    private final ListView<Conference> lvwConferences;
    private final ListView<Excursion> lvwExcursions;
    private final ListView<Hotel> lvwHotels;
    private final ListView<AddOn> lvwAddOns;
    private final TextField txfName, txfAddress, txfCountry, txfCity, txfTelephone;
    private final TextField txfCompanyName, txfCompanyTelephone, txfCompanionName, txfPrice;
    private final CheckBox chbSpeaker, chbCompanion, chbHotel;
    private final DatePicker dtpStart, dtpEnd;
    private final Label lblCompanionName, lblExcursions, lblHotels, lblAddOns, lblDouble;
    private final Button btnClear, btnSubmit;

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
        this.stringValidation(this.txfName);

        Label lblSpeaker = new Label("Foredragsholder");
        this.chbSpeaker = new CheckBox();
        lblSpeaker.setGraphic(this.chbSpeaker);
        lblSpeaker.setContentDisplay(ContentDisplay.RIGHT);
        this.participantGridPane.add(lblSpeaker, 2, 1, 2, 1);

        Label lblAddress = new Label("Adresse:");
        this.participantGridPane.add(lblAddress, 0, 2);

        this.txfAddress = new TextField();
        this.participantGridPane.add(this.txfAddress, 1, 2);
        this.stringValidation(this.txfAddress);

        Label lblCity = new Label("By:");
        this.participantGridPane.add(lblCity, 2, 2);

        this.txfCity = new TextField();
        this.participantGridPane.add(this.txfCity, 3, 2);
        this.stringValidation(this.txfCity);

        Label lblCountry = new Label("Land:");
        this.participantGridPane.add(lblCountry, 0, 3);

        this.txfCountry = new TextField();
        this.participantGridPane.add(this.txfCountry, 1, 3);
        this.stringValidation(this.txfCountry);

        Label lblTelephone = new Label("Tlf.nr.");
        this.participantGridPane.add(lblTelephone, 2, 3);

        this.txfTelephone = new TextField();
        this.participantGridPane.add(this.txfTelephone, 3, 3);
        this.numberValidation(this.txfTelephone);

        Label lblStartDate = new Label("Ankomstdato:");
        this.participantGridPane.add(lblStartDate, 0, 4);

        this.dtpStart = new DatePicker();
        this.dtpStart.valueProperty().addListener((ov, oldValue, newValue) -> this.datePickerChanged());
        this.participantGridPane.add(this.dtpStart, 1, 4);

        Label lblEndDate = new Label("Afrejsedato:");
        this.participantGridPane.add(lblEndDate, 2, 4);

        this.dtpEnd = new DatePicker();
        this.dtpEnd.valueProperty().addListener((ov, oldValue, newValue) -> this.datePickerChanged());
        this.participantGridPane.add(this.dtpEnd, 3, 4);

        Label lblCompany = new Label("Deltager du i forbindelse med arbejde, indtast venligst nedenstående");
        lblCompany.setFont(new Font(15));
        this.participantGridPane.add(lblCompany, 0, 5, 4, 1);

        Label lblCompanyName = new Label("Firmanavn:");
        this.participantGridPane.add(lblCompanyName, 0, 6);

        this.txfCompanyName = new TextField();
        this.participantGridPane.add(this.txfCompanyName, 1, 6);
        this.stringValidation(this.txfCompanyName);

        Label lblCompanyTelephone = new Label("Firma tlf.nr.");
        this.participantGridPane.add(lblCompanyTelephone, 2, 6);

        this.txfCompanyTelephone = new TextField();
        this.participantGridPane.add(this.txfCompanyTelephone, 3, 6);
        this.numberValidation(this.txfCompanyTelephone);

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
        this.stringValidation(this.txfCompanionName);

        this.lblExcursions = new Label("Udflugter");
        this.lblExcursions.setFont(new Font(15));
        this.lblExcursions.setDisable(true);
        this.companionGridPane.add(this.lblExcursions, 0, 2, 4, 1);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(500, 200);
        this.lvwExcursions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lvwExcursions.setDisable(true);
        this.companionGridPane.add(this.lvwExcursions, 0, 3, 4, 1);

        ChangeListener<Excursion> listenerExcursions = (ov, oldExcursion, newExcursion) -> this.selectedExcursionChanged();
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listenerExcursions);

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

        this.lblHotels = new Label("Hoteller");
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

        ChangeListener<AddOn> listenerAddOns = (ov, oldAddOn, newAddOn) -> this.selectedAddOnChanged();
        this.lvwAddOns.getSelectionModel().selectedItemProperty().addListener(listenerAddOns);

        this.lblDouble = new Label("Bemærk at såfremt du medbringer en ledsager tildeles i automatisk et dobbeltværelse");
        this.lblDouble.setWrapText(true);
        this.lblDouble.setDisable(true);
        this.hotelGridPane.add(this.lblDouble, 0, 4, 2, 1);

        // --------------------------------------------------------------

        this.btnClear = new Button("Start forfra");
        this.btnClear.setDisable(true);
        this.btnClear.setOnAction(event -> this.clearControls());
        GridPane.setHalignment(this.btnClear, HPos.RIGHT);
        this.add(this.btnClear, 0, 3);

        HBox hbox = new HBox();
        this.add(hbox, 1, 3);

        this.btnSubmit = new Button("Send registration");
        this.btnSubmit.setDisable(true);
        this.btnSubmit.setOnAction(event -> this.submitAction());
        hbox.getChildren().add(this.btnSubmit);

        Label lblPrice = new Label("Samlet pris:");
        hbox.getChildren().add(lblPrice);

        this.txfPrice = new TextField();
        hbox.getChildren().add(this.txfPrice);
    }

    // --------------------------------------------------------------

    private void selectedConferenceChanged (Conference newConference) {
        boolean isNull = newConference == null;

        this.participantGridPane.setDisable(isNull);
        this.companionGridPane.setDisable(isNull);
        this.hotelGridPane.setDisable(isNull);
        this.btnSubmit.setDisable(isNull);
        this.btnClear.setDisable(isNull);

        this.conference = newConference;

        if (!isNull) {
            this.dtpStart.setValue(this.conference.getStartDate().toLocalDate());
            this.dtpEnd.setValue(this.conference.getEndDate().toLocalDate());
            this.restrictDatePicker(this.dtpStart, this.conference.getStartDate().toLocalDate(), this.conference.getEndDate().toLocalDate());
            this.restrictDatePicker(this.dtpEnd, this.conference.getStartDate().toLocalDate(), this.conference.getEndDate().toLocalDate());

            this.lvwExcursions.getItems().setAll(this.conference.getExcursions());
            this.lvwHotels.getItems().setAll(this.conference.getHotels());
        }

        this.updatePrice();
    }

    private void selectedExcursionChanged () {
        this.updatePrice();
    }

    private void selectedHotelChanged (Hotel newHotel) {
        this.hotel = newHotel;
        if (newHotel != null) {
            this.lvwAddOns.getItems().setAll(newHotel.getAddOns());
        } else {
            this.lvwAddOns.getItems().clear();
        }

        this.updatePrice();
    }

    private void selectedAddOnChanged () {
        this.updatePrice();
    }

    private void checkBoxCompanionAction () {
        boolean checked = this.chbCompanion.isSelected();

        this.lblCompanionName.setDisable(!checked);
        this.txfCompanionName.setDisable(!checked);
        this.lblExcursions.setDisable(!checked);
        this.lvwExcursions.setDisable(!checked);

        if (!checked) {
            this.lvwExcursions.getSelectionModel().clearSelection();
        }
    }

    private void checkBoxHotelAction () {
        boolean checked = this.chbHotel.isSelected();

        this.lblDouble.setDisable(!checked);
        this.lvwHotels.setDisable(!checked);
        this.lblHotels.setDisable(!checked);
        this.lblAddOns.setDisable(!checked);
        this.lvwAddOns.setDisable(!checked);

        if (!checked) {
            this.lvwHotels.getSelectionModel().clearSelection();
        }
    }

    private void datePickerChanged () {
        this.updatePrice();
    }

    // --------------------------------------------------------------

    private void updatePrice () {
        if (this.conference != null && this.dtpStart.getValue() != null && this.dtpEnd.getValue() != null) {
            int stayInDays = (int) ChronoUnit.DAYS.between(this.dtpStart.getValue(), this.dtpEnd.getValue());
            int conferencePrice = this.conference.getDailyPrice() * stayInDays;

            int hotelPrice = 0;
            if (this.hotel != null) {
                int addOnPrice = 0;
                for (AddOn addOn : this.lvwAddOns.getSelectionModel().getSelectedItems()) {
                    addOnPrice += addOn.getPrice();
                }

                hotelPrice = (((this.chbCompanion.isSelected()) ? this.hotel.getDoublePrice() : this.hotel.getSinglePrice()) + addOnPrice) * stayInDays;
            }

            int excursionPrice = 0;
            if (this.chbCompanion.isSelected()) {
                for (Excursion excursion : this.lvwExcursions.getSelectionModel().getSelectedItems()) {
                    excursionPrice += excursion.getPrice();
                }
            }

            this.txfPrice.setText(conferencePrice + hotelPrice + excursionPrice + "");
        }
    }

    private void clearControls () {
        this.lvwConferences.getSelectionModel().clearSelection();
        this.lvwExcursions.getItems().clear();
        this.lvwHotels.getItems().clear();
        this.lvwAddOns.getItems().clear();

        this.chbHotel.setSelected(false);
        this.chbCompanion.setSelected(false);
        this.chbSpeaker.setSelected(false);

        this.dtpStart.setValue(null);
        this.dtpEnd.setValue(null);

        this.txfName.clear();
        this.txfAddress.clear();
        this.txfCity.clear();
        this.txfCountry.clear();
        this.txfTelephone.clear();
        this.txfCompanyName.clear();
        this.txfCompanyTelephone.clear();
        this.txfCompanionName.clear();
        this.txfPrice.clear();

        App.removeClass(this.txfName, "error");
        App.removeClass(this.txfAddress, "error");
        App.removeClass(this.txfCity, "error");
        App.removeClass(this.txfCountry, "error");
        App.removeClass(this.txfTelephone, "error");
        App.removeClass(this.txfCompanyName, "error");
        App.removeClass(this.txfCompanyTelephone, "error");
        App.removeClass(this.txfCompanionName, "error");
    }

    // --------------------------------------------------------------

    private void submitAction () {
        ArrayList<TextField> errorableTextFields = new ArrayList<>(Arrays.asList(
                this.txfName, this.txfAddress, this.txfCity, this.txfCountry, this.txfTelephone,
                this.txfCompanyName, this.txfCompanyTelephone, this.txfCompanionName));
        for (TextField textField : errorableTextFields) {
            if (textField.getStyleClass().contains("error")) {
                return;
            }
        }

        String name = this.txfName.getText().trim();
        String address = this.txfAddress.getText().trim();
        String city = this.txfCity.getText().trim();
        String country = this.txfCountry.getText().trim();
        String telephone = this.txfTelephone.getText().trim();
        String companyName = this.txfCompanyName.getText().trim();
        String companyTelephone = this.txfCompanyTelephone.getText().trim();
        String companionName = this.txfCompanionName.getText().trim();

        boolean speaker = this.chbSpeaker.isSelected();

        LocalDate arrivalDate = this.dtpStart.getValue();
        LocalDate departureDate = this.dtpEnd.getValue();

        Participant participant = Controller.createParticipant(name, telephone, address, country, city);
        Registration registration = participant.createRegistration(companyName, companyTelephone, arrivalDate, departureDate, speaker, this.conference);

        if (this.chbCompanion.isSelected()) {
            Companion companion = registration.createCompanion(companionName);

            for (Excursion excursion : this.lvwExcursions.getSelectionModel().getSelectedItems()) {
                companion.addExcursion(excursion);
            }
        }

        if (this.chbHotel.isSelected()) {
            // TODO Change the number to automatically update
            HotelRoom hotelRoom = this.hotel.createHotelRoom(1,
                    (this.chbCompanion.isSelected()) ? this.hotel.getDoublePrice() : this.hotel.getSinglePrice(),
                    !this.chbCompanion.isSelected());

            for (AddOn addOn : this.lvwAddOns.getSelectionModel().getSelectedItems()) {
                hotelRoom.addAddOn(addOn);
            }

            registration.setHotelRoom(hotelRoom);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrering gennemført!");
        alert.setHeaderText("Registrering gennemført!");
        alert.setContentText("Tak for at du registrerede dig til " + this.conference.getName() + " " + participant.getName() + "! Hav en god dag");
        alert.showAndWait();

        this.clearControls();
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

    private void stringValidation (final TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue.trim();
            if (text.length() == 0) {
                App.removeClass(textField, "valid");
                App.addClass(textField, "error");
            } else {
                App.removeClass(textField, "error");
                App.addClass(textField, "valid");
            }
        });
    }

    private void numberValidation (final TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue.trim();
            if (text.length() != 8) {
                App.removeClass(textField, "valid");
                App.addClass(textField, "error");
            } else {
                App.removeClass(textField, "error");
                App.addClass(textField, "valid");
            }
        });
    }

}

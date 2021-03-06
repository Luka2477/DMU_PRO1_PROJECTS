package gui;

import application.controller.Controller;
import application.model.*;
import gui.components.NumericField;
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

    private final GridPane participantGridPane;
    private final GridPane companionGridPane;
    private final GridPane hotelGridPane;

    private final ListView<Conference> lvwConferences;
    private final ListView<Excursion> lvwExcursions;
    private final ListView<Hotel> lvwHotels;
    private final ListView<AddOn> lvwAddOns;
    private final TextField txfName, txfAddress, txfCountry, txfCity;
    private final TextField txfCompanyName, txfCompanionName;
    private final NumericField nufTelephone, nufCompanyTelephone, nufPrice;
    private final CheckBox chbSpeaker, chbCompanion, chbHotel;
    private final DatePicker dtpStart, dtpEnd;
    private final Label lblCompanionName, lblExcursions, lblHotels, lblAddOns, lblDouble;
    private final Button btnClear, btnSubmit;

    /**
     * Initialisere RegistrationPane
     */
    public RegistrationPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        GridPane conferencesGridPane = new GridPane();
        conferencesGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        conferencesGridPane.setPadding(new Insets(10));
        conferencesGridPane.setHgap(10);
        conferencesGridPane.setVgap(10);
        conferencesGridPane.setGridLinesVisible(false);
        this.add(conferencesGridPane, 0, 0, 2, 1);

        Label lblConferenceHeader = new Label("V??lg konference");
        lblConferenceHeader.setFont(new Font(25));
        GridPane.setHalignment(lblConferenceHeader, HPos.CENTER);
        conferencesGridPane.add(lblConferenceHeader, 0, 0);

        this.lvwConferences = new ListView<>();
        this.lvwConferences.getItems().setAll(Controller.getConferences());
        this.lvwConferences.setPrefSize(1000, 200);
        conferencesGridPane.add(this.lvwConferences, 0, 1);

        ChangeListener<Conference> listener = (ov, oldConference, newConference) -> this.selectedConferenceChanged(newConference);
        this.lvwConferences.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        this.participantGridPane = new GridPane();
        this.participantGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.participantGridPane.setPadding(new Insets(10));
        this.participantGridPane.setHgap(10);
        this.participantGridPane.setVgap(10);
        this.participantGridPane.setGridLinesVisible(false);
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

        this.nufTelephone = new NumericField();
        this.participantGridPane.add(this.nufTelephone, 3, 3);
        this.numberValidation(this.nufTelephone);

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

        Label lblCompany = new Label("Deltager du i forbindelse med arbejde, indtast venligst nedenst??ende");
        lblCompany.setFont(new Font(15));
        this.participantGridPane.add(lblCompany, 0, 5, 4, 1);

        Label lblCompanyName = new Label("Firmanavn:");
        this.participantGridPane.add(lblCompanyName, 0, 6);

        this.txfCompanyName = new TextField();
        this.participantGridPane.add(this.txfCompanyName, 1, 6);
        this.specificStringValidation(this.txfCompanyName);

        Label lblCompanyTelephone = new Label("Firma tlf.nr.");
        this.participantGridPane.add(lblCompanyTelephone, 2, 6);

        this.nufCompanyTelephone = new NumericField();
        this.participantGridPane.add(this.nufCompanyTelephone, 3, 6);
        this.specificNumberValidation(this.nufCompanyTelephone);

        // --------------------------------------------------------------

        this.companionGridPane = new GridPane();
        this.companionGridPane.setPrefWidth(500);
        this.companionGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.companionGridPane.setPadding(new Insets(10));
        this.companionGridPane.setHgap(10);
        this.companionGridPane.setVgap(10);
        this.companionGridPane.setGridLinesVisible(false);
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
        this.specificStringValidation(this.txfCompanionName);

        this.lblExcursions = new Label("Udflugter");
        this.lblExcursions.setFont(new Font(15));
        this.lblExcursions.setDisable(true);
        this.companionGridPane.add(this.lblExcursions, 0, 2, 4, 1);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(500, 130);
        this.lvwExcursions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lvwExcursions.setDisable(true);
        this.companionGridPane.add(this.lvwExcursions, 0, 3, 4, 1);

        ChangeListener<Excursion> listenerExcursions = (ov, oldExcursion, newExcursion) -> this.selectedExcursionChanged();
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listenerExcursions);

        // --------------------------------------------------------------

        this.hotelGridPane = new GridPane();
        this.hotelGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.hotelGridPane.setPadding(new Insets(10));
        this.hotelGridPane.setHgap(10);
        this.hotelGridPane.setVgap(10);
        this.hotelGridPane.setGridLinesVisible(false);
        this.hotelGridPane.setDisable(true);
        this.add(this.hotelGridPane, 2, 0, 1, 2);

        Label lblHotelHeader = new Label("Overnatnings??nsker");
        lblHotelHeader.setFont(new Font(25));
        GridPane.setHalignment(lblHotelHeader, HPos.CENTER);
        this.hotelGridPane.add(lblHotelHeader, 0, 0);

        Label lblHotel = new Label("Vil du booke hotel igennem os");
        this.chbHotel = new CheckBox();
        this.chbHotel.setOnAction(event -> this.checkBoxHotelAction());
        lblHotel.setGraphic(this.chbHotel);
        lblHotel.setContentDisplay(ContentDisplay.RIGHT);
        GridPane.setHalignment(lblHotel, HPos.CENTER);
        this.hotelGridPane.add(lblHotel, 0, 1);

        this.lblHotels = new Label("Hoteller");
        this.lblHotels.setFont(new Font(15));
        this.lblHotels.setDisable(true);
        this.hotelGridPane.add(this.lblHotels, 0, 2);

        this.lvwHotels = new ListView<>();
        this.lvwHotels.setPrefSize(200, 175);
        this.lvwHotels.setDisable(true);
        this.hotelGridPane.add(this.lvwHotels, 0, 3);

        ChangeListener<Hotel> listenerHotels = (ov, oldHotel, newHotel) -> this.selectedHotelChanged(newHotel);
        this.lvwHotels.getSelectionModel().selectedItemProperty().addListener(listenerHotels);

        this.lblAddOns = new Label("Till??g");
        this.lblAddOns.setFont(new Font(15));
        this.lblAddOns.setDisable(true);
        this.hotelGridPane.add(this.lblAddOns, 0, 4);

        this.lvwAddOns = new ListView<>();
        this.lvwAddOns.setPrefSize(200, 150);
        this.lvwAddOns.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lvwAddOns.setDisable(true);
        this.hotelGridPane.add(this.lvwAddOns, 0, 5);

        ChangeListener<AddOn> listenerAddOns = (ov, oldAddOn, newAddOn) -> this.selectedAddOnChanged();
        this.lvwAddOns.getSelectionModel().selectedItemProperty().addListener(listenerAddOns);

        this.lblDouble = new Label("Bem??rk at s??fremt du medbringer en ledsager tildeles i automatisk et dobbeltv??relse");
        this.lblDouble.setWrapText(true);
        this.lblDouble.setDisable(true);
        this.hotelGridPane.add(this.lblDouble, 0, 6);

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

        this.nufPrice = new NumericField();
        hbox.getChildren().add(this.nufPrice);
    }

    // --------------------------------------------------------------

    /**
     * Handler hvad der sker n??r brugeren v??lger en konference.
     *
     * @param newConference konferencen der er blevet valgt
     */
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

    /**
     * Handler hvad der sker n??r brugeren v??lger en udflugt.
     */
    private void selectedExcursionChanged () {
        this.updatePrice();
    }

    /**
     * Handler hvad der sker n??r brugeren v??lger et hotel.
     *
     * @param newHotel hotellet der er blevet valgt
     */
    private void selectedHotelChanged (Hotel newHotel) {
        this.hotel = newHotel;
        if (newHotel != null) {
            this.lvwAddOns.getItems().setAll(newHotel.getAddOns());
        } else {
            this.lvwAddOns.getItems().clear();
        }

        this.updatePrice();
    }

    /**
     * Handler hvad der sker n??r brugeren v??lger till??g.
     */
    private void selectedAddOnChanged () {
        this.updatePrice();
    }

    /**
     * Handler hvad der sker n??r brugeren v??lger at medbringe en ledsager.
     */
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

    /**
     * Handler hvad der sker n??r brugeren v??lger et hotel.
     */
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

    /**
     * Handler hvad der sker n??r en DatePicker skrifter v??rdi.
     */
    private void datePickerChanged () {
        this.updatePrice();
    }

    // --------------------------------------------------------------

    /**
     * Opdatere den samlede pris p?? registrationen.
     */
    private void updatePrice () {
        if (this.conference != null && this.dtpStart.getValue() != null && this.dtpEnd.getValue() != null) {
            int stayInDays = (int) ChronoUnit.DAYS.between(this.dtpStart.getValue(), this.dtpEnd.getValue());
            int conferencePrice = 0;

            if (!this.chbSpeaker.isSelected()) {
                conferencePrice = this.conference.getDailyPrice() * (stayInDays + 1);
            }

            int hotelPrice = 0;
            if (this.hotel != null) {
                int addOnPrice = 0;
                for (AddOn addOn : this.lvwAddOns.getSelectionModel().getSelectedItems()) {
                    addOnPrice += addOn.getPrice();
                }

                if (this.chbCompanion.isSelected()) {
                    hotelPrice = this.hotel.getDoublePrice();
                } else {
                    hotelPrice = this.hotel.getSinglePrice();
                }
                hotelPrice = (hotelPrice + addOnPrice) * stayInDays;
            }

            int excursionPrice = 0;
            if (this.chbCompanion.isSelected()) {
                for (Excursion excursion : this.lvwExcursions.getSelectionModel().getSelectedItems()) {
                    excursionPrice += excursion.getPrice();
                }
            }

            this.nufPrice.setText(conferencePrice + hotelPrice + excursionPrice + "");
        }
    }

    /**
     * Nulstiller alle kontrol textfielder, datepickers, labels og lister.
     */
    private void clearControls () {
        this.lvwConferences.getSelectionModel().clearSelection();
        this.lvwExcursions.getItems().clear();
        this.lvwExcursions.setDisable(true);
        this.lvwHotels.getItems().clear();
        this.lvwHotels.setDisable(true);
        this.lvwAddOns.getItems().clear();
        this.lvwAddOns.setDisable(true);

        this.chbHotel.setSelected(false);
        this.chbCompanion.setSelected(false);
        this.chbSpeaker.setSelected(false);

        this.dtpStart.setValue(null);
        this.dtpEnd.setValue(null);

        this.txfName.clear();
        this.txfAddress.clear();
        this.txfCity.clear();
        this.txfCountry.clear();
        this.nufTelephone.clear();
        this.txfCompanyName.clear();
        this.nufCompanyTelephone.clear();
        this.txfCompanionName.clear();
        this.txfCompanionName.setDisable(true);
        this.nufPrice.clear();

        this.lblHotels.setDisable(true);
        this.lblExcursions.setDisable(true);
        this.lblAddOns.setDisable(true);
        this.lblCompanionName.setDisable(true);

        App.removeClass(this.txfName, "error");
        App.removeClass(this.txfAddress, "error");
        App.removeClass(this.txfCity, "error");
        App.removeClass(this.txfCountry, "error");
        App.removeClass(this.nufTelephone, "error");
        App.removeClass(this.txfCompanyName, "error");
        App.removeClass(this.nufCompanyTelephone, "error");
        App.removeClass(this.txfCompanionName, "error");
    }

    // --------------------------------------------------------------

    /**
     * Handler hvad der sker n??r registrationen skal oprettes.
     */
    private void submitAction () {
        // Tjek om alle kr??vede felter er udfyldt, alts?? om de har "valid" css classen
        ArrayList<TextField> errorableTextFields = new ArrayList<>(Arrays.asList(
                this.txfName, this.txfAddress, this.txfCity, this.txfCountry, this.nufTelephone));
        for (TextField textField : errorableTextFields) {
            if (!textField.getStyleClass().contains("valid")) {
                App.addClass(textField, "error");
                return;
            }
        }

        // Hvis enten firmanavn eller firma tlf. nr. er skrevet skal begge udfyldes
        if (this.txfCompanyName.getText().trim().length() != 0 || this.nufCompanyTelephone.getText().trim().length() != 0) {
            if (!this.txfCompanyName.getStyleClass().contains("valid")) {
                App.addClass(this.txfCompanyName, "error");
                return;
            } else if (!this.nufCompanyTelephone.getStyleClass().contains("valid")) {
                App.addClass(this.nufCompanyTelephone, "error");
                return;
            }
        }

        // Hvis medbring ledsager er valgt, s?? skal ledsager navn ogs?? v??re udfyldt,
        // ellers tilf??j "error" css class til ledsager navn textfield
        if (this.chbCompanion.isSelected() && !this.txfCompanionName.getStyleClass().contains("valid")) {
            App.addClass(this.txfCompanionName, "error");
            return;
        }

        // --------------------------------------------------------------

        String name = this.txfName.getText().trim();
        String address = this.txfAddress.getText().trim();
        String city = this.txfCity.getText().trim();
        String country = this.txfCountry.getText().trim();
        String telephone = this.nufTelephone.getText().trim();
        String companyName = this.txfCompanyName.getText().trim();
        String companyTelephone = this.nufCompanyTelephone.getText().trim();
        String companionName = this.txfCompanionName.getText().trim();

        boolean speaker = this.chbSpeaker.isSelected();

        LocalDate arrivalDate = this.dtpStart.getValue();
        LocalDate departureDate = this.dtpEnd.getValue();

        // Hvis deltager med samme navn og tlf. nr. findes, s?? tilf??j registration til den deltager.
        // Ellers lav en ny deltager.
        Participant participant = null;
        for (Participant p : Controller.getParticipants()) {
            if (name.equalsIgnoreCase(p.getName()) && telephone.equalsIgnoreCase(p.getTelephone())) {
                participant = p;
                break;
            }
        }
        if (participant == null) {
            participant = Controller.createParticipant(name, telephone, address, country, city);
        }

        Registration registration = participant.createRegistration(companyName, companyTelephone, arrivalDate, departureDate, speaker, this.conference);

        // Hvis medbring ledsager er valgt, skal vi lave en ledsager p?? registrationen,
        // samt udflugter til ledsageren.
        if (this.chbCompanion.isSelected()) {
            Companion companion = registration.createCompanion(companionName);

            for (Excursion excursion : this.lvwExcursions.getSelectionModel().getSelectedItems()) {
                companion.addExcursion(excursion);
            }
        }

        // Hvis deltageren gerne vil ans??ge om hotel gennem os, skal registrationen gives et hotelv??relse,
        // samt tilf??je de valgte till??g til hotelv??relset.
        if (this.chbHotel.isSelected()) {
            HotelRoom hotelRoom = this.hotel.createHotelRoom(!this.chbCompanion.isSelected());

            for (AddOn addOn : this.lvwAddOns.getSelectionModel().getSelectedItems()) {
                hotelRoom.addAddOn(addOn);
            }

            registration.setHotelRoom(hotelRoom);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrering gennemf??rt!");
        alert.setHeaderText("Registrering gennemf??rt!");
        alert.setContentText("Tak for at du registrerede dig til " + this.conference.getName() + " " + participant.getName() + "! Hav en god dag");
        alert.showAndWait();

        this.clearControls();
    }

    // --------------------------------------------------------------

    /**
     * Begr??nser en DatePicker til mellem to LocalDate objekter.
     *
     * @param datePicker den DatePicker der skal begr??nses
     * @param startDate startdatoen p?? begr??nsningen
     * @param endDate slutdatoen p?? begr??nsningen
     */
    private void restrictDatePicker (DatePicker datePicker, LocalDate startDate, LocalDate endDate) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem (LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
            }
        });
    }

    /**
     * Validere et n??dvendigt textfield.
     *
     * @param textField det textfield der skal valideres
     */
    private void stringValidation (final TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue.trim();
            if (text.length() <= 2) {
                App.removeClass(textField, "valid");
                App.addClass(textField, "error");
            } else {
                App.removeClass(textField, "error");
                App.addClass(textField, "valid");
            }
        });
    }

    /**
     * Validere et ikke n??dvendigt textfield.
     *
     * @param textField det textfield der skal valideres
     */
    private void specificStringValidation (final TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue.trim();
            if (text.length() == 0) {
                App.removeClass(textField, "error");
                App.removeClass(textField, "valid");
            } else if (text.length() <= 2) {
                App.removeClass(textField, "valid");
                App.addClass(textField, "error");
            } else {
                App.removeClass(textField, "error");
                App.addClass(textField, "valid");
            }
        });
    }

    /**
     * Validere et n??dvendigt tlf. nr. textfield.
     *
     * @param textField det textfield der skal valideres
     */
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

    /**
     * Validere et ikke n??dvendigt tlf. nr. textfield.
     *
     * @param textField det textfield der skal valideres
     */
    private void specificNumberValidation (final TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = newValue.trim();
            if (text.length() == 0) {
                App.removeClass(textField, "error");
                App.removeClass(textField, "valid");
            } else if (text.length() != 8) {
                App.removeClass(textField, "valid");
                App.addClass(textField, "error");
            } else {
                App.removeClass(textField, "error");
                App.addClass(textField, "valid");
            }
        });
    }

}

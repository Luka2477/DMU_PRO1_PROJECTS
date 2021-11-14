package gui;

import application.controller.Controller;
import application.model.Conference;
import application.model.Excursion;
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
    private final TextField txfName, txfAddress, txfCountry, txfCity, txfTelephone, txfCompanyName, txfCompanyTelephone, txfCompanionName, txfCompanionTelephone;
    private final CheckBox chbSpeaker, chbCompanion;
    private final DatePicker dtpStart, dtpEnd;
    private final Label lblCompanionName, lblCompanionTelephone, lblExcursions;

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

        this.chbSpeaker = new CheckBox("Foredragsholder");
        GridPane.setHalignment(this.chbSpeaker, HPos.CENTER);
        this.participantGridPane.add(this.chbSpeaker, 2, 1, 2, 1);

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

        Label lblCompanyName = new Label("Firmanavn:");
        this.participantGridPane.add(lblCompanyName, 0, 5);

        this.txfCompanyName = new TextField();
        this.participantGridPane.add(this.txfCompanyName, 1, 5);

        Label lblCompanyTelephone = new Label("Firma tlf.nr.");
        this.participantGridPane.add(lblCompanyTelephone, 2, 5);

        this.txfCompanyTelephone = new TextField();
        this.participantGridPane.add(this.txfCompanyTelephone, 3, 5);

        // --------------------------------------------------------------

        this.companionGridPane = new GridPane();
        this.companionGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.companionGridPane.setDisable(true);
        this.add(this.companionGridPane, 1, 1);

        Label lblCompanionHeader = new Label("Ledsager");
        lblCompanionHeader.setFont(new Font(25));
        GridPane.setHalignment(lblCompanionHeader, HPos.CENTER);
        this.companionGridPane.add(lblCompanionHeader, 0, 0, 4, 1);

        this.chbCompanion = new CheckBox("Medbring ledsager");
        this.chbCompanion.setOnAction(event -> this.checkBoxCompanionAction());
        GridPane.setHalignment(this.chbCompanion, HPos.CENTER);
        this.companionGridPane.add(this.chbCompanion, 0, 1, 4, 1);

        this.lblCompanionName = new Label("Navn:");
        this.lblCompanionName.setDisable(true);
        this.companionGridPane.add(this.lblCompanionName, 0, 2);

        this.txfCompanionName = new TextField();
        this.txfCompanionName.setDisable(true);
        this.companionGridPane.add(this.txfCompanionName, 1, 2);

        this.lblCompanionTelephone = new Label("Tlf.nr.");
        this.lblCompanionTelephone.setDisable(true);
        this.companionGridPane.add(this.lblCompanionTelephone, 2, 2);

        this.txfCompanionTelephone = new TextField();
        this.txfCompanionTelephone.setDisable(true);
        this.companionGridPane.add(this.txfCompanionTelephone, 3, 2);

        this.lblExcursions = new Label("Udflugter");
        this.lblExcursions.setFont(new Font(20));
        this.lblExcursions.setDisable(true);
        GridPane.setHalignment(this.lblExcursions, HPos.CENTER);
        this.companionGridPane.add(this.lblExcursions, 0, 3, 4, 1);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(500, 200);
        this.lvwExcursions.setDisable(true);
        this.companionGridPane.add(this.lvwExcursions, 0, 4, 4, 1);

        ChangeListener<Excursion> listenerExcursion = (ov, oldExcursion, newExcursion) -> this.selectedExcursionChanged(newExcursion);
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listenerExcursion);

        // --------------------------------------------------------------

        this.hotelGridPane = new GridPane();
        this.hotelGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.hotelGridPane.setDisable(true);
        this.add(this.hotelGridPane, 0, 2, 2, 1);

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

    private void checkBoxCompanionAction () {
        boolean checked = this.chbCompanion.isSelected();

        this.lblCompanionName.setDisable(!checked);
        this.txfCompanionName.setDisable(!checked);
        this.lblCompanionTelephone.setDisable(!checked);
        this.txfCompanionTelephone.setDisable(!checked);
        this.lblExcursions.setDisable(!checked);
        this.lvwExcursions.setDisable(!checked);
    }

    private void selectedExcursionChanged (Excursion newExcursion) {
        // TODO
    }

    // --------------------------------------------------------------

    public void updateControls () {
        this.dtpStart.setValue(this.conference.getStartDate().toLocalDate());
        this.dtpEnd.setValue(this.conference.getEndDate().toLocalDate());
        this.restrictDatePicker(this.dtpStart, this.conference.getStartDate().toLocalDate(), this.conference.getEndDate().toLocalDate());
        this.restrictDatePicker(this.dtpEnd, this.conference.getStartDate().toLocalDate(), this.conference.getEndDate().toLocalDate());

        this.lvwExcursions.getItems().setAll(this.conference.getExcursions());
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

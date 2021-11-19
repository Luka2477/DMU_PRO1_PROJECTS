package gui;

import application.controller.Controller;
import application.model.Conference;
import application.model.Excursion;
import application.model.Hotel;
import application.model.Registration;
import gui.components.NumericField;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

public class AdminConferencesPane extends GridPane {

    private Conference conference;
    private Hotel hotel;
    private Excursion excursion;

    private final ListView<Conference> lvwConferences;
    private final ListView<Hotel> lvwHotels;
    private final ListView<Excursion> lvwExcursions;
    private final TextField txfName, txfAddress, txfStartDate, txfEndDate, txfDeadline;
    private final NumericField nufDailyPrice;
    private final TextArea txaRegistrations;
    private final Button btnDelete, btnUpdate, btnRemoveHotel, btnRemoveExcursion, btnGetCode, btnAddHotel, btnAddExcursion;

    AdminConferencesPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwConferences = new ListView<>();
        this.lvwConferences.setPrefSize(250, 400);
        this.add(this.lvwConferences, 0, 0, 1, 7);

        ChangeListener<Conference> listener = (ov, oldValue, newValue) -> this.selectedConferenceChanged(newValue);
        this.lvwConferences.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        Label lblName = new Label("Navn:");
        this.add(lblName, 1, 0);

        Label lblAddress = new Label("Adresse:");
        this.add(lblAddress, 1, 1);

        Label lblDailyPrice = new Label("Dagspris:");
        this.add(lblDailyPrice, 1, 2);

        Label lblStartDate = new Label("Startdato og tid:");
        this.add(lblStartDate, 1, 3);

        Label lblEndDate = new Label("Slutdato og tid:");
        this.add(lblEndDate, 1, 4);

        Label lblDeadline = new Label("Tilmeldingsfrist:");
        this.add(lblDeadline, 1, 5);

        Label lblHotels = new Label("Hoteller:");
        this.add(lblHotels, 1, 6);

        this.txfName = new TextField();
        this.txfName.setEditable(false);
        this.add(this.txfName, 2, 0);

        this.txfAddress = new TextField();
        this.txfAddress.setEditable(false);
        this.add(this.txfAddress, 2, 1);

        this.nufDailyPrice = new NumericField();
        this.nufDailyPrice.setEditable(false);
        this.add(this.nufDailyPrice, 2, 2);

        this.txfStartDate = new TextField();
        this.txfStartDate.setEditable(false);
        this.add(this.txfStartDate, 2, 3);

        this.txfEndDate = new TextField();
        this.txfEndDate.setEditable(false);
        this.add(this.txfEndDate, 2, 4);

        this.txfDeadline = new TextField();
        this.txfDeadline.setEditable(false);
        this.add(this.txfDeadline, 2, 5);

        this.lvwHotels = new ListView<>();
        this.lvwHotels.setPrefSize(200, 100);
        this.add(this.lvwHotels, 2, 6);

        ChangeListener<Hotel> listenerHotel = (ov, oldValue, newValue) -> this.selectedHotelChanged(newValue);
        this.lvwHotels.getSelectionModel().selectedItemProperty().addListener(listenerHotel);

        Label lblRegistrations = new Label("Registrationer:");
        this.add(lblRegistrations, 3, 0, 1, 6);

        Label lblExcursions = new Label("Udflugter:");
        this.add(lblExcursions, 3, 6);

        this.txaRegistrations = new TextArea();
        this.txaRegistrations.setPrefSize(200, 100);
        this.txaRegistrations.setEditable(false);
        this.add(this.txaRegistrations, 4, 0, 1, 6);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(200, 100);
        this.add(this.lvwExcursions, 4, 6);

        ChangeListener<Excursion> listenerExcursion = (ov, oldValue, newValue) -> this.selectedExcursionChanged(newValue);
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listenerExcursion);

        // --------------------------------------------------------------

        HBox hBoxConference = new HBox(10);
        this.add(hBoxConference, 0, 7);

        this.btnDelete = new Button("Slet");
        this.btnDelete.setOnAction(event -> this.deleteAction());
        this.btnDelete.setDisable(true);
        hBoxConference.getChildren().add(this.btnDelete);

        this.btnUpdate = new Button("Opdatere");
        this.btnUpdate.setOnAction(event -> this.updateAction());
        this.btnUpdate.setDisable(true);
        hBoxConference.getChildren().add(this.btnUpdate);

        Button btnCreate = new Button("Opret");
        btnCreate.setOnAction(event -> this.createAction());
        hBoxConference.getChildren().add(btnCreate);

        HBox hBoxHotel = new HBox(10);
        this.add(hBoxHotel, 2, 7);

        this.btnRemoveHotel = new Button("Fjern hotel");
        this.btnRemoveHotel.setOnAction(event -> this.removeHotelAction());
        this.btnRemoveHotel.setDisable(true);
        hBoxHotel.getChildren().add(this.btnRemoveHotel);

        this.btnAddHotel = new Button("Tilføj hotel");
        this.btnAddHotel.setOnAction(event -> this.addHotelAction());
        this.btnAddHotel.setDisable(true);
        hBoxHotel.getChildren().add(this.btnAddHotel);

        HBox hBoxExcursion = new HBox(10);
        this.add(hBoxExcursion, 4, 7);

        this.btnRemoveExcursion = new Button("Fjern udflugt");
        this.btnRemoveExcursion.setOnAction(event -> this.removeExcursionAction());
        this.btnRemoveExcursion.setDisable(true);
        hBoxExcursion.getChildren().add(this.btnRemoveExcursion);

        this.btnAddExcursion = new Button("Tilføj udflugt");
        this.btnAddExcursion.setOnAction(event -> this.addExcursionAction());
        this.btnAddExcursion.setDisable(true);
        hBoxExcursion.getChildren().add(this.btnAddExcursion);

        this.btnGetCode = new Button("Få kode");
        this.btnGetCode.setOnAction(event -> this.getCodeAction());
        this.btnGetCode.setDisable(true);
        GridPane.setHalignment(this.btnGetCode, HPos.RIGHT);
        this.add(this.btnGetCode, 4, 8);

        // --------------------------------------------------------------

        this.updateConferences();
    }

    // --------------------------------------------------------------

    private void selectedConferenceChanged (Conference conference) {
        this.conference = conference;

        this.updateControls();
    }

    private void selectedHotelChanged (Hotel hotel) {
        this.hotel = hotel;

        this.updateButtons();
    }

    private void selectedExcursionChanged (Excursion excursion) {
        this.excursion = excursion;

        this.updateButtons();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        if (this.conference != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM-yyyy @ HH:mm");

            this.txfName.setText(this.conference.getName());
            this.txfAddress.setText(this.conference.getAddress());
            this.nufDailyPrice.setText(this.conference.getDailyPrice() + "");
            this.txfStartDate.setText(this.conference.getStartDate().format(dtf));
            this.txfEndDate.setText(this.conference.getEndDate().format(dtf));
            this.txfDeadline.setText(this.conference.getDeadline().format(dtf));
            this.lvwHotels.getItems().setAll(this.conference.getHotels());
            this.lvwExcursions.getItems().setAll(this.conference.getExcursions());

            StringBuilder registrations = new StringBuilder();
            for (Registration registration : this.conference.getRegistrations()) {
                registrations.append(registration.getParticipant().getName()).append(" | ");
                registrations.append(registration.getParticipant().getTelephone()).append("\n");
            }
            this.txaRegistrations.setText(registrations.toString());
        }

        this.updateButtons();
    }

    private void updateButtons () {
        boolean conference = this.conference == null;
        boolean hotel = this.hotel == null;
        boolean excursion = this.excursion == null;

        this.btnUpdate.setDisable(conference);
        this.btnDelete.setDisable(conference);
        this.btnGetCode.setDisable(conference);
        this.btnAddHotel.setDisable(conference);
        this.btnAddExcursion.setDisable(conference);

        this.btnRemoveHotel.setDisable(hotel);
        this.btnRemoveExcursion.setDisable(excursion);
    }

    private void clearControls () {
        this.txfName.clear();
        this.txfAddress.clear();
        this.nufDailyPrice.clear();
        this.txfStartDate.clear();
        this.txfEndDate.clear();
        this.txfDeadline.clear();
        this.lvwHotels.getItems().clear();
        this.lvwExcursions.getItems().clear();
        this.txaRegistrations.clear();

        this.updateButtons();
    }

    private void updateConferences () {
        this.lvwConferences.getItems().setAll(Controller.getConferences());
    }

    // --------------------------------------------------------------

    private void createAction () {
        AdminCreateConferenceWindow adminCreateConferenceWindow = new AdminCreateConferenceWindow();
        adminCreateConferenceWindow.showAndWait();

        this.conference = adminCreateConferenceWindow.getConference();
        this.updateControls();
        this.updateConferences();
    }

    private void updateAction () {
        AdminCreateConferenceWindow adminCreateConferenceWindow = new AdminCreateConferenceWindow(this.conference);
        adminCreateConferenceWindow.showAndWait();

        this.updateControls();
        this.updateConferences();
    }

    private void deleteAction () {
        Controller.removeConference(this.conference);

        this.conference = null;
        this.clearControls();
        this.updateConferences();
    }

    // --------------------------------------------------------------

    private void removeHotelAction () {
        this.conference.removeHotel(this.hotel);

        this.hotel = null;
        this.updateControls();
    }

    private void addHotelAction () {
        AdminAddHotelWindow adminAddHotelWindow = new AdminAddHotelWindow(this.conference);
        adminAddHotelWindow.showAndWait();

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void removeExcursionAction () {
        this.conference.removeExcursion(excursion);

        this.excursion = null;
        this.updateControls();
    }

    private void addExcursionAction () {
        AdminAddExcursionToConferenceWindow adminAddExcursionWindow = new AdminAddExcursionToConferenceWindow(this.conference);
        adminAddExcursionWindow.showAndWait();

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void getCodeAction () {
        AdminGetCodeWindow adminGetCodeWindow = new AdminGetCodeWindow(this.conference);
        adminGetCodeWindow.show();
    }

}

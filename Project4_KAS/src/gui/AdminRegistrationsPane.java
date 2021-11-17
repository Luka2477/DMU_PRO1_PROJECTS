package gui;

import application.controller.Controller;
import application.model.AddOn;
import application.model.Excursion;
import application.model.Registration;
import gui.components.NumericField;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

public class AdminRegistrationsPane extends GridPane {

    private Registration registration;
    private Excursion excursion;

    private final ListView<Registration> lvwRegistrations;
    private final ListView<Excursion> lvwExcursions;
    private final TextField txfName, txfArrivalDate, txfDepartureDate, txfCompanyName;
    private final TextField txfCompanionName, txfConference, txfHotel;
    private final NumericField nufTelephone, nufCompanyTelephone, nufPrice;
    private final CheckBox chbSpeaker;
    private final TextArea txaAddOns;

    AdminRegistrationsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwRegistrations = new ListView<>();
        this.lvwRegistrations.setPrefSize(250, 400);
        this.add(this.lvwRegistrations, 0, 0, 1, 6);

        ChangeListener<Registration> listener = (ov, oldValue, newValue) -> this.selectedRegistrationChanged(newValue);
        this.lvwRegistrations.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        Label lblName = new Label("Deltager navn:");
        this.add(lblName, 1, 0);

        Label lblTelephone = new Label("Deltager tlf.nr:");
        this.add(lblTelephone, 1, 1);

        Label lblArrivalDate = new Label("Ankomstdato:");
        this.add(lblArrivalDate, 1, 2);

        Label lblDepartureDate = new Label("Afrejsedato:");
        this.add(lblDepartureDate, 1, 3);

        Label lblSpeaker = new Label("Foredragsholder");
        this.add(lblSpeaker, 1, 4);

        Label lblExcursions = new Label("Udflugter:");
        this.add(lblExcursions, 1, 5);

        this.txfName = new TextField();
        this.txfName.setEditable(false);
        this.add(this.txfName, 2, 0);

        this.nufTelephone = new NumericField();
        this.nufTelephone.setEditable(false);
        this.add(this.nufTelephone, 2, 1);

        this.txfArrivalDate = new TextField();
        this.txfArrivalDate.setEditable(false);
        this.add(this.txfArrivalDate, 2, 2);

        this.txfDepartureDate = new TextField();
        this.txfDepartureDate.setEditable(false);
        this.add(this.txfDepartureDate, 2, 3);

        this.chbSpeaker = new CheckBox();
        this.chbSpeaker.setDisable(true);
        this.chbSpeaker.setOpacity(1);
        this.add(this.chbSpeaker, 2, 4);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(200, 100);
        this.add(this.lvwExcursions, 2, 5);

        ChangeListener<Excursion> listenerExcursion = (ov, oldValue, newValue) -> this.selectedExcursionChanged(newValue);
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listenerExcursion);

        Label lblCompanyName = new Label("Firmanavn:");
        this.add(lblCompanyName, 3, 0);

        Label lblCompanyTelephone = new Label("Firma tlf.nr:");
        this.add(lblCompanyTelephone, 3, 1);

        Label lblCompanionName = new Label("Ledsager navn:");
        this.add(lblCompanionName, 3, 2);

        Label lblConference = new Label("Konference:");
        this.add(lblConference, 3, 3);

        Label lblHotel = new Label("Hotel navn:");
        this.add(lblHotel, 3, 4);

        Label lblAddOns = new Label("Tillæg:");
        this.add(lblAddOns, 3, 5);

        Label lblPrice = new Label("Samlet pris:");
        this.add(lblPrice, 3, 6);

        this.txfCompanyName = new TextField();
        this.txfCompanyName.setEditable(false);
        this.add(this.txfCompanyName, 4, 0);

        this.nufCompanyTelephone = new NumericField();
        this.nufCompanyTelephone.setEditable(false);
        this.add(this.nufCompanyTelephone, 4, 1);

        this.txfCompanionName = new TextField();
        this.txfCompanionName.setEditable(false);
        this.add(this.txfCompanionName, 4, 2);

        this.txfConference = new TextField();
        this.txfConference.setEditable(false);
        this.add(this.txfConference, 4, 3);

        this.txfHotel = new TextField();
        this.txfHotel.setEditable(false);
        this.add(this.txfHotel, 4, 4);

        this.txaAddOns = new TextArea();
        this.txaAddOns.setEditable(false);
        this.txaAddOns.setPrefSize(200, 100);
        this.add(this.txaAddOns, 4, 5);

        this.nufPrice = new NumericField();
        this.add(this.nufPrice, 4, 6);

        // --------------------------------------------------------------

        HBox hBox = new HBox(10);
        this.add(hBox, 0, 6);

        Button btnDelete = new Button("Slet registration");
        btnDelete.setOnAction(event -> this.deleteAction());
        hBox.getChildren().add(btnDelete);

        Button btnUpdate = new Button("Opdatere registration");
        btnUpdate.setOnAction(event -> this.updateAction());
        hBox.getChildren().add(btnUpdate);

        HBox hBoxExcursion = new HBox(10);
        this.add(hBoxExcursion, 2, 6);

        Button btnRemoveExcursion = new Button("Fjern udflugt");
        btnRemoveExcursion.setOnAction(event -> this.removeExcursionAction());
        hBoxExcursion.getChildren().add(btnRemoveExcursion);

        Button btnAddExcursion = new Button("Tilføj udflugt");
        btnAddExcursion.setOnAction(event -> this.addExcursionAction());
        hBoxExcursion.getChildren().add(btnAddExcursion);

        // --------------------------------------------------------------

        this.updateRegistrations();
    }

    // --------------------------------------------------------------

    private void selectedRegistrationChanged (Registration registration) {
        this.registration = registration;

        this.updateControls();
    }

    private void selectedExcursionChanged (Excursion excursion) {
        this.excursion = excursion;
    }

    // --------------------------------------------------------------

    private void updateControls () {
        if (this.registration != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM-yyyy");

            this.txfName.setText(this.registration.getParticipant().getName());
            this.nufTelephone.setText(this.registration.getParticipant().getTelephone());
            this.txfArrivalDate.setText(this.registration.getArrivalDate().format(dtf));
            this.txfDepartureDate.setText(this.registration.getDepartureDate().format(dtf));
            this.chbSpeaker.setSelected(this.registration.isSpeaker());
            this.txfCompanyName.setText(this.registration.getCompanyName());
            this.nufCompanyTelephone.setText(this.registration.getCompanyTelephone());

            if (this.registration.getCompanion() != null) {
                this.lvwExcursions.getItems().setAll(this.registration.getCompanion().getExcursions());
            }

            this.txfConference.setText(this.registration.getConference().getName());

            if (this.registration.getHotelRoom() != null) {
                this.txfHotel.setText(this.registration.getHotelRoom().getHotel().getName());

                StringBuilder addOns = new StringBuilder();
                for (AddOn addOn : this.registration.getHotelRoom().getAddOns()) {
                    addOns.append(addOn.getName()).append("\n");
                }
                this.txaAddOns.setText(addOns.toString());
            }

            this.nufPrice.setText(this.registration.calulateTotalPrice() + "");
        }
    }

    private void clearControls () {
        this.txfName.clear();
        this.nufTelephone.clear();
        this.txfArrivalDate.clear();
        this.txfDepartureDate.clear();
        this.chbSpeaker.setSelected(false);
        this.txfCompanyName.clear();
        this.nufCompanyTelephone.clear();
        this.txfCompanionName.clear();
        this.lvwExcursions.getItems().clear();
        this.txfConference.clear();
        this.txfHotel.clear();
        this.txaAddOns.clear();
        this.nufPrice.clear();
    }

    private void updateRegistrations () {
        this.lvwRegistrations.getItems().setAll(Controller.getRegistrations());
    }

    // --------------------------------------------------------------

    private void updateAction () {
        if (this.registration != null) {
            AdminUpdateRegistrationWindow adminUpdateRegistrationWindow = new AdminUpdateRegistrationWindow(this.registration);
            adminUpdateRegistrationWindow.showAndWait();

            this.updateControls();
            this.updateRegistrations();
        }
    }

    private void deleteAction () {
        if (this.registration != null) {
            Controller.removeRegistration(this.registration);

            this.registration = null;
            this.clearControls();
            this.updateRegistrations();
        }
    }

    // --------------------------------------------------------------

    private void removeExcursionAction () {
        if (this.registration != null && this.excursion != null) {
            this.registration.getCompanion().removeExcursion(excursion);

            this.excursion = null;
            this.updateControls();
        }
    }

    private void addExcursionAction () {
        if (this.registration != null) {
            AdminAddExcursionToRegistrationWindow adminAddExcursionToRegistrationWindow = new AdminAddExcursionToRegistrationWindow(this.registration);
            adminAddExcursionToRegistrationWindow.showAndWait();

            this.updateControls();
        }
    }

}

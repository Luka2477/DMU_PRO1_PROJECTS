package gui;

import application.controller.Controller;
import application.model.AddOn;
import application.model.Excursion;
import application.model.Participant;
import application.model.Registration;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class AdminRegistrationsPane extends GridPane {

    private Registration registration;

    private final ListView<Registration> lvwRegistrations;
    private final TextField txfName, txfTelephone, txfArrivalDate, txfDepartureDate, txfCompanyName;
    private final TextField txfCompanyTelephone, txfCompanionName, txfConference, txfHotel, txfPrice;
    private final CheckBox chbSpeaker;
    private final TextArea txaExcursions, txaAddOns;

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

        this.txfTelephone = new TextField();
        this.txfTelephone.setEditable(false);
        this.add(this.txfTelephone, 2, 1);

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

        this.txaExcursions = new TextArea();
        this.txaExcursions.setEditable(false);
        this.txaExcursions.setPrefSize(200, 100);
        this.add(this.txaExcursions, 2, 5);

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

        this.txfCompanyTelephone = new TextField();
        this.txfCompanyTelephone.setEditable(false);
        this.add(this.txfCompanyTelephone, 4, 1);

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

        this.txfPrice = new TextField();
        this.add(this.txfPrice, 4, 6);

        // --------------------------------------------------------------

        HBox hBox = new HBox(10);
        this.add(hBox, 0, 6);

        Button btnDelete = new Button("Slet");
        btnDelete.setOnAction(event -> this.deleteAction());
        hBox.getChildren().add(btnDelete);

        Button btnUpdate = new Button("Opdatere");
        btnUpdate.setOnAction(event -> this.updateAction());
        hBox.getChildren().add(btnUpdate);

        // --------------------------------------------------------------

        this.updateRegistrations();
        this.updateControls();
    }

    // --------------------------------------------------------------

    private void selectedRegistrationChanged (Registration registration) {
        this.registration = registration;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        if (this.registration != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM-yyyy");

            this.txfName.setText(this.registration.getParticipant().getName());
            this.txfTelephone.setText(this.registration.getParticipant().getTelephone());
            this.txfArrivalDate.setText(this.registration.getArrivalDate().format(dtf));
            this.txfDepartureDate.setText(this.registration.getDepartureDate().format(dtf));
            this.chbSpeaker.setSelected(this.registration.isSpeaker());
            this.txfCompanyName.setText(this.registration.getCompanyName());
            this.txfCompanyTelephone.setText(this.registration.getCompanyTelephone());

            if (this.registration.getCompanion() != null) {
                this.txfCompanionName.setText(this.registration.getCompanion().getName());

                StringBuilder excursions = new StringBuilder();
                for (Excursion excursion : this.registration.getCompanion().getExcursions()) {
                    excursions.append(excursion.getName()).append("\n");
                }
                this.txaExcursions.setText(excursions.toString());
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

            this.txfPrice.setText(this.registration.calulateTotalPrice() + "");
        }
    }

    private void clearControls () {
        this.txfName.clear();
        this.txfTelephone.clear();
        this.txfArrivalDate.clear();
        this.txfDepartureDate.clear();
        this.chbSpeaker.setSelected(false);
        this.txfCompanyName.clear();
        this.txfCompanyTelephone.clear();
        this.txfCompanionName.clear();
        this.txaExcursions.clear();
        this.txfConference.clear();
        this.txfHotel.clear();
        this.txaAddOns.clear();
        this.txfPrice.clear();
    }

    private void updateRegistrations () {
        this.lvwRegistrations.getItems().setAll(Controller.getRegistrations());
    }

    // --------------------------------------------------------------

    private void updateAction () {
        AdminUpdateRegistrationWindow adminUpdateRegistrationWindow = new AdminUpdateRegistrationWindow(this.lvwRegistrations.getSelectionModel().getSelectedItem());
        adminUpdateRegistrationWindow.showAndWait();

        this.updateControls();
    }

    private void deleteAction () {
        if (this.registration != null) {
            Controller.removeRegistration(this.registration);

            this.clearControls();
            this.updateRegistrations();
        }
    }

}

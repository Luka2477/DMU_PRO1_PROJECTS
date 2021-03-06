package gui;

import application.controller.Controller;
import application.model.Participant;
import application.model.Registration;
import gui.components.NumericField;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminParticipantsPane extends GridPane {

    private Participant participant;

    private final ListView<Participant> lvwParticipants;
    private final TextField txfName, txfAddress, txfCountry, txfCity;
    private final NumericField nufTelephone;
    private final TextArea txaRegistrations;
    private final Button btnDelete, btnUpdate, btnGetCode;

    AdminParticipantsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwParticipants = new ListView<>();
        this.lvwParticipants.setPrefSize(250, 400);
        this.add(this.lvwParticipants, 0, 0, 1, 6);

        ChangeListener<Participant> listener = (ov, oldValue, newValue) -> this.selectedParticipantChanged(newValue);
        this.lvwParticipants.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        Label lblName = new Label("Navn:");
        this.add(lblName, 1, 0);

        Label lblAddress = new Label("Adresse:");
        this.add(lblAddress, 1, 1);

        Label lblCountry = new Label("Land:");
        this.add(lblCountry, 1, 2);

        Label lblCity = new Label("By:");
        this.add(lblCity, 1, 3);

        Label lblTelephone = new Label("Tlf. nr:");
        this.add(lblTelephone, 1, 4);

        Label lblRegistrations = new Label("Registrationer:");
        this.add(lblRegistrations, 1, 5);

        this.txfName = new TextField();
        this.txfName.setEditable(false);
        this.add(this.txfName, 2, 0);

        this.txfAddress = new TextField();
        this.txfAddress.setEditable(false);
        this.add(this.txfAddress, 2, 1);

        this.txfCountry = new TextField();
        this.txfCountry.setEditable(false);
        this.add(this.txfCountry, 2, 2);

        this.txfCity = new TextField();
        this.txfCity.setEditable(false);
        this.add(this.txfCity, 2, 3);

        this.nufTelephone = new NumericField();
        this.nufTelephone.setEditable(false);
        this.add(this.nufTelephone, 2, 4);

        this.txaRegistrations = new TextArea();
        this.txaRegistrations.setPrefSize(200, 100);
        this.txaRegistrations.setEditable(false);
        this.add(this.txaRegistrations, 2, 5);

        // --------------------------------------------------------------

        HBox hBox = new HBox(10);
        this.add(hBox, 0, 6);

        this.btnDelete = new Button("Slet");
        this.btnDelete.setOnAction(event -> this.deleteAction());
        hBox.getChildren().add(this.btnDelete);

        this.btnUpdate = new Button("Opdatere");
        this.btnUpdate.setOnAction(event -> this.updateAction());
        hBox.getChildren().add(this.btnUpdate);

        this.btnGetCode = new Button("F?? kode");
        this.btnGetCode.setOnAction(event -> this.getCodeAction());
        GridPane.setHalignment(this.btnGetCode, HPos.RIGHT);
        this.add(this.btnGetCode, 2, 7);

        // --------------------------------------------------------------

        this.updateParticipants();
        this.updateButtons();
    }

    // --------------------------------------------------------------

    private void selectedParticipantChanged (Participant participant) {
        this.participant = participant;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        this.clearControls();

        if (this.participant != null) {
            this.txfName.setText(this.participant.getName());
            this.txfAddress.setText(this.participant.getAddress());
            this.txfCountry.setText(this.participant.getCountry());
            this.txfCity.setText(this.participant.getCity());
            this.nufTelephone.setText(this.participant.getTelephone());

            StringBuilder registrations = new StringBuilder();
            for (Registration registration : this.participant.getRegistrations()) {
                registrations.append(registration.getConference().getName()).append("\n");
            }
            this.txaRegistrations.setText(registrations.toString());
        }

        this.updateButtons();
    }

    private void updateButtons () {
        boolean participant = this.participant == null;

        this.btnDelete.setDisable(participant);
        this.btnUpdate.setDisable(participant);
        this.btnGetCode.setDisable(participant);
    }

    private void clearControls () {
        this.txfName.clear();
        this.txfAddress.clear();
        this.txfCountry.clear();
        this.txfCity.clear();
        this.nufTelephone.clear();
        this.txaRegistrations.clear();

        this.updateButtons();
    }

    private void updateParticipants () {
        this.lvwParticipants.getItems().setAll(Controller.getParticipants());
    }

    // --------------------------------------------------------------

    private void updateAction () {
        AdminUpdateParticipantsWindow adminUpdateParticipantsWindow = new AdminUpdateParticipantsWindow(this.participant);
        adminUpdateParticipantsWindow.showAndWait();

        this.updateControls();
        this.updateParticipants();
    }

    private void deleteAction () {
        Controller.removeParticipant(this.participant);

        this.participant = null;
        this.clearControls();
        this.updateParticipants();
    }

    // --------------------------------------------------------------

    private void getCodeAction () {
        AdminGetCodeWindow adminGetCodeWindow = new AdminGetCodeWindow(this.participant);
        adminGetCodeWindow.show();
    }

}

package gui;

import application.controller.Controller;
import application.model.Participant;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class AdminParticipantsPane extends GridPane {

    private Participant participant;

    private final ListView<Participant> lvwParticipants;

    AdminParticipantsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwParticipants = new ListView<>();
        this.lvwParticipants.setPrefSize(250, 400);
        this.add(this.lvwParticipants, 0, 0);

        ChangeListener<Participant> listener = (ov, oldValue, newValue) -> this.selectedParticipantChanged(newValue);
        this.lvwParticipants.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        // TODO GUI

        // --------------------------------------------------------------

        Button btnDelete = new Button("Slet");
        btnDelete.setOnAction(event -> this.deleteAction());
        this.add(btnDelete, 0, 1);

        Button btnUpdate = new Button("Opdatere");
        btnUpdate.setOnAction(event -> this.updateAction());
        this.add(btnUpdate, 1, 1);

        // --------------------------------------------------------------

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void selectedParticipantChanged (Participant participant) {
        this.participant = participant;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        // TODO update data
        this.lvwParticipants.getItems().setAll(Controller.getParticipants());
    }

    // --------------------------------------------------------------

    private void updateAction () {
        AdminUpdateParticipantsWindow adminUpdateParticipantsWindow = new AdminUpdateParticipantsWindow(this.lvwParticipants.getSelectionModel().getSelectedItem());
        adminUpdateParticipantsWindow.showAndWait();

        this.updateControls();
    }

    private void deleteAction () {
        if (this.participant != null) {
            Controller.removeParticipant(this.participant);

            this.updateControls();
        }
    }

}

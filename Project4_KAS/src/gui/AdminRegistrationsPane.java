package gui;

import application.controller.Controller;
import application.model.Registration;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class AdminRegistrationsPane extends GridPane {

    private Registration registration;

    private final ListView<Registration> lvwRegistrations;

    AdminRegistrationsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwRegistrations = new ListView<>();
        this.lvwRegistrations.setPrefSize(250, 400);
        this.add(this.lvwRegistrations, 0, 0);

        ChangeListener<Registration> listener = (ov, oldValue, newValue) -> this.selectedRegistrationChanged(newValue);
        this.lvwRegistrations.getSelectionModel().selectedItemProperty().addListener(listener);

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

    private void selectedRegistrationChanged (Registration registration) {
        this.registration = registration;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        // TODO update data
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

            this.updateControls();
        }
    }

}

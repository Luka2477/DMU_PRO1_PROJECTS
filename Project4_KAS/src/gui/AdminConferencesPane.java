package gui;

import application.controller.Controller;
import application.model.Conference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminConferencesPane extends GridPane {

    private Conference conference;

    private final ListView<Conference> lvwConferences;

    AdminConferencesPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwConferences = new ListView<>();
        this.lvwConferences.setPrefSize(250, 400);
        this.add(this.lvwConferences, 0, 0);

        ChangeListener<Conference> listener = (ov, oldValue, newValue) -> this.selectedConferenceChanged(newValue);
        this.lvwConferences.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        // TODO

        // --------------------------------------------------------------

        Button btnDelete = new Button("Slet");
        btnDelete.setOnAction(event -> this.deleteAction());
        this.add(btnDelete, 0, 1);

        HBox hBox = new HBox();
        this.add(hBox, 1, 1);

        Button btnUpdate = new Button("Opdatere");
        btnUpdate.setOnAction(event -> this.updateAction());
        hBox.getChildren().add(btnUpdate);

        Button btnCreate = new Button("Opret");
        btnCreate.setOnAction(event -> this.createAction());
        hBox.getChildren().add(btnCreate);

        // --------------------------------------------------------------

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void selectedConferenceChanged (Conference conference) {
        this.conference = conference;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        // TODO update data
    }

    // --------------------------------------------------------------

    private void createAction () {
        AdminCreateConferenceWindow adminCreateConferenceWindow = new AdminCreateConferenceWindow();
        adminCreateConferenceWindow.showAndWait();

        this.updateControls();
    }

    private void updateAction () {
        AdminCreateConferenceWindow adminCreateConferenceWindow = new AdminCreateConferenceWindow(this.conference);
        adminCreateConferenceWindow.showAndWait();

        this.updateControls();
    }

    private void deleteAction () {
        if (this.conference != null) {
            Controller.removeConference(this.conference);

            this.updateControls();
        }
    }

}

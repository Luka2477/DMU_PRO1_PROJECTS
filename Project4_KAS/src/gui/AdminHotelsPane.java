package gui;

import application.controller.Controller;
import application.model.Conference;
import application.model.Hotel;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminHotelsPane extends GridPane {

    private Hotel hotel;

    private final ListView<Hotel> lvwHotels;

    AdminHotelsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwHotels = new ListView<>();
        this.lvwHotels.setPrefSize(250, 400);
        this.add(this.lvwHotels, 0, 0);

        ChangeListener<Hotel> listener = (ov, oldValue, newValue) -> this.selectedHotelChanged(newValue);
        this.lvwHotels.getSelectionModel().selectedItemProperty().addListener(listener);

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

    private void selectedHotelChanged (Hotel hotel) {
        this.hotel = hotel;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        // TODO update data
    }

    // --------------------------------------------------------------

    private void createAction () {
        AdminCreateHotelWindow adminCreateHotelWindow = new AdminCreateHotelWindow();
        adminCreateHotelWindow.showAndWait();

        this.updateControls();
    }

    private void updateAction () {
        AdminCreateHotelWindow adminCreateHotelWindow = new AdminCreateHotelWindow(this.lvwHotels.getSelectionModel().getSelectedItem());
        adminCreateHotelWindow.showAndWait();

        this.updateControls();
    }

    private void deleteAction () {
        if (this.hotel != null) {
            Controller.removeHotel(this.hotel);

            this.updateControls();
        }
    }

}

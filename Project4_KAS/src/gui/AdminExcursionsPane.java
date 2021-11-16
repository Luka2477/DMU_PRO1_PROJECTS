package gui;

import application.controller.Controller;
import application.model.Excursion;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminExcursionsPane extends GridPane {

    private Excursion excursion;

    private final ListView<Excursion> lvwExcursions;

    AdminExcursionsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(250, 400);
        this.add(this.lvwExcursions, 0, 0);

        ChangeListener<Excursion> listener = (ov, oldValue, newValue) -> this.selectedExcursionChanged(newValue);
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listener);

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

    private void selectedExcursionChanged (Excursion excursion) {
        this.excursion = excursion;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        // TODO update data
    }

    // --------------------------------------------------------------

    private void createAction () {
        AdminCreateExcursionWindow adminCreateExcursionWindow = new AdminCreateExcursionWindow();
        adminCreateExcursionWindow.showAndWait();

        this.updateControls();
    }

    private void updateAction () {
        AdminCreateExcursionWindow adminCreateExcursionWindow = new AdminCreateExcursionWindow(this.lvwExcursions.getSelectionModel().getSelectedItem());
        adminCreateExcursionWindow.showAndWait();

        this.updateControls();
    }

    private void deleteAction () {
        if (this.excursion != null) {
            Controller.removeExcursion(this.excursion);

            this.updateControls();
        }
    }

}

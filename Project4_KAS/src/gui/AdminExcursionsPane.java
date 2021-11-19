package gui;

import application.controller.Controller;
import application.model.Conference;
import application.model.Excursion;
import gui.components.NumericField;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

public class AdminExcursionsPane extends GridPane {

    private Excursion excursion;

    private final ListView<Excursion> lvwExcursions;
    private final TextField txfName, txfDescription, txfDestination, txfDateTime;
    private final NumericField nufPrice;
    private final CheckBox chbLunch;
    private final TextArea txaConferences;
    private final Button btnDelete, btnUpdate, btnGetCode;

    AdminExcursionsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(250, 400);
        this.add(this.lvwExcursions, 0, 0, 1, 7);

        ChangeListener<Excursion> listener = (ov, oldValue, newValue) -> this.selectedExcursionChanged(newValue);
        this.lvwExcursions.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        Label lblName = new Label("Navn:");
        this.add(lblName, 1, 0);

        Label lblDescription = new Label("Beskrivelse:");
        this.add(lblDescription, 1, 1);

        Label lblDestination = new Label("Destination:");
        this.add(lblDestination, 1, 2);

        Label lblDateTime = new Label("Dato og tid:");
        this.add(lblDateTime, 1, 3);

        Label lblPrice = new Label("Pris:");
        this.add(lblPrice, 1, 4);

        Label lblLunch = new Label("Frokost inkluderet:");
        this.add(lblLunch, 1, 5);

        Label lblConferences = new Label("Konferencer:");
        this.add(lblConferences, 1, 6);

        this.txfName = new TextField();
        this.txfName.setEditable(false);
        this.add(this.txfName, 2, 0);

        this.txfDescription = new TextField();
        this.txfDescription.setEditable(false);
        this.add(this.txfDescription, 2, 1);

        this.txfDestination = new TextField();
        this.txfDestination.setEditable(false);
        this.add(this.txfDestination, 2, 2);

        this.txfDateTime = new TextField();
        this.txfDateTime.setEditable(false);
        this.add(this.txfDateTime, 2, 3);

        this.nufPrice = new NumericField();
        this.nufPrice.setEditable(false);
        this.add(this.nufPrice, 2, 4);

        this.chbLunch = new CheckBox();
        this.chbLunch.setDisable(true);
        this.chbLunch.setOpacity(1);
        this.add(this.chbLunch, 2, 5);

        this.txaConferences = new TextArea();
        this.txaConferences.setPrefSize(200, 100);
        this.txaConferences.setEditable(false);
        this.add(this.txaConferences, 2, 6);

        // --------------------------------------------------------------

        HBox hBox = new HBox(10);
        this.add(hBox, 0, 7);

        this.btnDelete = new Button("Slet");
        this.btnDelete.setOnAction(event -> this.deleteAction());
        hBox.getChildren().add(this.btnDelete);

        this.btnUpdate = new Button("Opdatere");
        this.btnUpdate.setOnAction(event -> this.updateAction());
        hBox.getChildren().add(this.btnUpdate);

        Button btnCreate = new Button("Opret");
        btnCreate.setOnAction(event -> this.createAction());
        hBox.getChildren().add(btnCreate);

        this.btnGetCode = new Button("Få kode");
        this.btnGetCode.setOnAction(event -> this.getCodeAction());
        GridPane.setHalignment(this.btnGetCode, HPos.RIGHT);
        this.add(this.btnGetCode, 2, 8);

        // --------------------------------------------------------------

        this.updateExcursions();
        this.updateButtons();
    }

    // --------------------------------------------------------------

    private void selectedExcursionChanged (Excursion excursion) {
        this.excursion = excursion;

        this.updateControls();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        this.clearControls();

        if (this.excursion != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM-yyyy @ HH:mm");

            this.txfName.setText(this.excursion.getName());
            this.txfDescription.setText(this.excursion.getDescription());
            this.txfDestination.setText(this.excursion.getDestination());
            this.txfDateTime.setText(this.excursion.getDateTime().format(dtf));
            this.nufPrice.setText(this.excursion.getPrice() + "");
            this.chbLunch.setSelected(this.excursion.isLunchIncluded());

            StringBuilder conferences = new StringBuilder();
            for (Conference conference : Controller.getConferences()) {
                if (conference.getExcursions().contains(this.excursion)) {
                    conferences.append(conference.getName()).append("\n");
                }
            }
            this.txaConferences.setText(conferences.toString());
        }

        this.updateButtons();
    }

    private void updateButtons () {
        boolean excursion = this.excursion == null;

        this.btnDelete.setDisable(excursion);
        this.btnUpdate.setDisable(excursion);
        this.btnGetCode.setDisable(excursion);
    }

    private void clearControls () {
        this.txfName.clear();
        this.txfDescription.clear();
        this.txfDestination.clear();
        this.txfDateTime.clear();
        this.nufPrice.clear();
        this.chbLunch.setSelected(false);
        this.txaConferences.clear();

        this.updateButtons();
    }

    private void updateExcursions () {
        this.lvwExcursions.getItems().setAll(Controller.getExcursions());
    }

    // --------------------------------------------------------------

    private void createAction () {
        AdminCreateExcursionWindow adminCreateExcursionWindow = new AdminCreateExcursionWindow();
        adminCreateExcursionWindow.showAndWait();

        this.excursion = adminCreateExcursionWindow.getExcursion();
        this.updateControls();
        this.updateExcursions();
    }

    private void updateAction () {
        AdminCreateExcursionWindow adminCreateExcursionWindow = new AdminCreateExcursionWindow(this.excursion);
        adminCreateExcursionWindow.showAndWait();

        this.updateControls();
        this.updateExcursions();
    }

    private void deleteAction () {
        Controller.removeExcursion(this.excursion);

        this.excursion = null;
        this.clearControls();
        this.updateExcursions();
    }

    // --------------------------------------------------------------

    private void getCodeAction () {
        if (this.excursion != null) {
            AdminGetCodeWindow adminGetCodeWindow = new AdminGetCodeWindow(this.excursion);
            adminGetCodeWindow.show();
        }
    }

}

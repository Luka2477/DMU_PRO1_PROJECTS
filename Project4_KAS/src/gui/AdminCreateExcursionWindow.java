package gui;

import application.controller.Controller;
import application.model.Excursion;
import gui.components.NumericField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminCreateExcursionWindow extends Stage {

    private Excursion excursion;

    private TextField txfName, txfDescription, txfDestination;
    private NumericField nufTime, nufPrice;
    private DatePicker dtpDate;
    private CheckBox chbLunch;
    private Label lblError;

    AdminCreateExcursionWindow (Excursion excursion) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(String.format("%s udflugt - KAS", (excursion != null) ? "Opdater" : "Opret"));

        this.excursion = excursion;

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    AdminCreateExcursionWindow () {
        this(null);
    }

    // -------------------------------------------------------------------------

    private void initContent (GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // -------------------------------------------------------------------------

        VBox vBox = new VBox();
        pane.add(vBox, 0, 0, 2, 1);

        Label lblInfo = new Label("BEMÆRK: vil du ændre på konferenceinformationer,");
        vBox.getChildren().add(lblInfo);

        Label lblInfo1 = new Label("kan det ikke gøres her.");
        vBox.getChildren().add(lblInfo1);

        Label lblName = new Label("Navn:");
        pane.add(lblName, 0, 1);

        Label lblDescription = new Label("Beskrivelse:");
        pane.add(lblDescription, 0, 2);

        Label lblDestination = new Label("Destination:");
        pane.add(lblDestination, 0, 3);

        Label lblDate = new Label("Dato:");
        pane.add(lblDate, 0, 4);

        Label lblTime = new Label("Tid (Time):");
        pane.add(lblTime, 0, 5);

        Label lblPrice = new Label("Pris:");
        pane.add(lblPrice, 0, 6);

        Label lblLunch = new Label("Frokost inkluderet:");
        pane.add(lblLunch, 0, 7);

        this.lblError = new Label();
        this.lblError.setTextFill(Color.RED);
        pane.add(this.lblError, 0, 8, 2, 1);

        this.txfName = new TextField();
        pane.add(this.txfName, 1, 1);

        this.txfDescription = new TextField();
        pane.add(this.txfDescription, 1, 2);

        this.txfDestination = new TextField();
        pane.add(this.txfDestination, 1, 3);

        this.dtpDate = new DatePicker();
        pane.add(this.dtpDate, 1, 4);

        this.nufTime = new NumericField();
        pane.add(this.nufTime, 1, 5);

        this.nufPrice = new NumericField();
        pane.add(this.nufPrice, 1, 6);

        this.chbLunch = new CheckBox();
        pane.add(this.chbLunch, 1, 7);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 9);

        Button btnSaveCreate = new Button((this.excursion != null) ? "Gem" : "Opret");
        btnSaveCreate.setOnAction(event -> this.saveCreateAction());
        pane.add(btnSaveCreate, 1, 9);

        // -------------------------------------------------------------------------

        if (this.excursion != null) {
            this.initControls();
        }
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        this.txfName.setText(this.excursion.getName());
        this.txfDescription.setText(this.excursion.getDescription());
        this.txfDestination.setText(this.excursion.getDestination());
        this.dtpDate.setValue(this.excursion.getDateTime().toLocalDate());
        this.nufTime.setText(this.excursion.getDateTime().getHour() + "");
        this.nufPrice.setText(this.excursion.getPrice() + "");
        this.chbLunch.setSelected(this.excursion.isLunchIncluded());
    }

    // -------------------------------------------------------------------------


    private void cancelAction () {
        this.hide();
    }

    private void saveCreateAction () {
        String name = this.txfName.getText().trim();
        String description = this.txfDescription.getText().trim();
        String destination = this.txfDestination.getText().trim();
        LocalDate date = this.dtpDate.getValue();
        boolean isLunchIncluded = this.chbLunch.isSelected();
        int price = Integer.parseInt(this.nufPrice.getText().trim());
        int timeHour = Integer.parseInt(this.nufTime.getText().trim());

        LocalDateTime dateTime;
        if (timeHour >= 0 && timeHour <= 23) {
            dateTime = date.atTime(timeHour, 0);
        } else {
            this.lblError.setText("Tid skal være fra 0-23!");
            return;
        }

        if (this.excursion != null) {
            this.excursion.setName(name);
            this.excursion.setDescription(description);
            this.excursion.setDestination(destination);
            this.excursion.setDateTime(dateTime);
            this.excursion.setPrice(price);
            this.excursion.setLunchIncluded(isLunchIncluded);
        } else {
            this.excursion = Controller.createExcursion(name, description, destination, dateTime, price, isLunchIncluded);
        }

        this.hide();
    }

    // -------------------------------------------------------------------------

    public Excursion getExcursion () {
        return this.excursion;
    }

}

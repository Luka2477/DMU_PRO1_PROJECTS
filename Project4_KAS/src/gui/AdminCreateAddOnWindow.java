package gui;

import application.controller.Controller;
import application.model.AddOn;
import application.model.Hotel;
import gui.components.NumericField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminCreateAddOnWindow extends Stage {

    private final Hotel hotel;
    private AddOn addOn;

    private TextField txfName;
    private NumericField nufPrice;
    private Label lblError;

    AdminCreateAddOnWindow (Hotel hotel, AddOn addOn) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(String.format("%s tillæg - KAS", (addOn != null) ? "Opdater" : "Opret"));

        this.hotel = hotel;
        this.addOn = addOn;

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    AdminCreateAddOnWindow (Hotel hotel) {
        this(hotel, null);
    }

    // -------------------------------------------------------------------------

    private void initContent (GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // -------------------------------------------------------------------------

        Label lblName = new Label("Navn:");
        pane.add(lblName, 0, 0);

        Label lblPrice = new Label("Pris:");
        pane.add(lblPrice, 0, 1);

        this.lblError = new Label();
        this.lblError.setTextFill(Color.RED);
        pane.add(this.lblError, 0, 2, 2, 1);

        this.txfName = new TextField();
        pane.add(this.txfName, 1, 0);

        this.nufPrice = new NumericField();
        pane.add(this.nufPrice, 1, 1);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 3);

        Button btnSaveCreate = new Button((this.addOn != null) ? "Gem" : "Opret");
        btnSaveCreate.setOnAction(event -> this.saveCreateAction());
        pane.add(btnSaveCreate, 1, 3);

        // -------------------------------------------------------------------------

        if (this.addOn != null) {
            this.initControls();
        }
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        this.txfName.setText(this.addOn.getName());
        this.nufPrice.setText(this.addOn.getPrice() + "");
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void saveCreateAction () {
        String name = this.txfName.getText().trim();

        String strPrice = this.nufPrice.getText().trim();
        int price;
        if (!strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
        } else {
            this.lblError.setText("Pris er ikke angivet!");
            return;
        }

        if (this.addOn != null) {
            this.addOn.setName(name);
            this.addOn.setPrice(price);
        } else {
            this.addOn = Controller.createAddOn(this.hotel, name, price);
        }

        this.hide();
    }

    // -------------------------------------------------------------------------

    public AddOn getAddOn () {
        return this.addOn;
    }

}

package gui;

import application.controller.Controller;
import application.model.Hotel;
import gui.components.NumericField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminCreateHotelWindow extends Stage {

    private Hotel hotel;

    private TextField txfName, txfAddress;
    private NumericField nufSinglePrice, nufDoublePrice;

    AdminCreateHotelWindow (Hotel hotel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(String.format("%s hotel - KAS", (hotel != null) ? "Opdater" : "Opret"));

        this.hotel = hotel;

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    AdminCreateHotelWindow () {
        this(null);
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

        Label lblAddress = new Label("Adresse:");
        pane.add(lblAddress, 0, 1);

        Label lblSinglePrice = new Label("Enkeltværelsespris:");
        pane.add(lblSinglePrice, 0, 2);

        Label lblDoublePrice = new Label("Doubleværelsespris:");
        pane.add(lblDoublePrice, 0, 3);

        this.txfName = new TextField();
        pane.add(this.txfName, 1, 0);

        this.txfAddress = new TextField();
        pane.add(this.txfAddress, 1, 1);

        this.nufSinglePrice = new NumericField();
        pane.add(this.nufSinglePrice, 1, 2);

        this.nufDoublePrice = new NumericField();
        pane.add(this.nufDoublePrice, 1, 3);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 4);

        Button btnSaveCreate = new Button((this.hotel != null) ? "Gem" : "Opret");
        btnSaveCreate.setOnAction(event -> this.saveCreateAction());
        pane.add(btnSaveCreate, 1, 4);

        // -------------------------------------------------------------------------

        if (this.hotel != null) {
            this.initControls();
        }
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        this.txfName.setText(this.hotel.getName());
        this.txfAddress.setText(this.hotel.getAddress());
        this.nufSinglePrice.setText(this.hotel.getSinglePrice() + "");
        this.nufDoublePrice.setText(this.hotel.getDoublePrice() + "");
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void saveCreateAction () {
        String name = this.txfName.getText().trim();
        String address = this.txfAddress.getText().trim();
        int singlePrice = Integer.parseInt(this.nufSinglePrice.getText().trim());
        int doublePrice = Integer.parseInt(this.nufDoublePrice.getText().trim());

        if (this.hotel != null) {
            this.hotel.setName(name);
            this.hotel.setAddress(address);
            this.hotel.setSinglePrice(singlePrice);
            this.hotel.setDoublePrice(doublePrice);
        } else {
            this.hotel = Controller.createHotel(name, address, singlePrice, doublePrice);
        }

        this.hide();
    }

    // -------------------------------------------------------------------------

    public Hotel getHotel () {
        return this.hotel;
    }

}

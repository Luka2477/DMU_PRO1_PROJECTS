package gui;

import application.controller.Controller;
import application.model.AddOn;
import application.model.Conference;
import application.model.Hotel;
import application.model.HotelRoom;
import gui.components.NumericField;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class AdminHotelsPane extends GridPane {

    private Hotel hotel;
    private AddOn addOn;

    private final ListView<Hotel> lvwHotels;
    private final ListView<AddOn> lvwAddOns;
    private final TextField txfName, txfAddress;
    private final NumericField nufSinglePrice, nufDoublePrice;
    private final TextArea txaConferences, txaHotelRooms;
    private final Button btnGetCode, btnDeleteHotel, btnUpdateHotel, btnDeleteAddOn, btnUpdateAddOn, btnCreateAddOn;

    AdminHotelsPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        this.lvwHotels = new ListView<>();
        this.lvwHotels.setPrefSize(250, 400);
        this.add(this.lvwHotels, 0, 0, 1, 5);

        ChangeListener<Hotel> listener = (ov, oldValue, newValue) -> this.selectedHotelChanged(newValue);
        this.lvwHotels.getSelectionModel().selectedItemProperty().addListener(listener);

        // --------------------------------------------------------------

        Label lblName = new Label("Navn:");
        this.add(lblName, 1, 0);

        Label lblAddress = new Label("Adressse:");
        this.add(lblAddress, 1, 1);

        Label lblSinglePrice = new Label("Enkeltværelsespris:");
        this.add(lblSinglePrice, 1, 2);

        Label lblDoublePrice = new Label("Doubleværelsespris:");
        this.add(lblDoublePrice, 1, 3);

        Label lblAddOns = new Label("Tillæg:");
        this.add(lblAddOns, 1, 4);

        this.txfName = new TextField();
        this.txfName.setEditable(false);
        this.add(this.txfName, 2, 0);

        this.txfAddress = new TextField();
        this.txfAddress.setEditable(false);
        this.add(this.txfAddress, 2, 1);

        this.nufSinglePrice = new NumericField();
        this.nufSinglePrice.setEditable(false);
        this.add(this.nufSinglePrice, 2, 2);

        this.nufDoublePrice = new NumericField();
        this.nufDoublePrice.setEditable(false);
        this.add(this.nufDoublePrice, 2, 3);

        this.lvwAddOns = new ListView<>();
        this.lvwAddOns.setPrefSize(200, 100);
        this.add(this.lvwAddOns, 2, 4);

        ChangeListener<AddOn> listenerAddOn = (ov, oldValue, newValue) -> this.selectedAddOnChanged(newValue);
        this.lvwAddOns.getSelectionModel().selectedItemProperty().addListener(listenerAddOn);

        Label lbl = new Label("Konferencer:");
        this.add(lbl, 3, 0, 1, 4);

        Label lblHotelRooms = new Label("Hotelværelser:");
        this.add(lblHotelRooms, 3, 4);

        this.txaConferences = new TextArea();
        this.txaConferences.setPrefSize(200, 100);
        this.txaConferences.setEditable(false);
        this.add(this.txaConferences, 4, 0, 1, 4);

        this.txaHotelRooms = new TextArea();
        this.txaHotelRooms.setPrefSize(200, 100);
        this.txaHotelRooms.setEditable(false);
        this.add(this.txaHotelRooms, 4, 4);

        // --------------------------------------------------------------

        HBox hBoxHotels = new HBox(10);
        this.add(hBoxHotels, 0, 5);

        this.btnDeleteHotel = new Button("Slet hotel");
        this.btnDeleteHotel.setOnAction(event -> this.deleteHotelAction());
        hBoxHotels.getChildren().add(this.btnDeleteHotel);

        this.btnUpdateHotel = new Button("Opdatere hotel");
        this.btnUpdateHotel.setOnAction(event -> this.updateHotelAction());
        hBoxHotels.getChildren().add(this.btnUpdateHotel);

        Button btnCreateHotel = new Button("Opret hotel");
        btnCreateHotel.setOnAction(event -> this.createHotelAction());
        hBoxHotels.getChildren().add(btnCreateHotel);

        HBox hBoxAddOns = new HBox(10);
        this.add(hBoxAddOns, 2, 5);

        this.btnDeleteAddOn = new Button("Slet tillæg");
        this.btnDeleteAddOn.setOnAction(event -> this.deleteAddOnAction());
        hBoxAddOns.getChildren().add(this.btnDeleteAddOn);

        this.btnUpdateAddOn = new Button("Opdatere tillæg");
        this.btnUpdateAddOn.setOnAction(event -> this.updateAddOnAction());
        hBoxAddOns.getChildren().add(this.btnUpdateAddOn);

        this.btnCreateAddOn = new Button("Opret tillæg");
        this.btnCreateAddOn.setOnAction(event -> this.createAddOnAction());
        hBoxAddOns.getChildren().add(this.btnCreateAddOn);

        this.btnGetCode = new Button("Få kode");
        this.btnGetCode.setOnAction(event -> this.getCodeAction());
        GridPane.setHalignment(this.btnGetCode, HPos.RIGHT);
        this.add(this.btnGetCode, 4, 6);

        // --------------------------------------------------------------

        this.updateHotels();
        this.updateButtons();
    }

    // --------------------------------------------------------------

    private void selectedHotelChanged (Hotel hotel) {
        this.hotel = hotel;

        this.updateControls();
    }

    private void selectedAddOnChanged (AddOn addOn) {
        this.addOn = addOn;

        this.updateButtons();
    }

    // --------------------------------------------------------------

    private void updateControls () {
        this.clearControls();

        if (this.hotel != null) {
            this.txfName.setText(this.hotel.getName());
            this.txfAddress.setText(this.hotel.getAddress());
            this.nufSinglePrice.setText(this.hotel.getSinglePrice() + "");
            this.nufDoublePrice.setText(this.hotel.getDoublePrice() + "");

            StringBuilder conferences = new StringBuilder();
            for (Conference conference : this.hotel.getConferences()) {
                conferences.append(conference.getName()).append("\n");
            }
            this.txaConferences.setText(conferences.toString());

            this.lvwAddOns.getItems().setAll(this.hotel.getAddOns());

            StringBuilder hotelRooms = new StringBuilder();
            for (HotelRoom hotelRoom : this.hotel.getHotelRooms()) {
                hotelRooms.append(hotelRoom.getNr()).append(" ").append((hotelRoom.isSingle()) ? "Enkeltværelse " : "Doubleværelse ");

                ArrayList<String> addOnNames = new ArrayList<>();
                for (AddOn addOn : hotelRoom.getAddOns()) {
                    addOnNames.add(addOn.getName());
                }
                hotelRooms.append("(").append(String.join(", ", addOnNames)).append(")\n");
            }
            this.txaHotelRooms.setText(hotelRooms.toString());
        }

        this.updateButtons();
    }

    private void updateButtons () {
        boolean hotel = this.hotel == null;
        boolean addOn = this.addOn == null;

        this.btnDeleteHotel.setDisable(hotel);
        this.btnUpdateHotel.setDisable(hotel);
        this.btnCreateAddOn.setDisable(hotel);
        this.btnGetCode.setDisable(hotel);

        this.btnUpdateAddOn.setDisable(addOn);
        this.btnDeleteAddOn.setDisable(addOn);
    }

    private void clearControls () {
        this.txfName.clear();
        this.txfAddress.clear();
        this.nufSinglePrice.clear();
        this.nufDoublePrice.clear();
        this.txaConferences.clear();
        this.lvwAddOns.getItems().clear();
        this.txaHotelRooms.clear();

        this.addOn = null;

        this.updateButtons();
    }

    private void updateHotels () {
        this.lvwHotels.getItems().setAll(Controller.getHotels());
    }

    // --------------------------------------------------------------

    private void createHotelAction () {
        AdminCreateHotelWindow adminCreateHotelWindow = new AdminCreateHotelWindow();
        adminCreateHotelWindow.showAndWait();

        this.hotel = adminCreateHotelWindow.getHotel();
        this.updateControls();
        this.updateHotels();
    }

    private void updateHotelAction () {
        AdminCreateHotelWindow adminCreateHotelWindow = new AdminCreateHotelWindow(this.lvwHotels.getSelectionModel().getSelectedItem());
        adminCreateHotelWindow.showAndWait();

        this.updateControls();
        this.updateHotels();
    }

    private void deleteHotelAction () {
        Controller.removeHotel(this.hotel);

        this.hotel = null;
        this.clearControls();
        this.updateHotels();
    }

    // --------------------------------------------------------------

    private void createAddOnAction () {
        AdminCreateAddOnWindow adminCreateAddOnWindow = new AdminCreateAddOnWindow(this.hotel);
        adminCreateAddOnWindow.showAndWait();

        this.addOn = adminCreateAddOnWindow.getAddOn();
        this.updateControls();
    }

    private void updateAddOnAction () {
        AdminCreateAddOnWindow adminCreateAddOnWindow = new AdminCreateAddOnWindow(this.hotel, this.addOn);
        adminCreateAddOnWindow.showAndWait();

        this.updateControls();
    }

    private void deleteAddOnAction () {
        this.hotel.removeAddOn(this.addOn);

        this.addOn = null;
        this.updateControls();
    }

    // --------------------------------------------------------------

    private void getCodeAction () {
        AdminGetCodeWindow adminGetCodeWindow = new AdminGetCodeWindow(this.hotel);
        adminGetCodeWindow.show();
    }

}

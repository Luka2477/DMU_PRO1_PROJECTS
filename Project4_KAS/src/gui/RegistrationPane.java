package gui;

import application.model.Conference;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;

public class RegistrationPane extends GridPane {

    private ListView<Conference> lvwConferences;
    private TextField txfName, txfAddress, txfCountry, txfCity, txfTelephone, txfCompanyName, txfCompanyTelephone;
    private CheckBox chbSpeaker;
    private DatePicker dtpStart, dtpEnd;

    public RegistrationPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(true);

        // --------------------------------------------------------------

        GridPane participantGridPane = new GridPane();
        participantGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.add(participantGridPane, 0, 0);

        Label lblHeader = new Label("Deltager");
        lblHeader.setFont(new Font(25));
        GridPane.setHalignment(lblHeader, HPos.CENTER);
        participantGridPane.add(lblHeader, 0, 0, 4, 1);

        Label lblName = new Label("Navn:");
        participantGridPane.add(lblName, 0, 1);

        this.txfName = new TextField();
        participantGridPane.add(this.txfName, 1, 1);

        this.chbSpeaker = new CheckBox("Foredragsholder");
        GridPane.setHalignment(this.chbSpeaker, HPos.CENTER);
        participantGridPane.add(this.chbSpeaker, 2, 1, 2, 1);

        Label lblAddress = new Label("Adresse:");
        participantGridPane.add(lblAddress, 0, 2);

        this.txfAddress = new TextField();
        participantGridPane.add(this.txfAddress, 1, 2);

        Label lblCity = new Label("By:");
        participantGridPane.add(lblCity, 2, 2);

        this.txfCity = new TextField();
        participantGridPane.add(this.txfCity, 3, 2);

        Label lblCountry = new Label("Land:");
        participantGridPane.add(lblCountry, 0, 3);

        this.txfCountry = new TextField();
        participantGridPane.add(this.txfCountry, 1, 3);

        Label lblTelephone = new Label("Tlf.nr.");
        participantGridPane.add(lblTelephone, 2, 3);

        this.txfTelephone = new TextField();
        participantGridPane.add(this.txfTelephone, 3, 3);

        Label lblStartDate = new Label("Ankomstdato:");
        participantGridPane.add(lblStartDate, 0, 4);

        this.dtpStart = new DatePicker();
        this.dtpStart.setValue(LocalDate.now());
        this.restrictDatePicker(this.dtpStart, LocalDate.now(), LocalDate.now().plusDays(3));
        participantGridPane.add(this.dtpStart, 1, 4);

        Label lblEndDate = new Label("Afrejsedato:");
        participantGridPane.add(lblEndDate, 2, 4);

        this.dtpEnd = new DatePicker();
        this.dtpEnd.setValue(LocalDate.now().plusDays(1));
        this.restrictDatePicker(this.dtpEnd, this.dtpStart.getValue().plusDays(1), this.dtpStart.getValue().plusDays(4));
        participantGridPane.add(this.dtpEnd, 3, 4);

        // --------------------------------------------------------------

        GridPane companionGridPane = new GridPane();
        companionGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.add(companionGridPane, 1, 0);

        // --------------------------------------------------------------

        GridPane hotelGridPane = new GridPane();
        hotelGridPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        this.add(hotelGridPane, 0, 1, 2, 1);
    }

    // --------------------------------------------------------------

    public void updateControls () {

    }

    // --------------------------------------------------------------

    private void restrictDatePicker (DatePicker datePicker, LocalDate startDate, LocalDate endDate) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem (LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
            }
        });
    }

}

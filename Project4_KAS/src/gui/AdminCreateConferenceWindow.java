package gui;

import application.controller.Controller;
import application.model.Conference;
import gui.components.NumericField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminCreateConferenceWindow extends Stage {

    private Conference conference;

    private TextField txfName, txfAddress;
    private DatePicker dtpStartDate, dtpEndDate, dtpDeadline;
    private NumericField nufDailyPrice, nufStartTime, nufEndTime;
    private Label lblError;

    AdminCreateConferenceWindow (Conference conference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(String.format("%s konference - KAS", (conference != null) ? "Opdater" : "Opret"));

        this.conference = conference;

        GridPane pane = new GridPane();
        pane.setPrefSize(290, 325);
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    AdminCreateConferenceWindow () {
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

        Label lblDailyPrice = new Label("Dagspris:");
        pane.add(lblDailyPrice, 0, 2);

        Label lblStartDate = new Label("Startdato:");
        pane.add(lblStartDate, 0, 3);

        Label lblStartTime = new Label("Starttid (Time):");
        pane.add(lblStartTime, 0, 4);

        Label lblEndDate = new Label("Slutdato:");
        pane.add(lblEndDate, 0, 5);

        Label lblEndTime = new Label("Sluttid (Time):");
        pane.add(lblEndTime, 0, 6);

        Label lblDeadline = new Label("Tilmeldingsfrist:");
        pane.add(lblDeadline, 0, 7);

        this.lblError = new Label();
        this.lblError.setTextFill(Color.RED);
        pane.add(this.lblError, 0, 8, 2, 1);

        this.txfName = new TextField();
        pane.add(this.txfName, 1, 0);

        this.txfAddress = new TextField();
        pane.add(this.txfAddress, 1, 1);

        this.nufDailyPrice = new NumericField();
        pane.add(this.nufDailyPrice, 1, 2);

        this.dtpStartDate = new DatePicker();
        this.dtpStartDate.valueProperty().addListener((ov, oldValue, newValue) -> this.startDateChanged(newValue));
        pane.add(this.dtpStartDate, 1, 3);
        this.restrictDatePicker(this.dtpStartDate, LocalDate.now(), true);

        this.nufStartTime = new NumericField();
        pane.add(this.nufStartTime, 1, 4);

        this.dtpEndDate = new DatePicker();
        pane.add(this.dtpEndDate, 1, 5);
        this.restrictDatePicker(this.dtpEndDate, LocalDate.now(), true);

        this.nufEndTime = new NumericField();
        pane.add(this.nufEndTime, 1, 6);

        this.dtpDeadline = new DatePicker();
        pane.add(this.dtpDeadline, 1, 7);
        this.restrictDatePicker(this.dtpDeadline, LocalDate.now(), true);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 9);

        Button btnSaveCreate = new Button((this.conference != null) ? "Gem" : "Opret");
        btnSaveCreate.setOnAction(event -> this.saveCreateAction());
        pane.add(btnSaveCreate, 1, 9);

        // -------------------------------------------------------------------------

        if (this.conference != null) {
            this.initControls();
        }
    }

    // -------------------------------------------------------------------------

    private void startDateChanged (LocalDate date) {
        this.restrictDatePicker(this.dtpEndDate, date, true);
        this.restrictDatePicker(this.dtpDeadline, LocalDate.now(), date);
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        this.txfName.setText(this.conference.getName());
        this.txfAddress.setText(this.conference.getAddress());
        this.nufDailyPrice.setText(this.conference.getDailyPrice() + "");
        this.dtpStartDate.setValue(this.conference.getStartDate().toLocalDate());
        this.nufStartTime.setText(this.conference.getStartDate().getHour() + "");
        this.dtpEndDate.setValue(this.conference.getEndDate().toLocalDate());
        this.restrictDatePicker(this.dtpEndDate, this.dtpStartDate.getValue(), true);
        this.nufEndTime.setText(this.conference.getEndDate().getHour() + "");
        this.dtpDeadline.setValue(this.conference.getDeadline().toLocalDate());
        this.restrictDatePicker(this.dtpDeadline, LocalDate.now(), this.dtpStartDate.getValue());
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void saveCreateAction () {
        String name = this.txfName.getText().trim();
        String address = this.txfAddress.getText().trim();

        String strDailyPrice = this.nufDailyPrice.getText().trim();
        String strStartTime = this.nufStartTime.getText().trim();
        String strEndTime = this.nufEndTime.getText().trim();
        int dailyPrice, startTime, endTime;
        if (!strDailyPrice.isEmpty() && !strStartTime.isEmpty() && !strEndTime.isEmpty()) {
            dailyPrice = Integer.parseInt(strDailyPrice);
            startTime = Integer.parseInt(strStartTime);
            endTime = Integer.parseInt(strEndTime);
        } else {
            this.lblError.setText("Dagspris, starttid eller sluttid er ikke angivet!");
            return;
        }

        LocalDateTime startDateTime = this.dtpStartDate.getValue().atTime(startTime, 0);
        LocalDateTime endDateTime = this.dtpEndDate.getValue().atTime(endTime, 0);
        LocalDateTime deadline = this.dtpDeadline.getValue().atTime(23, 59);

        if (this.conference != null) {
            this.conference.setName(name);
            this.conference.setAddress(address);
            this.conference.setDailyPrice(dailyPrice);
            this.conference.setStartDate(startDateTime);
            this.conference.setEndDate(endDateTime);
            this.conference.setDeadline(deadline);
        } else {
            this.conference = Controller.createConference(name, address, dailyPrice, startDateTime, endDateTime, deadline);
        }

        this.hide();
    }

    // -------------------------------------------------------------------------

    public Conference getConference () {
        return this.conference;
    }

    // -------------------------------------------------------------------------

    private void restrictDatePicker (DatePicker datePicker, LocalDate givenDate, boolean fromDate) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem (LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (fromDate) {
                    setDisable(empty || date.compareTo(givenDate) < 0);
                } else {
                    setDisable(empty || date.compareTo(givenDate) > 0);
                }
            }
        });
    }

    private void restrictDatePicker (DatePicker datePicker, LocalDate startDate, LocalDate endDate) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem (LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
            }
        });
    }

}

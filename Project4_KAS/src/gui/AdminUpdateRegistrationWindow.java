package gui;

import application.model.Excursion;
import application.model.Registration;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class AdminUpdateRegistrationWindow extends Stage {

    private final Registration registration;

    private TextField txfCompanyName, txfCompanyTelephone, txfCompanionName;
    private DatePicker dtpArrivalDate, dtpDepartureDate;
    private CheckBox chbSpeaker;
    private ListView<Excursion> lvwExcursions;

    AdminUpdateRegistrationWindow (Registration registration) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opdatere konference - KAS");

        this.registration = registration;

        GridPane pane = new GridPane();
        pane.setPrefWidth(400);
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
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

        Label lblInfo = new Label("BEMÆRK: vil du ændre på deltager-, konference-, hotelinformationer,");
        vBox.getChildren().add(lblInfo);

        Label lblInfo1 = new Label("kan det ikke gøres her.");
        vBox.getChildren().add(lblInfo1);

        Label lblCompanyName = new Label("Firmanavn:");
        pane.add(lblCompanyName, 0, 1);

        Label lblCompanyTelephone = new Label("Firma tlf.nr:");
        pane.add(lblCompanyTelephone, 0, 2);

        Label lblCompanionName = new Label("Ledsager navn:");
        pane.add(lblCompanionName, 0, 3);

        Label lblArrivalDate = new Label("Ankomstdato:");
        pane.add(lblArrivalDate, 0, 4);

        Label lblDepartureDate = new Label("Afrejsedato:");
        pane.add(lblDepartureDate, 0, 5);

        Label lblSpeaker = new Label("Foredagsholder:");
        pane.add(lblSpeaker, 0, 6);

        Label lblExcursions = new Label("Udflugter:");
        pane.add(lblExcursions, 0, 7);

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(400, 150);
        this.lvwExcursions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pane.add(this.lvwExcursions, 0, 8, 2, 1);

        this.txfCompanyName = new TextField();
        pane.add(this.txfCompanyName, 1, 1);

        this.txfCompanyTelephone = new TextField();
        pane.add(this.txfCompanyTelephone, 1, 2);

        this.txfCompanionName = new TextField();
        pane.add(this.txfCompanionName, 1, 3);

        this.dtpArrivalDate = new DatePicker();
        pane.add(this.dtpArrivalDate, 1, 4);

        this.dtpDepartureDate = new DatePicker();
        pane.add(this.dtpDepartureDate, 1, 5);

        this.chbSpeaker = new CheckBox();
        pane.add(this.chbSpeaker, 1, 6);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 9);

        Button btnSave = new Button("Gem");
        btnSave.setOnAction(event -> this.saveAction());
        pane.add(btnSave, 1, 9);

        // -------------------------------------------------------------------------

        this.initControls();
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        this.restrictDatePicker(this.dtpArrivalDate, this.registration.getConference().getStartDate().toLocalDate(), this.registration.getConference().getEndDate().toLocalDate());
        this.restrictDatePicker(this.dtpDepartureDate, this.registration.getConference().getStartDate().toLocalDate(), this.registration.getConference().getEndDate().toLocalDate());

        this.txfCompanyName.setText(this.registration.getCompanyName());
        this.txfCompanyTelephone.setText(this.registration.getCompanyTelephone());
        this.dtpArrivalDate.setValue(this.registration.getArrivalDate());
        this.dtpDepartureDate.setValue(this.registration.getDepartureDate());
        this.chbSpeaker.setSelected(this.registration.isSpeaker());
        this.lvwExcursions.getItems().setAll(this.registration.getConference().getExcursions());

        if (this.registration.getCompanion() != null) {
            this.txfCompanionName.setText(this.registration.getCompanion().getName());
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void saveAction () {
        // TODO get data
        String companyName = this.txfCompanyName.getText().trim();
        String companyTelephone = this.txfCompanyTelephone.getText().trim();
        String companionName = this.txfCompanionName.getText().trim();
        LocalDate arrivalDate = this.dtpArrivalDate.getValue();
        LocalDate departureDate = this.dtpDepartureDate.getValue();
        boolean isSpeaker = this.chbSpeaker.isSelected();
        ObservableList<Excursion> excursions = this.lvwExcursions.getSelectionModel().getSelectedItems();

        this.registration.setCompanyName(companyName);
        this.registration.setCompanyTelephone(companyTelephone);
        this.registration.setArrivalDate(arrivalDate);
        this.registration.setDepartureDate(departureDate);
        this.registration.setSpeaker(isSpeaker);

        if (this.registration.getCompanion() == null && !companionName.isEmpty()) {
            this.registration.createCompanion(companionName);
        }

        if (this.registration.getCompanion() != null) {
            this.registration.getCompanion().setName(companionName);

            for (Excursion excursion : excursions) {
                this.registration.getCompanion().addExcursion(excursion);
            }
        }

        this.hide();
    }

    // -------------------------------------------------------------------------

    private void restrictDatePicker (DatePicker datePicker, LocalDate startDate, LocalDate endDate) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem (LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
            }
        });
    }
}

package gui;

import application.controller.Controller;
import application.model.Conference;
import application.model.Excursion;
import application.model.Hotel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class AdminAddExcursionToConferenceWindow extends Stage {

    private final Conference conference;

    private ListView<Excursion> lvwExcursions;

    AdminAddExcursionToConferenceWindow(Conference conference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Tilføj udflugt - KAS");

        this.conference = conference;

        GridPane pane = new GridPane();
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

        this.lvwExcursions = new ListView<>();
        this.lvwExcursions.setPrefSize(300, 150);
        pane.add(this.lvwExcursions, 0, 0, 2, 1);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 1);

        Button btnAdd = new Button("Tilføj");
        btnAdd.setOnAction(event -> this.addAction());
        pane.add(btnAdd, 1, 1);

        // -------------------------------------------------------------------------

        this.initControls();
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        ArrayList<Excursion> excursions = Controller.getExcursions();
        excursions.removeAll(this.conference.getExcursions());
        this.lvwExcursions.getItems().setAll(excursions);
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void addAction () {
        for (Excursion excursion : this.lvwExcursions.getSelectionModel().getSelectedItems()) {
            this.conference.addExcursion(excursion);
        }

        this.hide();
    }

}

package gui;

import application.model.Excursion;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminCreateExcursionWindow extends Stage {

    private final Excursion excursion;

    AdminCreateExcursionWindow (Excursion excursion) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret udflugt - KAS");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.excursion = excursion;
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

        // TODO

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 1);

        Button btnSaveCreate = new Button((this.excursion != null) ? "Gem" : "Opret");
        btnSaveCreate.setOnAction(event -> this.saveCreateAction());
        pane.add(btnSaveCreate, 1, 1);
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void saveCreateAction () {
        // TODO get data

        if (this.excursion != null) {
            // TODO update the addOn
        } else {
            // TODO create the addOn
        }
    }

}

package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class AdminPane extends GridPane {

    public AdminPane () {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(true);


    }

    // --------------------------------------------------------------

    public void updateControls () {

    }

}

package gui.components;

import javafx.scene.control.TextField;

public class NumericField extends TextField {

    public NumericField () {
        super();

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}

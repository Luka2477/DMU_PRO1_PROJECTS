package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

    @Override
    public void init () {
        Controller.init();
    }

    @Override
    public void start (Stage stage) {
        stage.setTitle("Konference Administration System");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("stylesheets/textField.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    // --------------------------------------------------------------

    private void initContent (BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane (TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabRegistration = new Tab("Registration");
        tabPane.getTabs().add(tabRegistration);

        RegistrationPane registrationPane = new RegistrationPane();
        tabRegistration.setContent(registrationPane);

        Tab tabAdmin = new Tab("Admin");
        tabPane.getTabs().add(tabAdmin);

        AdminPane adminPane = new AdminPane();
        tabAdmin.setContent(adminPane);
    }

}

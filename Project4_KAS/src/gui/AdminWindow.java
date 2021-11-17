package gui;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminWindow extends Stage {

    AdminWindow () {
        this.setResizable(false);
        this.setTitle("Admin - KAS");

        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // --------------------------------------------------------------

    private void initContent (BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane (TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabParticipants = new Tab("Deltagere");
        tabPane.getTabs().add(tabParticipants);

        AdminParticipantsPane participantsPane = new AdminParticipantsPane();
        tabParticipants.setContent(participantsPane);

        Tab tabRegistrations = new Tab("Registrationer");
        tabPane.getTabs().add(tabRegistrations);

        AdminRegistrationsPane registrationsPane = new AdminRegistrationsPane();
        tabRegistrations.setContent(registrationsPane);

        Tab tabConferences = new Tab("Konferencer");
        tabPane.getTabs().add(tabConferences);

        AdminConferencesPane conferencesPane = new AdminConferencesPane();
        tabConferences.setContent(conferencesPane);

        Tab tabHotels = new Tab("Hoteller");
        tabPane.getTabs().add(tabHotels);

        AdminHotelsPane hotelsPane = new AdminHotelsPane();
        tabHotels.setContent(hotelsPane);

        Tab tabExcursions = new Tab("Udflugter");
        tabPane.getTabs().add(tabExcursions);

        AdminExcursionsPane excursionsPane = new AdminExcursionsPane();
        tabExcursions.setContent(excursionsPane);
    }

}

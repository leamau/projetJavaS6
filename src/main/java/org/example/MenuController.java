package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {

    @FXML
    private void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    @FXML
    private void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    private void switchToChaines() throws IOException {
        App.setRoot("Chaines");
    }

    @FXML
    private void switchToPersonnel() throws IOException {
        App.setRoot("Personnels");
    }
}

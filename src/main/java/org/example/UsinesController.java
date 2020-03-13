package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UsinesController {
    @FXML
    public void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    public void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }
}
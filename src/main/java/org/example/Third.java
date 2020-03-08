package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Third {
    @FXML
    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }
}
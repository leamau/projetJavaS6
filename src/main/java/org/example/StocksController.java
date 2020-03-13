package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class StocksController {

    @FXML
    private void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    @FXML
    private void switchToUsines() throws IOException {
        App.setRoot("Usines");
    }
}

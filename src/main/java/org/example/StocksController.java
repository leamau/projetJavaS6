package org.example;

import javafx.fxml.FXML;
import java.io.IOException;

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

package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class AchatsController {

    @FXML
    private void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    private void switchToUsines() throws IOException {
        App.setRoot("Usines");
    }
}
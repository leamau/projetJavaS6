package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChainesController implements Initializable {
    public TableView<Chaines> tableChaines;
    public TableColumn<Chaines,String> cdeChaines;
    public TableColumn<Chaines,String> produitSortieChaines;
    public TableColumn<Chaines,String> tempsChaines;
    public TableColumn<Chaines,Integer> indValeurChaines;
    public TableColumn<Chaines,Integer> indCommandeChaines;

    @FXML
    public void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    public void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cdeChaines.setCellValueFactory(new PropertyValueFactory<>("CodeChaines"));
        produitSortieChaines.setCellValueFactory(new PropertyValueFactory<>("ChainesProduitSortie"));
        tempsChaines.setCellValueFactory(new PropertyValueFactory<>("ChainesTemps"));
        indValeurChaines.setCellValueFactory(new PropertyValueFactory<>("ChainesIndValeur"));
        indCommandeChaines.setCellValueFactory(new PropertyValueFactory<>("ChainesIndCommande"));
        tableChaines.setItems(observableList);
    }

    ObservableList<Chaines> observableList = FXCollections.observableArrayList(
        new Chaines("C001", "eau", 1, 1.1, 1),
        new Chaines("C002", "blanc d'oeuf", 1, 1.1, 1)
    );
}
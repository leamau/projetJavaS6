package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.Element;
import org.app.Usine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StocksController implements Initializable {

    public TableView<Element> tableStocks;
    public TableColumn<Element,String> cdeElemStocks;
    public TableColumn<Element,String> nomStocks;
    public TableColumn<Element,String> uniteMesureStocks;
    public TableColumn<Element,Double> quantiteStocks;

    /**
     * Permet de se déplacer vers l'interface Achats
     * @throws IOException
     */
    @FXML
    private void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    /**
     * Permet de se déplacer vers l'interface Chaine
     * @throws IOException
     */
    @FXML
    private void switchToChaines() throws IOException {
        App.setRoot("Chaines");
    }

    /**
     * Permet de se déplacer vers l'interface Personnel'
     * @throws IOException
     */
    @FXML
    public void switchToPersonnel() throws IOException {
        App.setRoot("Personnels");
    }

    /**
     * Permet de se déplacer vers le Menu de l'application
     * @throws IOException
     */
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }

    /**
     * Permet d'initialiser le contenu de la page Stocks.fxml
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cdeElemStocks.setCellValueFactory(cellData -> cellData.getValue().codeEProperty());
        nomStocks.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        uniteMesureStocks.setCellValueFactory(cellData -> cellData.getValue().uniteMesureProperty());
        quantiteStocks.setCellValueFactory(cellData -> cellData.getValue().quantiteStockProperty().asObject());
        observableList.addAll(Usine.getInstance().getElements());
        tableStocks.setItems(observableList);
    }

    ObservableList<Element> observableList = FXCollections.observableArrayList(
    );
}

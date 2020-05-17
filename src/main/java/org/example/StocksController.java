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

/**
 * Classe permettant de controller tous les éléments contenu dans les stocks et de les liers avec l'interface
 */
public class StocksController implements Initializable {

    /**
     * Tableau pour visualiser toutes les informations des stocks de l'usine
     */
    public TableView<Element> tableStocks;

    /**
     * Colonne pour visualiser le code des éléments en stock
     */
    public TableColumn<Element,String> cdeElemStocks;

    /**
     * Colonne pour visualiser le nom des éléments en stock
     */
    public TableColumn<Element,String> nomStocks;

    /**
     * Colonne pour visualiser l'unité de mesure des éléments/produits en stock
     */
    public TableColumn<Element,String> uniteMesureStocks;

    /**
     * Colonne pour visualiser la quantité en stocks de chaques produits de l'usine
     */
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

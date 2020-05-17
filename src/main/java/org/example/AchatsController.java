package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.Chaine;
import org.app.Element;
import org.app.Usine;

/**
 * Classe permettant de controller tous les achats de produits et de les liers avec l'interface
 */
public class AchatsController implements Initializable {

    /**
     * Tableau pour visualiser toutes les informations des produits achetés pour les chaînes
     */
    public TableView<Element> tableAchats;

    /**
     * Colonne pour visualiser les noms des produits achetés pour les chaînes
     */
    public TableColumn<Element, String> nomElemAchats;

    /**
     * Colonne pour visualiser les prix des produits achetés pour les chaînes
     */
    public TableColumn<Element, Double> prixAchat;

    /**
     * Colonne pour visualiser la quantité des produits achetés pour les chaînes
     */
    public TableColumn<Element, Double> qteAcheter;

    /**
     * Colonne pour visualiser le cout total des produits achetés pour les chaînes
     */
    public TableColumn<Element, Double> coutTotal;

    /**
     * Permet de se déplacer vers l'interface Stocks
     * @throws IOException
     */
    @FXML
    private void switchToStocks() throws IOException {
        App.setRoot("Stocks");
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
     * Permet de se déplacer vers l'interface Personnels
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
     * Permet d'initialiser le contenu de la page Chaine.fxml
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Chaine c: Usine.getInstance().getChaines()) {
            c.calculIndicateurValeurSemaine(Usine.getInstance().getNbSemaines());
        }
        nomElemAchats.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prixAchat.setCellValueFactory(cellData -> cellData.getValue().prixAchatProperty().asObject());
        qteAcheter.setCellValueFactory(cellData -> cellData.getValue().qteAcheterProperty().asObject());
        coutTotal.setCellValueFactory(cellData -> cellData.getValue().coutTotal().asObject());
        observableList.addAll(Usine.getInstance().getElements());
        tableAchats.setItems(observableList);
    }

    ObservableList<Element> observableList = FXCollections.observableArrayList(
           // new Element("E001","Sucre",1.0,5.0,5.7,4.1,"g",1)
    );




}
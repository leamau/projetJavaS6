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
import org.app.Element;
import org.app.Usine;

public class AchatsController implements Initializable {

    public Usine usine = Usine.getInstance();

    public TableView<Element> tableAchats;
    public TableColumn<Element, String> nomElemAchats;
    public TableColumn<Element, Double> prixAchat;
    public TableColumn<Element, Double> qteAcheter;
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
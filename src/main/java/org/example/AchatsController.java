package org.example;

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

public class AchatsController implements Initializable {

    public TableView<Element> tableAchats;
    public TableColumn<Element, String> nomElemAchats;
    public TableColumn<Element, Double> prixAchat;
    public TableColumn<Element, Double> qteAcheter;
    public TableColumn<Element, Double> coutTotal;

    @FXML
    private void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    private void switchToChaines() throws IOException {
        App.setRoot("Chaines");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomElemAchats.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prixAchat.setCellValueFactory(cellData -> cellData.getValue().prixAchatProperty().asObject());
        qteAcheter.setCellValueFactory(cellData -> cellData.getValue().qteAcheterProperty().asObject());
        coutTotal.setCellValueFactory(cellData -> cellData.getValue().coutTotal().asObject());
        tableAchats.setItems(observableList);
    }

    ObservableList<Element> observableList = FXCollections.observableArrayList(
        new Element("Sucre",1.0,5.0)
    );
}
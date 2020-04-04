package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.app.Chaine;

public class ChaineController {
    public TableView<Chaine> tableChaine;
    public TableColumn<Chaine,String> codeChaine;
    public TableColumn<Chaine,String> nomChaine;
    public TableColumn<Chaine,Integer> niveauAct;
    public TableColumn<Chaine, HashMap> elementEntree;
    public TableColumn<Chaine, HashMap> elementSortie;

    @FXML
    public void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    public void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeChaine.setCellValueFactory(cellData -> cellData.getValue().codeCProperty());
        nomChaine.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        niveauAct.setCellValueFactory(cellData -> cellData.getValue().niveauActivationProperty().asObject());
        elementEntree.setCellValueFactory(cellData -> cellData.getValue().elementsEntreeProperty());
        elementSortie.setCellValueFactory(cellData -> cellData.getValue().elementsSortieProperty());
        tableChaine.setItems(observableList);
    }

    ObservableList<Chaine> observableList = FXCollections.observableArrayList(
            new Chaine("E001","sac", 0,0, 0)
    );

}
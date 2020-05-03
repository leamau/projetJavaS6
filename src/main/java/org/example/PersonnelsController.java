package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.app.Personnel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonnelsController implements Initializable {
    public TableView<Personnel> tablePersonnels;
    public TableColumn<Personnel,String> idPersonnel;
    public TableColumn<Personnel,String> nomPersonnel;
    public TableColumn<Personnel,String> prenomPersonnel;
    public TableColumn<Personnel,Boolean> disponibilite;
    public TableColumn<Personnel,Double> nbHeureDispo;
    public TableColumn<Personnel,Double> nbHeureAssignes;

    @FXML
    private void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    @FXML
    private void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    private void switchToChaines() throws IOException {
        App.setRoot("Chaines");
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPersonnel.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nomPersonnel.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomPersonnel.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        disponibilite.setCellValueFactory(cellData -> cellData.getValue().disponibiliteProperty());
        nbHeureDispo.setCellValueFactory(cellData -> cellData.getValue().nbHeuresDispoProperty().asObject());
        nbHeureAssignes.setCellValueFactory(cellData -> cellData.getValue().nbHeuresAssignesProperty().asObject());
        tablePersonnels.setItems(observableList);
    }

    ObservableList<Personnel> observableList = FXCollections.observableArrayList(
            new Personnel("lacaud", "aurelien", 8.0, 6.0)
    );
}

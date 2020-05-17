package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.app.Chaine;
import org.app.Personnel;
import org.app.Usine;

import java.io.FileNotFoundException;
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

    /**
     * Permet d'appeler cette méthode sur un bouton export et d'exporter les personnels en fichier txt
     * @throws IOException
     */
    @FXML
    public void exportPersonnel() throws IOException {
        Usine.getInstance().exportPersonnelTxt();
    }

    /**
     * Permet de se déplacer vers l'interface Achats
     * @throws IOException
     */
    @FXML
    private void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

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
     * Permet de se déplacer vers le menu de l'application
     * @throws IOException
     */
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }

    /**
     * Permet d'initialiser le contenu de la page Personnel.fxml
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Chaine c: Usine.getInstance().getChaines()) {
            c.calculIndicateurPersonnelSemaine(Usine.getInstance().getNbSemaines());
        }
        idPersonnel.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nomPersonnel.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomPersonnel.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        disponibilite.setCellValueFactory(cellData -> cellData.getValue().disponibiliteProperty());
        nbHeureDispo.setCellValueFactory(cellData -> cellData.getValue().nbHeuresDispoProperty().asObject());
        nbHeureAssignes.setCellValueFactory(cellData -> cellData.getValue().nbHeuresAssignesProperty().asObject());
        observableList.addAll(Usine.getInstance().getPersonnelsNonQualifies());
        observableList.addAll(Usine.getInstance().getPersonnelsQualifies());
        tablePersonnels.setItems(observableList);
    }

    ObservableList<Personnel> observableList = FXCollections.observableArrayList(
           // new Personnel("lacaud", "aurelien", 8.0, 6.0)
    );
}

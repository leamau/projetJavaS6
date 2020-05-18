package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.app.Chaine;
import org.app.Personnel;
import org.app.Usine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Classe permettant de controller tous les personnels de l'usine et de les liers avec l'interface
 */
public class PersonnelsController implements Initializable {

    /**
     * Tableau pour visualiser toutes les informations du personnels de l'usine
     */
    public TableView<Personnel> tablePersonnels;

    /**
     * Colonne pour visualiser l'identifiant de tout le personnels de l'usine
     */
    public TableColumn<Personnel,String> idPersonnel;

    /**
     * Colonne pour visualiser le nom de tous le personnels de l'usine
     */
    public TableColumn<Personnel,String> nomPersonnel;

    /**
     * Colonne pour visualiser le prénom de tous le personnels de l'usine
     */
    public TableColumn<Personnel,String> prenomPersonnel;

    /**
     * Colonne pour visualiser la disponibilité de tous le personnels de l'usine
     */
    public TableColumn<Personnel,Boolean> disponibilite;

    /**
     * Colonne pour visualiser le nombre d'heures disponible pour chacun des personnels de l'usine
     */
    public TableColumn<Personnel,Double> nbHeureDispo;

    /**
     * Colonne pour visualiser le nombre d'heures assignés pour chacun des personnels de l'usine
     */
    public TableColumn<Personnel,Double> nbHeureAssignes;

    /**
     * L'objet pour choisir où enregistrer l'export personnel.
     */
    public FileChooser fileChooser = new FileChooser();

    /**
     * Le bouton pour exporter les données du personnel.
     */
    @FXML public Button exportbuttonpersonnel;

    /**
     * Permet d'appeler cette méthode sur un bouton export et d'exporter les personnels en fichier txt
     * @throws IOException
     */
    @FXML
    public void exportPersonnel() throws IOException {
        Usine.getInstance().exportPersonnelTxt();
    }

    /**
     * Permet d'exporter les informations sur le personnel avec un FileChooser.
     */
    @FXML
    private void exportPersonnelV2(ActionEvent event) {

        // Ajouter la date au nom du fichier.
        DateFormat formatCourt = new SimpleDateFormat("yyyyMMdd-HHmm");
        DateFormat formatStandard = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();

        // Gestion détaillée du nom du fichier.
        String filePath = ""; // src/main/resources/org.export/ ?
        String fileName = "personnel";
        String fileDate = formatCourt.format(d);
        String fileExtension = ".txt";
        final String completeFileName = filePath + fileName + "-" + fileDate + fileExtension;

        fileChooser.setTitle("Export Personnel");
        fileChooser.setInitialFileName(completeFileName);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".txt", ".txt"));

        Window stage = exportbuttonpersonnel.getScene().getWindow();
        try {
            File f = new File(completeFileName);
            f = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(f.getParentFile());
            Usine.getInstance().exportPersonnelTxtV2(f);
        } catch (Exception e) {

        }
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

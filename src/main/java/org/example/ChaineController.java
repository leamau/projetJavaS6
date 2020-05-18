package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import org.app.Chaine;
import org.app.Element;
import org.app.Usine;

/**
 * Classe permettant de controller toutes les chaînes de l'usine et de les liers avec l'interface
 */
public class ChaineController  implements Initializable {

    /**
     * ComboBox permettant de choisir le nombre de semaine à utiliser pour calculer la production des chaînes
     */
    @FXML public ComboBox<String> choixSemainesListe;

    /**
     * Tableau pour visualiser toutes les informations des chaînes
     */
    @FXML public TableView<Chaine> tableChaines;

    /**
     * Colonne pour visualiser le code de chaques chaînes
     */
    @FXML public TableColumn<Chaine, String> codeC;

    /**
     * Colonne pour visualiser le nom de chaques chaînes
     */
    @FXML public TableColumn<Chaine, String> nom;

    /**
     * Colonne pour visualiser et éditer le niveau d'activation des chaines
     */
    @FXML public TableColumn<Chaine, Integer> niveauActivation;

    /**
     * Colonne pour visualiser les éléments en entrée de chaques chaînes
     */
    @FXML public TableColumn<Chaine, String> elementsEntree;

    /**
     * Colonne pour visualiser les éléments en sorties de chaques chaînes
     */
    @FXML public TableColumn<Chaine, String> elementsSortie;

    /**
     * Colonne pour visualiser l'état des chaînes
     */
    @FXML public TableColumn<Chaine, Boolean> etatChaine;

    /**
     * Colonne pour visualiser l'indicateur de valeur des chaines
     */
    @FXML public TableColumn<Chaine, Double> indicateurValeur;

    /**
     * Label pour afficher le pourcentage de commandes satisfaites
     */
    @FXML public Label indicateurCommandes;
     * L'objet pour choisir où enregistrer l'export personnel.
     */
    public FileChooser fileChooser = new FileChooser();

    /**
     * Le bouton pour exporter les données du personnel.
     */
    @FXML public Button exportbuttonsimulation;

    /**
     * Permet d'exporter les informations sur le personnel avec un FileChooser.
     */
    @FXML
    private void exportChaineV2(ActionEvent event) {

        // Ajouter la date au nom du fichier.
        DateFormat formatCourt = new SimpleDateFormat("yyyyMMdd-HHmm");
        DateFormat formatStandard = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();

        // Gestion détaillée du nom du fichier.
        String filePath = "";
        String fileName = "simulation";
        String fileDate = formatCourt.format(d);
        String fileExtension = ".txt";
        final String completeFileName = filePath + fileName + "-" + fileDate + fileExtension;

        fileChooser.setTitle("Export Simulation");
        fileChooser.setInitialFileName(completeFileName);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".txt", ".txt"));

        Window stage = exportbuttonsimulation.getScene().getWindow();
        try {
            File f = new File(completeFileName);
            f = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(f.getParentFile());
            Usine.getInstance().exportChaineTxtV2(f);
        } catch (Exception e) {

        }
    }

    /**
     * Permet à l'utilisateur d'utiliser un double clic sur un case pour modifier et valider son contenu
     * @param edditedCell
     */
    public void doubleClicColonneEvent(TableColumn.CellEditEvent edditedCell){
        Chaine modifNiveauAct = tableChaines.getSelectionModel().getSelectedItem();
        modifNiveauAct.setNiveauActivation((int) edditedCell.getNewValue());
    }

    /**
     * Permet d'appeler cette méthode sur un bouton export et d'exporter les chaines en fichier txt
     * @throws IOException
     */
    @FXML
    public void exportChaine() throws IOException {
        Usine.getInstance().exportChainesTxt();
    }

    /**
     * Permet d'appeler cette méthode sur un bouton export et d'exporter les chaines en fichier txt
     * @throws IOException
     */
    @FXML
    public void resetSimulation() throws IOException {
        Usine.getInstance().resetUsine();
        niveauActivation.setCellValueFactory(cellData -> cellData.getValue().niveauActivationProperty().asObject());
        choixSemainesListe.getSelectionModel().select(Usine.getInstance().getNbSemaines()-1);
        this.changeLabelText();
    }

    /**
     * Permet de se déplacer vers l'interface Stocks
     * @throws IOException
     */
    @FXML
    public void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    /**
     * Permet de se déplacer vers l'interface Achats
     * @throws IOException
     */
    @FXML
    public void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    /**
     * Permet de se déplacer vers l'interface Personnel
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
     * permet de changer la valeur du label d'indicateur de commandes
     */
    private void changeLabelText(){
        indicateurCommandes.setText(String.valueOf(Math.round(Usine.getInstance().calculIndicateurCommande()))+" %");
    }

    /**
     * Permet d'initialiser le contenu de la page Chaine.fxml
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeC.setCellValueFactory(cellData -> cellData.getValue().codeCProperty());
        nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        elementsEntree.setCellValueFactory(cellData -> cellData.getValue().toStringElementsEnEntreeProperty());
        elementsSortie.setCellValueFactory(cellData -> cellData.getValue().toStringElementsEnSortieProperty());
        niveauActivation.setCellValueFactory(cellData -> cellData.getValue().niveauActivationProperty().asObject());
        //indicateurValeur.setCellValueFactory(cellData -> cellData.getValue().calculIndicateurValeurSemaineProperty(Usine.getInstance().getNbSemaines()).asObject());
        indicateurCommandes.setText(String.valueOf(Math.round(Usine.getInstance().calculIndicateurCommande()))+" %");
        choixSemainesListe.getSelectionModel().select(Usine.getInstance().getNbSemaines()-1);
        //gestion de l'état de la chaine
        //etatChaine.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().chaineIsOk(Usine.getInstance().getNbSemaines())));

        //System.out.println(choixSemainesListe.getValue());
        choixSemainesListe.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
                //id récupérer mais non affiché dans l'interface
                System.out.println(newValue.substring(0,1));
                Usine.getInstance().setNbSemaines(Integer.parseInt(newValue.substring(0,1)));
                //gestion de l'état de la chaine
                //etatChaine.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().chaineIsOk(Integer.parseInt(newValue.substring(0,1)))));
                changeLabelText();
                //valeur booléenne récupérée mais non affichée dans l'inteface
            }
        });

        niveauActivation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Chaine, Integer>>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                tableChaines.getSelectionModel().getSelectedItem().setNiveauActivation( Integer.parseInt(event.getNewValue().toString()));
                niveauActivation.setCellValueFactory(cellData -> cellData.getValue().niveauActivationProperty().asObject());
                System.out.println(tableChaines.getSelectionModel().getSelectedItem().getNiveauActivation());
                for (Chaine c: Usine.getInstance().getChaines()) {
                    c.calculIndicateurPersonnelSemaine(Usine.getInstance().getNbSemaines());
                    c.calculIndicateurValeurSemaine(Usine.getInstance().getNbSemaines());
                }
                changeLabelText();
            }
        });

        // mapElementE.put(new Element("Etest","nom","g"),3);
        // mapElementS.put(new Element("Etest2","nom2","g"),3);

        observableList.addAll(Usine.getInstance().getChaines());

        tableChaines.setItems(observableList);

        //Mise à jour de la table pour prendre en compte les modifications sur la colonne Niveau D'activation
        tableChaines.setEditable(true);
        niveauActivation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    ObservableList<Chaine> observableList = FXCollections.observableArrayList(
    );
}
package org.app;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Chaine {
    /**
     * atribut statique permettant l'incrémentation du code de la chaine
     */
    static int lastValueId = 0;
    /**
     * code de la chaine
     */
    private SimpleStringProperty codeC;
    /**
     * nom de la chaine
     */
    private SimpleStringProperty nom;
    /**
     * niveau d'activation de la chaine
     */
    private SimpleIntegerProperty niveauActivation;
    /**
     * éléments en entrée de la chaine
     * <qte,element>
     */
    private SimpleMapProperty<Element,Double> elementsEntree;
    /**
     * éléments en sortie de la chaine
     * <qte,element>
     */
    private SimpleMapProperty<Element,Double> elementsSortie;
    /**
     * nombre de personnel non qualifié nécessaire au déroulement de la chaine
     *
     */
    private SimpleIntegerProperty nbPersonnelNQNecessaire;
    /**
     * nombre de personnel qualifié nécessaire au déroulement de la chaine
     *
     */
    private SimpleIntegerProperty nbPersonnelQNecessaire;
    /**
     * personnel qualifié convoqué sur la chaine
     * <PersonnelQualifié,nbHeuresSurLaChaine>
     */
    private SimpleMapProperty<PersonnelQualifie,Double> PersonnelsQualifiesConvoque;

    /**
     * personnel non qualifié convoqué sur la chaine
     * <PersonnelNonQualifié,nbHeuresSurLaChaine>
     */
    private SimpleMapProperty<PersonnelNonQualifie,Double> PersonnelsNonQualifiesConvoque;

    /**
     * constructeur de la classe avec le code incrémenté automatiquement
     * @param nom : nom de la chaine
     * @param activation : niveau d'activation de la chaine
     * @throws IllegalArgumentException
     */
    public Chaine(final String nom,final int activation) throws IllegalArgumentException{
        this.nom = new SimpleStringProperty(nom);
        /*incrémentation du dernier id pour le code de la chaine*/
        lastValueId ++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.codeC = new SimpleStringProperty("C00"+lastValueId);
                break;
            case 2 :
                this.codeC = new SimpleStringProperty("C0"+lastValueId);
                break;
            case 3 :
                this.codeC = new SimpleStringProperty("C" + lastValueId);
                break;
        }
        this.elementsEntree = new SimpleMapProperty<Element,Double>();
        this.elementsSortie = new SimpleMapProperty<Element,Double>();
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>();
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>();
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
        if(activation >= 0){
            this.niveauActivation = new SimpleIntegerProperty(activation);
        }else{
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
    }

    /**
     * constructeur de la classe avec le code incrémenté automatiquement et aucun niveau d'activation
     * @param nom : nom de la chaine
     * @throws IllegalArgumentException
     */
    public Chaine(final String nom) throws IllegalArgumentException{
        this.nom = new SimpleStringProperty(nom);
        /*incrémentation du dernier id pour le code de la chaine*/
        lastValueId ++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.codeC = new SimpleStringProperty("C00"+lastValueId);
                break;
            case 2 :
                this.codeC = new SimpleStringProperty("C0"+lastValueId);
                break;
            case 3 :
                this.codeC = new SimpleStringProperty("C" + lastValueId);
                break;
        }
        this.elementsEntree = new SimpleMapProperty<Element,Double>();
        this.elementsSortie = new SimpleMapProperty<Element,Double>();
        this.niveauActivation = new SimpleIntegerProperty(0);
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>();
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>();
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
    }

    /**
     * Constructeur de Chaine sans génération automatique du code.
     * @param code le code de la chaine.
     * @param nom le nom de la chaine.
     * @param temps le niveau d'activation.
     * @param entrees l'ensemble des éléments en entrée et leur quantité.
     * @param sorties l'ensemble des éléments en sortie et leur quantité.
     * @throws IllegalArgumentException
     */

    public Chaine(final String code, final String nom, final int temps, final SimpleMapProperty<Element,Double> entrees, final SimpleMapProperty<Element,Double> sorties)  throws IllegalArgumentException {
        this.codeC = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.elementsEntree = new SimpleMapProperty<>(entrees);
        this.elementsSortie = new SimpleMapProperty<>(sorties);

        if(temps >= 0) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>();
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>();
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
    }

    public Chaine(final String code, final String nom, final int temps, final SimpleMapProperty<Element,Double> entrees, final SimpleMapProperty<Element,Double> sorties, final int nbNQ,final int nbQ)  throws IllegalArgumentException {
        this.codeC = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.elementsEntree = new SimpleMapProperty<>(entrees);
        this.elementsSortie = new SimpleMapProperty<>(sorties);

        if(temps >= 0) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>();
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>();
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(nbNQ);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(nbQ);
    }

    /**
     *
     * @return
     * @throws IllegalArgumentException
     */
    public double calculIndicateurValeur() throws IllegalArgumentException{
        double valeurVente = 0;
        double valeurAchat = 0;
        /*pour chaque élément en entrée*/
        for(Map.Entry<Element, Double> entree : this.elementsEntree.entrySet()) {
            Double qteElementEntree = entree.getValue();
            Element elementEntree = entree.getKey();
            double stock = elementEntree.getQuantiteStock();
            /*si sa quantitée demandée est supèrieure a sa quantitée en stock on affiche une erreur*/
            if(stock - qteElementEntree*this.getNiveauActivation() < 0){
                throw new IllegalArgumentException("Il n'y a pas assez d'élément dans le stock pour garantir l'exécution de la chaine de production");
            }else{
                valeurAchat += elementEntree.getPrixAchat();
                for(Map.Entry<Element, Double> sortie : this.elementsSortie.entrySet()) {
                    Double qteElementSortie = entree.getValue();
                    Element elementSortie = entree.getKey();
                    valeurVente += elementSortie.getPrixVente();
                    elementSortie.setQuantiteStock(elementSortie.getQuantiteStock() + qteElementEntree*this.getNiveauActivation());
                }
            }
        }
        return valeurVente - valeurAchat;

    }

    //TODO: gérer le calcul de l'indicateur de commande (dans une V2 car pour l'instant je n'en vois pas l'utilité)
    public double calculIndicateurCommande() throws IllegalArgumentException{
        //par rapport aux demandes combien de commandes sont réalisés
        //calculer le pourcentage de commandes satisfaites par rapport à la demande
        //1 pourcentage Demande
        //2 pourcentage accomplis
        //3 res = 1-2
        return 0;
    }

    /**
     * permet de savoir si il y a assez de personnel disponible pour réaliser la chaine
     * @return vrai si il y  a assez de personnel disponible et faux dans le cas inverse
     */
    public boolean calculIndicateurPersonnel() throws FileNotFoundException {

        //on compare le nombre de personnel necessaire au nombre disponible
        boolean chaineOk = false;
        int nbPersonnelsQDispo = 0;
        int nbPersonnelsNQDispo = 0;
        for (Personnel perso:Usine.getInstance().getPersonnels()) {
            //recherche des personnels Disponibles
            if(perso.etreDisponible() && perso.getClass().toString().equals("org.app.PersonnelQualifie")){
                nbPersonnelsQDispo ++;
            }else if(perso.etreDisponible() && perso.getClass().toString().equals("org.app.PersonnelNonQualifie")){
                nbPersonnelsNQDispo ++;
            }
        }
        //si le qualif necessaire est > au disponible
        if(this.nbPersonnelQNecessaire.get() > nbPersonnelsQDispo){
            //chaine non ok
            chaineOk = false;
        }else{//sinon
            //si le non quali necessaire est supèrieur au disponible
            if(this.nbPersonnelNQNecessaire.get() > nbPersonnelsNQDispo){
                //si le non quali Necessaire est > quali Disponible
                if(this.nbPersonnelNQNecessaire.get() > nbPersonnelsQDispo){
                    //chaine impossible
                    chaineOk = false;
                }else{
                    chaineOk = true;
                }
            }else{
                chaineOk = true;
            }
        }

        return chaineOk;
    }

    public String toStringV2() {
        String str = "Chaine {\n" +
                    "\tcodeC = " + codeC.getValue() +
                    "\tnom = " + nom.getValue() +
                    "\tniveauActivation = " + niveauActivation.getValue() +
                    "\nentrées = ";
        for (Map.Entry<Element,Double> e : elementsEntree.get().entrySet()) {
            str += e.getKey().getCodeE(); // Possible d'utiliser .getNom() ?
            str += "\t";
        }
        str += "\nsorties = ";
        for (Map.Entry<Element,Double> e : elementsSortie.get().entrySet()) {
            str += e.getKey().getCodeE();
            str += "\t";
        }
        return str;
    }

    @Override
    public String toString() {
        return "Chaine {\n" +
                "\tcodeC = " + codeC.getValue() +
                "\tnom = " + nom.getValue() +
                "\tniveauActivation = " + niveauActivation.getValue() +
                "\telementsEntree = " + elementsEntree.getName() +
                "\telementsSortie = " + elementsSortie.getName() +
                "\n}";
    }

    /**
     * transforme en string la liste des éléments en entrée
     * @return
     */
    public String ToStringElementsEnEntree(){
        String valeurToString = "";
            for(Map.Entry<Element, Double> entree : this.elementsEntree.entrySet()) {
                valeurToString += "\n"+entree.getValue().toString() + " * "+entree.getKey();
            }
        return  valeurToString;
    }

    /**
     * transforme en string la liste des éléments en sortie
     * @return
     */
    public String ToStringElementsEnSortie(){
        String valeurToString = "";
        for(Map.Entry<Element, Double> sortie : this.elementsSortie.entrySet()) {
            valeurToString += "\n"+sortie.getValue().toString() + " * "+sortie.getKey();
        }
        return  valeurToString;
    }

    public SimpleStringProperty toStringElementsEnSortieProperty(){
        return new SimpleStringProperty(ToStringElementsEnSortie());
    }

    public SimpleStringProperty toStringElementsEnEntreeProperty(){
        return new SimpleStringProperty(ToStringElementsEnEntree());
    }

    public ObservableList<Element> getElementsEntreeProperty(){
        ObservableList<Element> elements = FXCollections.observableArrayList();
        for(Map.Entry<Element,Double> entree : this.elementsEntree.entrySet()) {
            elements.add(entree.getKey());
        }
        return elements;
    }

    public ObservableList<Element> getElementsSortieProperty() {
        ObservableList<Element> elements = FXCollections.observableArrayList();
        for (Map.Entry<Element, Double> entree : this.elementsSortie.entrySet()) {
            elements.add(entree.getKey());
        }
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if((o instanceof Chaine) && (((Chaine) o).getCodeC().equals(this.codeC)))
            res = true;
        return res;
    }

    //getters & setters

    public String getCodeC() {
        return codeC.get();
    }

    public SimpleStringProperty codeCProperty() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC.set(codeC);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getNiveauActivation() {
        return niveauActivation.get();
    }

    public SimpleIntegerProperty niveauActivationProperty() {
        return niveauActivation;
    }

    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation.set(niveauActivation);
    }

    public ObservableMap<Element, Double> getElementsEntree() {
        return elementsEntree.get();
    }

    public SimpleMapProperty<Element, Double> elementsEntreeProperty() {
        return elementsEntree;
    }

    public void setElementsEntree(ObservableMap<Element, Double> elementsEntree) {
        this.elementsEntree.set(elementsEntree);
    }

    public ObservableMap<Element, Double> getElementsSortie() {
        return elementsSortie.get();
    }

    public SimpleMapProperty<Element, Double> elementsSortieProperty() {

        return elementsSortie;
    }

    public void setElementsSortie(ObservableMap<Element, Double> elementsSortie) {
        this.elementsSortie.set(elementsSortie);
    }


}

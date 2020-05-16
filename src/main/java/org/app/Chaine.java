package org.app;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chaine {
    //TODO: pour l'ajout d'un employé a la chaine on incrémente son nombre d'heures
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
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
        if(activation >= 0 && activation <=6){
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
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
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

        if(temps >= 0 && temps <=6) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
    }

    public Chaine(final String code, final String nom, final int temps, final SimpleMapProperty<Element,Double> entrees, final SimpleMapProperty<Element,Double> sorties, final int nbNQ,final int nbQ)  throws IllegalArgumentException {
        this.codeC = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.elementsEntree = new SimpleMapProperty<>(entrees);
        this.elementsSortie = new SimpleMapProperty<>(sorties);
        if(temps >= 0 && temps <=6) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(nbNQ);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(nbQ);
    }

    /**
     * vérifie la faisabilité de la chaine en fonction des personnels et des stocks
     * @return un booléen indiquant si la chaine est faisable ou non
     */
    public boolean chaineIsOk(int nbSemaines) {
        //si il y a un indicateur de valeur poditif et assez de personnel disponible
        return (this.calculIndicateurValeur()>0 && this.calculIndicateurPersonnelSemaine(nbSemaines));
    }

    public SimpleBooleanProperty chaineIsOkProperty(int nbSemaines) {
        //si il y a un indicateur de valeur poditif et assez de personnel disponible
        return new SimpleBooleanProperty((this.calculIndicateurValeur()>0 && this.calculIndicateurPersonnelSemaine(nbSemaines)));
    }

    /**
     * Cet indicateur donne une estimation financière de la rentabilité de la production envisagée
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
                valeurVente = -1;
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

    public double calculIndicateurValeurSemaine(int nbSemaines){
        //on considère des semaines de 5jours
        int nbJours = 5;
        double valeurVente = 0;
        double valeurAchat = 0;
        /*pour chaque élément en entrée*/
        for(Map.Entry<Element, Double> entree : this.elementsEntree.entrySet()) {
            Double qteElementEntree = entree.getValue();
            Element elementEntree = entree.getKey();
            double stock = elementEntree.getQuantiteStock();
            /*si sa quantitée demandée est supèrieure a sa quantitée en stock on affiche une erreur*/
            if(stock - qteElementEntree*this.getNiveauActivation() < 0){
                valeurVente = -1;
                throw new IllegalArgumentException("Il n'y a pas assez d'élément dans le stock pour garantir l'exécution de la chaine de production");
            }else{
                valeurAchat += elementEntree.getPrixAchat();
                for(Map.Entry<Element, Double> sortie : this.elementsSortie.entrySet()) {
                    Double qteElementSortie = entree.getValue();
                    Element elementSortie = entree.getKey();
                    valeurVente += elementSortie.getPrixVente();
                    elementSortie.setQuantiteStock(elementSortie.getQuantiteStock() + qteElementEntree*(this.getNiveauActivation()*nbJours*nbSemaines));
                }
            }
        }
        return valeurVente - valeurAchat;
    }
    /**
     * permet de savoir si il y a assez de personnel disponible pour réaliser la chaine
     * @return vrai si il y  a assez de personnel disponible et faux dans le cas inverse
     */
    public boolean calculIndicateurPersonnelSemaine(int nbSemaines) {

        //on compare le nombre de personnel necessaire au nombre disponible
        boolean chaineOk = false;
        int nbPersonnelsQDispo = 0;
        ArrayList<PersonnelNonQualifie> persoNQDispo = new ArrayList<PersonnelNonQualifie>() ;
        ArrayList<PersonnelQualifie> persoQDispo = new ArrayList<PersonnelQualifie>() ;
        int nbPersonnelsNQDispo = 0;

        for (PersonnelQualifie perso:Usine.getInstance().getPersonnelsQualifies()) {
            if(perso.etreDisponible()){
                //recherche des personnels Disponibles
                nbPersonnelsQDispo ++;
            }
        }
        for (PersonnelNonQualifie perso:Usine.getInstance().getPersonnelsNonQualifies()) {
            if(perso.etreDisponible()){
                //recherche des personnels Disponibles
                nbPersonnelsNQDispo ++;
            }
        }

        //si le qualif necessaire est > au disponible
        if(this.nbPersonnelQNecessaire.get() > nbPersonnelsQDispo){
            //chaine non ok
            chaineOk = false;
        }else{//sinon
            if(this.nbPersonnelQNecessaire.get() < nbPersonnelsQDispo){
                int cptTrouve = 0;
                int i = 0;
                //ajouter le nombre necessaire de personnel qualifié
                while (cptTrouve < this.nbPersonnelQNecessaire.get() && i < Usine.getInstance().getPersonnelsQualifies().size()){
                    if (Usine.getInstance().getPersonnelsQualifies().get(i).etreDisponible()){
                        //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                        if(!this.PersonnelsQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsQualifies().get(i))){
                            //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                            chaineOk = this.assignerPersonnelQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsQualifies().get(i));
                            //si le personnel n'est plus disponible, l'enlever du compteur
                            if(!Usine.getInstance().getPersonnelsQualifies().get(i).etreDisponible()){
                                nbPersonnelsQDispo--;
                                cptTrouve++;
                            }
                        }
                    }
                    i++;
                }
             //TODO: finir de revoir la méthode
            }

            //si le non quali necessaire est supèrieur au disponible
            if(this.nbPersonnelNQNecessaire.get() > nbPersonnelsNQDispo){
                //si le non quali Necessaire est > quali Disponible
                if(this.nbPersonnelNQNecessaire.get() > nbPersonnelsQDispo){
                    //chaine impossible
                    chaineOk = false;
                }else{
                    chaineOk = true;
                    /*for (int i = 0; i < nbPersonnelsNQDispo ;i++){
                        this.assignerPersonnelNQSemaine(niveauActivation.get(),nbSemaines,persoNQDispo.get(0));
                        persoNQDispo.remove(0);
                        nbPersonnelsNQDispo--;
                    }*/
                    int cptTrouve = 0;
                    int i = 0;
                    //ajouter le nombre necessaire de personnel non qualifié
                    while (cptTrouve < nbPersonnelsNQDispo && i < Usine.getInstance().getPersonnelsNonQualifies().size()){
                        if (Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                            //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                            if(!this.PersonnelsNonQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsNonQualifies().get(i))){
                                //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                                chaineOk = this.assignerPersonnelNQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsNonQualifies().get(i));
                                //si le personnel n'est plus disponible, l'enlever du compteur
                                if(!Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                                    cptTrouve++;
                                    nbPersonnelsNQDispo--;
                                }
                            }
                        }
                        i++;
                    }

                    cptTrouve = 0;
                    i = 0;
                    //ajouter le nombre necessaire de personnel qualifié
                    while (cptTrouve < nbPersonnelsQDispo && i < Usine.getInstance().getPersonnelsQualifies().size()){
                        if (Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                            //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                            if(!this.PersonnelsQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsQualifies().get(i))){
                                //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                                chaineOk = this.assignerPersonnelQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsQualifies().get(i));
                                //si le personnel n'est plus disponible, l'enlever du compteur
                                if(!Usine.getInstance().getPersonnelsQualifies().get(i).etreDisponible()){
                                    cptTrouve++;
                                    nbPersonnelsQDispo--;
                                }
                            }
                        }
                        i++;
                    }
                    /*
                    //ajouter des personnels qualifié a la chaine
                    for (int i = 0; i < nbPersonnelsQDispo;i++){
                        this.assignerPersonnelQSemaine(niveauActivation.get(),nbSemaines,persoQDispo.get(0));
                        persoQDispo.remove(0);
                        nbPersonnelsQDispo--;
                    }*/
                }
            }else{
                chaineOk = true;
                //ajouter la bonne quandtité de personnels non qualifié à la chaine
                int cptTrouve = 0;
                int i = 0;
                //ajouter le nombre necessaire de personnel non qualifié
                while (cptTrouve < this.nbPersonnelNQNecessaire.get() && i < Usine.getInstance().getPersonnelsNonQualifies().size()){
                    if (Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                        //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                        if(!this.PersonnelsNonQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsNonQualifies().get(i))){
                            //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                            chaineOk = this.assignerPersonnelNQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsNonQualifies().get(i));
                            //si le personnel n'est plus disponible, l'enlever du compteur
                            if(!Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                                nbPersonnelsNQDispo--;
                                cptTrouve++;
                            }
                        }
                    }
                    i++;
                }
            }
        }

        return chaineOk;
    }


    public boolean assignerPersonnelNQSemaine(double nbHeures,int nbSemaines,PersonnelNonQualifie personnel){
        boolean isOk = false;
        System.out.println(personnel.getNom()+ "1 : dispo = "+personnel.getNbHeuresDispo()+" -> assigne = "+personnel.getNbHeuresAssignes());
        this.PersonnelsNonQualifiesConvoque.get().put(personnel,nbHeures*nbSemaines);
        if(personnel.getNbHeuresDispo() - nbHeures*nbSemaines > 0){
            personnel.setNbHeuresAssignes(personnel.getNbHeuresAssignes() + nbHeures*nbSemaines);
            personnel.setNbHeuresDispo(personnel.getNbHeuresDispo() - nbHeures*nbSemaines);
            isOk = true;
        }
        System.out.println(personnel.getNom()+ "2 : dispo = "+personnel.getNbHeuresDispo()+" -> assigne = "+personnel.getNbHeuresAssignes());
        return isOk;
    }

    public boolean assignerPersonnelQSemaine(double nbHeures,int nbSemaines,PersonnelQualifie personnel){
        boolean isOk = false;
        System.out.println(personnel.getNom()+ "1 : dispo = "+personnel.getNbHeuresDispo()+" -> assigne = "+personnel.getNbHeuresAssignes());
        this.PersonnelsQualifiesConvoque.put(personnel,nbHeures*nbSemaines);
        if(personnel.getNbHeuresDispo() - nbHeures*nbSemaines > 0){
            personnel.setNbHeuresAssignes(personnel.getNbHeuresAssignes() + nbHeures*nbSemaines);
            personnel.setNbHeuresDispo(personnel.getNbHeuresDispo() - nbHeures*nbSemaines);
            isOk = true;
        }
        System.out.println(personnel.getNom()+ "1 : dispo = "+personnel.getNbHeuresDispo()+" -> assigne = "+personnel.getNbHeuresAssignes());
        return isOk;
    }

    public boolean assignerPersonnelNQ(double nbHeures,PersonnelNonQualifie personnel){
        boolean isOk = false;
        this.PersonnelsNonQualifiesConvoque.put(personnel,nbHeures);
        if(personnel.getNbHeuresDispo() - nbHeures > 0){
            personnel.setNbHeuresAssignes(personnel.getNbHeuresAssignes() + nbHeures);
            personnel.setNbHeuresDispo(personnel.getNbHeuresDispo() - nbHeures);
            isOk = true;
        }
        return isOk;
    }

    public boolean assignerPersonnelQ(double nbHeures,PersonnelQualifie personnel){
        boolean isOk = false;
        this.PersonnelsQualifiesConvoque.put(personnel,nbHeures);
        if(personnel.getNbHeuresDispo() - nbHeures > 0){
            personnel.setNbHeuresAssignes(personnel.getNbHeuresAssignes() + nbHeures);
            personnel.setNbHeuresDispo(personnel.getNbHeuresDispo() - nbHeures);
            isOk = true;
        }
        return isOk;
    }

    @Override
    public String toString() {
        return "Chaine {\n" +
                "\tcodeC = " + codeC +
                "\tnom = " + nom +
                "\tniveauActivation = " + niveauActivation +
                "\telementsEntree = " + ToStringElementsEnEntree() +
                "\telementsSortie = " + ToStringElementsEnSortie() +
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
        this.niveauActivation = new SimpleIntegerProperty(niveauActivation);
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

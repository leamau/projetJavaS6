package org.app;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;

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
    private SimpleMapProperty<Element,Integer> elementsEntree;
    /**
     * éléments en sortie de la chaine
     * <qte,element>
     */
    private SimpleMapProperty<Element,Integer> elementsSortie;

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
        this.elementsEntree = new SimpleMapProperty<Element,Integer>();
        this.elementsSortie = new SimpleMapProperty<Element,Integer>();
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
        this.elementsEntree = new SimpleMapProperty<Element,Integer>();
        this.elementsSortie = new SimpleMapProperty<Element,Integer>();
        this.niveauActivation = new SimpleIntegerProperty(0);
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
    public Chaine(final String code, final String nom, final int temps, final SimpleMapProperty<Element,Integer> entrees, final SimpleMapProperty<Element,Integer> sorties)  throws IllegalArgumentException {
        this.codeC = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.elementsEntree = new SimpleMapProperty<>(entrees);
        this.elementsSortie = new SimpleMapProperty<>(sorties);
        if(temps >= 0) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
    }

    public double calculIndicateurValeur() throws IllegalArgumentException{
        double valeurVente = 0;
        double valeurAchat = 0;
        /*pour chaque élément en entrée*/
        for(Map.Entry<Element,Integer> entree : this.elementsEntree.entrySet()) {
            Integer qteElementEntree = entree.getValue();
            Element elementEntree = entree.getKey();
            double stock = elementEntree.getQuantiteStock();
            /*si sa quantitée demandée est supèrieure a sa quantitée en stock on affiche une erreur*/
            if(stock - qteElementEntree*this.getNiveauActivation() < 0){
                throw new IllegalArgumentException("Il n'y a pas assez d'élément dans le stock pour garantir l'exécution de la chaine de production");
            }else{
                valeurAchat += elementEntree.getPrixAchat();
                for(Map.Entry<Element,Integer> sortie : this.elementsSortie.entrySet()) {
                    Integer qteElementSortie = entree.getValue();
                    Element elementSortie = entree.getKey();
                    valeurVente += elementSortie.getPrixVente();
                    elementSortie.setQuantiteStock(elementSortie.getQuantiteStock() + qteElementEntree*this.getNiveauActivation());
                }
            }
        }
        return valeurVente - valeurAchat;
    }

    //TODO: gérer le calcul de l'indicateur de commande (dans une V2 car pour l'instant je n'en vois pas l'utilité)

    @Override
    public String toString() {
        return "Chaine{" +
                "codeC='" + codeC + '\'' +
                ", nom='" + nom + '\'' +
                ", niveauActivation=" + niveauActivation +
                ", elementsEntree=" + elementsEntree +
                ", elementsSortie=" + elementsSortie +
                '}';
    }

    /**
     * transforme en string la liste des éléments en entrée
     * @return
     */
    public String ToStringElementsEnEntree(){
        String valeurToString = "";
            for(Map.Entry<Element,Integer> entree : this.elementsEntree.entrySet()) {
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
        for(Map.Entry<Element,Integer> sortie : this.elementsSortie.entrySet()) {
            valeurToString += "\n"+sortie.getValue().toString() + " * "+sortie.getKey();
        }
        return  valeurToString;
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

    public ObservableMap<Element, Integer> getElementsEntree() {
        return elementsEntree.get();
    }

    public SimpleMapProperty<Element, Integer> elementsEntreeProperty() {
        return elementsEntree;
    }

    public void setElementsEntree(ObservableMap<Element, Integer> elementsEntree) {
        this.elementsEntree.set(elementsEntree);
    }

    public ObservableMap<Element, Integer> getElementsSortie() {
        return elementsSortie.get();
    }

    public SimpleMapProperty<Element, Integer> elementsSortieProperty() {
        return elementsSortie;
    }

    public void setElementsSortie(ObservableMap<Element, Integer> elementsSortie) {
        this.elementsSortie.set(elementsSortie);
    }
}

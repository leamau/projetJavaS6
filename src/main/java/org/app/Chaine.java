package org.app;

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
    private String codeC;
    /**
     * nom de la chaine
     */
    private String nom;
    /**
     * niveau d'activation de la chaine
     */
    private int niveauActivation;
    /**
     * éléments en entrée de la chaine
     * <qte,element>
     */
    private HashMap<Element,Integer> elementsEntree;
    /**
     * éléments en sortie de la chaine
     * <qte,element>
     */
    private HashMap<Element,Integer> elementsSortie;

    /**
     * constructeur de la classe avec le code incrémenté automatiquement
     * @param nom : nom de la chaine
     * @param activation : niveau d'activation de la chaine
     * @throws IllegalArgumentException
     */
    public Chaine(final String nom,final int activation) throws IllegalArgumentException{
        this.nom = nom;
        /*incrémentation du dernier id pour le code de la chaine*/
        lastValueId ++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.codeC = "C00"+lastValueId;
                break;
            case 2 :
                this.codeC = "C0"+lastValueId;
                break;
            case 3 :
                this.codeC = "C" + lastValueId;
                break;
        }
        this.elementsEntree = new HashMap<Element,Integer>();
        this.elementsSortie = new HashMap<Element,Integer>();
        if(activation >= 0){
            this.niveauActivation = activation;
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
        this.nom = nom;
        /*incrémentation du dernier id pour le code de la chaine*/
        lastValueId ++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.codeC = "C00"+lastValueId;
                break;
            case 2 :
                this.codeC = "C0"+lastValueId;
                break;
            case 3 :
                this.codeC = "C" + lastValueId;
                break;
        }
        this.elementsEntree = new HashMap<Element,Integer>();
        this.elementsSortie = new HashMap<Element,Integer>();
        this.niveauActivation = 0;
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
            if(stock - qteElementEntree*this.niveauActivation < 0){
                throw new IllegalArgumentException("Il n'y a pas assez d'élément dans le stock pour garantir l'exécution de la chaine de production");
            }else{
                valeurAchat += elementEntree.getPrixAchat();
                for(Map.Entry<Element,Integer> sortie : this.elementsSortie.entrySet()) {
                    Integer qteElementSortie = entree.getValue();
                    Element elementSortie = entree.getKey();
                    valeurVente += elementSortie.getPrixVente();
                    elementSortie.setQuantiteStock(elementSortie.getQuantiteStock() + qteElementSortie*this.niveauActivation);
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
        return codeC;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public int getNiveauActivation() {
        return niveauActivation;
    }

    public void setNiveauActivation(final int niveauActivation) {
        this.niveauActivation = niveauActivation;
    }

    public HashMap<Element,Integer> getElementsEntree() {
        return elementsEntree;
    }

    public HashMap<Element,Integer> getElementsSortie() {
        return elementsSortie;
    }
}

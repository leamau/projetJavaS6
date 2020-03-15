package org.app;

import java.util.HashMap;
import java.util.Map;

public class Chaine {
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
     * constructeur de la classe
     * @param code : code de la chaine
     * @param nom : nom de la chaine
     * @param activation : niveau d'activation de la chaine
     * @throws IllegalArgumentException
     */
    public Chaine(String code,String nom,int activation) throws IllegalArgumentException{
        this.nom = nom;
        this.codeC = code;
        this.elementsEntree = new HashMap<Element,Integer>();
        this.elementsSortie = new HashMap<Element,Integer>();
        if(activation > 0){
            this.niveauActivation = activation;
        }else{
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
    }

    public double calculIndicateurValeur() throws IllegalArgumentException{
        double valeurVente = 0;
        double valeurAchat = 0;
        //pour chaque élément en entrée
        for(Map.Entry<Element,Integer> entree : this.elementsEntree.entrySet()) {
            Integer qteElementEntree = entree.getValue();
            Element elementEntree = entree.getKey();
            double stock = elementEntree.getQuantiteStock();
            //si sa quantitée demandée est supèrieure a sa quantitée en stock on affiche une erreur
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

    //getters & setters

    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveauActivation() {
        return niveauActivation;
    }

    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation = niveauActivation;
    }

    public HashMap<Element,Integer> getElementsEntree() {
        return elementsEntree;
    }

    public HashMap<Element,Integer> getElementsSortie() {
        return elementsSortie;
    }
}

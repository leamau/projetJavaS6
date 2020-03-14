package org.app;

import java.util.HashMap;

public class Chaine {
    private String codeC;
    private String nom;
    private int niveauActivation;
    private double indicateurValeur = 0;
    private int indicateurCommande = 0;
    private HashMap<Integer,Element> elementsEntrée;
    private HashMap<Integer,Element> elementsSortie;

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
        this.elementsEntrée = new HashMap<Integer,Element>();
        this.elementsSortie = new HashMap<Integer,Element>();
        if(activation > 0){
            this.niveauActivation = activation;
        }else{
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
    }
}

package org.app;

import java.util.HashMap;

public class chaines_old {
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


        private double indicateurValeur = 0;
        private int indicateurCommande = 0;
        private HashMap<Integer,Element> elementsEntrée;
        private HashMap<Integer,Element> elementsSortie;
     /**
      *  constructeur de la classe
      *  */
     public chaines_old(String code,String nom,int activation) throws IllegalArgumentException {
         this.nom = nom;
         this.codeC = code;
         this.elementsEntrée = new HashMap<Integer, Element>();
         this.elementsSortie = new HashMap<Integer, Element>();
         if (activation > 0) {
             this.niveauActivation = activation;
         } else {
             throw new IllegalArgumentException("le niveau d'activation doit être positif");
         }
     }
}
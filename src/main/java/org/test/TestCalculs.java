package org.test;

import org.app.*;

import java.io.FileNotFoundException;
import java.lang.System;

public class TestCalculs {
    public static void main(String [] args) throws FileNotFoundException {
     /*   //création d'elements
        Element cookie = new Element("cookie",10,1.4,2,1, "g");
        Element chocolat  = new Element("chocolat",5,0.5,1,1,"g");
        Element beurre = new Element("beurre",8,0.8,1.5,1,"g");
        Element farine = new Element("farine",3,0.7,1.7,1,"g");

        //création de chaine
        Chaine ch1 = new Chaine("fabrication de cookie",1);
        ch1.getElementsEntree().put(chocolat,2.0);
        ch1.getElementsEntree().put(beurre,1.0);
        ch1.getElementsEntree().put(farine,1.0);
        ch1.getElementsSortie().put(cookie,1.0);

        System.out.println(ch1.toString());
        System.out.println("indicateur de valeur de la chaine 1 : { " +ch1.calculIndicateurValeur()+" }");
        */
        PersonnelQualifie perso = new PersonnelQualifie("n","n",3,3);
            System.out.println("\n\n"+perso.getClass().toString());



    }
}

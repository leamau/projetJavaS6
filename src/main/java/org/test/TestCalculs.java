package org.test;

import org.app.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System;

/**
 * Classe permettant de tester tous les calculs d'une usine.
 */
public class TestCalculs {
    /**
     * Permet d'executer les classes éléments, chaîne et Usine pour tester leurs calculs
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String [] args) throws FileNotFoundException {
        //création d'elements
       /* Element cookie = new Element("cookie",10,1.4,2,1, "g");
        Element chocolat  = new Element("chocolat",5,0.5,1,1,"g");
        Element beurre = new Element("beurre",8,0.8,1.5,1,"g");
        Element farine = new Element("farine",3,0.7,1.7,1,"g");

        //création de chaine
        Chaine ch1 = new Chaine("fabrication de cookie",1);
        ch1.getElementsEntree().put(chocolat,2.0);
        ch1.getElementsEntree().put(beurre,1.0);
        ch1.getElementsEntree().put(farine,1.0);
        ch1.getElementsSortie().put(cookie,1.0);

        System.out.println(ch1.toString());*/
        Usine.getInstance().getChaines().get(2).setNiveauActivation(1);
        System.out.println("indicateur de valeur : "+Usine.getInstance().getChaines().get(2).calculIndicateurValeurSemaine(1));        System.out.println("indicateur de valeur : "+Usine.getInstance().getChaines().get(0).getNiveauActivation());
        System.out.println("indicateur de personnel : "+Usine.getInstance().getChaines().get(2).calculIndicateurPersonnelSemaine(1));
        System.out.println("etat de la chaine : "+Usine.getInstance().getChaines().get(2).chaineIsOk(1));
      /*  System.out.println("indicateur de valeur de la chaine "+Usine.getInstance().getChaines().get(0).getCodeC()+" : { " +Usine.getInstance().getChaines().get(0).calculIndicateurValeurSemaine(2)+" }");
        System.out.println("indicateur de Personnel de la chaine "+Usine.getInstance().getChaines().get(0).getCodeC()+" : { " +Usine.getInstance().getChaines().get(0).calculIndicateurPersonnelSemaine(46)+" }");
*/
        System.out.println("indicateur de commandes : "+Usine.getInstance().calculIndicateurCommande());




    }
}

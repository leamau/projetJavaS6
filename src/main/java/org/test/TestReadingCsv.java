package org.test;
import org.app.*;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe permettant de tester l'exportation des fichiers csv et leurs mise en forme
 */
public class TestReadingCsv{
    /**
     * Execution des tests d'exportation pour les csv des chaînes et du personnels
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

        Usine.getInstance();
        System.out.println(Usine.getInstance().getChaines().get(0).toString());

        /*for (Chaine chaine: Usine.getInstance().getChaines() ) {
            for(Map.Entry<Element, Double> s : chaine.getElementsSortie().entrySet()) {
                System.out.println(s.toString());
            }
            for(Map.Entry<Element, Double> e : chaine.getElementsEntree().entrySet()) {
                System.out.println(e.toString());
            }
        }*/

        for (Chaine chaine: Usine.getInstance().getChaines() ) {
            for(Map.Entry<Element, Double> sortie : chaine.getElementsSortie().entrySet()) {
                System.out.println(sortie.toString());
            }
        }

        //System.out.println(Usine.getInstance().getPersonnels().toString());
    }
}

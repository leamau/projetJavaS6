package org.test;
import org.app.*;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class TestReadingCsv{
    public static void main(String[] args) throws Exception
    {
        ConversionsCsv convert = new ConversionsCsv();
        //convert.ReadCsv("prix");
        //convert.ReadCsv("chaines");
        //convert.ReadCsv("elements");

       /* String valeurToString = "";
        for( Element element :  convert.CsvToElement()) {
            valeurToString += "\n"+element.toString();
        }
        System.out.println(valeurToString);*/

        /*String valeurToString = "";
        for( Chaine c :  convert.csvToChaines()) {
            valeurToString += "\n"+c.toString();
        }
        System.out.println(valeurToString);*/
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

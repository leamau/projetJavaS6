package org.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConversionsCsv {

    public void ReadCsv(String className) throws FileNotFoundException {
        String affiche= className+ " : \n";
        String next = "";
        //créer le scanner
        Scanner sc = new Scanner(new File("./src/main/resources/org/csvFiles/"+className+".csv"));
        sc.useDelimiter(";");   //délimiter par virgule
        while (sc.hasNext())  //tant qu'il y a des lignes
        {
            next = sc.next();
            affiche += next.toString()+";";
            //System.out.print(next);  //afficher la ligne
        }
        sc.close();  //fermet scanner

        System.out.println(affiche);
    }

    /**
     * permet de convertir Prix.csv et elements.csv en un Objet Element
     * @return
     */
  /*  public  Element CsvToElement(){

        return nexElement;
    }

    /*public Chaine CsvToChaine(){



        //Chaine newChaine = new Chaine();

        return newChaine;
    }*/


}

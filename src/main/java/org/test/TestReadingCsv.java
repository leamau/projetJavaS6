package org.test;
import org.app.Chaine;
import org.app.ConversionsCsv;
import org.app.Element;
import org.app.Usine;

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

        System.out.println(Usine.getInstance().toString());
    }
}

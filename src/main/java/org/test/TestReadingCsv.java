package org.test;
import org.app.ConversionsCsv;
import org.app.Element;

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

        String valeurToString = "";
        for( Element element :  convert.CsvToElement()) {
            valeurToString += "\n"+element.toString();
        }
        System.out.println(valeurToString);

    }
}

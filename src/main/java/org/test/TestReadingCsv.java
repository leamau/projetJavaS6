package org.test;
import org.app.ConversionsCsv;

import java.io.*;
import java.util.Scanner;

public class TestReadingCsv{
    public static void main(String[] args) throws Exception
    {
        ConversionsCsv convert = new ConversionsCsv();
        //convert.ReadCsv("prix");
        //convert.ReadCsv("chaines");
        convert.ReadCsv("elements");
    }
}

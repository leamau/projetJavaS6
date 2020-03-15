package org.test;

import org.app.Chaine;
import org.app.Element;
import java.lang.System;

public class TestCalculs {
    public static void main(String [] args)
    {
        //création d'elements
        Element cookie = new Element("e1","cookie",10,1.4,2,1);
        Element chocolat  = new Element("e2","chocolat",5,0.5,1,1);
        Element beurre = new Element("e3","beurre",8,0.8,1.5,1);
        Element farine = new Element("e4","farine",3,0.7,1.7,1);

        //création de chaine
        Chaine ch1 = new Chaine("ch1","fabrication de cookie",1);
        ch1.getElementsEntree().put(chocolat,2);
        ch1.getElementsEntree().put(beurre,1);
        ch1.getElementsEntree().put(farine,1);
        ch1.getElementsSortie().put(cookie,1);

        System.out.println(ch1.toString());
        System.out.println(cookie.toString());
        System.out.println(ch1.calculIndicateurValeur());



    }
}

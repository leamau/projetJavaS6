package org.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Usine {
    private ArrayList<Element> elements;

    private ArrayList<Chaine>  chaines;

    private static Usine instance = null;

    private Usine() throws FileNotFoundException {
        this.elements = new ArrayList<Element>();
        this.chaines = new ArrayList<Chaine>();
        csvToElements();
    }

    public static Usine getInstance() throws FileNotFoundException {
        if (instance == null) {
            Usine.instance = new Usine();
        }
        return Usine.instance;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public ArrayList<Chaine> getChaines() {
        return chaines;
    }

    public void setChaines(ArrayList<Chaine> chaines) {
        this.chaines = chaines;
    }

    public String toString(){
        return "Uine : {\n " +
                "Chaines :{\n"+this.chaines.toString()+"},"+
                "Elements : {\n"+this.elements.toString()+"}\n"+
                "}";
    }

    //TODO: gérer les quantitiés a acherter
    //TODO: les extraction de CSV
    //TODO: faire les arraylist en hashset ou juste vérifier avant insertion qu'il n'y a pas de doublons

    public void readCsv(String className) throws FileNotFoundException {
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
    public void csvToElements() throws FileNotFoundException {
        //TODO:optimiser le code

        ArrayList<Element> newElements = new ArrayList<Element>();
        String value = "";
        int cptTours = 0;
        int cptLigne = 0;
        String codeE = "";
        String nom="";
        double qteStock=0;
        String unite="";
        double prixAchat=0;
        double prixVente=0;
        double demande=0;

        //créer le scanner pour le fichier elements.csv
        Scanner sc = new Scanner(new File("./src/main/resources/org/csvFiles/elements.csv"));
        sc.useDelimiter(";");   //délimiter par virgule
        //ignorer la première ligne
        while (sc.hasNext())  //tant qu'il y a des lignes
        {
            value = sc.next();
            //System.out.print(value + ";");  //afficher la ligne
            if(!value.equals("Code") && !value.equals("Nom") && !value.equals("Quantite") && !value.equals("unite")) {
                switch (cptTours) {
                    case 0:
                        codeE = value.substring(2,value.length());
                        break;
                    case 1:
                        nom = value.replace(".", " ");
                        break;
                    case 2:
                        qteStock = Double.parseDouble(value);
                        break;
                    case 3:
                        unite = value;
                        break;
                }
                if ((cptTours < 3)) {
                    cptTours++;
                } else {
                    Element newE = new Element(codeE, nom, unite);
                    newE.setQuantiteStock(qteStock);
                    newElements.add(newE);
                    cptTours = 0;
                }
            }
        }
        sc.close();  //fermer scanner

        String value2 = "";
        int cptTours2 = 0;

        //créer le scanner pour le fichier prix.csv
        Scanner sc2 = new Scanner(new File("./src/main/resources/org/csvFiles/prix.csv"));
        sc2.useDelimiter(";");   //délimiter par virgule

        while (sc2.hasNext())  //tant qu'il y a des lignes
        {
            value2 = sc2.next();

            if(!value2.equals("Code") && !value2.equals("achat") && !value2.equals("vente") && !value2.equals("Demande")) {
                //System.out.println("tour: "+cptTours2);
                //Code;Achat;Vente;Demande
                switch (cptTours2) {
                    case 0:
                        codeE = value2.substring(2,value2.length());
                        //System.out.println("code: "+value2);
                        break;
                    case 1:
                        prixAchat = (value2.equals("NA"))?-1:Double.parseDouble(value2);
                        //System.out.println("achat: "+value2);

                        break;
                    case 2:
                        prixVente =(value2.equals("NA"))?-1:Double.parseDouble(value2);
                        //System.out.println("vente: "+value2);

                        break;
                    case 3:
                        demande =(value2.equals("NA"))?-1:Integer.parseInt(value2);
                        //System.out.println("demande: "+value2);

                        break;
                }

                if ((cptTours2 < 3)) {
                    cptTours2++;
                } else {
                    cptTours2 = 0;
                    int i = 0;
                    boolean trouve = false;
                    System.out.println("code: "+codeE+"; achat: "+prixAchat+"; vente: "+prixVente+"; demande: "+demande);
                    //chercher l'élément avec le bon code
                    while ( i < newElements.size() && !trouve) {
                        //System.out.println(newElements.get(i).getCodeE()+" "+codeE);  //afficher la ligne
                        if(newElements.get(i).getCodeE().equals(codeE)){
                            newElements.get(i).setPrixAchat(prixAchat);
                            newElements.get(i).setPrixVente(prixVente);
                            newElements.get(i).setDemande(demande);
                            trouve = true;
                        }
                        i++;
                    }
                }
                //System.out.println(value);  //afficher la ligne
            }
        }
        sc2.close();  //fermet scanner

        /*System.out.println(newElements.toString());*/

        this.elements = newElements;
    }

}

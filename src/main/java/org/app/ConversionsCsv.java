package org.app;

import javafx.beans.property.SimpleMapProperty;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    public ArrayList<Element> CsvToElement() throws FileNotFoundException {
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
                        nom = value;
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
                        System.out.println("code: "+value2);
                        break;
                    case 1:
                        prixAchat = (value2.equals("NA"))?-1:Double.parseDouble(value2);
                        System.out.println("achat: "+value2);

                        break;
                    case 2:
                        prixVente =(value2.equals("NA"))?-1:Double.parseDouble(value2);
                        System.out.println("vente: "+value2);

                        break;
                    case 3:
                        demande =(value2.equals("NA"))?-1:Integer.parseInt(value2);
                        System.out.println("demande: "+value2);

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
                    while ( i<newElements.size() && !trouve) {
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

        return newElements;
    }


    /**
     * Enregistre les chaînes présentes dans le CSV.
     * @param stock l'ensemble des éléments stockés dans l'usine.
     * @return l'ensemble sous forme d'une ArrayList.
     */
    public ArrayList<Chaine> csvToChaines(HashSet<Element> stock) { // CHECK LE TYPE

        // Le nom du CSV à extraire.
        final String FILENAME = "chaines";

        // Ouverture du fichier CSV.
        try (Scanner sc = new Scanner(new File("./src/main/resources/org/csvFiles/" + FILENAME + ".csv"))) {

            // L'ensemble des chaines.
            ArrayList<Chaine> chaines = new ArrayList<>();
            // Compteur de chaines.
            int cpt = 0; // Pour une v2

            sc.useDelimiter(";");   //délimiter par virgule

            // Elimination de la première ligne du csv.
            sc.next(); sc.next(); sc.next(); sc.next(); sc.next(); sc.next(); sc.next();

            while (sc.hasNext())  // Tant qu'il y a des lignes.
            {
                // Code;Nom;Entree.(code,qte);Sortie.(code,qte);Temps;Personnels.non.qualifies;Personnels.qualifies
                String code = sc.next();
                String nom = sc.next();

                SimpleMapProperty<Element, Double> entrees = stringToElements(sc.next(), stock);
                SimpleMapProperty<Element, Double> sorties = stringToElements(sc.next(), stock);

                int temps = Integer.parseInt(sc.next());
                int pnq = Integer.parseInt(sc.next()); // Not used yet.
                int pq = Integer.parseInt(sc.next()); // Not used yet.

                SimpleMapProperty<Element, Double> e = new SimpleMapProperty<>();
                e.putAll(entrees);
                SimpleMapProperty<Element, Double> s = new SimpleMapProperty<>();
                s.putAll(entrees);

                // Construction de la chaine à ajouter.
                Chaine c = new Chaine(code, nom, temps, e, s);
                // Ajout de la chaine à l'ensemble des chaines.
                chaines.add(c);
            }
            // Fermeture du scanner.
            sc.close();

            // Retour de l'arraylist construite.
            return chaines;

            // En cas d'erreur.
        } catch (Exception e) {
            // Code to handle error.
            return null;
        }
    }

    /**
     * Convertis une chaîne de caractère en élément.
     * @param s la chaîne à convertir.
     * @param stock l'ensemble des éléments stockés dans l'usine.
     * @return l'élément résultant sous forme d'Element.
     */
    private SimpleMapProperty<Element, Double> stringToElements(final String s, HashSet<Element> stock) {

        // Exemple d'entrée : (E012,3)/(E014,5)/(E011,2)/(E001,3).
        // Exemple de sortie : (E019,10).
        // Découpage de la chaîne des entrées.
        String[] elementsS = s.split(Pattern.quote("/"));
        HashMap<Element, Double> elements = new HashMap<>();

        // Parcours des éléments.
        for(String e : elementsS) {
            // On traîte un élément tel que (E012,3).
            // On retire les parenthèses.
            String e2 = e.replaceAll("[()]", "");

            // On traîte un élément tel que E012,3.
            String[] eData = e2.split(Pattern.quote(","));

            // On a donc un tableau eData contenant [E012][3].
            // On récupère l'élément via son code.
            String codeE = eData[0];

            // On récupère la quantité.
            double qteE = Double.parseDouble(eData[1]);

            // On parcours tous les éléments en stock.
            for(Element elem : stock) {
                // Si le code récupéré est identique à un code en stock.
                if(elem.getCodeE() == codeE) {
                    elements.put(elem, qteE);
                }
            }
        }
        // On renvoie la liste ainsi complétée.
        SimpleMapProperty<Element, Double> e = new SimpleMapProperty<>();
        e.putAll(elements);

        return e;
    }
}

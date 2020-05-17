package org.app;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Usine {
    private ArrayList<Element> elements;

    private ArrayList<Chaine>  chaines;

    private ArrayList<PersonnelQualifie>  personnelsQualifies;

    private ArrayList<PersonnelNonQualifie>  personnelsNonQualifies;

    private static Usine instance = null;

    private int nbSemaines;

    private Usine()  {
        this.elements = new ArrayList<Element>();
        this.chaines = new ArrayList<Chaine>();
        this.personnelsQualifies = new ArrayList<PersonnelQualifie>();
        this.personnelsNonQualifies = new ArrayList<PersonnelNonQualifie>();
        this.nbSemaines = 1; //TODO: gérer le calcul de l'indicateur de commande (dans une V2 car pour l'instant je n'en vois pas l'utilité)
        try {
            csvToElements();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            csvToPersonnel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.chaines = csvToChaines(this.elements);
    }

    public static Usine getInstance() {
        if (instance == null) {
            Usine.instance = new Usine();
        }
        return Usine.instance;
    }

    public ArrayList<PersonnelQualifie> getPersonnelsQualifies() {
        return personnelsQualifies;
    }

    public void setPersonnelsQualifies(ArrayList<PersonnelQualifie> personnelsQualifies) {
        this.personnelsQualifies = personnelsQualifies;
    }

    public ArrayList<PersonnelNonQualifie> getPersonnelsNonQualifies() {
        return personnelsNonQualifies;
    }

    public void setPersonnelsNonQualifies(ArrayList<PersonnelNonQualifie> personnelsNonQualifies) {
        this.personnelsNonQualifies = personnelsNonQualifies;
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
        if(this.chaines == null)
            System.out.println("ERREUR toString Chaine");
        return "Uine : {\n " +
                    "\tChaines : {" +
                        "\n\t\t"+this.chaines.toString()+"}\n"+
                    "\tElements : {" +
                        "\n\t\t"+this.elements.toString()+"}\n"+
                    "\tPersonnels Non Qualifies : {" +
                        "\n\t\t"+this.personnelsNonQualifies.toString()+"}\n"+
                    "\tPersonnels Qualifies : {" +
                        "\n\t\t"+this.personnelsQualifies.toString()+"}\n"+
                "\t}";
    }

    //TODO: gérer les quantitiés a acherter

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

        //System.out.println(affiche);
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
                    //System.out.println("code: "+codeE+"; achat: "+prixAchat+"; vente: "+prixVente+"; demande: "+demande);
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

    public synchronized void addElemtsEntree(HashMap<Element, Double> elements,ObservableMap<Element, Double> e){
        elements.forEach((key, value) -> {
            //System.out.println("Entrée : Key : " + key + " Value : " + value);
            e.put(key,value);
        });
    }
    public synchronized void addElemtsSortie(HashMap<Element, Double> elements,ObservableMap<Element, Double> s){
        elements.forEach((key, value) -> {
            //System.out.println("Entrée : Key : " + key + " Value : " + value);
            s.put(key,value);
        });
    }
    /**
     * Enregistre les chaînes présentes dans le CSV.
     * @param stock l'ensemble des éléments stockés dans l'usine.
     * @return l'ensemble sous forme d'une ArrayList.
     */
    public ArrayList<Chaine> csvToChaines(ArrayList<Element> stock) {

        // Le nom du CSV à extraire.
        final String FILENAME = "chaines";

        // Ouverture du fichier CSV.
        try (Scanner sc = new Scanner(new File("./src/main/resources/org/csvFiles/" + FILENAME + ".csv"))) {

            // L'ensemble des chaines.
            ArrayList<Chaine> chaines = new ArrayList<>();

            // Compteur de chaines.
            int cpt = 0; // Pour une v2

            sc.useDelimiter(";");   //délimiter par virgule
/*
            while (sc.hasNext())  //tant qu'il y a des lignes
            {
                // Code;Nom;Entree.(code,qte);Sortie.(code,qte);Temps;Personnels.non.qualifies;Personnels.qualifies
                String code = sc.next();
                String nom = sc.next();
                SimpleMapProperty<Element,Integer> entrees = stringToElements(sc.next());
                SimpleMapProperty<Element,Integer> sorties = stringToElements(sc.next());
                int temps = Integer.parseInt(sc.next());
                int pnq = Integer.parseInt(sc.next()); // Not used yet.
                int pq = Integer.parseInt(sc.next()); // Not used yet.

                // Construction de la chaine à ajouter.
                Chaine c = new Chaine(code, nom, temps, entrees, sorties);
                // Ajout de la chaine à l'ensemble des chaines.
                newChaines.add(c);
            }
*/

            // Elimination de la première ligne du csv.
            sc.next(); sc.next(); sc.next(); sc.next(); sc.next(); sc.next(); sc.next();

            while (sc.hasNext())  // Tant qu'il y a des lignes.
            {
                // Code;Nom;Entree.(code,qte);Sortie.(code,qte);Temps;Personnels.non.qualifies;Personnels.qualifies

                /*
                VERIFICATION DE LA VALEUR DES VARIABLES A CHAQUE INSTANT
                NECESSITE DE VIRER LA VARIABLE VAL
                REMETTRE SC.NEXT() COMME ASSIGNATION
                 */
                String val = sc.next();
                //System.out.println("CODE CHAINE EN TRAITEMENT : " + val);
                String code = val.replace(System.getProperty("line.separator"), "");
                val = sc.next();
                //System.out.println("NOM : " + val);
                String nom = val;
                val = sc.next();
                //System.out.println("ENTREE(S) : " + val);
                HashMap<Element, Double> entrees = stringToElements(val, stock);
                val = sc.next();
                //System.out.println("SORTIES(S) : " + val);
                HashMap<Element, Double> sorties = stringToElements(val, stock);
                val = sc.next();
                //System.out.println("TEMPS : " + val);
                int temps = Integer.parseInt(val);
                val = sc.next();
                //System.out.println("PERS NON-QUAL : " + val);
                int pnq = Integer.parseInt(val); // Not used yet.
                val = sc.next();
                //System.out.println("PERS QUAL : " + val);
                int pq = Integer.parseInt(val); // Not used yet.

                // Transformation des entrées et sorties.
                ObservableMap<Element, Double> s = FXCollections.observableHashMap();
                ObservableMap<Element, Double> e = FXCollections.observableHashMap();

                this.addElemtsEntree(entrees,e);
                this.addElemtsSortie(sorties,s);

                /*for(Map.Entry<Element, Double> sortie : sorties.entrySet()) {
                    System.out.println("so " + sortie);
                    s.put(sortie.getKey(),sortie.getValue());
                }*/

                /*for(Map.Entry<Element, Double> entree : entrees.entrySet()) {
                    System.out.println("en "+entree);
                    e.put(entree.getKey(),entree.getValue());
                }*/

                /*e.putAll(entrees);

                s.putAll(sorties);*/
                /*e.forEach((value,key)->{
                    System.out.println("Entrée : Key : " + key + " Value : " + value);
                });
                s.forEach((value,key)->{
                    System.out.println("Sortie : Key : " + key + " Value : " + value);
                });*/
                /*System.out.println("e "+e.toString());
                System.out.println("s "+s.toString());*/



                // Construction de la chaine à ajouter.
                //System.out.println("CHAINE A CREER : " + code + ", " + nom + ", " + temps + ", " + e.toString() + ", " + s.toString() + ", " + pnq + ", " + pq);
                Chaine c = new Chaine(code, nom, temps, new SimpleMapProperty<>(e), new SimpleMapProperty<>(s), pnq, pq);
                //System.out.println("CHAINE CREEE :" + c.toStringV2());

                // Ajout de la chaine à l'ensemble des chaines.
                if(!chaineExist(c, this.chaines)) {
                    chaines.add(c);
                    //System.out.println("CHAINE AJOUTEE");
                } // VERIF // System.out.println("ETAT DES CHAINES : " + chaines.toString());
            }

            // Fermeture du scanner.
            sc.close();

            // Retour de l'arraylist construite.
            return chaines;

            // En cas d'erreur.
        } catch (Exception e) {
            // Code to handle error.
            System.out.println(e.getMessage());
            return null;
        }
    }

    //TODO: gérer le calcul de l'indicateur de commande (dans une V2 car pour l'instant je n'en vois pas l'utilité)
    public double calculIndicateurCommande() throws IllegalArgumentException, FileNotFoundException {
        double nbChaines = this.chaines.size();
        double nbChainesOk = 0;

        //pourcentages de commandes ok par rapport au nombre de commandes totales 
        for (Chaine chaine : this.chaines) {
            if(chaine.chaineIsOk(this.nbSemaines)){
                nbChainesOk++;
            }
        }
        System.out.println("nbchaine : "+nbChaines);
        System.out.println("nbok :" +nbChainesOk);
        System.out.println("res :" +(nbChainesOk/nbChaines)*100+"%");
        //calcul du pourcentage
        return ((nbChainesOk/nbChaines)*100);
    }


    /**
     * permet de convertir Prix.csv et elements.csv en un Objet Element
     * @return
     */
    public void csvToPersonnel() throws FileNotFoundException {
        //TODO:optimiser le code
        ArrayList<PersonnelQualifie> newPersonnelsQ = new ArrayList<PersonnelQualifie>();
        ArrayList<PersonnelNonQualifie> newPersonnelsNQ = new ArrayList<PersonnelNonQualifie>();
        String id = "";
        int cptTours = 0;
        int cptLigne = 0;
        double nbHeuresDispo = 0;
        double nbHeuresAssignes = 0;
        String nom = "";
        String prenom="";
        boolean disponibilite = false;
        String qualification = "";

        String value2 = "";
        int cptTours2 = 0;

        //créer le scanner pour le fichier prix.csv
        Scanner sc2 = new Scanner(new File("./src/main/resources/org/csvFiles/Personnels.csv"));
        sc2.useDelimiter(";");   //délimiter par virgule

        while (sc2.hasNext())  //tant qu'il y a des lignes
        {
            value2 = sc2.next();

            if(!value2.equals("Identifiant") && !value2.equals("Nom") && !value2.equals("Prenom") && !value2.equals("Qualification")&&!value2.contains("HeuresDispo")) {

                //System.out.println("tour: "+cptTours2);
                //Code;Achat;Vente;Demande
                switch (cptTours2) {
                    case 0:
                        id = value2.substring(2,value2.length());
                        //System.out.println(" id: "+id);
                        break;
                    case 1:
                        nom = value2;
                        //System.out.println("nom: "+value2);
                        break;
                    case 2:
                        prenom =value2;
                        //System.out.println("prenom: "+value2);

                        break;
                    case 3:
                        qualification = value2;
                        //System.out.println("qualification: "+value2 );
                    case 4:
                        if(!value2.contains("q")){
                            nbHeuresDispo = Double.parseDouble(value2);
                            //System.out.println("nbHeuresDispo: "+value2);
                        }
                        break;
                }

                if ((cptTours2 < 4)) {
                    cptTours2++;
                } else {
                    if(qualification.equals("q")){
                        PersonnelQualifie newPerso = new PersonnelQualifie(id,nom,prenom,nbHeuresDispo,0);
                        newPersonnelsQ.add(newPerso);
                    }else{
                        if(qualification.equals("nq")){
                            PersonnelNonQualifie newPerso = new PersonnelNonQualifie(id,nom,prenom,nbHeuresDispo,0);
                            newPersonnelsNQ.add(newPerso);
                        }
                    }
                    cptTours2 = 0;
                }
                //System.out.println(value2);  //afficher la ligne
            }
        }
        sc2.close();  //fermet scanner

        //System.out.println(newPersonnels.toString());

        this.personnelsNonQualifies = newPersonnelsNQ;
        this.personnelsQualifies = newPersonnelsQ;

    }

    /**
     * Convertis une chaîne de caractère en élément.
     //* @param s la chaîne à convertir.
     * @return l'élément résultant sous forme d'Element.
     */
  /*  private SimpleMapProperty<Element,Integer> stringToElements(String s) {

        // Exemple d'entrée : (E012,3),(E014,5),(E011,2),(E001,3)
        // Exemple de sortie : (E019,10)

        SimpleMapProperty<Element,Integer> elements = new SimpleMapProperty<>();

        return elements;
    }
    /*
     * Fonction évitant d'insérer des chaînes en doublon.
     * @param c la chaîne dont on veut tester l'existence.
     * @param chaines la liste de chaînes à compléter.
     * @return true si la chaîne testée existe déjà dans la liste.
     */
    private boolean chaineExist(final Chaine c, final ArrayList<Chaine> chaines) {

        boolean exist = false;

        // Parcours des chaines de la liste.
        for (Chaine ch : chaines) {
            // Si les deux chaînes sont identiques (même codeC).
            if (ch.equals(c))
                // On affecte true à la variable.
                exist = true;
        }
        return exist;
    }

    /**
     * Convertis une chaîne de caractère en élément.
     * @param s la chaîne à convertir.
     * @param stock l'ensemble des éléments stockés dans l'usine.
     * @return l'élément résultant sous forme d'Element.
     */
    private HashMap<Element, Double> stringToElements(final String s, ArrayList<Element> stock) {

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
            //System.out.println("Données à ajouter : " + codeE + "," + qteE);

            // On parcours tous les éléments en stock.
            for(Element elem : stock) {
                // Si le code récupéré est identique à un code en stock.
                if(elem.getCodeE().equals((codeE))) {  // == Le programme continue correctement, .equals() le programme stop ?
                    elements.put(elem, qteE);
                    //System.out.println("Element ajouté : " + elem.toString());
                }
            }
        }
        // On renvoie la liste ainsi complétée.
        return elements;
    }

    /**
     * Méthode exportant au format txt l'état des chaînes.
     * @return 0 si l'export a bien fonctionné.
     */
    public int exportChainesTxt() throws IOException {

        // Nombre de chaînes écrites.
        int n = 0;

        // Ajouter la date au nom du fichier.
        DateFormat formatCourt = new SimpleDateFormat("yyyyMMdd-HHmm");
        DateFormat formatStandard = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();

        // Gestion détaillée du nom du fichier.
        String filePath = ""; // src/main/resources/org.export/ ?
        String fileName = "simulation";
        String fileDate = formatCourt.format(d);
        String fileExtension = ".txt";
        final String completeFileName = filePath + fileName + "-" + fileDate + fileExtension;

        // Création du fichier.
        File f = new File(completeFileName);
        f.createNewFile();

        // Objet écrivant dans le fichier.
        FileWriter fw = null;

        try {

            fw = new FileWriter(completeFileName);
            fw.write("=====================================\n");
            fw.write("=====================================\n");
            fw.write("=                                   =\n");
            fw.write("= Simulation du " + formatStandard.format(d) + " =\n");
            fw.write("=                                   =\n");
            fw.write("=====================================\n");
            fw.write("=====================================\n\n");

            fw.write( "Indicateur de commande = " + this.calculIndicateurCommande() + "%\n\n");
            // Parcours des chaînes de l'usine.
            for(Chaine c : this.chaines) {

                // Ajout du toString de chaque chaîne.
                fw.write(c.toStringV2() + "\n");

                // Ajout des indicateurs disponibles pour chaque chaîne.
                fw.write( "\tIndicateur de valeur = " + c.calculIndicateurValeurSemaine(this.nbSemaines) + "\n");
                fw.write("\tIndicateur de personnel = " + c.calculIndicateurPersonnelSemaine(this.nbSemaines) + "\n\n");
                fw.write("=====================================\n\n");
                n++;
            }

            // Fermeture du fileWriter.
            fw.close();

        } catch (IOException e) {

            e.printStackTrace();
            return -1;
        }
        return n;
    }

    /**
     * Méthode exportant au format txt l'état des chaînes.
     * @return 0 si l'export a bien fonctionné (on peut imaginer retourner le nombre de personnel).
     */
    public int exportPersonnelTxt() throws IOException {

        // Assignation du personnel
        for(Chaine c : this.chaines) {
            c.calculIndicateurPersonnelSemaine(getNbSemaines());
        }

        // Nombre de chaînes écrites.
        int n = 0;

        // Ajouter la date au nom du fichier.
        DateFormat formatCourt = new SimpleDateFormat("yyyyMMdd-HHmm");
        DateFormat formatStandard = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();

        // Gestion détaillée du nom du fichier.
        String filePath = ""; // src/main/resources/org.export/ ?
        String fileName = "personnel";
        String fileDate = formatCourt.format(d);
        String fileExtension = ".txt";
        final String completeFileName = filePath + fileName + "-" + fileDate + fileExtension;

        // Création du fichier.
        File f = new File(completeFileName);
        f.createNewFile();

        // Objet écrivant dans le fichier.
        FileWriter fw = null;

        try {

            fw = new FileWriter(completeFileName);
            fw.write("=============================================\n");
            fw.write("=============================================\n");
            fw.write("=                                           =\n");
            fw.write("= Liste du Personnel au " + formatStandard.format(d) + " =\n");
            fw.write("=                                           =\n");
            fw.write("=============================================\n");
            fw.write("=============================================\n\n");

            fw.write("Personnels Non-qualifiés :\n\n");

            // Parcours des personnels de l'usine.
            for(Personnel pnq : this.personnelsNonQualifies) {

                // Ajout du toString de chaque chaîne.
                fw.write(pnq.toString() + "\n");

                // Ajout du nb d'heure par chaîne.
                for(Chaine c : this.chaines) {

                    // Si la personne fait partie du personnel convoqué pour cette chaîne.
                    if(c.getPersonnelsNonQualifiesConvoque().get().containsKey(pnq)) {
                        // On écrit "CodeChaine(NiveauActivation) : NombreHeuresh".
                        fw.write("\n" + c.getCodeC() + "(niveau d'activation " + c.getNiveauActivation() + ")" + " : " + c.getPersonnelsNonQualifiesConvoque().get(pnq) + "h");
                    }
                }

                fw.write("\n\n=============================================\n\n");
                n++;
            }

            fw.write("Personnels Qualifiés :\n\n");

            for(Personnel pq : this.personnelsQualifies) {

                // Ajout du toString de chaque chaîne.
                fw.write(pq.toString() + "\n");

                // Ajout du nb d'heure par chaîne.
                for(Chaine c : this.chaines) {

                    // Si la personne fait partie du personnel convoqué pour cette chaîne.
                    if(c.getPersonnelsQualifiesConvoque().get().containsKey(pq)) {
                        // On écrit "CodeChaine(NiveauActivation) : NombreHeuresh".
                        fw.write("\n" + c.getCodeC() + "(niveau d'activation " + c.getNiveauActivation() + ")" + " : " + c.getPersonnelsQualifiesConvoque().get(pq) + "h");
                    }
                }

                fw.write("\n\n=============================================\n\n");
                n++;
            }

            // Fermeture du fileWriter.
            fw.close();

        } catch (IOException e) {

            e.printStackTrace();
            return -1;
        }
        return n;
    }

    public int getNbSemaines() { return this.nbSemaines; }

    public void setNbSemaines(final int n) { this.nbSemaines = n; }
}

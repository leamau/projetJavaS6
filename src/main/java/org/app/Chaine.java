package org.app;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chaine {
    //TODO: pour l'ajout d'un employé a la chaine on incrémente son nombre d'heures
    /**
     * atribut statique permettant l'incrémentation du code de la chaine
     */
    static int lastValueId = 0;
    /**
     * code de la chaine
     */
    private SimpleStringProperty codeC;
    /**
     * nom de la chaine
     */
    private SimpleStringProperty nom;
    /**
     * niveau d'activation de la chaine
     */
    private SimpleIntegerProperty niveauActivation;
    /**
     * éléments en entrée de la chaine
     * <qte,element>
     */
    private SimpleMapProperty<Element,Double> elementsEntree;
    /**
     * éléments en sortie de la chaine
     * <qte,element>
     */
    private SimpleMapProperty<Element,Double> elementsSortie;
    /**
     * nombre de personnel non qualifié nécessaire au déroulement de la chaine
     *
     */
    private SimpleIntegerProperty nbPersonnelNQNecessaire;
    /**
     * nombre de personnel qualifié nécessaire au déroulement de la chaine
     *
     */
    private SimpleIntegerProperty nbPersonnelQNecessaire;
    /**
     * personnel qualifié convoqué sur la chaine
     * <PersonnelQualifié,nbHeuresSurLaChaine>
     */
    private SimpleMapProperty<PersonnelQualifie,Double> PersonnelsQualifiesConvoque;

    /**
     * personnel non qualifié convoqué sur la chaine
     * <PersonnelNonQualifié,nbHeuresSurLaChaine>
     */
    private SimpleMapProperty<PersonnelNonQualifie,Double> PersonnelsNonQualifiesConvoque;

    /**
     * constructeur de la classe avec le code incrémenté automatiquement
     * @param nom : nom de la chaine
     * @param activation : niveau d'activation de la chaine
     * @throws IllegalArgumentException
     */
    public Chaine(final String nom,final int activation) throws IllegalArgumentException{
        this.nom = new SimpleStringProperty(nom);
        /*incrémentation du dernier id pour le code de la chaine*/
        lastValueId ++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.codeC = new SimpleStringProperty("C00"+lastValueId);
                break;
            case 2 :
                this.codeC = new SimpleStringProperty("C0"+lastValueId);
                break;
            case 3 :
                this.codeC = new SimpleStringProperty("C" + lastValueId);
                break;
        }
        this.elementsEntree = new SimpleMapProperty<Element,Double>();
        this.elementsSortie = new SimpleMapProperty<Element,Double>();
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
        if(activation >= 0 && activation <=6){
            this.niveauActivation = new SimpleIntegerProperty(activation);
        }else{
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
    }

    /**
     * constructeur de la classe avec le code incrémenté automatiquement et aucun niveau d'activation
     * @param nom : nom de la chaine
     * @throws IllegalArgumentException
     */
    public Chaine(final String nom) throws IllegalArgumentException{
        this.nom = new SimpleStringProperty(nom);
        /*incrémentation du dernier id pour le code de la chaine*/
        lastValueId ++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.codeC = new SimpleStringProperty("C00"+lastValueId);
                break;
            case 2 :
                this.codeC = new SimpleStringProperty("C0"+lastValueId);
                break;
            case 3 :
                this.codeC = new SimpleStringProperty("C" + lastValueId);
                break;
        }
        this.elementsEntree = new SimpleMapProperty<Element,Double>();
        this.elementsSortie = new SimpleMapProperty<Element,Double>();
        this.niveauActivation = new SimpleIntegerProperty(0);
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
    }

    /**
     * Constructeur de Chaine sans génération automatique du code.
     * @param code le code de la chaine.
     * @param nom le nom de la chaine.
     * @param temps le niveau d'activation.
     * @param entrees l'ensemble des éléments en entrée et leur quantité.
     * @param sorties l'ensemble des éléments en sortie et leur quantité.
     * @throws IllegalArgumentException
     */

    public Chaine(final String code, final String nom, final int temps, final SimpleMapProperty<Element,Double> entrees, final SimpleMapProperty<Element,Double> sorties)  throws IllegalArgumentException {
        this.codeC = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.elementsEntree = new SimpleMapProperty<>(entrees);
        this.elementsSortie = new SimpleMapProperty<>(sorties);

        if(temps >= 0 && temps <=6) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(0);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(0);
    }

    public Chaine(final String code, final String nom, final int temps, final SimpleMapProperty<Element,Double> entrees, final SimpleMapProperty<Element,Double> sorties, final int nbNQ,final int nbQ)  throws IllegalArgumentException {
        this.codeC = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.elementsEntree = new SimpleMapProperty<>(entrees);
        this.elementsSortie = new SimpleMapProperty<>(sorties);
        if(temps >= 0 && temps <=6) {
            this.niveauActivation = new SimpleIntegerProperty(temps);
        } else {
            throw new IllegalArgumentException("le niveau d'activation doit être positif");
        }
        this.PersonnelsQualifiesConvoque = new SimpleMapProperty<PersonnelQualifie,Double>(FXCollections.observableHashMap());
        this.PersonnelsNonQualifiesConvoque = new SimpleMapProperty<PersonnelNonQualifie,Double>(FXCollections.observableHashMap());
        this.nbPersonnelNQNecessaire = new SimpleIntegerProperty(nbNQ);
        this.nbPersonnelQNecessaire = new SimpleIntegerProperty(nbQ);
    }

    /**
     *
     * vérifie la faisabilité de la chaine en fonction des personnels et des stocks
     * @param nbSemaines : nombre de semaines de déroulement de la chaine
     * @return un booléen indiquant si la chaine est faisable ou non
     */
    public boolean chaineIsOk(int nbSemaines) {
        //si il y a un indicateur de valeur poditif et assez de personnel disponible
        return (this.calculIndicateurValeurSemaine(nbSemaines)>0 && this.calculIndicateurPersonnelSemaine(nbSemaines));
    }



    /**
     * Cet indicateur donne une estimation financière de la rentabilité de la production envisagée
     * @param nbSemaines : nombre de semaines de déroulement de la chaine
     * @return double resultat
     * @throws IllegalArgumentException
     */
    public double calculIndicateurValeurSemaine(int nbSemaines){
        //on considère des semaines de 5jours
        int nbJours = 5;
        double valeurVente = 0;
        double valeurAchat = 0;
        double resultat = 0;
        boolean stockOK = true;
        /*pour chaque élément en entrée*/
        for(Map.Entry<Element, Double> entree : this.elementsEntree.entrySet()) {
            Double qteElementEntree = entree.getValue();
            Element elementEntree = entree.getKey();
            double stock = elementEntree.getQuantiteStock();
            /*si sa quantitée demandée est supèrieure a sa quantitée en stock on affiche une erreur*/
            if(stock - qteElementEntree*this.getNiveauActivation()*nbJours*nbSemaines < 0){
                //ajouter dans les elements a acheter
                elementEntree.setQteAcheter(elementEntree.getQteAcheter()-(stock - qteElementEntree*this.getNiveauActivation()*nbJours*nbSemaines));
                stockOK = false;
            }else{
                //déduction des éléments dans le stock
                elementEntree.setQuantiteStock(elementEntree.getQuantiteStock() - qteElementEntree*(this.getNiveauActivation()*nbJours*nbSemaines));
                //modification de la valeur d'achat totale
                valeurAchat += elementEntree.getPrixAchat()*qteElementEntree;
            }
        }
        /*si il y a assez de stock */
        if(stockOK){
            //parcours des éléments en sortie
            for(Map.Entry<Element, Double> sortie : this.elementsSortie.entrySet()) {
                Double qteElementSortie = sortie.getValue();
                Element elementSortie = sortie.getKey();
                //modification de la valeur de vente totale
                valeurVente += elementSortie.getPrixVente()*qteElementSortie;
                //changement de la quantité en stock de l'élément (soustraction de la quantitée necessaire au stock)
                elementSortie.setQuantiteStock(elementSortie.getQuantiteStock() + qteElementSortie*(this.getNiveauActivation()*nbJours*nbSemaines));
            }
            //calcul du résultat
            resultat = (valeurVente - valeurAchat)*nbJours*nbSemaines;
        }else {
            resultat = -1;
        }

        return resultat;
    }

    public SimpleDoubleProperty calculIndicateurValeurSemaineProperty(int nbSemaines){
        return new SimpleDoubleProperty(this.calculIndicateurValeurSemaine(nbSemaines));
    }
    /**
     * permet de savoir si il y a assez de personnel disponible pour réaliser la chaine
     * @param nbSemaines : nombre de semaines de déroulement de la chaine
     * @return vrai si il y  a assez de personnel disponible et faux dans le cas inverse
     */
    public boolean calculIndicateurPersonnelSemaine(int nbSemaines) {
        //on compare le nombre de personnel necessaire au nombre disponible
        boolean chaineOk = false;
        int nbPersonnelsQDispo = 0;
        ArrayList<PersonnelNonQualifie> persoNQDispo = new ArrayList<PersonnelNonQualifie>() ;
        ArrayList<PersonnelQualifie> persoQDispo = new ArrayList<PersonnelQualifie>() ;
        int nbPersonnelsNQDispo = 0;

        for (PersonnelQualifie perso:Usine.getInstance().getPersonnelsQualifies()) {
            if(perso.etreDisponible()){
                //recherche des personnels Disponibles
                nbPersonnelsQDispo ++;
            }
        }
        for (PersonnelNonQualifie perso:Usine.getInstance().getPersonnelsNonQualifies()) {
            if(perso.etreDisponible()){
                //recherche des personnels Disponibles
                nbPersonnelsNQDispo ++;
            }
        }

        //si le qualif necessaire est > au disponible
        if(this.nbPersonnelQNecessaire.get() > nbPersonnelsQDispo){
            //chaine non ok
            chaineOk = false;
        }else{//sinon
            if(this.nbPersonnelQNecessaire.get() < nbPersonnelsQDispo){
                int cptTrouve = 0;
                int i = 0;
                //ajouter le nombre necessaire de personnel qualifié
                while (cptTrouve < this.nbPersonnelQNecessaire.get() && i < Usine.getInstance().getPersonnelsQualifies().size()){
                    if (Usine.getInstance().getPersonnelsQualifies().get(i).etreDisponible()){
                        //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                        if(!this.PersonnelsQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsQualifies().get(i))){
                            //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                            chaineOk = this.assignerPersonnelQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsQualifies().get(i));
                            //si le personnel n'est plus disponible, l'enlever du compteur
                            if(!Usine.getInstance().getPersonnelsQualifies().get(i).etreDisponible()){
                                nbPersonnelsQDispo--;
                                cptTrouve++;
                            }
                        }
                    }
                    i++;
                }
                //TODO: finir de revoir la méthode
            }

            //si le non quali necessaire est supèrieur au disponible
            if(this.nbPersonnelNQNecessaire.get() > nbPersonnelsNQDispo){
                //si le non quali Necessaire est > quali Disponible
                if(this.nbPersonnelNQNecessaire.get() > nbPersonnelsQDispo){
                    //chaine impossible
                    chaineOk = false;
                }else{
                    chaineOk = true;
                    int cptTrouve = 0;
                    int i = 0;
                    //ajouter le nombre necessaire de personnel non qualifié
                    while (cptTrouve < nbPersonnelsNQDispo && i < Usine.getInstance().getPersonnelsNonQualifies().size()){
                        if (Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                            //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                            if(!this.PersonnelsNonQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsNonQualifies().get(i))){
                                //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                                chaineOk = this.assignerPersonnelNQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsNonQualifies().get(i));
                                //si le personnel n'est plus disponible, l'enlever du compteur
                                if(!Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                                    cptTrouve++;
                                    nbPersonnelsNQDispo--;
                                }
                            }
                        }
                        i++;
                    }

                    cptTrouve = 0;
                    i = 0;
                    //ajouter le nombre necessaire de personnel qualifié
                    while (cptTrouve < nbPersonnelsQDispo && i < Usine.getInstance().getPersonnelsQualifies().size()){
                        if (Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                            //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                            if(!this.PersonnelsQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsQualifies().get(i))){
                                //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                                chaineOk = this.assignerPersonnelQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsQualifies().get(i));
                                //si le personnel n'est plus disponible, l'enlever du compteur
                                if(!Usine.getInstance().getPersonnelsQualifies().get(i).etreDisponible()){
                                    cptTrouve++;
                                    nbPersonnelsQDispo--;
                                }
                            }
                        }
                        i++;
                    }
                }
            }else{
                chaineOk = true;
                //ajouter la bonne quandtité de personnels non qualifié à la chaine
                int cptTrouve = 0;
                int i = 0;
                //ajouter le nombre necessaire de personnel non qualifié
                while (cptTrouve < this.nbPersonnelNQNecessaire.get() && i < Usine.getInstance().getPersonnelsNonQualifies().size()){
                    if (Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                        //vérifier que le personnel n'est pas déjà attribué a la chaine avant de l'ajouter
                        if(!this.PersonnelsNonQualifiesConvoque.containsKey(Usine.getInstance().getPersonnelsNonQualifies().get(i))){
                            //assigner les heures au personnel concerné et l'ajouter au personnel assigné a la chaine
                            chaineOk = this.assignerPersonnelNQSemaine(niveauActivation.get(),nbSemaines,Usine.getInstance().getPersonnelsNonQualifies().get(i));
                            //si le personnel n'est plus disponible, l'enlever du compteur
                            if(!Usine.getInstance().getPersonnelsNonQualifies().get(i).etreDisponible()){
                                nbPersonnelsNQDispo--;
                                cptTrouve++;
                            }
                        }
                    }
                    i++;
                }
            }
        }

        return chaineOk;
    }

    /**
     *
     * @return
     */
    public String toStringV2() {
        String str = "Chaine {\n" +
                "\tcodeC = " + codeC.getValue() +
                "\tnom = " + nom.getValue().replaceAll("\\.", " ") +
                "\tniveauActivation = " + niveauActivation.getValue() +
                "\n\tentrées = ";
        for (Map.Entry<Element, Double> e : elementsEntree.get().entrySet()) {
            str += e.getKey().getCodeE(); // Possible d'utiliser .getNom() ?
            str += "\t";
        }
        str += "\n\tsorties = ";
        for (Map.Entry<Element, Double> e : elementsSortie.get().entrySet()) {
            str += e.getKey().getCodeE();
            str += "\t";
        }
        str += "\n}";
        return str;
    }

    /**
     *
     * @param nbHeures: nombre d'heures de déroulement de la chaine
     * @param nbSemaines : nombre de semaines de déroulement de la chaine
     * @param personnel : personnel non qualifié a modifier
     * @return si le personnel a bien été assigné
     */
    public boolean assignerPersonnelNQSemaine(double nbHeures,int nbSemaines,PersonnelNonQualifie personnel){
        boolean isOk = false;
        //insertion du personnel dans la liste
        this.PersonnelsNonQualifiesConvoque.get().put(personnel,nbHeures*nbSemaines);
        //si le nouveau nombre d'heures est superieur a 0
        if(personnel.getNbHeuresDispo() - nbHeures*nbSemaines > 0){
            //changer le nombre d'heures assignés au personnel
            personnel.setNbHeuresAssignes(personnel.getNbHeuresAssignes() + nbHeures*nbSemaines);
            //changer le nombre d'heures disponibles du personnel
            personnel.setNbHeuresDispo(personnel.getNbHeuresDispo() - nbHeures*nbSemaines);
            isOk = true;
        }
        return isOk;
    }

    /***
     *
     * @param nbHeures: nombre d'heures de déroulement de la chaine
     * @param nbSemaines : nombre de semaines de déroulement de la chaine
     * @param personnel : personnel non qualifié a modifier
     * @return si le personnel a bien été assigné
     */
    public boolean assignerPersonnelQSemaine(double nbHeures,int nbSemaines,PersonnelQualifie personnel){
        boolean isOk = false;
        //insertion du personnel dans la liste
        this.PersonnelsQualifiesConvoque.put(personnel,nbHeures*nbSemaines);
        //si le nouveau nombre d'heures est superieur a 0
        if(personnel.getNbHeuresDispo() - nbHeures*nbSemaines > 0){
            //changer le nombre d'heures assignés au personnel
            personnel.setNbHeuresAssignes(personnel.getNbHeuresAssignes() + nbHeures*nbSemaines);
            //changer le nombre d'heures disponibles du personnel
            personnel.setNbHeuresDispo(personnel.getNbHeuresDispo() - nbHeures*nbSemaines);
            isOk = true;
        }
        return isOk;
    }

    /**
     * Méthodes affichant les caractéristiques d'une chaîne.
     * @return ces caractéristiques en tant que String.
     */
    @Override
    public String toString() {
        return "Chaine {\n" +
                "\tcodeC = " + codeC.getValue() +
                "\tnom = " + nom.getValue() +
                "\tniveauActivation = " + niveauActivation.getValue() +
                "\telementsEntree = { " + toStringElementsEnEntree() +"\n } "+
                "\telementsSortie = { " + toStringElementsEnSortie()+"\n } "+
                "\n}";
    }

    /**
     * transforme en string la liste des éléments en entrée
     * @return
     */
    public String toStringElementsEnEntree(){
        String valeurToString = "";
        for(Map.Entry<Element, Double> entree : this.elementsEntree.entrySet()) {
            valeurToString += ""+entree.getValue().toString() + " * "+entree.getKey()+" , ";
        }
        return  valeurToString;
    }

    /**
     * transforme en string la liste des éléments en sortie
     * @return
     */
    public String toStringElementsEnSortie(){
        String valeurToString = "";
        for(Map.Entry<Element, Double> sortie : this.elementsSortie.entrySet()) {
            valeurToString += ""+sortie.getValue().toString() + " * "+sortie.getKey()+" , ";
        }
        return  valeurToString;
    }

    /**
     * transforme en string la liste des éléments en entrée
     * @return
     */
    public String toStringElementsInterface(SimpleMapProperty<Element,Double> liste){
        String valeurToString = "";
        for(Map.Entry<Element, Double> element : liste.entrySet()) {
            valeurToString += element.getKey().getNom()+" x "+element.getValue().toString() +" \n ";
        }
        return  valeurToString;
    }

    /**
     * Getter sur l'attribut elementsSortie.
     * @return l'ensemble des éléments en sortie en tant que SimpleStringProperty.
     */
    public SimpleStringProperty toStringElementsEnSortieProperty(){
        return new SimpleStringProperty(toStringElementsInterface(this.elementsSortie));
    }

    /**
     * Getter sur l'attribut elementsEntree.
     * @return l'ensemble des éléments en entrée en tant que SimpleStringProperty.
     */
    public SimpleStringProperty toStringElementsEnEntreeProperty(){
        return new SimpleStringProperty(toStringElementsInterface(this.elementsEntree));
    }

    /**
     * Méthode comparant un objet à la chaine courante.
     * @param o l'objet comparé
     * @return true sur le code (String) des Chaine est identique.
     */
    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if((o instanceof Chaine) && (((Chaine) o).getCodeC().equals(this.codeC)))
            res = true;
        return res;
    }

    /**
     * donne le code chaine
     * @return le code de la chaine
     */
    public String getCodeC() {
        return codeC.get();
    }
    /**
     * donne la propriété code de la chaine
     * @return la propriété code de la chaine
     */
    public SimpleStringProperty codeCProperty() {
        return codeC;
    }

    /**
     * permet de modifier le code de la chaine
     * @param codeC
     */
    public void setCodeC(String codeC) {
        this.codeC.set(codeC);
    }

    /**
     * donne le nom de la chaine
     * @return le nom de la chaine
     */
    public String getNom() {
        return nom.get();
    }

    /**
     * retourne la propriété nom
     * @return la propriété nom
     */
    public SimpleStringProperty nomProperty() {
        return new SimpleStringProperty(nom.get().replace('.', ' '));
    }

    /**
     * moddifie le nom de la chaine
     * @param nom
     */
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    /**
     * modifie le niveau d'activation
     * @param niveauActivation
     */
    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation = new SimpleIntegerProperty(niveauActivation);
    }

    /**
     * obtenir les éléments en sortie
     * @return les éléments en sortie
     */
    public ObservableMap<Element, Double> getElementsSortie() {
        return elementsSortie.get();
    }

    /**
     * obtenir le niveau d'activation
     * @return niveau d'activation
     */
    public int getNiveauActivation() {
        return niveauActivation.get();
    }

    /**
     * obtenir la propriété de niveau d'activation
     * @return
     */
    public SimpleIntegerProperty niveauActivationProperty() {
        return niveauActivation;
    }

    /**
     * Getter sur l'attribut personnelsQualifiesCovoque
     * @return l'ensemble des personnels qualifiés convoqués en tant que SimpleMapProperty.
     */
    public SimpleMapProperty<PersonnelQualifie,Double> getPersonnelsQualifiesConvoque() { return this.PersonnelsQualifiesConvoque; }

    /**
     * Getter sur l'attribut personnelsNonQualifiesCovoque
     * @return l'ensemble des personnels non-qualifiés convoqués en tant que SimpleMapProperty.
     */
    public SimpleMapProperty<PersonnelNonQualifie,Double> getPersonnelsNonQualifiesConvoque() { return this.PersonnelsNonQualifiesConvoque; }
}



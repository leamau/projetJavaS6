package org.app;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe permettant de créer et de gérer tout les personnels de l'usine
 */
public class Personnel {
    static int lastValueId = 0;

    /**
     * identifiant du personnel
     */
    private SimpleStringProperty id;
    /**
     * nom du personnel
     */
    private SimpleStringProperty nom;

    /**
     * prenom du personnel
     */
    private SimpleStringProperty prenom;

    /**
     * disponibilité du personnel
     */
    private SimpleBooleanProperty disponibilite;
    /**
     * nombre d'heure disponible de travail du personnel a la semaine
     */
    private SimpleDoubleProperty nbHeuresDispo;
    /**
     * nombre d'heures assigné au personnel à la semaine
     */
    private SimpleDoubleProperty nbHeuresAssignes;

    /**
     * constructeur de la classe avec incrémentation de l'id
     * @param nom
     * @param prenom
     * @param nbDispo
     * @param nbAssigne
     */
    public Personnel(String nom,String prenom, double nbDispo, double nbAssigne){
        lastValueId++;
        switch (String.valueOf(lastValueId).length()){
            case 1 :
                this.id = new SimpleStringProperty("P00"+lastValueId);
                break;
            case 2 :
                this.id = new SimpleStringProperty("P0"+lastValueId);
                break;
            case 3 :
                this.id = new SimpleStringProperty("P"+lastValueId);
                break;
        }
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.nbHeuresAssignes = new SimpleDoubleProperty(nbAssigne);
        this.nbHeuresDispo = new SimpleDoubleProperty(nbDispo);
        this.etreDisponible();
    }

    /**
     * constructeur de la classe avec toutes les données en entrée
     * @param id
     * @param nom
     * @param prenom
     * @param nbDispo
     * @param nbAssigne
     */
    public Personnel(String id, String nom,String prenom, double nbDispo, double nbAssigne){
        this.id = new SimpleStringProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.nbHeuresAssignes = new SimpleDoubleProperty(nbAssigne);
        this.nbHeuresDispo = new SimpleDoubleProperty(nbDispo);
        this.etreDisponible();
    }

    /**
     * met a jour la variable disponibilite en fonction de la disponibilité du personnel
     * @return le resultat de la disponibilité
     */
    public boolean etreDisponible(){
        //si le personnel a plus dh'eures disponibles que d'heures assignées il est disponible
        this.disponibilite = new SimpleBooleanProperty(this.nbHeuresDispo.get() > this.nbHeuresAssignes.get());
        return this.disponibilite.get();
    }

    /**
     * Méthodes assignant un nombre d'heures à une personne.
     * @param heures le nombre d'heure à ajouter.
     */
    public void ajouterHeuresAssignes(double heures){
        //ajouter une heure assignée
        this.nbHeuresAssignes = new SimpleDoubleProperty(this.nbHeuresAssignes.get() + heures);
        //déduire une heure de dispo
        this.nbHeuresDispo = new SimpleDoubleProperty(this.nbHeuresDispo.get() - heures);
    }

    /**
     * getters & setters
     */

    /**
     * recupérer l'id du personnel
     * @return l'id du personnel
     */
    public String getId() {
        return id.get();
    }

    /**
     * récupérer la propriété id
     * @return la propriété id
     */
    public SimpleStringProperty idProperty() {
        return id;
    }

    /**
     * modifier l'id du personnel
     * @param id
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * récupère le nom du personnel
     * @return le nom du personnel
     */
    public String getNom() {
        return nom.get();
    }

    /**
     * récupère la propriété nom du personnel
     * @return la propriété nom
     */
    public SimpleStringProperty nomProperty() {
        return nom;
    }

    /**
     * modifie le nom du personnel
     * @param nom
     */
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    /**
     * donne le nombre d'heures disponibles
     * @return le nombre d'heures disponibles
     */
    public double getNbHeuresDispo() {
        return nbHeuresDispo.get();
    }

    /**
     * retourne la propriété nombre d'heures disponibles
     * @return la propriété nombre d'heures disponibles
     */
    public SimpleDoubleProperty nbHeuresDispoProperty() {
        return nbHeuresDispo;
    }

    /**
     * modifie le nombre d'heures disponibles
     * @param nbHeuresDispo
     */
    public void setNbHeuresDispo(double nbHeuresDispo) {
        this.nbHeuresDispo.set(nbHeuresDispo);
    }

    /**
     * obtenir le nombre d'heures assignées
     * @return nombre d'heures assignées
     */
    public double getNbHeuresAssignes() {
        return nbHeuresAssignes.get();
    }

    /**
     * retourne la propriété nombre d'heures assignées
     * @return propriété nombre d'heures assignées
     */
    public SimpleDoubleProperty nbHeuresAssignesProperty() {
        return nbHeuresAssignes;
    }

    /**
     * modifier le nombre d'heures assignées
     * @param nbHeuresAssignes
     */
    public void setNbHeuresAssignes(double nbHeuresAssignes) {
        this.nbHeuresAssignes.set(nbHeuresAssignes);
    }

    /**
     * Getter sur l'attribut prenom.
     * @return le prénom en tant que SimpleStringProperty.
     */
    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    /**
     * Getter sur l'attribut disponibilite.
     * @return la disponibilité en tant que SimpleBooleanProperty.
     */
    public SimpleBooleanProperty disponibiliteProperty() {
        return disponibilite;
    }

    /**
     * Setter sur l'attribut disponibilite.
     * @param disponibilite la nouvelle disponibilité de la personne.
     */
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite.set(disponibilite);
    }

    /**
     * Méthode affichant les caractéristiques d'un personnel.
     * @return ces caractéristiques en tant que String.
     */
    @Override
    public String toString(){

        return "Personne {\n" +
                "\tid = '" + this.id.getValue() +
                "\tnom = '" + this.nom.getValue() +
                "\tprenom = " + this.prenom.getValue() +
                "\tdisponibilite = " + this.disponibilite.getValue() +
                "\tnbHeuresAssignes = " + this.nbHeuresAssignes.getValue() +
                "\tnbHeuresDispo = " + this.nbHeuresDispo.getValue() +
                "\n}";
    }

}

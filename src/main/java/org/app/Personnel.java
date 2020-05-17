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

    public void ajouterHeuresAssignes(double heures){
        //ajouter une heure assignée
        this.nbHeuresAssignes = new SimpleDoubleProperty(this.nbHeuresAssignes.get() + heures);
        //déduire une heure de dispo
        this.nbHeuresDispo = new SimpleDoubleProperty(this.nbHeuresDispo.get() - heures);
    }

    /**
     * getters & setters
     */
    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public boolean isDisponibilite() {
        return disponibilite.get();
    }

    public SimpleBooleanProperty disponibiliteProperty() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite.set(disponibilite);
    }

    public double getNbHeuresDispo() {
        return nbHeuresDispo.get();
    }

    public SimpleDoubleProperty nbHeuresDispoProperty() {
        return nbHeuresDispo;
    }

    public void setNbHeuresDispo(double nbHeuresDispo) {
        this.nbHeuresDispo.set(nbHeuresDispo);
    }

    public double getNbHeuresAssignes() {
        return nbHeuresAssignes.get();
    }

    public SimpleDoubleProperty nbHeuresAssignesProperty() {
        return nbHeuresAssignes;
    }

    public void setNbHeuresAssignes(double nbHeuresAssignes) {
        this.nbHeuresAssignes.set(nbHeuresAssignes);
    }

    public String toString(){

        return "Personne{" +
                "id='" + this.id.getValue() + '\'' +
                ", nom='" + this.nom.getValue() + '\'' +
                ", prenom=" + this.prenom.getValue() +
                ", disponibilite=" + this.disponibilite.getValue() +
                ", nbHeuresAssignes=" + this.nbHeuresAssignes.getValue() +
                ", nbHeuresDispo=" + this.nbHeuresDispo.getValue() +
                "}\n";
    }

}

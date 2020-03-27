package org.example;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chaines {
    private SimpleStringProperty codeChaines;
    private SimpleStringProperty chainesProduitSortie;
    private SimpleIntegerProperty chainesTemps;
    private SimpleDoubleProperty chainesIndValeur;
    private SimpleIntegerProperty chainesIndCommande;

    public Chaines(String codeChaines, String chainesProduitSortie, int chainesTemps, double chainesIndValeur, int chainesIndCommande) {
        this.codeChaines = new SimpleStringProperty(codeChaines);
        this.chainesProduitSortie = new SimpleStringProperty(chainesProduitSortie);
        this.chainesTemps = new SimpleIntegerProperty(chainesTemps);
        this.chainesIndValeur = new SimpleDoubleProperty(chainesIndValeur);
        this.chainesIndCommande = new SimpleIntegerProperty(chainesIndCommande);
    }

    public String getCodeChaines() {
        return codeChaines.get();
    }

    public SimpleStringProperty codeChainesProperty() {
        return codeChaines;
    }

    public void setCodeChaines(String codeChaines) {
        this.codeChaines.set(codeChaines);
    }

    public String getChainesProduitSortie() {
        return chainesProduitSortie.get();
    }

    public SimpleStringProperty chainesProduitSortieProperty() {
        return chainesProduitSortie;
    }

    public void setChainesProduitSortie(String chainesProduitSortie) {
        this.chainesProduitSortie.set(chainesProduitSortie);
    }

    public int getChainesTemps() {
        return chainesTemps.get();
    }

    public SimpleIntegerProperty chainesTempsProperty() {
        return chainesTemps;
    }

    public void setChainesTemps(int chainesTemps) {
        this.chainesTemps.set(chainesTemps);
    }

    public double getChainesIndValeur() {
        return chainesIndValeur.get();
    }

    public SimpleDoubleProperty chainesIndValeurProperty() {
        return chainesIndValeur;
    }

    public void setChainesIndValeur(double chainesIndValeur) {
        this.chainesIndValeur.set(chainesIndValeur);
    }

    public int getChainesIndCommande() {
        return chainesIndCommande.get();
    }

    public SimpleIntegerProperty chainesIndCommandeProperty() {
        return chainesIndCommande;
    }

    public void setChainesIndCommande(int chainesIndCommande) {
        this.chainesIndCommande.set(chainesIndCommande);
    }
}

package org.example;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProduitsAchats {
    private SimpleStringProperty productAchatsCode;
    private SimpleIntegerProperty productAchatNbAchats;
    private SimpleDoubleProperty productAchatPrixTotal;
    private SimpleIntegerProperty productAchatNbVentes;
    private SimpleIntegerProperty productAchatDemande;

    public ProduitsAchats(String productAchatsCode, int productAchatNbAchats, double productAchatPrixTotal, int productAchatNbVentes, int productAchatDemande) {
        this.productAchatsCode = new SimpleStringProperty(productAchatsCode);
        this.productAchatNbAchats = new SimpleIntegerProperty(productAchatNbAchats);
        this.productAchatPrixTotal = new SimpleDoubleProperty(productAchatPrixTotal);
        this.productAchatNbVentes = new SimpleIntegerProperty(productAchatNbVentes);
        this.productAchatDemande = new SimpleIntegerProperty(productAchatDemande);
    }

    public String getProductAchatsCode() {
        return productAchatsCode.get();
    }

    public SimpleStringProperty productAchatsCodeProperty() {
        return productAchatsCode;
    }

    public void setProductAchatsCode(String productAchatsCode) {
        this.productAchatsCode.set(productAchatsCode);
    }

    public int getProductAchatNbAchats() {
        return productAchatNbAchats.get();
    }

    public SimpleIntegerProperty productAchatNbAchatsProperty() {
        return productAchatNbAchats;
    }

    public void setProductAchatNbAchats(int productAchatNbAchats) {
        this.productAchatNbAchats.set(productAchatNbAchats);
    }

    public double getProductAchatPrixTotal() {
        return productAchatPrixTotal.get();
    }

    public SimpleDoubleProperty productAchatPrixTotalProperty() {
        return productAchatPrixTotal;
    }

    public void setProductAchatPrixTotal(double productAchatPrixTotal) {
        this.productAchatPrixTotal.set(productAchatPrixTotal);
    }

    public int getProductAchatNbVentes() {
        return productAchatNbVentes.get();
    }

    public SimpleIntegerProperty productAchatNbVentesProperty() {
        return productAchatNbVentes;
    }

    public void setProductAchatNbVentes(int productAchatNbVentes) {
        this.productAchatNbVentes.set(productAchatNbVentes);
    }

    public int getProductAchatDemande() {
        return productAchatDemande.get();
    }

    public SimpleIntegerProperty productAchatDemandeProperty() {
        return productAchatDemande;
    }

    public void setProductAchatDemande(int productAchatDemande) {
        this.productAchatDemande.set(productAchatDemande);
    }
}

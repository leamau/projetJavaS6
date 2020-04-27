package org.app;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.System;

/**
 * Classe décrivant un élément (matière première ou produit)
 */
public class Element {

	//TODO:différencier les produits des matières premières dans le but d'avoir un meilleur affichage dans l'interface graphique a terme

	// Attributs.
	/**
	 * atribut statique permettant l'incrémentation du code de l'élément
	 */
	static int lastValueId = 0;
	/**
	 * Code unique de l'élément.
	 */
	private SimpleStringProperty codeE;
	
	/**
	 * Désignation de l'élément.
	 */
	private SimpleStringProperty nom;
	
	/**
	 * Quantité disponible en stock.
	 */
	private SimpleDoubleProperty quantiteStock;
	/**
	 * L'unité avec laquelle on exprime la quantité du produit.
	 */
	//private Unite uniteMesure;
	private SimpleStringProperty uniteMesure;

	/**
	 * Le prix auquel le produit est acheté.
	 */
	private SimpleDoubleProperty prixAchat;

	/**
	 * Le prix auquel est vendu le produit.
	 */
	private SimpleDoubleProperty prixVente;

	/**
	 * Le demande estimée sur le produit.
	 */
	private SimpleDoubleProperty demande;

	private SimpleDoubleProperty qteAcheter;



	/**
	 * constructeur de la classe avec tous les attributs en entrée
	 * @param nom : nom du produit
	 * @param qte : quantité en stock de l'élément
	 * @param prixAchat : prix d'achat dé l'élément
	 * @param prixVente : prix de vente de l'élément
	 * @param demande :  demande lié a l'élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 */
	public Element(final String codeE,final String nom,final double qte,final double prixAchat,final double prixVente,final double demande,final /*Unite*/String uniteMesure,final double qteAcheter){
		//TODO:verifier que le code n'existe pas déjà dans la liste avant de l'insérer
		this.codeE = new SimpleStringProperty(codeE);
		this.nom = new SimpleStringProperty(nom);
		this.quantiteStock = new SimpleDoubleProperty(qte);
		this.prixAchat = new SimpleDoubleProperty(prixAchat);
		this.prixVente = new SimpleDoubleProperty(prixVente);
		this.demande = new SimpleDoubleProperty(demande);
		this.qteAcheter = new SimpleDoubleProperty(qteAcheter);
		this.uniteMesure = new SimpleStringProperty(uniteMesure);
	}

	public Element(String code, String nom,String unite,double qte){
		this.codeE = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.quantiteStock = new SimpleDoubleProperty(qte);
		this.uniteMesure = new SimpleStringProperty(unite);
		this.qteAcheter = new SimpleDoubleProperty(0);
		this.prixAchat = new SimpleDoubleProperty(0);
		this.prixVente = new SimpleDoubleProperty(0);
	}

	/**
	 * constructeur de la classe avec tous les attributs en entrée
	 * @param nom : nom du produit
	 * @param qte : quantité en stock de l'élément
	 * @param prixAchat : prix d'achat dé l'élément
	 * @param prixVente : prix de vente de l'élément
	 * @param demande :  demande lié a l'élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 */
	public Element(final String nom,final double qte,final double prixAchat,final double prixVente,final double demande,final /*Unite*/String uniteMesure){
		lastValueId++;
		switch (String.valueOf(lastValueId).length()){
			case 1 :
				this.codeE = new SimpleStringProperty("E00"+lastValueId);
				break;
			case 2 :
				this.codeE = new SimpleStringProperty("E0"+lastValueId);
				break;
			case 3 :
				this.codeE = new SimpleStringProperty("E"+lastValueId);
				break;
		}
		this.nom = new SimpleStringProperty(nom);
		this.quantiteStock = new SimpleDoubleProperty(qte);
		this.prixAchat = new SimpleDoubleProperty(prixAchat);
		this.prixVente = new SimpleDoubleProperty(prixVente);
		this.demande = new SimpleDoubleProperty(demande);
		this.uniteMesure = new SimpleStringProperty(uniteMesure);
		this.qteAcheter = new SimpleDoubleProperty(0);
	}

	/**
	 * constructeur de la classe avec seulement le nom et la qte en entrée
	 * @param nom : nom du élément
	 * @param prixAchat : prix d'achat dé l'élément
	 * @param prixVente : prix de vente de l'élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 * @param
	 */
	public Element(final String nom,final double prixAchat,final double prixVente,final /*Unite*/String uniteMesure){
		lastValueId++;
		switch (String.valueOf(lastValueId).length()){
			case 1 :
				this.codeE = new SimpleStringProperty("E00"+lastValueId);
				break;
			case 2 :
				this.codeE = new SimpleStringProperty("E0"+lastValueId);
				break;
			case 3 :
				this.codeE = new SimpleStringProperty("E"+lastValueId);
				break;
		}
		this.nom = new SimpleStringProperty(nom);
		this.quantiteStock = new SimpleDoubleProperty(0);
		this.prixAchat = new SimpleDoubleProperty(prixAchat);
		this.prixVente = new SimpleDoubleProperty(prixVente);
		this.demande = new SimpleDoubleProperty(0);
		this.uniteMesure = new SimpleStringProperty(uniteMesure);
		this.qteAcheter = new SimpleDoubleProperty(0);
	}

	/**
	 * constructeur de la classe avec seulement le nom et la qte en entrée
	 * @param codeE
	 * @param nom : nom du élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 * @param
	 */
	public Element(final String codeE,final String nom,final /*Unite*/String uniteMesure){
		//TODO:verifier que le code n'existe pas déjà dans la liste avant de l'insérer
		this.codeE = new SimpleStringProperty(codeE);
		this.nom = new SimpleStringProperty(nom);
		this.prixAchat = new SimpleDoubleProperty(0);
		this.prixVente = new SimpleDoubleProperty(0);
		this.quantiteStock = new SimpleDoubleProperty(0);
		this.demande = new SimpleDoubleProperty(0);
		this.uniteMesure = new SimpleStringProperty(uniteMesure);
		this.qteAcheter = new SimpleDoubleProperty(0);
	}

	public Element(final String nom,final double prixA,final double qteAchat){
		this.nom = new SimpleStringProperty(nom);
		this.prixAchat = new SimpleDoubleProperty(prixA);
		this.qteAcheter = new SimpleDoubleProperty(qteAchat);
	}

	public SimpleDoubleProperty coutTotal(){
		return new SimpleDoubleProperty(this.qteAcheter.getValue() * this.prixAchat.getValue());
	}

	@Override
	public String toString() {
		return "Element{" +
				"codeE='" + codeE + '\'' +
				", nom='" + nom + '\'' +
				", quantiteStock=" + quantiteStock +
				", uniteMesure=" + uniteMesure +
				", prixAchat=" + prixAchat +
				", prixVente=" + prixVente +
				", demande=" + demande +
				'}';
	}

	// Getters & Setters.
	public String getCodeE() {
		return codeE.get();
	}

	public SimpleStringProperty codeEProperty() {
		return codeE;
	}

	public void setCodeE(String codeE) {
		this.codeE.set(codeE);
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

	public double getQuantiteStock() {
		return quantiteStock.get();
	}

	public SimpleDoubleProperty quantiteStockProperty() {
		return quantiteStock;
	}

	public void setQuantiteStock(double quantiteStock) {
		this.quantiteStock.set(quantiteStock);
	}

	public String getUniteMesure() {
		return uniteMesure.get();
	}

	public SimpleStringProperty uniteMesureProperty() {
		return uniteMesure;
	}

	public void setUniteMesure(String uniteMesure) {
		this.uniteMesure.set(uniteMesure);
	}

	public double getPrixAchat() {
		return prixAchat.get();
	}

	public SimpleDoubleProperty prixAchatProperty() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat.set(prixAchat);
	}

	public double getPrixVente() {
		return prixVente.get();
	}

	public SimpleDoubleProperty prixVenteProperty() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente.set(prixVente);
	}

	public double getDemande() {
		return demande.get();
	}

	public SimpleDoubleProperty demandeProperty() {
		return demande;
	}

	public void setDemande(double demande) {
		this.demande.set(demande);
	}

	public double getQteAcheter() {
		return qteAcheter.get();
	}

	public SimpleDoubleProperty qteAcheterProperty() {
		return qteAcheter;
	}

	public void setQteAcheter(double qteAcheter) {
		this.qteAcheter.set(qteAcheter);
	}

	/**
	 * Fonction comparant un élément à l'objet this.
	 * @param e l'élément à comparer.
	 * @return vrai si les éléments ont le même codeE.
	 */
	public boolean equals(Element e) {
		return (e.getCodeE().equals(this.codeE));
	}
}

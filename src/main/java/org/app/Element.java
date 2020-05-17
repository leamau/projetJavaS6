package org.app;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.System;

/**
 * Classe décrivant un élément (matière première ou produit)
 */
public class Element {

	// Attributs.
	/**
	 * Attribut statique permettant l'incrémentation du code de l'élément.
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
	private SimpleStringProperty uniteMesure;
	//private Unite uniteMesure; pour une amélioration future.

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

	/**
	 * La quantité à acheter pour ce produit.
	 */
	private SimpleDoubleProperty qteAcheter;

	/**
	 * Constructeur de la classe avec tous les attributs en entrée
	 * @param nom : nom du produit
	 * @param qte : quantité en stock de l'élément
	 * @param prixAchat : prix d'achat dé l'élément
	 * @param prixVente : prix de vente de l'élément
	 * @param demande :  demande lié a l'élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 */
	public Element(final String codeE,final String nom,final double qte,final double prixAchat,final double prixVente,final double demande,final /*Unite*/String uniteMesure,final double qteAcheter){
		this.codeE = new SimpleStringProperty(codeE);
		this.nom = new SimpleStringProperty(nom);
		this.quantiteStock = new SimpleDoubleProperty(qte);
		this.prixAchat = new SimpleDoubleProperty(prixAchat);
		this.prixVente = new SimpleDoubleProperty(prixVente);
		this.demande = new SimpleDoubleProperty(demande);
		this.qteAcheter = new SimpleDoubleProperty(qteAcheter);
		this.uniteMesure = new SimpleStringProperty(uniteMesure);
	}

	/**
	 * Constructeur sans les prix ni quantité.
	 * @param code : code du produit.
	 * @param nom : nom du produit.
	 * @param unite : unité de mesure du produit.
	 * @param qte : quantité de stock du produit.
	 */
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
	 * Constructeur de la classe avec seulement le nom et la qte en entrée
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
	 * Constructeur de la classe avec seulement le nom et la qte en entrée
	 * @param codeE : code du produit
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

	/**
	 * Constructeur avec seulement le nom, le prix et la quantité d'achat.
	 * @param nom : nom du produit.
	 * @param prixA : prix d'achat du produit.
	 * @param qteAchat : quantité d'achat du produit.
	 */
	public Element(final String nom,final double prixA,final double qteAchat){
		this.nom = new SimpleStringProperty(nom);
		this.prixAchat = new SimpleDoubleProperty(prixA);
		this.qteAcheter = new SimpleDoubleProperty(qteAchat);
	}

	/**
	 * Méthode calculant le coût prévisionnel du produit.
	 * @return une somme en euros sous la format d'un SimpleDoubleProperty.
	 */
	public SimpleDoubleProperty coutTotal(){
		return new SimpleDoubleProperty(this.qteAcheter.getValue() * this.prixAchat.getValue());
	}

	/**
	 * Méthode d'affichage des caractéristiques d'un élément.
	 * @return ces caractéristiques sous format d'un String.
	 */
	@Override
	public String toString() {
		return "Element{" +
				"codeE='" + codeE.getValue() + '\'' +
				", nom='" + nom.getValue() + '\'' +
				", quantiteStock=" + quantiteStock.getValue() +
				", uniteMesure=" + uniteMesure.getValue() +
				", prixAchat=" + prixAchat.getValue() +
				", prixVente=" + prixVente.getValue() +
				", demande=" + demande.getValue() +
				'}';
	}

	// Getters & Setters.

	/**
	 * Getter de l'attribut codeE.
	 * @return le code en tant que String.
	 */
	public String getCodeE() {
		return codeE.get();
	}

	/**
	 * Getter de l'attribut codeE.
	 * @return le code en tant que SimpleStringProperty.
	 */
	public SimpleStringProperty codeEProperty() {
		return codeE;
	}

	/**
	 * Setter de l'attribut codeE.
	 * @param codeE le nouveau code de l'élément.
	 */
	public void setCodeE(String codeE) {
		this.codeE.set(codeE);
	}

	/**
	 * Getter de l'attribut nom.
	 * @return le nom en tant que String.
	 */
	public String getNom() {
		return nom.get();
	}

	/**
	 * Getter de l'attribut nom.
	 * @return le nom en tant que SimpleStringProperty.
	 */
	public SimpleStringProperty nomProperty() {
		return nom;
	}

	/**
	 * Setter de l'attribut nom.
	 * @param nom le nouveau nom du produit.
	 */
	public void setNom(String nom) {
		this.nom.set(nom);
	}

	/**
	 * Getter de l'attribut quantiteStock.
	 * @return la quantité en stock en tant que double.
	 */
	public double getQuantiteStock() {
		return quantiteStock.get();
	}

	/**
	 * Getter de l'attribut quantiteStock.
	 * @return la quantité en stock en tant que SimpleDoubleProperty.
	 */
	public SimpleDoubleProperty quantiteStockProperty() {
		return quantiteStock;
	}

	/**
	 * Setter de l'attribut quantiteStock.
	 * @param quantiteStock la nouvelle quantité en stock.
	 */
	public void setQuantiteStock(double quantiteStock) {
		this.quantiteStock.set(quantiteStock);
	}

	/**
	 * Getter de l'attribut uniteMesure.
	 * @return l'unité de mesure en tant que SimpleStringProperty.
	 */
	public SimpleStringProperty uniteMesureProperty() {
		return uniteMesure;
	}

	/**
	 * Getter de l'attribut prixAchat.
	 * @return le prix d'achat en tant que double.
	 */
	public double getPrixAchat() {
		return prixAchat.get();
	}

	/**
	 * Getter de l'attribut prixAchat.
	 * @return le prix d'achat en tant que SimpleDoubleProperty.
	 */
	public SimpleDoubleProperty prixAchatProperty() {
		return prixAchat;
	}

	/**
	 * Setter de l'attribut prixAchat.
	 * @param prixAchat le prix d'achat actualisé.
	 */
	public void setPrixAchat(double prixAchat) {
		this.prixAchat.set(prixAchat);
	}

	/**
	 * Getter de l'attribut prixVente.
	 * @return le prix de vente en tant que double.
	 */
	public double getPrixVente() {
		return prixVente.get();
	}

	/**
	 * Setter de l'attribut prixVente.
	 * @param prixVente le prix de vente actualisé.
	 */
	public void setPrixVente(double prixVente) {
		this.prixVente.set(prixVente);
	}

	/**
	 * Setter de l'attribut demande.
	 * @param demande la demande actualisée.
	 */
	public void setDemande(double demande) {
		this.demande.set(demande);
	}

	/**
	 * Getter de l'attribut qteAcheter.
	 * @return la quantité à acheter en tant que SimpleDoubleProperty.
	 */
	public SimpleDoubleProperty qteAcheterProperty() {
		return qteAcheter;
	}

	/**
	 * Fonction comparant un élément à l'objet this.
	 * @param e l'élément à comparer.
	 * @return vrai si les éléments ont le même codeE.
	 */
	public boolean equals(Element e) {
		return (e.getCodeE().equals(this.codeE.getValue()));
	}

	/**
	 * Getter sur l'attribut qteAcheter.
	 * @return la quantité à acheter en tant que double.
	 */
	public double getQteAcheter() {
		return qteAcheter.get();
	}

	/**
	 * Setter sur l'attribut
	 * @param qteAcheter la nouvelle quantité à acheter.
	 */
	public void setQteAcheter(double qteAcheter) {
		this.qteAcheter.set(qteAcheter);
	}

}

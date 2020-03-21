package org.app;
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
	private String codeE;
	
	/**
	 * Désignation de l'élément.
	 */
	private String nom;
	
	/**
	 * Quantité disponible en stock.
	 */
	private double quantiteStock;
	/**
	 * L'unité avec laquelle on exprime la quantité du produit.
	 */
	private Unite uniteMesure;

	/**
	 * Le prix auquel le produit est acheté.
	 */
	private double prixAchat;

	/**
	 * Le prix auquel est vendu le produit.
	 */
	private double prixVente;

	/**
	 * Le demande estimée sur le produit.
	 */
	private double demande;


	/**
	 * constructeur de la classe avec tous les attributs en entrée
	 * @param nom : nom du produit
	 * @param qte : quantité en stock de l'élément
	 * @param prixAchat : prix d'achat dé l'élément
	 * @param prixVente : prix de vente de l'élément
	 * @param demande :  demande lié a l'élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 */
	public Element(final String nom,final double qte,final double prixAchat,final double prixVente,final double demande,final Unite uniteMesure){
		lastValueId++;
		switch (String.valueOf(lastValueId).length()){
			case 1 :
				this.codeE = "E00"+lastValueId;
				break;
			case 2 :
				this.codeE = "E0"+lastValueId;
				break;
			case 3 :
				this.codeE = "E"+lastValueId;
				break;
		}
		this.nom = nom;
		this.quantiteStock = qte;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.demande = demande;
		this.uniteMesure = uniteMesure;
	}

	/**
	 * constructeur de la classe avec seulement le nom et la qte en entrée
	 * @param nom : nom du élément
	 * @param prixAchat : prix d'achat dé l'élément
	 * @param prixVente : prix de vente de l'élément
	 * @param uniteMesure : unité de mesure de la quantitée de l'élément
	 * @param
	 */
	public Element(final String nom,final double prixAchat,final double prixVente,final Unite uniteMesure){
		lastValueId++;
		switch (String.valueOf(lastValueId).length()){
			case 1 :
				this.codeE = "E00"+lastValueId;
				break;
			case 2 :
				this.codeE = "E0"+lastValueId;
				break;
			case 3 :
				this.codeE = "E"+lastValueId;
				break;
		}
		this.nom = nom;
		this.quantiteStock = 0;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.demande = 0;
		this.uniteMesure = uniteMesure;
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
	/**
	 * Renvoie le code de l'élément.
	 * @return le code comme un chaîne de caractères.
	 */
	public String getCodeE() {
		return codeE;
	}

	/**
	 * Modifie le code de l'élément.
	 * @param codeE le code actualisé.
	 */
	public void setCodeE(final String codeE) {
		this.codeE = codeE;
	}

	/**
	 * Renvoie le nom de l'élément.
	 * @return le nom comme un chaîne de caractères.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de l'élément.
	 * @param nom le nouveau nom à afficher.
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie le stock de l'élément.
	 * @return la quantité (attention à l'unité).
	 */
	public double getQuantiteStock() {
		return quantiteStock;
	}

	/**
	 * Modifie le stock de l'élément.
	 * @param quantite la nouvelle quantité en stock.
	 */
	public void setQuantiteStock(final double quantite) {
		this.quantiteStock = quantite;
	}

	/**
	 * Renvoie l'unité avec laquelle l'élément est mesurée.
	 * @return la valeur sous une chaîne de caractère.
	 */
	public Unite getUniteMesure() {
		return uniteMesure;
	}

	/**
	 * Modifie l'unité avec laquelle l'élément est mesurée.
	 * @param uniteMesure
	 */
	public void setUniteMesure(final Unite uniteMesure) {
		this.uniteMesure = uniteMesure;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public double getDemande() {
		return demande;
	}

	public void setDemande(double demande) {
		this.demande = demande;
	}


}

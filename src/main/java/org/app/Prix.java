package org.app;

/**
 * Classe décrivant un prix.
 */
public class Prix {

	// Attributs.

	/**
	 * code de l'élément auquel correspond le prix
	 */
	private String codeElement;
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

	public Prix(String code,double achat,double vente,double demande){
		this.codeElement = code;
		this.prixAchat = achat;
	}

	// Getters & Setters.
	
	/**
	 * Renvoie le prix d'achat du produit.
	 * @return le prix en euro.
	 */
	public double getPrixAchat() {
		return prixAchat;
	}

	/**
	 * Modifie le prix d'achat du produit.
	 * @param prixAchat le nouveau prix à appliquer.
	 */
	public void setPrixAchat(final double prixAchat) {
		this.prixAchat = prixAchat;
	}

	/**
	 * Renvoie le prix de vente du produit.
	 * @return le prix en euro.
	 */
	public double getPrixVente() {
		return prixVente;
	}

	/**
	 * Modifie le prix de vente du produit.
	 * @param prixVente le nouveau prix à appliquer.
	 */
	public void setPrixVente(final double prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Renvoie la demande estimée pour ce produit.
	 * @return la demande en unité ou masse.
	 */
	public double getDemande() {
		return demande;
	}

	/**
	 * Modifie la demande estimée pour ce produit.
	 * @param demande la nouvelle estimation à appliquer.
	 */
	public void setDemande(final double demande) {
		this.demande = demande;
	}	
}

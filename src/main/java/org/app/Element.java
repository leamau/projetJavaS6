package org.app;

/**
 * Classe décrivant un élément (matière première ou produit)
 */
public class Element {

	// Attributs.
	
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
	private double quantite;
	
	/**
	 * Enumération des unités que peut prendre un produit.
	 */
	private enum Unite {
		pièce,
		g,
		kg,
		m²,
		L,
		cl
	}
	
	/**
	 * L'unité avec laquelle on exprime la quantité du produit.
	 */
	private Unite uniteMesure;
	
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
	public double getQuantite() {
		return quantite;
	}

	/**
	 * Modifie le stock de l'élément.
	 * @param quantite la nouvelle quantité en stock.
	 */
	public void setQuantite(final double quantite) {
		this.quantite = quantite;
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
}

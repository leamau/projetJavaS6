package org.app;

/**
 * Cette classe permet de définir quel personnel est qualifié dans l'usine
 */
public class PersonnelQualifie extends Personnel  {

    /**
     * Constructeur du personnel non qualifié sans identifiant
     * @param nom
     * @param prenom
     * @param nbDispo
     * @param nbAssigne
     */
    public PersonnelQualifie(String nom, String prenom, double nbDispo, double nbAssigne) {
        super(nom, prenom, nbDispo, nbAssigne);
    }

    /**
     * Constructeur du personnel non qualifié avec un identifiant
     * @param id
     * @param nom
     * @param prenom
     * @param nbDispo
     * @param nbAssigne
     */
    public PersonnelQualifie(String id, String nom, String prenom, double nbDispo, double nbAssigne) {
        super(id, nom, prenom, nbDispo, nbAssigne);
    }
}

package org.app;

public class PersonnelNonQualifie extends Personnel {
    public PersonnelNonQualifie(String nom, String prenom, double nbDispo, double nbAssigne) {
        super(nom, prenom, nbDispo, nbAssigne);
    }

    public PersonnelNonQualifie(String id, String nom, String prenom, double nbDispo, double nbAssigne) {
        super(id, nom, prenom, nbDispo, nbAssigne);
    }
}

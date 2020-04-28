package org.app;

public class PersonnelQualifie extends Personnel  {

    public PersonnelQualifie(String nom, String prenom, double nbDispo, double nbAssigne) {
        super(nom, prenom, nbDispo, nbAssigne);
    }

    public PersonnelQualifie(String id, String nom, String prenom, double nbDispo, double nbAssigne) {
        super(id, nom, prenom, nbDispo, nbAssigne);
    }
}

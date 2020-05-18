package org.test;

import org.app.Usine;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe permettant de tester l'exportation de la simulation et du personnel.
 */
public class TestExport {

    /**
     * Permet d'executer les méthodes d'export de contenus des classes chaîne et personnel
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {

        // Export des chaînes en txt.
        Usine.getInstance().exportChainesTxt();
        Usine.getInstance().exportPersonnelTxt();
    }
}

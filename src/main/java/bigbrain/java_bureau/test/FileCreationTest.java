package bigbrain.java_bureau.test;

import java.io.File;
import java.io.IOException;

public class FileCreationTest {
    public static void main(String[] args) {
        String directoryPath = "C:\\HanjaDashaManel\\fichierscsv";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Répertoire créé : " + directoryPath);
            } else {
                System.out.println("Impossible de créer le répertoire : " + directoryPath);
                return;
            }
        }

        File file = new File(directoryPath + "/bonjourbonsoir.csv");
        try {
            if (file.createNewFile()) {
                System.out.println("Fichier créé : " + file.getAbsolutePath());
            } else {
                System.out.println("Le fichier existe déjà.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
        }
    }
}

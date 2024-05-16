package bigbrain.java_bureau.classe_java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcrireFichier {

    public static void ecrireHistorique(String filePath) {
        List<String> historique = HistoriqueAction.getHistorique();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String action : historique) {
                writer.write(action);
                writer.newLine();
            }
            System.out.println("Historique écrit dans le fichier " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture de l'historique dans le fichier: " + e.getMessage());
        }
    }
}

package bigbrain.java_bureau.classe_java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * Classe qui permet d'écrire des données dans un fichier.
 * Cette classe offre une méthode statique pour écrire un historique d'actions dans un fichier.
 */
public class EcrireFichier {
    /**
     * Écrit l'historique des actions dans un fichier texte.
     * Cette méthode prend le chemin du fichier en tant que paramètre, récupère l'historique des actions
     * depuis une source statique, et écrit chaque action dans le fichier avec une nouvelle ligne pour chaque action.
     *
     * @param filePath Le chemin du fichier où l'historique doit être écrit.
     */
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

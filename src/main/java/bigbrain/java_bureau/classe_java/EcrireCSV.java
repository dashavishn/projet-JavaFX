package bigbrain.java_bureau.classe_java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Cette classe gère l'écriture des données dans des fichiers CSV.
 * Elle permet d'écrire les informations sur les éléments et les modifications de stock.
 * @author HanjaRajaobelison
 */
public class EcrireCSV {

    /**
     * Efface le contenu d'un fichier CSV.
     * @param filePath Chemin d'accès au fichier CSV.
     */

    public static void clearCSVFile(String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");  // Écrire une chaîne vide pour effacer le fichier.
        } catch (IOException e) {
            System.err.println("Erreur lors de l'effacement du fichier CSV : " + filePath);
            e.printStackTrace();
        }
    }



    /**
     * Écrit les données des éléments dans un fichier CSV.
     * @param filePath Chemin d'accès au fichier CSV.
     * @param elements Liste des éléments à écrire.
     */
    public static void writeElementsToCSV(String filePath, List<Element> elements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Element element : elements) {
                // Utilisation de getQuantiteStock() au lieu de getQuantiteStockmodifiee()
                String line = String.format("%s;%s;%.2f;%s;%.2f;%.2f",
                        element.getCode(), element.getNom(), element.getQuantiteStock(), element.getUniteMesure(), element.getPrixAchat(), element.getPrixVente());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture des éléments dans le fichier CSV : " + filePath);
            e.printStackTrace();
        }
    }


    /**
     * Écrit l'historique des modifications dans un fichier CSV.
     * @param filePath Chemin d'accès au fichier CSV.
     * @param modifications Liste des modifications à écrire.
     */
    public static void writeModificationsToCSV(String filePath, List<ModificationStockElement> modifications) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ModificationStockElement mod : modifications) {
                String line = String.format("%s;%s;%.2f;%s;%.2f;%.2f",
                        mod.getCode(), mod.getNom(), mod.getQuantiteModifiee(), mod.getUniteMesure(), mod.getPrixAchat(), mod.getPrixVente());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Données de l'ArrayList reportées dans le fichier CSV : " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture de l'historique des modifications dans le fichier CSV : " + filePath);
            e.printStackTrace();
        }
    }
}

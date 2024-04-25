package bigbrain.java_bureau.classe_java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Cette classe gère l'écriture de données dans des fichiers CSV (Comma-Separated Values).
 * Elle fournit des méthodes pour :
 *  - vider le contenu d'un fichier CSV existant
 *  - écrire les données d'une liste d'éléments dans un fichier CSV
 *  - écrire les données d'une liste d'objets ChangementStock dans un fichier CSV
 *
 * @author HanjaRajaobelison
 */
public class EcrireCSV {

    public static void clearCSVFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Écriture d'une chaîne vide pour vider le contenu
            writer.write("");
            System.out.println("Contenu du fichier CSV vidé : " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la suppression du contenu du fichier CSV : " + filePath);
        }
    }


    public static void ecrireCSVFile (String filePath, ArrayList<Element> elements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Écriture des données de la liste dans le fichier CSV
            for (Element element : elements) {
                // Construction de la ligne CSV à partir des attributs de l'élément
                String csvLine = element.getCode() + ";" + element.getNom() + ";" + element.getQuantiteStock() +
                        ";" + element.getUniteMesure() + ";" + element.getPrixAchat() + ";" + element.getPrixVente();

                writer.write(csvLine);
                writer.newLine();
            }
            System.out.println("Données de la liste enregistrées dans le fichier CSV : " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'enregistrement des données dans le fichier CSV : " + filePath);
        }
    }


    public static void ecrireChangementCSV (String filePath, ArrayList<ModificationStockElement> Historique) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for ( ModificationStockElement change : Historique) {
                String csvLine = change.getCode() + ";" + change.getNom() + ";" + change.getQuantiteStockmodifiee() +
                        ";" + change.getUniteMesure() + ";" + change.getPrixAchat() + ";" + change.getPrixVente();

                writer.write(csvLine);
                writer.newLine();
            }
            System.out.println("Données de la liste enregistrées dans le fichier CSV : " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'enregistrement des données dans le fichier CSV : " + filePath);
        }
    }
}


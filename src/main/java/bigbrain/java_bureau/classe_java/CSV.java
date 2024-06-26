package bigbrain.java_bureau.classe_java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Classe responsable de la lecture des données des chaînes de production et des éléments à partir de fichiers CSV.
 */
public class CSV {
    /**
     * Liste stockant toutes les chaînes de production chargées.
     */
    public static ArrayList<ChaineProduction> liste = new ArrayList<ChaineProduction>();
    /**
     * Chemin d'accès au fichier CSV contenant les informations sur les éléments.
     */
    private static final String ELEMENTS_FILE_PATH = "C:\\HanjaDashaManel\\fichierscsv\\elements.csv";
    /**
     * Chemin d'accès au fichier CSV contenant les informations sur les chaînes de production.
     */
    private static final String CHAINES_FILE_PATH = "C:\\HanjaDashaManel\\fichierscsv\\chaines.csv";
    /**
     * Une méthode qui lit et charge les éléments à partir du fichier element.csv.
     * Les éléments sont ajoutés à l'inventaire global.
     */
    public void lireElements() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ELEMENTS_FILE_PATH))) {
            reader.readLine(); // Ignorer l'en-tête
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                double achat = data[5].equals("NA") ? 0 : Double.parseDouble(data[5]);
                double vente = data[4].equals("NA") ? 0 : Double.parseDouble(data[4]);
                Element element = new Element(data[0], data[1], Double.parseDouble(data[2]), data[3], achat, vente);
                Stocks.getInstance().ajouterElem(element, Double.parseDouble(data[2]));
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier des éléments: " + e.getMessage());
            System.err.println("Erreur lors de la lecture du fichier des éléments: " + e.getMessage());
        }
    }
    /**
     * Une méthode qui lit et charge les chaînes de production à partir du fichier chaines.csv.
     * Les chaînes sont stockées dans une liste statique et peuvent être ajoutées à un entrepôt de données.
     */
    public void lireChaines() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CHAINES_FILE_PATH))) {
            reader.readLine(); // Ignorer l'en-tête
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length < 4) {
                    System.err.println("Ligne incomplète ou mal formée: " + line);
                    continue; // Ignorer cette ligne et continuer avec la suivante
                }
                HashMap<Element, Float> entree = parseElements(data[2]);
                HashMap<Element, Float> sortie = parseElements(data[3]);

                ChaineProduction chaine = new ChaineProduction(data[0], data[1]);
                entree.forEach(chaine::ajouterElementEntree);
                sortie.forEach(chaine::ajouterElementSortie);
                Entrepot.addChaine(chaine);  // Assurez-vous d'ajouter la chaîne à votre entrepôt de données
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier des chaînes: " + e.getMessage());
        }
    }

    /**
     * Analyse une chaîne de caractères contenant des données d'éléments et les convertit en une map.
     * Chaque élément est associé à sa quantité nécessaire ou produite.
     *
     * @param elementData Données brutes des éléments sous forme de chaîne.
     * @return Une map associant chaque élément à sa quantité.
     */
    private HashMap<Element, Float> parseElements(String elementData) {
        HashMap<Element, Float> elements = new HashMap<>();
        if (elementData.isEmpty()) return elements;  // Retourner une map vide si la chaîne est vide

        elementData = elementData.replaceAll("[()]", ""); // Enlever les parenthèses
        for (String part : elementData.split(",")) {
            String[] detail = part.split(":");
            if (detail.length < 2) {
                System.err.println("Détail d'élément incomplet: " + part);
                continue; // Ignorer cette partie et continuer avec la suivante
            }
            Element el = Stocks.getInstance().getElement(detail[0]);
            if (el != null) {
                elements.put(el, Float.parseFloat(detail[1]));
            } else {
                System.err.println("Élément non trouvé: " + detail[0]);
            }
        }
        return elements;
    }


}
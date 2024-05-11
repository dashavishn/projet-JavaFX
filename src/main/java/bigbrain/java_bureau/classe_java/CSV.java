package bigbrain.java_bureau.classe_java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Cette classe gère la lecture de données à partir de fichiers CSV (Comma-Separated Values).
 * Elle fournit des méthodes pour lire des éléments, des chaînes de production et l'historique des changements
 * à partir de fichiers CSV spécifiques.
 *
 * @author HanjaRajaobelison
 */
public class CSV {

    public static ArrayList<ChaineProduction> Chaines = new ArrayList<ChaineProduction>();

    /**
     * Lit les informations sur les éléments d'un fichier CSV et les ajoute au stock.
     *
     * @throws FileNotFoundException Si le fichier CSV d'éléments n'est pas trouvé.
     * @throws IOException           En cas d'erreur de lecture du fichier CSV.
     */

    public void LireElement() {
        String file = "/bigbrain/fichierscsv/elements.csv";
        String line = "";
        try {
            // Utilisation de getResourceAsStream() pour obtenir un InputStream à partir d'une ressource
            InputStream is = getClass().getResourceAsStream(file);
            if (is == null) {
                throw new FileNotFoundException("Le fichier n'a pas été trouvé dans les ressources.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            // Lire et ignorer la première ligne (souvent utilisée pour les en-têtes)
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                float r2 = Float.parseFloat(row[2]);
                double r4 = getDouble(row[4]);
                double r5 = getDouble(row[5]);
                System.out.println("Ligne lue : " + line);
                for (String value : row) {
                    System.out.println("Valeur : " + value);
                }
                Element elem = new Element(row[0], row[1], r2, row[3], r4, r5);
                Stocks.ajouterElem(elem, r2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LireChaine() {
        String file = "/bigbrain/fichierscsv/chaines.csv";
        String line = "";
        try {
            // Utilisation de getResourceAsStream() pour obtenir un InputStream à partir d'une ressource
            InputStream is = getClass().getResourceAsStream(file);
            if (is == null) {
                throw new FileNotFoundException("Le fichier n'a pas été trouvé dans les ressources.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            // Lire et ignorer la première ligne (souvent utilisée pour les en-têtes)
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                HashMap<Element, Float> ElementEntree = new HashMap<Element, Float>();
                String[] elementEntree = row[2].split(",");
                for (String data : elementEntree) {
                    data = data.replaceAll("[()\\s]+", "");
                    String[] info = data.split(":");
                    String code = info[0];
                    float quantite = Float.parseFloat(info[1]);
                    Element e = Element.trouverElement(code);
                    ElementEntree.put(e, quantite);
                    e.setQuantiteStock(quantite);
                }
                HashMap<Element, Float> ElementSortie = new HashMap<Element, Float>();
                String[] elementSortie = row[3].split(",");
                for (String data : elementSortie) {
                    data = data.replaceAll("[()\\s]+", "");
                    String[] info = data.split(":");
                    String code = info[0];
                    float quantite = Float.parseFloat(info[1]);
                    Element e = Element.trouverElement(code);
                    ElementSortie.put(e, quantite);
                    e.setQuantiteStock(quantite);
                }

                ChaineProduction chaine = new ChaineProduction(row[0], row[1], ElementEntree, ElementSortie);
                Chaines.add(chaine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getDouble(String value) {
        double doubleValue;
        try {
            doubleValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            doubleValue = 0;
        }
        return doubleValue;
    }
}

package bigbrain.java_bureau.classe_java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public void LireElement () {
        String file = "/ressources/bigbrain/FichiersV1(2)/elements.csv";

        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                float r2 = Float.parseFloat(row[2]);
                double r4 = Double.parseDouble(row[4]);
                double r5 = Double.parseDouble(row[5]);
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
        String file = "src/main/ressources/bigbrain/FichiersV1(2)/elements.csv";
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
                    e.setQuantite(quantite);

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
                    e.setQuantite(quantite);

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
}

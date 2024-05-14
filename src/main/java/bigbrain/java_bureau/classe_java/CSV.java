package bigbrain.java_bureau.classe_java;

import java.io.*;
import java.util.HashMap;

public class CSV {

    private static final String ELEMENTS_FILE_PATH = "/bigbrain/fichierscsv/elements.csv";
    private static final String CHAINES_FILE_PATH = "/bigbrain/fichierscsv/chaines.csv";

    public void lireElements() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(ELEMENTS_FILE_PATH)))) {
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
        }
    }

    public void lireChaines() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(CHAINES_FILE_PATH)))) {
            reader.readLine(); // Ignorer l'en-tête
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                HashMap<Element, Float> entree = parseElements(data[2]);
                HashMap<Element, Float> sortie = parseElements(data[3]);

                ChaineProduction chaine = new ChaineProduction(data[0], data[1]);
                entree.forEach(chaine::ajouterElementEntree);
                sortie.forEach(chaine::ajouterElementSortie);
                GestionChaine.getInstance().addChaine(chaine);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier des chaînes: " + e.getMessage());
        }
    }

    private HashMap<Element, Float> parseElements(String elementData) {
        HashMap<Element, Float> elements = new HashMap<>();
        elementData = elementData.replaceAll("[()]", ""); // Enlever les parenthèses
        for (String part : elementData.split(",")) {
            String[] detail = part.split(":");
            Element el = Stocks.getInstance().getElement(detail[0]);
            if (el != null) {
                elements.put(el, Float.parseFloat(detail[1]));
            }
        }
        return elements;
    }
}

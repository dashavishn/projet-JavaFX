package bigbrain.java_bureau.test;


import bigbrain.java_bureau.classe_java.ChaineProduction;
import bigbrain.java_bureau.classe_java.Element;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        // Création des éléments avec des stocks initiaux
        Element elementE004 = new Element("E004", "Produit E004", 10, "unit", 1.0, 2.0);
        Element elementE002 = new Element("E002", "Produit E002", 10, "unit", 1.0, 2.0);
        Element elementE003 = new Element("E003", "Produit E003", 10, "unit", 1.0, 2.0);
        Element elementE005 = new Element("E005", "Produit E005", 10, "unit", 1.0, 2.0);

        // Configuration des éléments d'entrée et de sortie pour la chaîne de production
        Map<Element, Float> elementEntree = new HashMap<>();
        elementEntree.put(elementE004, 1.0f);
        elementEntree.put(elementE002, 5.0f);
        elementEntree.put(elementE003, 1.0f);

        Map<Element, Float> elementSortie = new HashMap<>();
        elementSortie.put(elementE005, 1.0f);

        // Création de la chaîne de production
        ChaineProduction chaineProduction = new ChaineProduction("C001", "Propulsion", 1, elementEntree, elementSortie);

        // Test 1 : Validation avec stock suffisant
        try {
            resetStocks(elementE004, elementE002, elementE003, elementE005);
            boolean result = chaineProduction.valider();
            System.out.println("Test 1 - Validation avec stock suffisant: " + (result ? "Réussite" : "Échec"));
            printStocks(elementE004, elementE002, elementE003, elementE005);
        } catch (Exception e) {
            System.out.println("Test 1 - Erreur: " + e.getMessage());
        }

        // Test 2 : Validation avec stock insuffisant
        try {
            resetStocks(elementE004, elementE002, elementE003, elementE005);
            elementE002.setQuantiteStock(2);  // Insufficient stock for element E002
            boolean result = chaineProduction.valider();
            System.out.println("Test 2 - Validation avec stock insuffisant: " + (result ? "Réussite" : "Échec"));
            printStocks(elementE004, elementE002, elementE003, elementE005);
        } catch (Exception e) {
            System.out.println("Test 2 - Erreur: " + e.getMessage());
        }

        // Test 3 : Réduction du stock après validation
        try {
            resetStocks(elementE004, elementE002, elementE003, elementE005);
            chaineProduction.valider();
            System.out.println("Test 3 - Réduction du stock après validation");
            printStocks(elementE004, elementE002, elementE003, elementE005);
        } catch (Exception e) {
            System.out.println("Test 3 - Erreur: " + e.getMessage());
        }

        // Test 4 : Validation avec niveau d'activation zéro
        try {
            resetStocks(elementE004, elementE002, elementE003, elementE005);
            chaineProduction.setNiveauActivation(0);
            boolean result = chaineProduction.valider();
            System.out.println("Test 4 - Validation avec niveau d'activation zéro: " + (result ? "Réussite" : "Échec"));
            printStocks(elementE004, elementE002, elementE003, elementE005);
        } catch (Exception e) {
            System.out.println("Test 4 - Erreur: " + e.getMessage());
        }

        // Test 5 : Validation avec plusieurs niveaux d'activation
        try {
            resetStocks(elementE004, elementE002, elementE003, elementE005);
            chaineProduction.setNiveauActivation(2);
            boolean result = chaineProduction.valider();
            System.out.println("Test 5 - Validation avec niveau d'activation de 2: " + (result ? "Réussite" : "Échec"));
            printStocks(elementE004, elementE002, elementE003, elementE005);
        } catch (Exception e) {
            System.out.println("Test 5 - Erreur: " + e.getMessage());
        }
    }

    private static void resetStocks(Element... elements) {
        for (Element element : elements) {
            element.setQuantiteStock(10);
        }
    }

    private static void printStocks(Element... elements) {
        for (Element element : elements) {
            System.out.println("Stock de " + element.getCode() + " après test: " + element.getQuantiteStock());
        }
    }
}

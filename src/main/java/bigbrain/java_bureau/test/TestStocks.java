package bigbrain.java_bureau.test;

import bigbrain.java_bureau.classe_java.Stocks;
import bigbrain.java_bureau.classe_java.Element;
public class TestStocks {
    public static void main(String[] args) {
        Element e1 = new Element("E001", "Circuit principal", 50, "pieces", 50, 70);
        Element e2 = new Element("E002", "Plastique", 100, "kg", 3, 5);

        Stocks.ajouterElem(e1, 50);
        Stocks.ajouterElem(e2, 100);

        // Affichage des éléments après ajout
        System.out.println("Stock après ajout:");
        Stocks.getStockItems().values().forEach(System.out::println);

        // Tentative de retrait d'un stock
        try {
            Stocks.retirerStock("E001", 20);
            System.out.println("20 pièces retirées de E001");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Vérifier la disponibilité
        boolean disponible = Stocks.verifierDisponibilite("E002", 50);
        System.out.println("Disponibilité de 50 kg de E002: " + disponible);
    }
}

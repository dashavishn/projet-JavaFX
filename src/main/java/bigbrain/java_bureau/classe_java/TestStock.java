package bigbrain.java_bureau.classe_java;

import bigbrain.java_bureau.classe_java.*;

public class TestStock {

    public static void main(String[] args) {
        // Créer des éléments
        Element e1 = new Element("E001", "Element 1", 100, "kg", 10, 15);
        Element e2 = new Element("E002", "Element 2", 50, "litres", 5, 8);

        // Ajouter les éléments au stock
        Stocks.ajouterElem(e1, 100);  // Supposons que 100kg de e1 sont ajoutés au stock
        Stocks.ajouterElem(e2, 50);   // Supposons que 50 litres de e2 sont ajoutés

        // Afficher les stocks après ajout
        System.out.println("Après ajout:");
        for (Element e : Stocks.ElemStocks) {
            System.out.println(e);
        }

        // Tester la disponibilité et le retrait du stock
        if (Stocks.verifierDisponibilite(e1, 90)) {
            Stocks.enleverElem(e1, 90); // Essayer de retirer 90 kg de e1
            System.out.println("90 kg de " + e1.getNom() + " ont été retirés.");
        } else {
            System.out.println("Stock insuffisant pour retirer 90 kg de " + e1.getNom());
        }

        // Afficher les stocks après retrait
        System.out.println("Après retrait:");
        for (Element e : Stocks.ElemStocks) {
            System.out.println(e);
        }
    }


}
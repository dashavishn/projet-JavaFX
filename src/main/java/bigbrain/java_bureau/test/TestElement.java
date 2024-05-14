package bigbrain.java_bureau.test;

import bigbrain.java_bureau.classe_java.Element;

public class TestElement {
    public static void main(String[] args) {
        Element element = new Element("E001", "Circuit principal", 100, "pieces", 50, 70);

        // Test d'ajout de quantité
        element.setQuantiteStock(element.getQuantiteStock() + 50);
        System.out.println("Après ajout de 50 pièces, quantité stock: " + element.getQuantiteStock());

        // Test de soustraction de quantité
        element.setQuantiteStock(element.getQuantiteStock() - 30);
        System.out.println("Après retrait de 30 pièces, quantité stock: " + element.getQuantiteStock());

        // Test de calcul de prix d'achat
        double coutAchat = element.getPrixAchat() * 20;
        System.out.println("Coût d'achat pour 20 pièces: " + coutAchat);
    }
}

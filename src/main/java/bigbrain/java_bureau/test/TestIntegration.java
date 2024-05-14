package bigbrain.java_bureau.test;

import bigbrain.java_bureau.classe_java.ChaineProduction;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Stocks;

public class TestIntegration {
    public static void main(String[] args) {
        // Initialisation des stocks et chaîne de production
        Stocks.ajouterElem(new Element("E001", "Circuit principal", 200, "pieces", 50, 70), 200);
        Stocks.ajouterElem(new Element("E002", "Plastique", 500, "kg", 3, 5), 500);

        ChaineProduction chaine = new ChaineProduction("C001", "Chaîne de Circuits");
        chaine.ajouterElementEntree(Stocks.getElement("E001"), 50);
        chaine.ajouterElementSortie(Stocks.getElement("E002"), 300);
        chaine.setNiveauActivation(1);

        // Vérifie que la chaîne peut produire avec le stock actuel
        try {
            chaine.simulerProduction();
            System.out.println("Simulation réussie, stock suffisant.");
        } catch (Exception e) {
            System.out.println("Échec de la simulation: " + e.getMessage());
        }
    }
}

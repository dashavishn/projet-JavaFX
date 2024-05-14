package bigbrain.java_bureau.test;


import bigbrain.java_bureau.classe_java.ChaineProduction;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Stocks;

public class TestChaineProduction {
    public static void main(String[] args) {
        ChaineProduction chaine = new ChaineProduction("C001", "Chaîne de Circuits");
        Element e1 = new Element("E001", "Circuit principal", 200, "pieces", 50, 70);
        Element e2 = new Element("E002", "Plastique", 500, "kg", 3, 5);

        Stocks.ajouterElem(e1, 200);
        Stocks.ajouterElem(e2, 500);

        chaine.ajouterElementEntree(e1, 50);
        chaine.ajouterElementSortie(e2, 300);

        // Simulation de production
        try {
            double resultat = chaine.simulerProduction();
            System.out.println("Coût additionnel si manque de stock pour la production: " + resultat);
        } catch (Exception e) {
            System.out.println("Erreur lors de la simulation: " + e.getMessage());
        }
    }
}

/*package bigbrain.java_bureau.classe_java;

public class testAjoutElements {
    public static void main(String[] args) {
        ChaineProduction chaine = new ChaineProduction("C001", "Test Chaine");
        Element e1 = new Element("E001", "Composant A", 0, "unit", 10.0, 15.0);
        Element e2 = new Element("E002", "Composant B", 0, "unit", 5.0, 8.0);

        chaine.ajouterElementEntree(e1, 100f);
        chaine.ajouterElementSortie(e2, 50f);

        // Test pour vérifier les quantités d'éléments en entrée
        if (chaine.getElementEntree().get(e1) != 100f) {
            System.out.println("Erreur : Quantité d'entrée incorrecte pour e1");
        } else {
            System.out.println("Quantité d'entrée correcte pour e1");
        }

        // Test pour vérifier les quantités d'éléments en sortie
        if (chaine.getElementSortie().get(e2) != 50f) {
            System.out.println("Erreur : Quantité de sortie incorrecte pour e2");
        } else {
            System.out.println("Quantité de sortie correcte pour e2");
        }
    }
}

 */
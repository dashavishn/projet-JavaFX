package bigbrain.java_bureau.classe_java;

import java.util.HashMap;
import java.util.Map;

public class Stocks {
    private static final Stocks instance = new Stocks();
    public static Map<String, Element> stockItems = new HashMap<>();

    private Stocks() {}

    public static Stocks getInstance() {
        return instance;
    }

    public static Map<String, Element> getStockItems() {
        return stockItems;
    }

    public static void ajouterElem(Element e, double quantite) {
        stockItems.compute(e.getCode(), (code, element) -> {
            if (element == null) {
                e.setQuantiteStock(e.getQuantiteStock() + quantite);
                return e;
            } else {
                element.setQuantiteStock(element.getQuantiteStock() + quantite);
                return element;
            }
        });
    }

    public static void enleverElem(String code, double quantite) throws Exception {
        Element element = stockItems.get(code);
        if (element == null || element.getQuantiteStock() < quantite) {
            throw new Exception("Stock insuffisant pour " + code);
        }
        element.setQuantiteStock(element.getQuantiteStock() - quantite);
    }

    public static Element getElement(String code) {
        return stockItems.get(code);
    }

    public static boolean verifierDisponibilite(String code, double quantiteNecessaire) {
        Element foundElement = getElement(code);
        return foundElement != null && foundElement.getQuantiteStock() >= quantiteNecessaire;
    }

    public static void retirerStock(String code, double quantite) throws Exception {
        instance.enleverElem(code, quantite);
    }

    public static void ajouterStock(Element e, double quantite) {
        instance.ajouterElem(e, quantite);
    }

    // Ajoute les méthodes pour simuler les opérations sur le stock
    public Map<String, Double> simulerOperation() {
        Map<String, Double> simulation = new HashMap<>();
        for (Map.Entry<String, Element> entry : stockItems.entrySet()) {
            simulation.put(entry.getKey(), entry.getValue().getQuantiteStock());
        }
        return simulation;
    }


    // Méthode pour simuler l'ajout de stock sans affecter le stock réel
    public void simulerAjout(String code, double quantite) {
        Map<String, Double> simulation = simulerOperation();
        if (simulation.containsKey(code)) {
            simulation.put(code, simulation.get(code) + quantite);
        } else {
            simulation.put(code, quantite);
        }
        System.out.println("Simulation d'ajout pour " + code + ": " + simulation.get(code));
    }

    // Méthode pour simuler la suppression de stock sans affecter le stock réel
    public void simulerSuppression(String code, double quantite) throws Exception {
        Map<String, Double> simulation = simulerOperation();
        if (simulation.containsKey(code) && simulation.get(code) >= quantite) {
            simulation.put(code, simulation.get(code) - quantite);
        } else {
            throw new Exception("Simulation échouée, stock insuffisant pour " + code);
        }
        System.out.println("Simulation de suppression pour " + code + ": " + simulation.get(code));
    }
}

package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueAction {
    private static List<String> historique = new ArrayList<>();

    public static void ajouterAction(String action) {
        historique.add(action);
        System.out.println("Action ajoutée à l'historique: " + action);
    }

    public static List<String> getHistorique() {
        return historique;
    }

    public static void clearHistorique() {
        historique.clear();
        System.out.println("Historique effacé.");
    }
}

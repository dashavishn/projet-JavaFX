package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
import java.util.List;
/**
 * Fournit une gestion du journal d'historique des actions.
 * Cette classe est utilisée pour enregistrer, récupérer et effacer un historique des actions
 */
public class HistoriqueAction {
    private static List<String> historique = new ArrayList<>();
    /**
     * Ajoute une action au journal d'historique.
     * Chaque action ajoutée est également imprimée dans la console, aidant au suivi des activités.
     * @param action Une représentation sous forme de chaîne de l'action à ajouter à l'historique.
     */
    public static void ajouterAction(String action) {
        historique.add(action);
        System.out.println("Action ajoutée à l'historique: " + action);
    }
    /**
     * Récupère l'historique complet des actions sous forme d'une liste de chaînes de caractères.
     * Cette méthode permet d'accéder à l'historique.
     *
     * @return Une liste de chaînes de caractères, chaque chaîne représentant une action qui a été consignée.
     */
    public static List<String> getHistorique() {
        return historique;
    }
    /**
     * Efface toutes les entrées de l'historique des actions.
     */
    public static void clearHistorique() {
        historique.clear();
        System.out.println("Historique effacé.");
    }
}

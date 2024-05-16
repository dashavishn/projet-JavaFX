package bigbrain.java_bureau.classe_java;
import java.util.HashMap;
import java.util.Map;
/**
 * Classe de gestion des stocks pour le suivi et la manipulation des éléments stockés.
 */
public class Stocks {
    /**
     * Instance unique de la classe Stocks.
     */
    private static final Stocks instance = new Stocks();
   /**
     * HashMap stockant les éléments par leur code.
      */
    public static Map<String, Element> stockItems = new HashMap<>();
    /**
     * Constructeur privé pour empêcher l'instanciation externe de la classe.
     */
    private Stocks() {
    }
    /**
     * Retourne l'instance unique de la classe Stocks.
     *
     * @return L'instance de Stocks.
     */
    public static Stocks getInstance() {
        return instance;
    }

    /**
     * Retourne une HaspMap des éléments en stock.
     * @return Une map des éléments stockés, clé par code d'élément.
     */
    public static Map<String, Element> getStockItems() {
        return stockItems;
    }
    /**
     * Ajoute une quantité spécifiée d'un élément au stock.
     * Si l'élément est déjà présent dans le stock, la quantité est simplement augmentée.
     *
     * @param e L'élément à ajouter ou dont la quantité doit être augmentée.
     * @param quantite La quantité de l'élément à ajouter.
     */
    public static void ajouterElem(Element e, double quantite) {
        stockItems.compute(e.getCode(), (code, element) -> {
            if (element == null) {
                e.setQuantiteStock(quantite); // Juste définir si l'élément est nouveau
                return e;
            } else {
                element.setQuantiteStock(element.getQuantiteStock() + quantite); // Augmenter seulement ici pour éviter les doublons
                return element;
            }
        });
    }

    /**
     * Enlève une quantité spécifiée d'un élément du stock.
     * Lance une exception si l'élément n'est pas trouvé ou si la quantité demandée dépasse celle disponible.
     *
     * @param code Le code de l'élément à enlever.
     * @param quantite La quantité à enlever.
     * @throws Exception Si le stock est insuffisant ou si l'élément n'est pas trouvé.
     */
    public static void enleverElem(String code, double quantite) throws Exception {
        Element element = stockItems.get(code);
        if (element == null || element.getQuantiteStock() < quantite) {
            throw new Exception("Stock insuffisant pour " + code);
        }
        element.setQuantiteStock(element.getQuantiteStock() - quantite);
    }
    /**
     * Récupère un élément du stock par son code.
     *
     * @param code Le code de l'élément à récupérer.
     * @return L'élément trouvé ou null si aucun élément correspondant n'est trouvé.
     */
    public static Element getElement(String code) {
        Element foundElement = stockItems.get(code);
        if (foundElement == null) {
            System.out.println("Élément non trouvé pour le code: " + code);
        } else {
            System.out.println("Élément trouvé: " + foundElement);
        }
        return foundElement;
    }
    /**
     * Vérifie si une quantité suffisante d'un élément est disponible en stock.
     *
     * @param code Le code de l'élément à vérifier.
     * @param quantiteNecessaire La quantité nécessaire.
     * @return true si la quantité disponible est suffisante, false autrement.
     */
    public static boolean verifierDisponibilite(String code, double quantiteNecessaire) {
        Element foundElement = getElement(code);
        return foundElement != null && foundElement.getQuantiteStock() >= quantiteNecessaire;
    }
    /**
     * Retire une quantité d'un élément du stock.
     *
     * @param code Le code de l'élément dont la quantité doit être retirée.
     * @param quantite La quantité à retirer.
     * @throws Exception Si l'opération ne peut pas être complétée en raison de stock insuffisant.
     */
    public static void retirerStock(String code, double quantite) throws Exception {
        instance.enleverElem(code, quantite);
    }


}

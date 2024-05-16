package bigbrain.java_bureau.classe_java;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Classe représentant une chaîne de production. Gère les éléments entrants et sortants en fonction du niveau d'activation.
 */
public class ChaineProduction {
    private String code;
    private String nom;
    private int niveauActivation;
    private Map<Element, Float> elementEntree;
    private Map<Element, Float> elementSortie;
    private String strSortie;
    private String strEntree;
    /**
     * Constructeur pour initialiser la chaîne de production sans éléments spécifiques.
     * @param code Le code identifiant la chaîne.
     * @param nom Le nom de la chaîne.
     */
    public ChaineProduction(String code, String nom) {
        this.code = code;
        this.nom = nom;
        this.elementEntree = new HashMap<>();
        this.elementSortie = new HashMap<>();
    }
    /**
     * Constructeur pour initialiser la chaîne de production avec des éléments et un niveau d'activation.
     * @param code Le code identifiant la chaîne.
     * @param nom Le nom de la chaîne.
     * @param niveauActivation Le niveau d'activation initial de la chaîne.
     * @param elementEntree Les éléments nécessaires pour la production avec leurs quantités.
     * @param elementSortie Les produits finis de la chaîne avec leurs quantités.
     */
    public ChaineProduction(String code, String nom, int niveauActivation, Map<Element, Float> elementEntree, Map<Element, Float> elementSortie) {
        this.code = code;
        this.nom = nom;
        this.niveauActivation = niveauActivation;
        this.elementEntree = elementEntree;
        this.elementSortie = elementSortie;
    }

    public ChaineProduction(String code, String name, String part, String part1) {
        this.code = code;
        this.nom = name;
        this.strEntree = part;
        this.strSortie = part1;
    }
    public String getStrSortie() {
        return strSortie;
    }

    public String getStrEntree() {
        return strEntree;
    }
    // Méthodes d'accès pour obtenir des informations de la chaîne
    public String getCode() {
        return code;
    }
    public Map<Element, Float> getElementEntree() {
        return new HashMap<>(this.elementEntree);
    }
    public Map<Element, Float> getElementSortie() {
        return new HashMap<>(this.elementSortie);
    }
    public String getNom() {
        return nom;
    }
    public int getNiveauActivation() {
        return niveauActivation;
    }
    /**
     * Définit le niveau d'activation de la chaîne de production.
     * @param niveauActivation Le nouveau niveau d'activation.
     */
    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation = niveauActivation;
    }
    /**
     * Ajoute un élément à la liste des entrées nécessaires pour la production.
     * @param element L'élément à ajouter.
     * @param quantite La quantité nécessaire de cet élément.
     */
    public void ajouterElementEntree(Element element, float quantite) {
        this.elementEntree.put(element, quantite);
    }
    /**
     * Ajoute un élément à la liste des sorties produites par la chaîne.
     * @param element L'élément à ajouter.
     * @param quantite La quantité produite de cet élément.
     */
    public void ajouterElementSortie(Element element, float quantite) {
        this.elementSortie.put(element, quantite);
    }

    public String getStringElementEntree() {
        return elementEntree.entrySet().stream()
                .map(entry -> entry.getKey().getNom() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public String getStringElementSortie() {
        return elementSortie.entrySet().stream()
                .map(entry -> entry.getKey().getNom() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
    /**
     * Valide si la chaîne peut fonctionner en vérifiant les stocks disponibles par rapport aux besoins d'entrée.
     * @return true si la production peut démarrer, false sinon.
     * @throws Exception Si une erreur survient durant la validation.
     */
    public boolean valider() throws Exception {
        if (niveauActivation == 0) {
            System.out.println("Le niveau d'activation est de 0, la chaîne ne peut pas fonctionner.");
            return false;  // Retourne false au lieu de lancer une exception si le niveau d'activation est 0
        }

        // Vérifier les stocks pour chaque élément en entrée
        for (Map.Entry<Element, Float> entry : elementEntree.entrySet()) {
            Element element = entry.getKey();
            float quantiteNecessaire = entry.getValue() * niveauActivation;
            if (element.getQuantiteStock() < quantiteNecessaire) {
                System.out.println("Stock insuffisant pour l'élément: " + element.getCode() + ". Nécessaire: " + quantiteNecessaire + ", Disponible: " + element.getQuantiteStock());
                return false;  // Pas assez de stock pour démarrer la production
            }
        }

        // Consommer les éléments d'entrée
        for (Map.Entry<Element, Float> entry : elementEntree.entrySet()) {
            Element element = entry.getKey();
            float quantiteNecessaire = entry.getValue() * niveauActivation;
            element.vendre(quantiteNecessaire);  // Utiliser la méthode vendre pour diminuer le stock
            System.out.println("Consommation de l'élément: " + element.getCode() + ". Quantité consommée: " + quantiteNecessaire);
            HistoriqueAction.ajouterAction("Consommation de " + quantiteNecessaire + " de " + element.getCode() + " pour la chaîne " + this.nom);
        }

        // Produire les éléments de sortie
        for (Map.Entry<Element, Float> entry : elementSortie.entrySet()) {
            Element element = entry.getKey();
            float quantiteProduite = entry.getValue() * niveauActivation;
            element.acheter(quantiteProduite);  // Utiliser la méthode acheter pour augmenter le stock
            System.out.println("Production de l'élément: " + element.getCode() + ". Quantité produite: " + quantiteProduite);
            HistoriqueAction.ajouterAction("Production de " + quantiteProduite + " de " + element.getCode() + " par la chaîne " + this.nom);
        }

        return true;  // Production réussie
    }


}




package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
/**
 * Représente un élément matériel ou un produit stocké dans l'inventaire.
 * Chaque élément est identifié par un code unique et inclut des détails tels que la quantité en stock,
 * l'unité de mesure, les prix d'achat et de vente, et la quantité actuellement en production.
 */
public class Element {
    /**
     * Code unique de l'élément.
     */
    private String code;
    /**
     * Nom de l'élément.
     */
    private String nom;
    /**
     * Quantité actuelle de l'élément en stock.
     */
    private double quantiteStock;
    /**
     * Unité de mesure de l'élément (par exemple, kg, litre, unité).
     */
    private String uniteMesure;
    /**
     * Prix d'achat de l'élément.
     */
    private double prixAchat;
    /**
     * Prix de vente de l'élément.
     */
    private double prixVente;
    /**
     * Quantité de l'élément actuellement en production.
     */
    private double quantiteEnProduction;
    private static ArrayList<Element> elements = new ArrayList<>();

    /**
     * Constructeur pour créer un nouvel élément.
     *
     * @param code Code unique de l'élément.
     * @param nom Nom de l'élément.
     * @param quantiteStock Quantité initiale de l'élément en stock.
     * @param uniteMesure Unité de mesure de l'élément.
     * @param prixAchat Prix d'achat de l'élément.
     * @param prixVente Prix de vente de l'élément.
     */
    public Element(String code, String nom, double quantiteStock, String uniteMesure, double prixAchat, double prixVente) {
        this.code = code;
        this.nom = nom;
        this.quantiteStock = quantiteStock;
        this.uniteMesure = uniteMesure;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.quantiteEnProduction = 0; // Initialisé à 0
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public double getQuantiteStock() {
        return quantiteStock;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    // Setters
    public void setQuantiteStock(double quantite) {
        this.quantiteStock = quantite;
    }

    /**
     * Trouve un élément par son code.
     *
     * @param code Le code de l'élément à trouver.
     * @return L'élément correspondant au code, ou null si aucun élément n'est trouvé.
     */
    public static Element trouverElement(String code) {
        for (Element elem : elements) {
            if (elem.getCode().equals(code)) {
                return elem;
            }
        }
        return null;
    }
    /**
     * Gère l'achat d'une quantité spécifiée de cet élément, ajoutant à son stock et enregistrant l'achat dans l'historique.
     *
     * @param quantite La quantité de l'élément à acheter.
     */
    public void acheter(double quantite) {
        System.out.println("Avant achat - Stock de " + this.code + ": " + this.quantiteStock);
        this.quantiteStock += quantite;
        System.out.println("Après achat - Stock de " + this.code + ": " + this.quantiteStock);
        Historique.ajouterChangement(new ModificationStockElement(code, nom, quantite, uniteMesure, quantite * prixAchat, 0, "acheter"));
    }
    /**
     * Gère la vente d'une quantité spécifiée de cet élément,
     * réduisant son stock et enregistrant la vente dans l'historique.
     * Si la quantité demandée dépasse le stock disponible, une exception est levée.
     *
     * @param quantite La quantité de l'élément à vendre.
     * @throws Exception Si la quantité demandée excède le stock disponible.
     */
    public void vendre(double quantite) throws Exception {
        if (quantite > quantiteStock) {
            throw new IllegalArgumentException("Quantité demandée excède le stock disponible.");
        }
        System.out.println("Avant vente - Stock de " + this.code + ": " + this.quantiteStock);
        this.quantiteStock -= quantite;
        System.out.println("Après vente - Stock de " + this.code + ": " + this.quantiteStock);
        Historique.ajouterChangement(new ModificationStockElement(code, nom, -quantite, uniteMesure, 0, quantite * prixVente, "vente"));
    }
    /**
     * Retourne une chaîne de caractères représentant l'objet Element.
     * Cette représentation inclut le code, le nom, la quantité en stock, l'unité de mesure,
     * le prix d'achat, le prix de vente et la quantité en production de l'élément.
     * @return Une chaîne de caractères décrivant l'élément avec tous ses attributs.
     */
    @Override
    public String toString() {
        return String.format("Element{code='%s', nom='%s', quantiteStock=%f, uniteMesure='%s', prixAchat=%f, prixVente=%f, quantiteEnProduction=%f}",
                code, nom, quantiteStock, uniteMesure, prixAchat, prixVente, quantiteEnProduction);
    }
}

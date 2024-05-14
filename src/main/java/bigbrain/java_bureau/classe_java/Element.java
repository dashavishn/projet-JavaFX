package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;

public class Element {
    private String code;
    private String nom;
    private double quantiteStock;
    private String uniteMesure;
    private double prixAchat;
    private double prixVente;
    private double quantiteEnProduction;  // Quantité en cours de production

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

    public double getQuantiteEnProduction() {
        return quantiteEnProduction;
    }

    // Setters
    public void setQuantiteStock(double quantite) {
        this.quantiteStock = quantite;
    }

    public void setQuantiteEnProduction(double quantite) {
        this.quantiteEnProduction = quantite;
    }

    public void ajouterQuantite(double quantite) {
        this.quantiteStock += quantite;
    }

    private static ArrayList<Element> elements = new ArrayList<>();

    public static Element trouverElement(String code) {
        for (Element elem : elements) {
            if (elem.getCode().equals(code)) {
                return elem;
            }
        }
        return null;
    }

    // Méthodes d'affaires
    /*
    public void acheter(Element elem, double quantite) {
        this.quantiteStock += quantite;
        Stocks.ajouterElem(this, quantite);
        Historique.ajouterChangement(new ModificationStockElement(code, nom, quantite, uniteMesure, quantite * prixAchat, 0, "acheter"));
    }
*/
    public void acheter(double quantite) {
        this.quantiteStock += quantite;
        Stocks.ajouterElem(this, quantite); // Assurez-vous que cette méthode ne duplique pas la quantité ajoutée
        Historique.ajouterChangement(new ModificationStockElement(code, nom, quantite, uniteMesure, quantite * prixAchat, 0, "acheter"));
    }




    public void vendre(double quantite) throws Exception {
        if (quantite > quantiteStock) {
            throw new IllegalArgumentException("Quantité demandée excède le stock disponible.");
        }
        this.quantiteStock -= quantite;
        Stocks.enleverElem(String.valueOf(this), quantite);
        Historique.ajouterChangement(new ModificationStockElement(code, nom, -quantite, uniteMesure, 0, quantite * prixVente, "vente"));
    }

    @Override
    public String toString() {
        return String.format("Element{code='%s', nom='%s', quantiteStock=%f, uniteMesure='%s', prixAchat=%f, prixVente=%f, quantiteEnProduction=%f}",
                code, nom, quantiteStock, uniteMesure, prixAchat, prixVente, quantiteEnProduction);
    }
}

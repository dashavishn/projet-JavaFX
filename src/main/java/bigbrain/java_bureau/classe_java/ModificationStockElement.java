package bigbrain.java_bureau.classe_java;

/**
 * Cette classe représente un changement de stock pour un élément.
 * Elle stocke les informations sur les modifications apportées à l'inventaire d'un élément,
 * y compris le code, le nom, la quantité modifiée, l'unité de mesure, les prix d'achat et de vente.
 * @author HanjaRajaobelison
 */
public class ModificationStockElement {
    private String code;
    private String nom;
    private double quantiteModifiee;
    private String uniteMesure;
    private double prixAchat;
    private double prixVente;
    private String origine;  // Origine du changement comme achat, vente, etc.

    /**
     * Constructeur pour créer une modification de stock.
     *
     * @param code Le code de l'élément.
     * @param nom Le nom de l'élément.
     * @param quantiteModifiee La quantité qui a été modifiée.
     * @param uniteMesure L'unité de mesure de l'élément.
     * @param prixAchat Le prix d'achat de l'élément.
     * @param prixVente Le prix de vente de l'élément.
     * @param origine L'origine de la modification (achat, vente, correction, etc.).
     */
    public ModificationStockElement(String code, String nom, double quantiteModifiee, String uniteMesure, double prixAchat, double prixVente, String origine) {
        this.code = code;
        this.nom = nom;
        this.quantiteModifiee = quantiteModifiee;
        this.uniteMesure = uniteMesure;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.origine = origine;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public double getQuantiteModifiee() {
        return quantiteModifiee;
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

    public String getOrigine() {
        return origine;
    }
}

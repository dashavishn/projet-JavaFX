package bigbrain.java_bureau.classe_java;



/**
* Cette classe représente un produit du stock qui le décrit par :
 * - un code unique
 * - un nom
 * - une quantité
 * - Une unité de mesure
 * - un prix d'achat
 * - un prix de vente
 * Des méthodes sont également présentes pour :
 * - mettre à jour la quantité de stock
 * - mettre à jour le code d'un élément (si c'est un nouveau)
 * -calculer le prix de l'achat d'une quantité d'élément
 * - vendre un élément puis mettre à jour la classe historique




 */
public class Element {

    private String code;
    private String nom;
    private double quantiteStock;
    private String uniteMesure;

    private double prixAchat;

    private double prixVente;
    /* ===========================================
     * Le constructeur et les fonctions dont on a besoin
     * =========================================== */

    public Element(String code, String nom, double quantiteStock, String uniteMesure, double prixAchat, double prixVente) {
        this.code=code;
        this.nom=nom;
        this.quantiteStock=quantiteStock;
        this.uniteMesure=uniteMesure;
        this.prixAchat=prixAchat;
        this.prixVente=prixVente;
    }

    /** public Element(String elemDatum, String elemDatum1) {
    }
*/

    /* ===========================================
     * Les Getteur et Setteur
     * =========================================== */


    /** retourne quantité actuelle du produit */

    public double getQuantiteStock(){
        return quantiteStock;

    }

    /** Met à jour la quantité */

    public void setQuantiteStock(){
        this.quantiteStock=quantiteStock;
    }
    /** va mettre à jour la quantité */
    public void setQuantite(double quantite){
        this.quantiteStock = quantite;
    }

    /** va retourner le code de l'élément */
    public String getCode(){
        return code;
    }
    /**va mettre à jour le code d'un nouvel élément */
    public void setCode(String code){
        this.code=code;
    }

/** retourne le nom d'un élément */
    public String getNom(){
        return nom;
    }
/** va mettre à jour le nom d'un nouvel élément */
    public void setNom(String nom){
        this.nom=nom;
    }
/** retourne l'unité de mesure de l'élément */
    public String getUniteMesure() {
        return uniteMesure;
    }
/** met à jour l'unité de l'élément */
    public void setUniteMesure(String uniteMesure){
        this.uniteMesure=uniteMesure;
    }
    /** retourne le prix d'achat */
    public double getPrixAchat(){
        return prixAchat;
    }
    /** Met à jour le prix d'achat */
    public void setPrixAchat(){
        this.prixAchat=prixAchat;
    }
    /** Retourne le prix de vente */

    public double getPrixVente() {
        return prixVente;
    }
    /** Met à jour le prix de vente */
    public void setPrixVente(){
        this.prixVente=prixVente;
    }



/** va retourner la descrition de l'élément */
    public String toString() {
        return "Element{" +
                "quantite=" + quantiteStock +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", prixAchat=" + prixAchat +
                ", prixVente=" + prixVente +
                ", unite='" + uniteMesure + '\'' +
                '}';
    }

    public double prixAchat (Element e, float quantiteStock){

        return (e.prixAchat) * quantiteStock;
    }

    public void Acheter(Element e, float quantiteCommandee) {
        double Prix = prixAchat(e, quantiteCommandee);

        Stocks.ajouterElem(e, quantiteCommandee);
        Historique.ajouterChangement(new ModificationStockElement(e.getCode(), e.getNom(), quantiteCommandee, e.getUniteMesure(), Prix, 0));
    }


    public static Element trouverElement(String code) {
        for (Element e : Stocks.ElemStocks) {
            if (e.getCode().equals(code))
                return e;
        }
        return null;
    }

    public void ajouterQuantite(float n) {
        this.quantiteStock += n;
    }

    public static void Vendre(Element e, float quantiteVendue) {
        for (Element a : Stocks.ElemStocks) {
            if (a.getCode().equals(e.getCode())) {
                Stocks.enleverElem(a, quantiteVendue);

            }
        }
        AjouterHistorique(e, quantiteVendue);
    }
    public static void AjouterHistorique (Element e, float quantiteVendue) {

        double Prix = (e.prixVente) * (quantiteVendue);

        Historique.ajouterChangement(new ModificationStockElement(e.getCode(), e.getNom(), quantiteVendue, e.getUniteMesure(), 0, Prix));
    }



}



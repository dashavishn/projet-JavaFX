package bigbrain.java_bureau.classe_java;


public class Element {
    private String code;
    private String nom;
    private double quantiteStock;
    private String uniteMesure;

    private double prixAchat;

    private double prixVente;


    public Element(String code, String nom, double quantiteStock, String uniteMesure, double prixAchat, double prixVente) {
        this.code=code;
        this.nom=nom;
        this.quantiteStock=quantiteStock;
        this.uniteMesure=uniteMesure;
        this.prixAchat=prixAchat;
        this.prixVente=prixVente;
    }
//va mettre à jour la quantité
    public void setQuantite(double quantite){
        this.quantiteStock = quantite;
    }


    //retourne quantité actuelle du produit

    public double getQuantiteStock(){
        return quantiteStock;

    }

    //va retourner le code de l'élément
    public String getCode(){
        return code;
    }
//va mettre à jour le code d'un nouvel élément
public void setCode(String code){
    this.code=code;
}

    //retourne le nom d'un élément
    public String getNom(){
        return nom;
    }
// va mettre à jour le nom d'un nouvel élément
    public void setNom(String nom){
        this.nom=nom;
    }
//retourne l'unité de mesure de l'élément
    public String getUniteMesure() {
        return uniteMesure;
    }
//met à jour l'unité de l'élément
    public void setUniteMesure(String uniteMesure){
        this.uniteMesure=uniteMesure;
    }

    public double getPrixAchat(){
        return prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }



//la descrition de l'élément
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









}



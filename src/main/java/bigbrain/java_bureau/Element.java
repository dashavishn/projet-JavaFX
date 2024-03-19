package bigbrain.java_bureau;

public class Element {
    private String code;
    private String nom;
    private double quantiteStock;
    private String uniteMesure;


    public void Element(String code, String nom, double quantiteStock, String uniteMesure) {
        this.code=code;
        this.nom=nom;
        this.quantiteStock=quantiteStock;
        this.uniteMesure=uniteMesure;
    }

    public void ajouetrQuanite(double quantite){
        this.quantiteStock += quantite;
    }
    public void retirerQuantite (double quantite){
        this.quantiteStock -= quantite;

    }

    public boolean estDisponible(double quantite){
        if (quantite>0)
            return True;
        return False;
    }












}



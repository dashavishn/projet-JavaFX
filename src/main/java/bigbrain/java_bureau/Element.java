package bigbrain.java_bureau;
import java.lang.Boolean;

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

    public void ajouterQuantite(double quantite){
        this.quantiteStock += quantite;
    }
    public void retirerQuantite (double quantite){
        this.quantiteStock -= quantite;

    }

    public double getQuantiteStock(){
        return quantiteStock;

    }
    public String getCode(){
        return code;
    }


    public String getNom(){
        return nom;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public double getPrixAchat(){
        return prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public boolean estDisponible(double quantite) {
        if (quantite > 0) {
            return true;
        }return false;

    }












}



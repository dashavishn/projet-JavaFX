package bigbrain.java_bureau.classe_java;

import java.util.HashMap;

public class ChaineProduction {
    private String code;
    private String nom;
    public int niveauActivation;
    private HashMap<Element, Double> elemEntree;
    private HashMap<Element, Double> elemSortie;

    public ChaineProduction(String code, String nom, int niveauActivation){
        this.code=code;
        this.nom=nom;
        this.niveauActivation=niveauActivation;
    }

    public void ajouterElementEntree(Element element, double quantite) {
        elemEntree.put(element, quantite);
    }

    public void ajouterElementSortie(Element element, double quantite) {
        elemSortie.put(element, quantite);
    }

    public void removeElemEntree(Element e) {
        elemEntree.remove(e);
    }

    public void removeElemSortie(Element e) {
        elemSortie.remove(e);
    }

    public String getCode(){
        return this.code;
    }
    public String getNom(){
        return this.nom;
    }
    public int getNiveauActivation(){
        return this.niveauActivation;
    }
    public void setNiveauActivation(int niveauA){
        this.niveauActivation=niveauA;
    }
}

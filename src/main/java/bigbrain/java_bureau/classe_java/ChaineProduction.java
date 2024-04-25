package bigbrain.java_bureau.classe_java;

import java.util.HashMap;
import java.util.HashSet;

public class ChaineProduction {
    private String code;
    private String nom;
    public int niveauActivation ;
  HashMap<Element, Float> ElementEntree =new HashMap<>();
  HashMap<Element, Float> ElementSortie =new HashMap<>();

    public ChaineProduction(String code, String nom, HashMap ElementEntree, HashMap ElementSortie){
        this.code=code;
        this.nom=nom;
        this.niveauActivation= 0;
        this.ElementEntree= ElementEntree;
        this.ElementSortie= ElementSortie;
    }

    public void ajouterElementEntree(Element element, float quantite) {

        ElementEntree.put(element, quantite);
    }

    public void ajouterElementSortie(Element element, float quantite) {
        ElementSortie.put(element, quantite);
    }

    public void removeElemEntree(Element e) {
        ElementEntree.remove(e);
    }

    public void removeElemSortie(Element e) {
        ElementSortie.remove(e);
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

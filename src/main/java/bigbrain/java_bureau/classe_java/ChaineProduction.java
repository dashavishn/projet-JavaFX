package bigbrain.java_bureau.classe_java;


import java.util.HashMap;
import java.util.HashSet;


public class ChaineProduction {
    private String code;
    private String nom;
    public int niveauActivation;
    HashMap<Element, Float> elementEntree = new HashMap<>();
    HashMap<Element, Float> elementSortie = new HashMap<>();


    public ChaineProduction(String code, String nom, HashMap<Element, Float> elementEntree, HashMap<Element, Float> elementSortie) {
        this.code = code;
        this.nom = nom;
        this.niveauActivation = 0;
        this.elementEntree = elementEntree;
        this.elementSortie = elementSortie;
    }


    public void ajouterElementEntree(Element element, float quantite) {
        elementEntree.put(element, quantite);
    }


    public void ajouterElementSortie(Element element, float quantite) {
        elementSortie.put(element, quantite);
    }


    public void removeElemEntree(Element e) {
        elementEntree.remove(e);
    }


    public void removeElemSortie(Element e) {
        elementSortie.remove(e);
    }


    public String getCode() {
        return this.code;
    }


    public String getNom() {
        return this.nom;
    }


    public int getNiveauActivation() {
        return this.niveauActivation;
    }


    public void setNiveauActivation(int niveauA) {
        this.niveauActivation = niveauA;
    }


    /* public HashMap<Element, Float> simulerProduction() {
         HashMap<Element, Float> productionResultat = new HashMap<>();
         for (Element e : elementEntree.keySet()) {
             float quantiteNecessaire = elementEntree.get(e) * this.niveauActivation;
             if (Stocks.verifierDisponibilite(e, quantiteNecessaire)) {
                 Stocks.retirerStock(e, quantiteNecessaire);
             } else {
                 System.out.println("Stock insuffisant pour " + e.getNom());
                 return null; // Arrête la production si le stock est insuffisant
             }
         }


         for (Element e : elementSortie.keySet()) {
             float quantiteProduite = elementSortie.get(e) * this.niveauActivation;
             productionResultat.put(e, quantiteProduite);
             Stocks.ajouterStock(e, quantiteProduite);
         }
         return productionResultat;
     }


     */
    public void simulerEtAfficherResultats() {
        HashMap<Element, Float> resultats = simulerProduction();
        if (resultats == null) {
            System.out.println("Simulation arrêtée en raison d'un manque de stock.");
        } else {
            System.out.println("Résultats de la production:");
            resultats.forEach((element, quantite) ->
                    System.out.println(element.getNom() + ": " + quantite + " " + element.getUniteMesure())
            );
        }
    }

    public HashMap<Element, Float> simulerProduction() {
        HashMap<Element, Float> productionResultat = new HashMap<>();
        boolean stockSuffisant = true;


        // Vérifie d'abord la disponibilité de tous les éléments nécessaires
        for (Element e : elementEntree.keySet()) {
            float quantiteNecessaire = elementEntree.get(e) * this.niveauActivation;
            if (!Stocks.verifierDisponibilite(e, quantiteNecessaire)) {
                System.out.println("Stock insuffisant pour " + e.getNom());
                stockSuffisant = false;
                break;
            }
        }


        if (stockSuffisant) {
            // Consomme les éléments d'entrée
            for (Element e : elementEntree.keySet()) {
                float quantiteNecessaire = elementEntree.get(e) * this.niveauActivation;
                Stocks.retirerStock(e, quantiteNecessaire);
            }


            // Produit et ajoute les éléments de sortie au stock
            for (Element e : elementSortie.keySet()) {
                float quantiteProduite = elementSortie.get(e) * this.niveauActivation;
                productionResultat.put(e, quantiteProduite);
                Stocks.ajouterStock(e, quantiteProduite);
            }
        } else {
            return null;  // Retourne null si le stock était insuffisant pour une ou plusieurs entrées
        }


        return productionResultat;
    }


}
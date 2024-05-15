package bigbrain.java_bureau.classe_java;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;

public class ChaineProduction {
    private String code;
    private String nom;
    private int niveauActivation;
    private Map<Element, Float> elementEntree;
    private Map<Element, Float> elementSortie;

    public ChaineProduction(String code, String nom) {
        this.code = code;
        this.nom = nom;
        this.elementEntree = new HashMap<>();
        this.elementSortie = new HashMap<>();
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public int getNiveauActivation() {
        return niveauActivation;
    }

    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation = niveauActivation;
    }
    public void ajouterElementEntree(Element element, float quantite) {
        this.elementEntree.put(element, quantite);
    }

    public void ajouterElementSortie(Element element, float quantite) {
        this.elementSortie.put(element, quantite);
    }
    public Map<Element, Float> getElementEntree() {
        return new HashMap<>(this.elementEntree);
    }

    public Map<Element, Float> getElementSortie() {
        return new HashMap<>(this.elementSortie);
    }

    public Boolean valider() throws Exception {
        if (niveauActivation == 0) {
            throw new IllegalStateException("Le niveau d'activation est de 0, la chaîne ne peut pas fonctionner.");
        }

        // Vérifier les stocks pour chaque élément en entrée
        for (Map.Entry<Element, Float> entry : elementEntree.entrySet()) {
            Element element = entry.getKey();
            float quantiteNecessaire = entry.getValue() * niveauActivation;
            if (element.getQuantiteStock() < quantiteNecessaire) {
                return false;  // Pas assez de stock pour démarrer la production
            }
        }

        // Consommer les éléments d'entrée
        for (Map.Entry<Element, Float> entry : elementEntree.entrySet()) {
            Element element = entry.getKey();
            float quantiteNecessaire = entry.getValue() * niveauActivation;
            element.vendre(quantiteNecessaire);  // Utiliser la méthode vendre pour diminuer le stock
        }

        // Produire les éléments de sortie
        for (Map.Entry<Element, Float> entry : elementSortie.entrySet()) {
            Element element = entry.getKey();
            float quantiteProduite = entry.getValue() * niveauActivation;
            element.acheter(quantiteProduite);  // Utiliser la méthode acheter pour augmenter le stock
        }

        return true;  // Production réussie
    }



}
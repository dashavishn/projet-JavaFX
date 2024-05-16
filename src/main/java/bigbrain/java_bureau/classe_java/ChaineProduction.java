package bigbrain.java_bureau.classe_java;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChaineProduction {
    private String code;
    private String nom;
    private int niveauActivation;
    private Map<Element, Float> elementEntree;
    private Map<Element, Float> elementSortie;

    private String strSortie;
    private String strEntree;

    public ChaineProduction(String code, String nom) {
        this.code = code;
        this.nom = nom;
        this.elementEntree = new HashMap<>();
        this.elementSortie = new HashMap<>();
    }

    public ChaineProduction(String code, String nom, int niveauActivation, Map<Element, Float> elementEntree, Map<Element, Float> elementSortie) {
        this.code = code;
        this.nom = nom;
        this.niveauActivation = niveauActivation;
        this.elementEntree = elementEntree;
        this.elementSortie = elementSortie;
    }

    public ChaineProduction(String code, String name, String part, String part1) {
        this.code = code;
        this.nom = name;
        this.strEntree = part;
        this.strSortie = part1;
    }

    public String getStrSortie() {
        return strSortie;
    }

    public String getStrEntree() {
        return strEntree;
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

    public String getStringElementEntree() {
        return elementEntree.entrySet().stream()
                .map(entry -> entry.getKey().getNom() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public String getStringElementSortie() {
        return elementSortie.entrySet().stream()
                .map(entry -> entry.getKey().getNom() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
    public Map<Element, Float> getElementEntree() {
        return new HashMap<>(this.elementEntree);
    }

    public Map<Element, Float> getElementSortie() {
        return new HashMap<>(this.elementSortie);
    }

    public boolean valider() throws Exception {
        if (niveauActivation == 0) {
            return false;  // Retourne false au lieu de lancer une exception si le niveau d'activation est 0
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




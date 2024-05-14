package bigbrain.java_bureau.classe_java;

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

    public void ajouterElementEntree(Element element, float quantite) {
        this.elementEntree.put(element, quantite);
    }

    public void ajouterElementSortie(Element element, float quantite) {
        this.elementSortie.put(element, quantite);
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

    // Simulation de la production qui calcule le coût des éléments manquants
    public double simulerProduction() {
        double totalAchat = 0.0;
        boolean stockSuffisant = true;
        Stocks stocksInstance = Stocks.getInstance(); // Obtenir l'instance du singleton

        // Vérifier chaque élément d'entrée pour la disponibilité et calculer le coût des achats nécessaires
        for (Map.Entry<Element, Float> entry : elementEntree.entrySet()) {
            Element element = entry.getKey();
            float quantiteNecessaire = entry.getValue() * niveauActivation;
            if (!stocksInstance.verifierDisponibilite(element.getCode(), quantiteNecessaire)) {
                double quantiteManquante = quantiteNecessaire - element.getQuantiteStock();
                totalAchat += quantiteManquante * element.getPrixAchat();
                stockSuffisant = false;
            }
        }

        if (!stockSuffisant) {
            // Si le stock n'est pas suffisant pour au moins un élément, retourner le coût total sans modifier les stocks
            return totalAchat;
        }

        // Si le stock est suffisant, simuler les modifications de stock (ceci est hypothétique, sans effet réel sur le stock)
        for (Map.Entry<Element, Float> entry : elementEntree.entrySet()) {
            Element element = entry.getKey();
            float quantiteNecessaire = entry.getValue() * niveauActivation;
            // Pas de modification réelle du stock ici, juste une simulation
        }

        for (Map.Entry<Element, Float> entry : elementSortie.entrySet()) {
            Element element = entry.getKey();
            float quantiteProduite = entry.getValue() * niveauActivation;
            // Pas de modification réelle du stock ici, juste une simulation
        }

        // Retourner 0 si tout est suffisant, signifiant aucun coût d'achat supplémentaire n'est nécessaire
        return totalAchat;
    }
}

package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
//une classe qui permet de gérer les stocks des articles


public class Stocks {
    //une liste qui contient les élements disponibles en stock
    public static ArrayList<Element> ElemStocks=new ArrayList<>();



    public Stocks(){
        this.ElemStocks = new ArrayList<>();
    }
    //méthode qui permet d'ajouter un élément dans la liste ElemStocks
    public static void ajouterElem(Element e,float n) {
        if (ElemStocks.contains(e)) {
            for (Element a : ElemStocks){
                if (a.getCode().equals(e.getCode())){
                    a.ajouterQuantite(n);
                }
            }
        }
        else{
            e.setQuantiteStock(n);
            ElemStocks.add(e);
        }
    }
    //une méthode qui permet de supprimer un élément de la liste ElemStocks
    public void supprimerElement(Element e) {
        ElemStocks.remove(e);
    }
    public static void enleverElem(Element e, float n) {
        for (Element a : ElemStocks){
            if (a.getCode().equals(e.getCode())){
                if(a.getQuantiteStock()<n){
                    System.err.println("Stock insuffisant");
                }
                else {
                    a.ajouterQuantite(-n);
                }
            }
        }
    }

    //une méthode qui permet de récupérer et renvoyer l'élément de la liste
    public Element getQElemStocks(String code) {
        for (Element e : ElemStocks) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        // Retourne null si l'élément avec le code donné n'est pas trouvé
        return null;
    }



    // Vérifie si la quantité nécessaire d'un élément est disponible en stock
    public static boolean verifierDisponibilite(Element e, double quantiteNecessaire) {
        Element foundElement = trouverElement(e.getCode());
        return foundElement != null && foundElement.getQuantiteStock() >= quantiteNecessaire;
    }

    // Retire une quantité spécifiée d'un élément du stock
    public static void retirerStock(Element e, double quantite) {
        Element foundElement = trouverElement(e.getCode());
        if (foundElement != null) {
            double newQuantity = foundElement.getQuantiteStock() - quantite;
            if (newQuantity >= 0) {
                foundElement.setQuantiteStock(newQuantity);
            } else {
                System.err.println("Stock insuffisant pour l'élément: " + e.getNom());
            }
        }
    }

    // Ajoute une quantité spécifiée à un élément dans le stock
    public static void ajouterStock(Element e, double quantite) {
        Element foundElement = trouverElement(e.getCode());
        if (foundElement != null) {
            foundElement.setQuantiteStock(foundElement.getQuantiteStock() + quantite);
        } else {
            e.setQuantiteStock(quantite);
            ElemStocks.add(e);
        }
    }

    // Trouve un élément par son code
    public static Element trouverElement(String code) {
        for (Element elem : ElemStocks) {
            if (elem.getCode().equals(code)) {
                return elem;
            }
        }
        return null; // Retourne null si l'élément avec le code donné n'est pas trouvé
    }

    // Ajoute un élément au stock, s'il n'est pas déjà présent
    public static void ajouterElem(Element e, double quantite) {
        Element foundElement = trouverElement(e.getCode());
        if (foundElement != null) {
            foundElement.ajouterQuantite((float) quantite);
        } else {
            e.setQuantiteStock(quantite);
            ElemStocks.add(e);
        }
    }

    // Enlève un élément du stock
    public static void enleverElem(Element e, double quantite) {
        Element foundElement = trouverElement(e.getCode());
        if (foundElement != null) {
            double newQuantity = foundElement.getQuantiteStock() - quantite;
            if (newQuantity >= 0) {
                foundElement.setQuantiteStock(newQuantity);
            } else {
                System.err.println("Stock insuffisant pour retirer la quantité demandée");
            }
        }
    }

    public void testRetraitExcessif() {
        Element element = Stocks.trouverElement("E001");
        float quantiteInitiale = (float) element.getQuantiteStock();
        float quantiteARetirer = quantiteInitiale + 10; // Plus que disponible
        Stocks.retirerStock(element, quantiteARetirer);
        float quantiteApres = (float) element.getQuantiteStock();

        assert quantiteApres == quantiteInitiale : "Le stock ne devrait pas changer";
    }


}

package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
//une classe qui permet de gérer les stocks des articles


public class Stocks {
    //une liste qui contient les élements disponibles en stock
    protected static ArrayList<Element> ElemStocks=new ArrayList<>();


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
            e.setQuantite(n);
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


}

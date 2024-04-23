package bigbrain.java_bureau;
import java.util.ArrayList;

public class Stocks {
    private static ArrayList<Element> ElemStocks;

    public Stocks(){
        this.ElemStocks = new ArrayList<>();
    }
    public static void ajouterElem(Element e){
        ElemStocks.add(e);
    }

    public void supprimerElement(Element e) {
        ElemStocks.remove(e);
    }

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

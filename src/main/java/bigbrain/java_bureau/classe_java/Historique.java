package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe enregistre les changements apportés au stock.
 * @author HanjaRajaobelison
 */
public class Historique {
    public static List<ModificationStockElement> historiqueModifications = new ArrayList<>();

    /**
     * Initialise l'historique, potentiellement en chargeant des données depuis un fichier ou une source externe.
     */
    public static void initialiserHistorique() {

    }

    /**
     * va ajouter un changement à l'historique.
     *
     * @param modification Le changement à ajouter à l'historique.
     */
    public static void ajouterChangement(ModificationStockElement modification) {
        historiqueModifications.add(modification);
    }

    /**
     * Renvoie la liste des modifications stockées dans l'historique.
     *
     * @return La liste des modifications.
     */
    public static List<ModificationStockElement> getHistoriqueModifications() {
        return historiqueModifications;
    }
}

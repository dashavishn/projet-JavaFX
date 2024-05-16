package bigbrain.java_bureau.classe_java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe de gestion de l'entrepôt qui stocke les chaînes de production et les éléments matériels.
 * Elle utilise des listes observables pour permettre une mise à jour lors des modifications.
 */
public class Entrepot {
    /**
     * Liste observable des chaînes de production.
     */
    private static ObservableList<ChaineProduction> chaines = FXCollections.observableArrayList();

    /**
     * Constructeur vide de la classe Entrepot.
     */
    public Entrepot() { }
    /**
     * Ajoute une nouvelle chaîne de production à la liste observable.
     *
     * @param chaine La chaîne de production à ajouter.
     */
    public static void addChaine(ChaineProduction chaine) {
        chaines.add(chaine);
    }
    /**
     * Retourne une liste immuable des chaînes de production stockées dans l'entrepôt.
     * Cette méthode empêche la modification de la liste retournée, garantissant ainsi l'intégrité des données.
     *
     * @return Une liste observable non modifiable des chaînes de production.
     */
    public static ObservableList<ChaineProduction> getChaine() {
        return FXCollections.unmodifiableObservableList(chaines);
    }
}

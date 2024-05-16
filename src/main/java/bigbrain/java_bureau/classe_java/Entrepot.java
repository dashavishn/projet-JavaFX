package bigbrain.java_bureau.classe_java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Entrepot {
    private static ObservableList<ChaineProduction> chaines = FXCollections.observableArrayList();
    private static ObservableList<Element> elements = FXCollections.observableArrayList();

    public Entrepot() {
        // Constructeur vide
    }

    public static ObservableList<Element> getElements() {
        return FXCollections.unmodifiableObservableList(elements);
    }

    public static void addChaine(ChaineProduction chaine) {
        chaines.add(chaine);
    }

    public static void addChaines(ArrayList<ChaineProduction> chaine) {
        chaines.addAll(chaine);
    }

    public static void addElement(ArrayList<Element> e) {
        elements.addAll(e);
    }

    public static ObservableList<ChaineProduction> getChaine() {
        return FXCollections.unmodifiableObservableList(chaines);
    }
}

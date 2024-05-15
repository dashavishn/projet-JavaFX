package bigbrain.java_bureau.test;


import bigbrain.java_bureau.classe_java.ChaineProduction;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Stocks;


import bigbrain.java_bureau.classe_java.ChaineProduction;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Stocks;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class TestChaineProduction {
    public static void main(String[] args) {
        // Création de la chaîne de production
        ChaineProduction chaine = new ChaineProduction("C001", "Chaîne de Circuits");

        // Création des éléments
        Element e1 = new Element("E001", "Circuit principal", 88, "pieces", 50, 20);
        Element e2 = new Element("E002", "Plastique", 0, "kg", 3, 5);

        // Ajout des éléments au stock
        Stocks.ajouterElem(e1, e1.getQuantiteStock());
        Stocks.ajouterElem(e2, e2.getQuantiteStock());

        chaine.ajouterElementEntree(e1, 50);
        chaine.ajouterElementSortie(e2, 300);

        // Ajout des éléments à la chaîne de production comme éléments d'entrée et sortie pour simplification
        chaine.ajouterElementEntree(e1, 10);  // Exemple: 10 pièces de circuits principaux nécessaires
        chaine.ajouterElementSortie(e2, 5);
    }
    }


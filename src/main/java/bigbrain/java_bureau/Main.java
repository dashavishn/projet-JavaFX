package bigbrain.java_bureau;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import bigbrain.java_bureau.classe_java.*;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        try {
            // Charger le fichier FXML pour la page principale de l'application
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/bigbrain/java_bureau/page_accueil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 960, 540); // Ajuster la taille de la scène selon vos besoins
            primaryStage.setTitle("Gestion de Chaînes de Production");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Initialisation des données de l'application
            initializeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise et charge les données nécessaires pour l'application.
     */
    private void initializeData() {
        try {
            // Création d'une instance de CSV pour charger les données
            CSV csv = new CSV();
            csv.lireElements();
            csv.lireChaines();
            Historique.initialiserHistorique();  // Initialiser l'historique si nécessaire
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation des données: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

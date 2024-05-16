package bigbrain.java_bureau;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import bigbrain.java_bureau.classe_java.*;
/**
 * Classe principale de l'application JavaFX.
 * Configure et lance l'interface utilisateur principale de l'application, charge les données initiales et gère le stage principal.
 */
public class Main extends Application {
    /**
     * Référence au stage principal de l'application.
     */
    public static Stage primaryStage;
    /**
     * Démarrage de l'application JavaFX.
     * Cette méthode est appelée automatiquement lors du lancement de l'application.
     * Elle charge l'interface utilisateur à partir d'un fichier FXML et initialise la scène principale.
     *
     * @param primaryStage Le stage principal pour cette application, fourni par la plateforme JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage; // Stocker la référence du stage principal.
        try {
            // Charger le fichier FXML pour la page principale de l'application
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/bigbrain/java_bureau/page_accueil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 960, 540); // Ajuster la taille de la scène
            primaryStage.setTitle("Gestion de Chaînes de Production");
            primaryStage.setScene(scene);
            primaryStage.show(); // Afficher le stage.

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
            csv.lireElements(); // Charger les éléments à partir d'un fichier CSV.
            csv.lireChaines(); // Charger les chaînes de production à partir d'un fichier CSV.
            Historique.initialiserHistorique();  // Initialiser l'historique si nécessaire
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation des données: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Le point d'entrée principal.

     *
     * @param args Les arguments de ligne de commande.
     */
    public static void main(String[] args) {
        launch(args); // Démarrer l'application
    }
}

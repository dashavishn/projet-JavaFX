package bigbrain.java_bureau;
import bigbrain.java_bureau.classe_java.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/bigbrain/java_bureau/page_accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Gestion de Chaînes de Production");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        initialiserDonnees();
        launch(args);
    }

    private static void initialiserDonnees() {
        CSV csv = new CSV();
        csv.lireElements();
        csv.lireChaines();
        Historique.initialiserHistorique();
    }

    public static void chargerPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(page));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*public static Stage getPrimaryStage() {
        return primaryStage;
    }

     */
}

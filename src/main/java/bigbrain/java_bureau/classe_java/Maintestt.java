package bigbrain.java_bureau.classe_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Maintestt extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        testLoadFXML("/bigbrain/java_bureau/stock.fxml");
    }

    private void testLoadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            System.out.println("Chargement réussi de " + fxmlPath);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}

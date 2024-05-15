package bigbrain.java_bureau;
import bigbrain.java_bureau.classe_java.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//import static bigbrain.java_bureau.classe_java.CSV.lireChaines;
//import static bigbrain.java_bureau.classe_java.CSV.lireElements;

public class Main extends Application {

    public Entrepot entrepot;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/bigbrain/java_bureau/page_accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Gestion de Chaînes de Production");
        primaryStage.setScene(scene);
        primaryStage.show();


        entrepot = new Entrepot();
        //entrepot.addChaines(lireChaines());
        //entrepot.addElement(lireElements());
        Historique.initialiserHistorique();
        CSV csv = new CSV();
        csv.lireElements();
        csv.lireChaines();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

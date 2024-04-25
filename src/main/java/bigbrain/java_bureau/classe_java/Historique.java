package bigbrain.java_bureau.classe_java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Historique {

    public static ArrayList<ModificationStockElement> modifications = new ArrayList<>();

    public static void ajouterChangement(ModificationStockElement modification) {
        modifications.add(modification);
    }

}
package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
import java.util.List;

public class GestionChaine {
    private static final GestionChaine instance = new GestionChaine();
    private List<ChaineProduction> chaines = new ArrayList<>();

    private GestionChaine() {}

}

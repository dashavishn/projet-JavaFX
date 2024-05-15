package bigbrain.java_bureau.classe_java;

import java.util.ArrayList;
import java.util.List;

public class Entrepot {
    private static final Entrepot instance = new Entrepot();
    private List<ChaineProduction> chaines = new ArrayList<>();

    private Entrepot() {}

    public static Entrepot getInstance() {
        return instance;
    }

    public void addChaine(ChaineProduction chaine) {
        chaines.add(chaine);
    }

    public List<ChaineProduction> getChaines() {
        return chaines;
    }
}

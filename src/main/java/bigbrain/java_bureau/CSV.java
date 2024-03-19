package bigbrain.java_bureau;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/* public class CSV {
    public static ArrayList<ChaineProduction> Chaine = new ArrayList<ChaineProduction>();

    public void LireElement () {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            While((line = reader.readLine()) != null) {
                String[] row = line.split(regex:";");
                float r2 = Float.parseFloat(row[2]);
                double r4 = Double.parseDouble(row[4]);
                double r5 = Double.parseDouble(row[5]);
                Element elem = new Element(row[0], row[1], r2, row[3], r4, r5);
                Stocks.ajouterElem(elem, r2);
            }
        } catsh(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void LireChaine(){
        String file = "src/OneDrive/Hanja - MIAGE /FichierV1(2)/chaines.csv";
        String line ="";
    }

    try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        While((line = reader.readLine()) != null) {
            String[] row = line.split(regex:";");

            HashMap<Element, Float> ElementEntree = new HashMap<Element, Float>();
            String [] elementEntree = row[2].split(regex: ",");
            for (String data : elementEntree){

            }

    }
}
*/
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CSV {
    public static ArrayList<ChaineProduction> chaines = new ArrayList<ChaineProduction>();

    public void lireElement(String file) {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                float r2 = Float.parseFloat(row[2]);
                double r4 = Double.parseDouble(row[4]);
                double r5 = Double.parseDouble(row[5]);
                Element elem = new Element("2345", "elem1", 2.000000f, "kg");
                Stocks.ajouterElem(elem, r2);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LireChaine() {
        String file = "src/OneDrive/Hanja - MIAGE /FichierV1(2)/chaines.csv";
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                String[] elementsEntree = row[2].split(",");
                HashMap<Element, Float> elemeEntree = new HashMap<>();
                for (String data : elementsEntree) {
                    // Récupérer l'élément et sa quantité, puis les ajouter à la HashMap
                    String[] elemData = data.split(":");
                    Element elem = new Element(elemData[0], elemData[1]);
                    Float quantite = Float.parseFloat(elemData[2]);
                    elemeEntree.put(elem, quantite);
                }
                ChaineProduction chaine = new ChaineProduction(row[0], row[1], elemeEntree);
                chaines.add(chaine);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

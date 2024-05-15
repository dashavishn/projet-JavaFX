package bigbrain.java_bureau.test;

import bigbrain.java_bureau.classe_java.EcrireCSV;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.ModificationStockElement;

import java.util.ArrayList;
import java.util.List;

public class EcrireCSVTest {

    public static void main(String[] args) {
        String basePath = "src/main/resources/bigbrain/fichierscsv";
        String elementsFilePath = basePath + "/historique.csv";
        String modificationsFilePath = basePath + "/bonjourbonsoir.csv";

        List<Element> elements = new ArrayList<>();
        elements.add(new Element("E001", "Element 1", 100, "kg", 10.0, 15.0));
        elements.add(new Element("E002", "Element 2", 150, "kg", 20.0, 30.0));

        List<ModificationStockElement> modifications = new ArrayList<>();
        modifications.add(new ModificationStockElement("E001", "Element 1", 50, "kg", 10.0, 15.0, "achat"));
        modifications.add(new ModificationStockElement("E002", "Element 2", 75, "kg", 20.0, 30.0, "vente"));

        EcrireCSV csvWriter = new EcrireCSV();
    }

       /* csvWriter.clearCSVFile(elementsFilePath);
        csvWriter.writeElementsToCSV(elementsFilePath, elements);
        System.out.println("Éléments écrits dans: " + elementsFilePath);

        csvWriter.clearCSVFile(modificationsFilePath);
        csvWriter.writeModificationsToCSV(modificationsFilePath, modifications);
        System.out.println("Modifications écrites dans: " + modificationsFilePath);


        */
}

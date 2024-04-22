package bigbrain.java_bureau;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Historique {

    public static <T> void serializeToFile(ArrayList<T> objects, String output) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(output), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne une liste contenant les données stockées en persistance
     * @param type type des données
     * @param path fichier utilisé par la pêrsistance
     * @return liste des données stockées
     */
    public static <T> ArrayList<T> deserializeFromFile(Class<T> type,String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayList<T> arrayList = objectMapper.readValue(new File(path),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, type));
            return arrayList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void addToFile(ArrayList<T> objects, Class<T> objectType, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        for (T object : deserializeFromFile(objectType,path)){
            if (objects.stream().noneMatch(p -> p.toString().equals(object.toString()))){
                objects.add(object);
            }
        }
        try {
            objectMapper.writeValue(new File(path), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
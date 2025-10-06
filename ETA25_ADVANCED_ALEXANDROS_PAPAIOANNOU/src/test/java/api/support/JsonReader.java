package api.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

// Clasa ajutatoare pentru a citi/scrie JSON
public final class JsonReader {

    // obiect de tip ObjectMapper folosit pentru
    // a transforma un JSON in obiect JAVA si invers
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // metoda generica (merge pentru orice tip de obiect
    // si il returneaza)
    // care primeste un JSON txt si il transforma in obiect JAVA
    public static <T> T fromString(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz); //citeste un JSON si il transforma in obiectul generic de tip clazz
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON to " + clazz.getSimpleName(), e);
        }
    }

    // metoda generica (merge pentru orice tip de obiect
    // si il returneaza)
    // care primeste un JSON dintr-o resursa a proiectului si il transforma in obiect JAVA
    public static <T> T fromResource(String resourcePath, Class<T> clazz) {
        String json = readResourceAsString(resourcePath); //deschide fisierul JSON din resursele proiectului, il citeste si il salveaza ca String
        return fromString(json, clazz); //foloseste metoda de mai sus pentru a transforma JSON txt in obiect JAVA
    }

    // gestioneaza un JSON dintr-o resursa a proiectului,
    // in cazul in care anumite valori din JSON sunt de tip ${token}
    public static <T> T fromResource(String resourcePath, Map<String, ?> tokens, Class<T> clazz) {
        String json = readResourceAsString(resourcePath);
        if (tokens != null) { //cauta, parcurge si inlocuieste toate valorile tip ${token}
            for (var e : tokens.entrySet()) {
                json = json.replace("${" + e.getKey() + "}", String.valueOf(e.getValue()));
            }
        }
        return fromString(json, clazz);
    }

    //metoda care primeste un obiect JAVA si il transforma in JSON
    //boolean pretty: JSON indentat sau liniar
    public static String toString(Object value, boolean pretty) {
        try {
            return pretty ? MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value)
                    : MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }


    //metoda care citeste un fisier din resursele proiectului
    //si returneaza continutul ca String liniar
    public static String readResourceAsString(String resourcePath) {
        try (InputStream is = Thread.currentThread() //deschide fisierul
                .getContextClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (is == null) throw new IllegalArgumentException("Resource not found " + resourcePath); //daca nu exista fisierul in resursa indicata, arunca un mesaj de eroare
            return new String(is.readAllBytes(), StandardCharsets.UTF_8); //citeste fisierul si il returneaza ca String liniar
        } catch (Exception e) {
            throw new RuntimeException("Failed to load resource: " + resourcePath, e); //arunca exceptie: cale incorecta, eroare de citire, eroare de encoding
        }
    }
}

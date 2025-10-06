package api.support;

import java.util.Map;

public final class Endpoints {

    //metoda care returneaza un endpoint din fisier de properties
    // pe baza parametrului furnizat, la care adauga prefixul endpoint.
    public static String resolve(String key) {
        return TestConfig.getProperty("endpoint." + key);
    }

    //metoda overloaded care returneaza un endpoint cu {token} din fisierul de properties
    public static String resolve(String key, Map<String, ?> pathParams) {
        String path = resolve(key);
        if (pathParams != null) {
            for (var e : pathParams.entrySet()) {
                path = path.replace("{" + e.getKey() + "}", String.valueOf(e.getValue()));
            }
        }
        return path;
    }
}

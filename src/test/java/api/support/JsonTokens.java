package api.support;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class JsonTokens {

    //metoda care incarca un fiser JSON cu ${token} din config.properties
    //inlocuieste ${token} cu valori dinamice
    public static String render(String jsonKey, Map<String, ?> vars) {
        String resourcePath = TestConfig.getProperty("json." + jsonKey);
        //String tkn = RequestClient.readResourceAsString(resourcePath);
        String tkn = JsonReader.readResourceAsString(resourcePath);

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("ts", System.currentTimeMillis());
        ctx.put("uuid", UUID.randomUUID().toString());

        if (vars != null) {
            ctx.putAll(vars);
        }

        for (var e : ctx.entrySet()) {
            tkn = tkn.replace("${" + e.getKey() + "}", String.valueOf(e.getValue()));
        }

        return tkn;
    }
}

package api.support;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ui.support.utils.LoggerUtility;

import java.util.Map;

//Runs before any tests in the subclass (because of @BeforeClass).
//Accepts an optional parameter baseUri (from a TestNG configuration file).
//If not provided, falls back to TestConfig.getProperty("baseUri").
// Initializes a reusable RequestClient (your HTTP client wrapper for making requests).
// Purpose: Makes sure all tests share the same preconfigured HTTP client.
public abstract class BaseTest {

    protected RequestClient client;

    @BeforeClass
    @Parameters({"baseUri"})
    public void setUp(@Optional String baseUriOverride) {
        String baseUri = (baseUriOverride != null && !baseUriOverride.isBlank())
                ? baseUriOverride
                : TestConfig.getProperty("baseUri");
        this.client = new RequestClient(baseUri);
    }

    //looks up endpoint paths from config properties file
    protected String endpoint(String key) {
        return Endpoints.resolve(key);
    }

    //looks up endpoint paths with {token} from config properties file
    protected String endpoint(String key, Map<String, ?> pathParams) {
        return Endpoints.resolve(key, pathParams);
    }

    //looks up json paths to project resource files from config properties file
    protected String json(String key) {
        return TestConfig.getProperty("json." + key);
    }

    //looks up json paths with {token} to project resource files from config properties file
    protected String body(String jsonKey, Map<String, ?> vars) {
        return JsonTokens.render(jsonKey, vars);
    }


    //metode pentru POST, PUT, GET
    //apeleaza metodele de mai sus pentru a lua endpoint-ul si json-ul
    protected Response post(String endpointKey, String jsonKey, Map<String, ?> vars) {
        String path = endpoint(endpointKey);
        String payload = body(jsonKey, vars);
        return client.sendWithBody(Method.POST, path, payload);
    }

    protected Response post(String endpointKey, Object body) {
        String path = endpoint(endpointKey);
        return client.sendWithBody(Method.POST, path, body);
    }

    protected Response put(String endpointKey, String jsonKey, Map<String, ?> vars) {
        String path = endpoint(endpointKey);
        String payload = body(jsonKey, vars);
        return client.sendWithBody(Method.PUT, path, payload);
    }

    protected Response get(String endpointKey) {
        return client.get(endpoint(endpointKey), null, null, null);
    }

    protected Response get(String endpointKey, Map<String, String> headers) {
        return client.get(endpoint(endpointKey), null, null, headers);
    }

//    protected Response delete(String endpointKey, Map<String, String> headers) {
//        return client.delete(endpoint(endpointKey), null, null, headers);
//    }

    protected Response delete(String endpointKey, Map<String, String> headers, Map<String, String> pathParams) {
        String url = endpoint(endpointKey, pathParams);
        return client.delete(url, null, null, headers);
    }
}

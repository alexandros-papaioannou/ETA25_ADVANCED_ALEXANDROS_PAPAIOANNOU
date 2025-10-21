package api.support;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;

//class that models the Client
public class RequestClient {

    private final RequestSpecification baseSpec;

    //constructor that inits the client with the same consistent configuration
    //makes sure that every req made by this client
    // will have an URI, content type and accept
    public RequestClient(String baseUri) {
        //daca testul pica, logheaza req si response
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.baseSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    //method that triggers a GET request and
    //returns a RestAssured HTTP response (status code, body, header)
    //path = relative path/endpoint
    //can take path(id), query parameters(sort asc, desc, page=1) and
    //header parameters (authorization bearer token)
    public Response get(String path,
                        Map<String, ?> pathParams,
                        Map<String, ?> queryParams,
                        Map<String, String> headers) {
        var req = given().spec(baseSpec); //starts RestAssured request
        if (pathParams != null) { //replaces parameters if provided
            req.pathParams(pathParams);
        }
        if (queryParams != null) { //replaces parameters if provided
            req.queryParams(queryParams);
        }
        if (headers != null) { //replaces parameters if provided
            req.headers(headers);
        }
        return req.when().get(path); //triggers the GET req and returns the GET response after the above setup
    }

    public Response delete(String path,
                        Map<String, ?> pathParams,
                        Map<String, ?> queryParams,
                        Map<String, String> headers) {
        var req = given().spec(baseSpec); //starts RestAssured request
        if (pathParams != null) { //replaces parameters if provided
            req.pathParams(pathParams);
        }
        if (queryParams != null) { //replaces parameters if provided
            req.queryParams(queryParams);
        }
        if (headers != null) { //replaces parameters if provided
            req.headers(headers);
        }
        return req.when().delete(path); //triggers the GET req and returns the GET response after the above setup
    }

    public Response postFromResource(String path, String resourcePath) {
        //String json = readResourceAsString(resourcePath);
        String json = JsonReader.readResourceAsString(resourcePath);
        return sendWithBody(Method.POST, path, json);
    }

    public Response putFromResource(String path, String resourcePath) {
        //String json = readResourceAsString(resourcePath);
        String json = JsonReader.readResourceAsString(resourcePath);
        return sendWithBody(Method.PUT, path, json);
    }

    //generic method to send any HTTP request, with optional body
    //returns a RestAssured HTTP response
    //Method method = Method.PUT, Method.POST
    //String path = endpoint
    //Object body = JSON string, Java object or null
    public Response sendWithBody(Method method, String path, Object body) {
        //builds RestAssured request
        var req = given().spec(baseSpec);
        //if body provided, is attached to the request;
        // if it's a JSON string, sends it as it is;
        // if it's a Java object, it's converted to JSON
        if (body != null) req.body(body);
        //executes the request with the given method and endpoint and returns the response
        return req.when().request(method, path);
    }

//    protected static String readResourceAsString(String resourcePath) {
//        try (InputStream is = Thread.currentThread()
//                .getContextClassLoader()
//                .getResourceAsStream(resourcePath)) {
//            if (is == null) throw new IllegalArgumentException("Resource not found " + resourcePath);
//            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load resource: " + resourcePath, e);
//        }
//    }
}

package com.github.lusoalex;

import java.util.List;
import java.util.Map;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by lusoalex @github.com on 30/11/2016.
 */
public class Chainr {

    final private com.bazaarvoice.jolt.Chainr proxyChainr;

    public Chainr(com.bazaarvoice.jolt.Chainr chainr) {
        this.proxyChainr = chainr;
    }

    public Object transform(JsonObject input) {
        return proxyChainr.transform(input.getMap());
    }

    public Object transform(int to, JsonObject input) {
        return proxyChainr.transform(to, input.getMap());
    }

    public Object transform(int from, int to, JsonObject input) {
        return proxyChainr.transform(from, to, input.getMap());
    }

    public JsonObject transformToJsonObject(JsonObject input) {
        Map<String, Object> transformed = (Map<String, Object>) transform(input);
        return new JsonObject(transformed);
    }

    public JsonObject transformToJsonObject(int to, JsonObject input) {
        Map<String, Object> transformed = (Map<String, Object>) transform(to, input);
        return new JsonObject(transformed);
    }

    public JsonObject transformToJsonObject(int from, int to, JsonObject input) {
        Map<String, Object> transformed = (Map<String, Object>) transform(from, to, input);
        return new JsonObject(transformed);
    }

    public JsonArray transformToJsonArray(JsonObject input) {
        List<Object> transformed = (List<Object>) transform(input);
        return new JsonArray(transformed);
    }

    public JsonArray transformToJsonArray(int to, JsonObject input) {
        List<Object> transformed = (List<Object> ) transform(to, input);
        return new JsonArray(transformed);
    }

    public JsonArray transformToJsonArray(int from, int to, JsonObject input) {
        List<Object> transformed = (List<Object> ) transform(from, to, input);
        return new JsonArray(transformed);
    }

}

package com.github.lusoalex;

import java.util.Map;

import io.vertx.core.json.JsonObject;

/**
 * Created by Alexandre on 30/11/2016.
 */
public class Chainr {

    final private com.bazaarvoice.jolt.Chainr proxyChainr;

    public Chainr(com.bazaarvoice.jolt.Chainr chainr) {
        this.proxyChainr = chainr;
    }

    public JsonObject transform(JsonObject input) {
        Map<String, Object> transformed = (Map<String, Object>) proxyChainr.transform(input.getMap());
        return new JsonObject(transformed);
    }

    public JsonObject transform(int to, JsonObject input) {
        Map<String, Object> transformed = (Map<String, Object>) proxyChainr.transform(to, input.getMap());
        return new JsonObject(transformed);
    }

    public JsonObject transform(int from, int to, JsonObject input) {
        Map<String, Object> transformed = (Map<String, Object>) proxyChainr.transform(from, to, input.getMap());
        return new JsonObject(transformed);
    }
}

package com.github.lusoalex;

import com.github.lusoalex.chainr.ChainrBuilder;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * These tests only aim to validate our entry point to jolt.
 * JOLT functions are/should be tested into the core project : https://github.com/bazaarvoice/jolt
 */
public class ChainrTestJsonArray {

    final static private String RESSOURCE_PATH = "src/test/resources/json/defaultr/json/array/";

    @Test
    public void testTransformWithFullSpecs() throws IOException {
        JsonObject jsonTest = Vertx.vertx().fileSystem().readFileBlocking(RESSOURCE_PATH+"simpleTestCaseOne.json").toJsonObject();

        JsonObject input = jsonTest.getJsonObject("input");
        JsonArray specs = jsonTest.getJsonArray("specs");
        JsonArray expected = jsonTest.getJsonArray("expected");

        Chainr chainr = new ChainrBuilder(specs).build();
        JsonArray result = chainr.transformToJsonArray(input);

        Assert.assertTrue("Jolt mapping result not as expected.", expected.equals(result));
    }

    @Test
    public void splitUsingOnlyFirstSpecs() throws IOException {
        JsonObject jsonTest = Vertx.vertx().fileSystem().readFileBlocking(RESSOURCE_PATH+"splitTestCaseOne.json").toJsonObject();

        JsonObject input = jsonTest.getJsonObject("input");
        JsonArray specs = jsonTest.getJsonArray("specs");
        JsonArray expected = jsonTest.getJsonArray("expected");

        Chainr chainr = new ChainrBuilder(specs).build();
        JsonArray result = chainr.transformToJsonArray(1,input);

        Assert.assertTrue("Jolt mapping result not as expected.", expected.equals(result));
    }

    @Test
    public void splitUsingOnlySecondSpecs() throws IOException {
        JsonObject jsonTest = Vertx.vertx().fileSystem().readFileBlocking(RESSOURCE_PATH+"splitTestCaseTwo.json").toJsonObject();

        JsonObject input = jsonTest.getJsonObject("input");
        JsonArray specs = jsonTest.getJsonArray("specs");
        JsonArray expected = jsonTest.getJsonArray("expected");

        Chainr chainr = new ChainrBuilder(specs).build();
        JsonArray result = chainr.transformToJsonArray(0,2,input);

        Assert.assertTrue("Jolt mapping result not as expected.", expected.equals(result));
    }

}

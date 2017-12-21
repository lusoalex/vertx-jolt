package com.github.lusoalex;

import java.io.IOException;

import com.github.lusoalex.chainr.ChainrBuilder;
import org.junit.Assert;
import org.junit.Test;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * These tests only aim to validate our entry point to jolt.
 * JOLT functions are/should be tested into the core project : https://github.com/bazaarvoice/jolt
 */
public class ChainrTestJsonObject {

    final static private String RESSOURCE_PATH = "src/test/resources/json/defaultr/json/object/";

    @Test
    public void shiftThenRemove() throws IOException {
        testTransformWithFullSpecs("simpleTestCaseOne.json");
    }

    @Test
    public void removeThenShift() throws IOException {
        testTransformWithFullSpecs("simpleTestCaseTwo.json");
    }

    private void testTransformWithFullSpecs(String fileName) throws IOException {
        JsonObject jsonTest = Vertx.vertx().fileSystem().readFileBlocking(RESSOURCE_PATH+fileName).toJsonObject();

        JsonObject input = jsonTest.getJsonObject("input");
        JsonArray specs = jsonTest.getJsonArray("specs");
        JsonObject expected = jsonTest.getJsonObject("expected");

        Chainr chainr = new ChainrBuilder(specs).build();
        JsonObject result = chainr.transformToJsonObject(input);

        Assert.assertTrue("Jolt mapping result not as expected.", expected.equals(result));
    }

    @Test
    public void splitUsingOnlyFirstSpecs() throws IOException {
        JsonObject jsonTest = Vertx.vertx().fileSystem().readFileBlocking(RESSOURCE_PATH+"splitTestCaseOne.json").toJsonObject();

        JsonObject input = jsonTest.getJsonObject("input");
        JsonArray specs = jsonTest.getJsonArray("specs");
        JsonObject expected = jsonTest.getJsonObject("expected");

        Chainr chainr = new ChainrBuilder(specs).build();
        JsonObject result = chainr.transformToJsonObject(1,input);

        Assert.assertTrue("Jolt mapping result not as expected.", expected.equals(result));
    }

    @Test
    public void splitUsingOnlySecondSpecs() throws IOException {
        JsonObject jsonTest = Vertx.vertx().fileSystem().readFileBlocking(RESSOURCE_PATH+"splitTestCaseTwo.json").toJsonObject();

        JsonObject input = jsonTest.getJsonObject("input");
        JsonArray specs = jsonTest.getJsonArray("specs");
        JsonObject expected = jsonTest.getJsonObject("expected");

        Chainr chainr = new ChainrBuilder(specs).build();
        JsonObject result = chainr.transformToJsonObject(1,2,input);

        Assert.assertTrue("Jolt mapping result not as expected.", expected.equals(result));
    }

}

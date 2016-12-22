# vertx-jolt

This project is a simple facade to original jolt project : https://github.com/bazaarvoice/jolt.
***JOLT project is a json to json transformation tool.***

It works like a proxy to original jolt objects, by providing exactly the same class names & methods, but requiring Vert.x JsonObject instead of Map &#60;String,Object&#62;

That way we do not need to perform transformation in our business code.

Without vertx-jolt :
<pre><code>
JsonArray joltJsonSpecs = this.readJsonToJsonSettings();
Chainr chainedRules = new ChainrBuilder(joltJsonSpecs.getList()).build();
Map &#60;String,Object&#62; transformedResult = (Map &#60;String,Object&#62;) chainedRules.transform(originalJson.toMap());
return new JsonObject(transformedResult);
</code></pre>

Wit vertx-jolt :
<pre><code>
JsonArray joltJsonSpecs = this.readJsonToJsonSettings();
Chainr chainedRules = new ChainrBuilder(joltJsonSpecs).build();
JsonObject transformedResult = chainedRules.transform(originalJson);
return transformedResult;
</code></pre>

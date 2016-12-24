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

Here we will take as example an input JSON to transform (originalJson input above) :
```json
{
	"content": [
		{
			"name": "Elysee",
			"country": "FRANCE",
			"address": "Avenue des Champs-Élysées",
			"city": "PARIS",
			"stores_id": 1,
			"zip_code": "75000",
			"gps_x": "48.869729",
			"gps_y": "2.307784",
			"phone_number": "+33 1 00 00 00 00",
			"backend_url": "https://north-europe.company.com/"
		},
		{
			"name": "Belem",
			"country": "PORTUGAL",
			"address": "Avenida Brasilia",
			"city": "LISBON",
			"stores_id": 2,
			"zip_code": "75000",
			"gps_x": "38.693060",
			"gps_y": "-9.218120",
			"phone_number": "+351 000 000 000",
			"backend_url": "https://south-europe.company.com/"
		},
		{
			"name": "Yu garden",
			"country": "CHINA",
			"address": "huanpu qu",
			"city": "Shanghai",
			"stores_id": 3,
			"zip_code": "XXXX",
			"gps_x": "31.227208",
			"gps_y": "121.491836",
			"phone_number": "+86 000 000 000",
			"backend_url": "https://south-asia.company.com/"
		}
	]
}
```

And the expected JSON result is a key value json (kind of UDDI) :
```json
[ 
  {
    "key" : 1,
    "value" : "https://north-europe.company.com/"
  }, 
  {
    "key" : 2,
    "value" : "https://south-europe.company.com/"
  }, 
  {
    "key" : 3,
    "value" : "https://south-asia.company.com/"
  } 
]
```

To Achieve this, we need to provide a JOLT JSON specification, in our case it will be :
```json
[
  {
    "operation": "shift",
    "spec": {
      "content": {
        "*": {
          "stores_id": "[&1].key",
          "backend_url": "[&1].value"
        }
      }
    }
  }
]

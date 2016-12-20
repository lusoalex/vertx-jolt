package com.github.lusoalex.chainr;


import java.util.List;
import java.util.Map;

import io.vertx.core.json.JsonArray;
import com.github.lusoalex.Chainr;

/**
 * Created by Alexandre on 30/11/2016.
 */
public class ChainrBuilder {

    final private List<Map<String, Object>> specs;

    public ChainrBuilder(JsonArray specs) {
        this.specs = specs.getList();
    }

    public Chainr build() {
        return new Chainr(new com.bazaarvoice.jolt.chainr.ChainrBuilder(specs).build());
    }
}

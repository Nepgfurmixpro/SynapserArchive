package com.neothedeveloper.latte.registries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DimensionRegistry {
    protected JsonObject registry;
    protected JsonArray dimensionEntries;
    public DimensionRegistry() {
        this.registry = new JsonObject();
        this.dimensionEntries = new JsonArray();
    }

    public void register(JsonObject dimension) {
        this.dimensionEntries.add(dimension);
    }

    public JsonObject finish() {
        registry.add("minecraft:dimension_type", dimensionEntries);
        return registry;
    }
}

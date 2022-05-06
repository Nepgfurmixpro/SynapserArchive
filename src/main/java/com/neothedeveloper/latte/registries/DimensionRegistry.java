package com.neothedeveloper.latte.registries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DimensionRegistry {
    protected JsonObject registry;
    protected JsonArray dimensionEntries;
    protected JsonArray biomeEntires;
    protected JsonArray identifiers;
    public DimensionRegistry() {
        this.registry = new JsonObject();
        this.dimensionEntries = new JsonArray();
        this.biomeEntires = new JsonArray();
        this.identifiers = new JsonArray();
    }

    public void registerDimension(JsonObject dimension) {
        this.dimensionEntries.add(dimension);
        this.identifiers.add(dimension.get("name"));
    }
    public void registerBiome(JsonObject biome) {
        this.biomeEntires.add(biome);
    }

    public JsonArray getIdentifiers() {
        return identifiers;
    }

    public JsonObject finish() {
        JsonObject dimensions = new JsonObject();
        dimensions.addProperty("type", "minecraft:dimension_type");
        dimensions.add("value", dimensionEntries);
        JsonObject biomes = new JsonObject();
        biomes.addProperty("value", "minecraft:worldgen/biome");
        biomes.add("values", biomeEntires);
        registry.add("minecraft:dimension_type", dimensions);
        registry.add("minecraft:worldgen/biome", biomes);
        return registry;
    }
}

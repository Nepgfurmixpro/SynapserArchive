package com.neothedeveloper.synapser.minecraft.dimension.builders;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.dimension.Biome;
import com.neothedeveloper.synapser.minecraft.dimension.exceptions.BiomePropertyException;

public class BiomeBuilder {
    protected JsonObject obj;
    protected String precipitation, category, temperature_modifier;
    protected JsonObject effects, particle;
    protected float depth, temperature, scale, downfall;
    public BiomeBuilder(String name, int id) {
        obj = new JsonObject();
        obj.addProperty("name", name);
        obj.addProperty("id", id);
    }

    public BiomeBuilder setPrecipitation(String value) {
        this.precipitation = value;
        return this;
    }

    public BiomeBuilder setDepth(float value) {
        this.depth = value;
        return this;
    }

    public BiomeBuilder setTemperature(float value) {
        this.temperature = value;
        return this;
    }

    public BiomeBuilder setScale(float value) {
        this.scale = value;
        return this;
    }

    public BiomeBuilder setDownfall(float value) {
        this.downfall = value;
        return this;
    }

    public BiomeBuilder setCategory(String value) {
        this.category = value;
        return this;
    }

    public BiomeBuilder setTemperatureModifier(String value) {
        this.temperature_modifier = value;
        return this;
    }

    public BiomeBuilder setEffects(JsonObject value) {
        this.effects = value;
        return this;
    }

    public BiomeBuilder setParticle(JsonObject value) {
        this.particle = value;
        return this;
    }

    public JsonObject build() {
        try {
            JsonObject element = new BiomePropertiesBuilder(precipitation, category, temperature_modifier, depth, temperature, scale, downfall, effects, particle).build();
            obj.add("element", element);
            return obj;
        } catch (BiomePropertyException e) {
            throw new RuntimeException(e);
        }
    }
}

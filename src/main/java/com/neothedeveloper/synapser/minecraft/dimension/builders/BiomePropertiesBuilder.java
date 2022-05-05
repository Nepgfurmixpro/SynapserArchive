package com.neothedeveloper.synapser.minecraft.dimension.builders;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.dimension.exceptions.BiomePropertyException;

public class BiomePropertiesBuilder {
    private final String
            precipitation, category,
            temperature_modifier;

    private final float
            depth, temperature,
            scale, downfall;
    public final JsonObject
            effect, particle;

    private boolean stringNotValid(String item) {
        return item == null || item.equals("");
    }

    public BiomePropertiesBuilder(String precipitation,
                                  String category,
                                  String temperature_modifier,
                                  float depth,
                                  float temperature,
                                  float scale,
                                  float downfall,
                                  JsonObject effect,
                                  JsonObject particle) throws BiomePropertyException {
        this.effect = effect;
        this.particle = particle;
        if (stringNotValid(precipitation) || stringNotValid(category)) throw new BiomePropertyException("Biome properties were not valid.");
        this.precipitation = precipitation;
        this.category = category;
        this.temperature_modifier = temperature_modifier;
        this.depth = depth;
        this.temperature = temperature;
        this.scale = scale;
        this.downfall = downfall;
    }
    public JsonObject build() {
        JsonObject obj = new JsonObject();
        obj.addProperty("precipitation", precipitation);
        obj.addProperty("depth", depth);
        obj.addProperty("temperature", temperature);
        obj.addProperty("scale", scale);
        obj.addProperty("downfall", downfall);
        obj.addProperty("category", category);
        if (temperature_modifier != null) obj.addProperty("temperature_modifer", temperature_modifier);
        if (effect != null) obj.add("effects", effect);
        if (particle != null) obj.add("particle", particle);
        return obj;
    }
}

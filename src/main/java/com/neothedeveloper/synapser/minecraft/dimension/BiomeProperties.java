package com.neothedeveloper.synapser.minecraft.dimension;

import com.neothedeveloper.synapser.minecraft.dimension.exceptions.BiomePropertyException;

public class BiomeProperties {
    private final String
            precipitation, category,
            temperature_modifier;

    private final float
            depth, temperature,
            scale, downfall;

    private boolean isStringInvalid(String item) {
        return item == null || item.equals("");
    }

    public BiomeProperties(String precipitation, String category, String temperature_modifier, float depth, float temperature, float scale, float downfall) throws BiomePropertyException {
        if (isStringInvalid(precipitation) || isStringInvalid(category) || isStringInvalid(temperature_modifier)) throw new BiomePropertyException("Biome properties were not valid.");
        this.precipitation = precipitation;
        this.category = category;
        this.temperature_modifier = temperature_modifier;
        this.depth = depth;
        this.temperature = temperature;
        this.scale = scale;
        this.downfall = downfall;
    }
}

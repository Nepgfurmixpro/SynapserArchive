package com.neothedeveloper.synapser.minecraft.dimension.biomes;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.dimension.Biome;
import com.neothedeveloper.synapser.minecraft.dimension.builders.BiomeBuilder;
import com.neothedeveloper.synapser.minecraft.dimension.builders.BiomeEffectBuilder;
import com.neothedeveloper.synapser.minecraft.dimension.builders.BiomePropertyCreators;

public class TheVoid extends Biome {
    @Override
    public JsonObject create() {
        BiomeBuilder builder = new BiomeBuilder("minecraft:the_void", 0);
        builder.setCategory("none")
                .setDownfall(0.5f)
                .setEffects(new BiomeEffectBuilder()
                        .setFogColor(12638463)
                        .setMoodSound(BiomePropertyCreators.createMoodSound("minecraft:ambient.cave", 6000, 6000, 8))
                        .setSkyColor(8103167)
                        .setWaterColor(4159204)
                        .setWaterFogColor(329011)
                        .build())
                .setPrecipitation("none")
                .setTemperature(0.5f);
        return builder.build();
    }
}

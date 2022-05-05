package com.neothedeveloper.synapser.minecraft.dimension.builders;

import com.google.gson.JsonObject;

public class BiomeEffectBuilder {
    protected int skyColor, waterFogColor, fogColor, waterColor, foliageColor, grassColor;
    protected String grassColorModifier, ambientSound;

    protected JsonObject music, additionsSounds, moodSound;

    public BiomeEffectBuilder setSkyColor(int skyColor) {
        this.skyColor = skyColor;
        return this;
    }
    public BiomeEffectBuilder setWaterFogColor(int waterFogColor) {
        this.waterFogColor = waterFogColor;
        return this;
    }
    public BiomeEffectBuilder setFogColor(int fogColor) {
        this.fogColor = fogColor;
        return this;
    }
    public BiomeEffectBuilder setWaterColor(int waterColor) {
        this.waterColor = waterColor;
        return this;
    }
}

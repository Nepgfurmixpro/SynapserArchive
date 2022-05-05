package com.neothedeveloper.synapser.minecraft.dimension.builders;

import com.google.gson.JsonObject;

public class BiomeEffectBuilder {
    protected int skyColor, waterFogColor, fogColor, waterColor, foliageColor, grassColor;
    protected String grassColorModifier, ambientSound;

    protected JsonObject music, additionsSound, moodSound;

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
    public BiomeEffectBuilder setFoliageColor(int foliageColor) {
        this.foliageColor = foliageColor;
        return this;
    }
    public BiomeEffectBuilder setGrassColor(int grassColor) {
        this.grassColor = grassColor;
        return this;
    }
    public BiomeEffectBuilder setGrassColorModifier(String grassColorModifier) {
        this.grassColorModifier = grassColorModifier;
        return this;
    }
    public BiomeEffectBuilder setAmbientSound(String ambientSound) {
        this.ambientSound = ambientSound;
        return this;
    }
    public BiomeEffectBuilder setMusic(JsonObject music) {
        this.music = music;
        return this;
    }
    public BiomeEffectBuilder setAdditionsSound(JsonObject additionsSound) {
        this.additionsSound = additionsSound;
        return this;
    }
    public BiomeEffectBuilder setMoodSound(JsonObject moodSound) {
        this.moodSound = moodSound;
        return this;
    }
    public JsonObject build() {
        JsonObject obj = new JsonObject();
        obj.addProperty("sky_color", skyColor);
        obj.addProperty("water_fog_color", waterFogColor);
        obj.addProperty("fog_color", fogColor);
        obj.addProperty("water_color", waterColor);
        if (foliageColor != 0) obj.addProperty("foliage_color", foliageColor);
        if (grassColor != 0) obj.addProperty("grass_color", grassColor);
        if (grassColorModifier != null) obj.addProperty("grass_color_modifier", grassColorModifier);
        if (music != null) obj.add("music", music);
        if (additionsSound != null) obj.add("additions_sounds", additionsSound);
        if (moodSound != null) obj.add("mood_sound", moodSound);
        return obj;
    }

}

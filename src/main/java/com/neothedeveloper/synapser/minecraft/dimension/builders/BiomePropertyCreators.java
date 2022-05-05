package com.neothedeveloper.synapser.minecraft.dimension.builders;

import com.google.gson.JsonObject;

public class BiomePropertyCreators {
    public static JsonObject createMusicEffect(boolean replaceCurrentMusic, String sound, int maxDelay, int minDelay) {
        JsonObject obj = new JsonObject();
        obj.addProperty("replace_current_music", replaceCurrentMusic ? 0x01 : 0x00);
        obj.addProperty("sound", sound);
        obj.addProperty("max_delay", maxDelay);
        obj.addProperty("min_delay", minDelay);
        return obj;
    }

    public static JsonObject createAdditionalSounds(String sound, double tickChance) {
        JsonObject obj = new JsonObject();
        obj.addProperty("sound", sound);
        obj.addProperty("tick_chance", tickChance);
        return obj;
    }

    public static JsonObject createMoodSound(String sound, int tickDelay, double offset, int blockSearchExtent) {
        JsonObject obj = new JsonObject();
        obj.addProperty("sound", sound);
        obj.addProperty("tick_delay", tickDelay);
        obj.addProperty("offset", offset);
        obj.addProperty("block_search_extent", blockSearchExtent);
        return obj;
    }

    public static JsonObject createParticle(String type, float probability) {
        JsonObject obj = new JsonObject();
        obj.addProperty("probability", probability);
        JsonObject options = new JsonObject();
        options.addProperty("type", type);
        obj.add("options", options);
        return obj;
    }
}

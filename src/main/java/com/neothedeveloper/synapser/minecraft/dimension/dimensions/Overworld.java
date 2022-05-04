package com.neothedeveloper.synapser.minecraft.dimension.dimensions;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.dimension.Dimension;
import com.neothedeveloper.synapser.minecraft.dimension.DimensionBuilder;

public class Overworld extends Dimension {
    @Override
    public JsonObject create() {
        DimensionBuilder builder = new DimensionBuilder("minecraft:overworld", 0);
        builder.ambientLight(0.0f)
                .doBedsWork(true)
                .setCoordinateScale(1.0)
                .effects("minecraft:overworld")
                .hasCeiling(false)
                .hasRaids(true)
                .hasSkyLight(true)
                .setHeight(384)
                .infiniburn("#minecraft:ininiburn_overworld")
                .setLogicalHeight(384)
                .setMinY(-64)
                .isNatural(true)
                .isPiglinSafe(false)
                .doRespawnAnchorsWork(false)
                .ultrawarm(false);

        return builder.build();
    }
}

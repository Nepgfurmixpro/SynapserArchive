package com.neothedeveloper.synapser.minecraft.dimension;

import com.google.gson.JsonObject;

public class DimensionType {
    private final boolean
            piglinSafe, bedWorks,
            hasRaids, natural,
            respawnAnchorWorks,
            hasSkyLight, ultrawarm,
            hasCeiling;
    private final long fixedTime;
    private final float ambientLight;
    private final String
            infiniburn, effects;
    private final int
            minY, height,
            logicalHeight;
    private final double coordinateScale;
    public DimensionType(
            boolean piglinSafe,
            boolean bedWorks,
            boolean hasRaids,
            boolean natural,
            float ambientLight,
            boolean respawnAnchorWorks,
            boolean hasSkyLight,
            boolean ultrawarm,
            boolean hasCeiling,
            long fixedtime,
            String infiniburn,
            String effects,
            int minY,
            int height,
            int logicalHeight,
            double coordinateScale) {
        this.piglinSafe = piglinSafe;
        this.bedWorks = bedWorks;
        this.hasRaids = hasRaids;
        this.natural = natural;
        this.ambientLight = ambientLight;
        this.respawnAnchorWorks = respawnAnchorWorks;
        this.hasSkyLight = hasSkyLight;
        this.ultrawarm = ultrawarm;
        this.hasCeiling = hasCeiling;
        this.fixedTime = fixedtime;
        this.infiniburn = infiniburn;
        this.effects = effects;
        this.minY = minY;
        this.height = height;
        this.logicalHeight = logicalHeight;
        this.coordinateScale = coordinateScale;
    }

    public JsonObject build() {
        JsonObject obj = new JsonObject();
        obj.addProperty("piglin_safe", piglinSafe ? 0x01 : 0x00);
        obj.addProperty("natural", natural ? 0x01 : 0x00);
        obj.addProperty("ambient_light", ambientLight);
        if (fixedTime == 0) obj.addProperty("fixed_time", fixedTime);
        obj.addProperty("infiniburn", infiniburn);
        obj.addProperty("respawn_anchor_works", respawnAnchorWorks ? 0x01 : 0x00);
        obj.addProperty("has_skylight", hasSkyLight ? 0x01 : 0x00);
        obj.addProperty("bed_works", bedWorks ? 0x01 : 0x00);
        obj.addProperty("effects", effects);
        obj.addProperty("has_raids", hasRaids ? 0x01 : 0x00);
        obj.addProperty("min_y", minY);
        obj.addProperty("height", height);
        obj.addProperty("logical_height", logicalHeight);
        obj.addProperty("coordinate_scale", coordinateScale);
        obj.addProperty("ultrawarm", ultrawarm ? 0x01 : 0x00);
        obj.addProperty("has_ceiling", hasCeiling ? 0x01 : 0x00);
        return obj;
    }
}

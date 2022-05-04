package com.neothedeveloper.synapser.minecraft.dimension;

import com.google.gson.JsonObject;

public class DimensionBuilder {
    JsonObject obj;
    protected boolean
            piglinSafe, bedWorks,
            hasRaids, natural,
            respawnAnchorWorks,
            hasSkyLight, ultrawarm,
            hasCeiling;
    protected long fixedTime;
    protected float ambientLight;
    protected String
            infiniburn, effects;
    protected int
            minY, height,
            logicalHeight;
    private double coordinateScale;
    public DimensionBuilder(String name, int id) {
        obj = new JsonObject();
        obj.addProperty("name", name);
        obj.addProperty("id", id);
    }

    public DimensionBuilder isPiglinSafe(boolean value) {
        this.piglinSafe = value;
        return this;
    }

    public DimensionBuilder doBedsWork(boolean value) {
        this.bedWorks = value;
        return this;
    }

    public DimensionBuilder hasRaids(boolean value) {
        this.hasRaids = value;
        return this;
    }

    public DimensionBuilder isNatural(boolean value) {
        this.natural = value;
        return this;
    }

    public DimensionBuilder ambientLight(float value) {
        this.ambientLight = value;
        return this;
    }

    public DimensionBuilder doRespawnAnchorsWork(boolean value) {
        this.respawnAnchorWorks = value;
        return this;
    }

    public DimensionBuilder hasSkyLight(boolean value) {
        this.hasSkyLight = value;
        return this;
    }

    public DimensionBuilder ultrawarm(boolean value) {
        this.ultrawarm = value;
        return this;
    }

    public DimensionBuilder hasCeiling(boolean value) {
        this.hasCeiling = value;
        return this;
    }

    public DimensionBuilder setFixedTime(long value) {
        this.fixedTime = value;
        return this;
    }

    public DimensionBuilder infiniburn(String value) {
        this.infiniburn = value;
        return this;
    }

    public DimensionBuilder effects(String value) {
        this.effects = value;
        return this;
    }

    public DimensionBuilder setMinY(int value) {
        this.minY = value;
        return this;
    }

    public DimensionBuilder setHeight(int value) {
        this.height = value;
        return this;
    }

    public DimensionBuilder setLogicalHeight(int value) {
        this.logicalHeight = value;
        return this;
    }

    public DimensionBuilder setCoordinateScale(double value) {
        this.coordinateScale = value;
        return this;
    }

    public JsonObject build() {
        obj.add("element", new DimensionType(piglinSafe, bedWorks, hasRaids, natural, ambientLight, respawnAnchorWorks, hasSkyLight, ultrawarm, hasCeiling, fixedTime, infiniburn, effects, minY, height, logicalHeight, coordinateScale).build());
        return obj;
    }
}

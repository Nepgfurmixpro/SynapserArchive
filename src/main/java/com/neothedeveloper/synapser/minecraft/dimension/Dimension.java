package com.neothedeveloper.synapser.minecraft.dimension;

import com.google.gson.JsonObject;

public class Dimension {
    public JsonObject create() {
        return new JsonObject();
    }
    private final String m_name;
    public Dimension(String name) {
        m_name = name;
    }
    public String getName() {
        return m_name;
    }
}

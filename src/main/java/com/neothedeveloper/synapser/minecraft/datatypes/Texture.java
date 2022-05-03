package com.neothedeveloper.synapser.minecraft.datatypes;

public class Texture {
    private final String m_data;
    private final String m_signature;
    public Texture(String data, String signature) {
        m_data = data;
        m_signature = signature;
    }

    public String getSignature() {
        return m_signature;
    }

    public String getData() {
        return m_data;
    }
}

package com.neothedeveloper.synapser.minecraft.builders;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.datatypes.Color;
import com.neothedeveloper.synapser.minecraft.datatypes.Component;

public class ChatBuilder {
    protected boolean m_isBold = false;
    protected boolean m_isItalic = false;
    protected boolean m_isUnderlined = false;
    protected boolean m_isStrikethrough = false;
    protected boolean m_isObfuscated = false;
    protected Color m_color = Color.none;
    protected String m_text = "";
    public ChatBuilder() {

    }
    public ChatBuilder determineByComponent(Component c) {
        switch (c) {
            case bold -> isBold(true);
            case italic -> isItalic(true);
            case underline -> isUnderlined(true);
            case strikethrough -> isStrikethrough(true);
            case obfuscated -> isObfuscated(true);
        }
        return this;
    }
    public ChatBuilder isBold(boolean value) {
        m_isBold = value;
        return this;
    }
    public ChatBuilder isItalic(boolean value) {
        m_isItalic = value;
        return this;
    }
    public ChatBuilder isUnderlined(boolean value) {
        m_isUnderlined = value;
        return this;
    }
    public ChatBuilder isStrikethrough(boolean value) {
        m_isStrikethrough = value;
        return this;
    }
    public ChatBuilder isObfuscated(boolean value) {
        m_isObfuscated = value;
        return this;
    }
    public ChatBuilder setColor(Color c) {
        m_color = c;
        return this;
    }
    public ChatBuilder reset() {
        m_isBold = false;
        m_isItalic = false;
        m_isUnderlined = false;
        m_isStrikethrough = false;
        m_isObfuscated = false;
        return this;
    }
    public String getText() { return m_text; }
    public ChatBuilder setText(String value) {
        m_text = value;
        return this;
    }
    public JsonObject build() {
        JsonObject out = new JsonObject();
        out.addProperty("text", m_text);
        if (m_isBold) out.addProperty("bold", "true");
        if (m_isItalic) out.addProperty("italic", "true");
        if (m_isUnderlined) out.addProperty("underline", "true");
        if (m_isStrikethrough) out.addProperty("strikethrough", "true");
        if (m_isObfuscated) out.addProperty("obfuscated", "true");
        if (m_color != Color.none) out.addProperty("color", m_color.toString());
        return out;
    }
}

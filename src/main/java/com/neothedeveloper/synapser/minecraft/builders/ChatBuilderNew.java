package com.neothedeveloper.synapser.minecraft.builders;

import com.neothedeveloper.synapser.minecraft.datatypes.Color;

public class ChatBuilderNew {
    private boolean m_isBold = false;
    private boolean m_isItalic = false;
    private boolean m_isUnderlined = false;
    private boolean m_isStrikethrough = false;
    private boolean m_isObfuscated = false;
    private Color m_color = Color.none;
    public ChatBuilderNew() {

    }
    public ChatBuilderNew isBold(boolean value) {
        m_isBold = value;
        return this;
    }
    public ChatBuilderNew isItalic(boolean value) {
        m_isItalic = value;
        return this;
    }
    public ChatBuilderNew isUnderlined(boolean value) {
        m_isUnderlined = value;
        return this;
    }
    public ChatBuilderNew isStrikethrough(boolean value) {
        m_isStrikethrough = value;
        return this;
    }
    public ChatBuilderNew isObfuscated(boolean value) {
        m_isObfuscated = value;
        return this;
    }

}

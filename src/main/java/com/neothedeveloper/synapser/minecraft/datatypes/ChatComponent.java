package com.neothedeveloper.synapser.minecraft.datatypes;

public class ChatComponent {
    private final Component m_component;
    private final String m_value;
    public ChatComponent(Component component, ComponentValue value) {
        this.m_component = component;
        this.m_value = value.toString();
    }
    public ChatComponent(Component component, String value) {
        this.m_component = component;
        this.m_value = value;
    }

    public Component GetComponent() { return m_component; }
    public String GetValue() { return m_value; }
}

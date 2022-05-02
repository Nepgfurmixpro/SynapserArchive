package com.neothedeveloper.synapser.minecraft.builders;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.datatypes.ChatComponent;
import com.neothedeveloper.synapser.minecraft.datatypes.Component;
import com.neothedeveloper.synapser.minecraft.datatypes.ComponentValue;

import java.util.ArrayList;
import java.util.List;

public class ChatExtraBuilder {
    List<ChatComponent> m_components;
    public ChatExtraBuilder() {
        this.m_components = new ArrayList<>();
    }
    public ChatExtraBuilder addTextComponent(String value) {
        m_components.add(new ChatComponent(Component.text, value));
        return this;
    }
    public ChatExtraBuilder addComponent(Component component, ComponentValue value) {
        m_components.add(new ChatComponent(component, value));
        return this;
    }
    public JsonObject build() {
        JsonObject obj = new JsonObject();
        for (ChatComponent comp : m_components) {
            obj.addProperty(comp.GetComponent().toString(), comp.GetValue());
        }
        return obj;
    }
}

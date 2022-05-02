package com.neothedeveloper.synapser.minecraft.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.datatypes.ChatComponent;
import com.neothedeveloper.synapser.minecraft.datatypes.Component;
import com.neothedeveloper.synapser.minecraft.datatypes.ComponentValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatBuilder {
    List<ChatComponent> m_components;
    List<JsonObject> m_extras;
    public ChatBuilder() {
        this.m_components = new ArrayList<>();
    }
    public ChatBuilder addTextComponent(String value) {
        m_components.add(new ChatComponent(Component.text, value));
        return this;
    }
    public ChatBuilder addComponent(Component component, ComponentValue value) {
        m_components.add(new ChatComponent(component, value));
        return this;
    }
    public ChatBuilder addExtraComponent(ChatExtraBuilder builder) {
        return addExtraComponent(builder.build());
    }
    public ChatBuilder addExtraComponent(JsonObject builderObj) {
        m_extras.add(builderObj);
        return this;
    }
    public JsonObject build() {
        JsonObject obj = new JsonObject();
        for (ChatComponent component : m_components) {
            obj.addProperty(component.GetComponent().toString().toLowerCase(), component.GetValue().toString().toLowerCase());
        }
        if (m_extras.size() != 0) {
            JsonArray arr = new JsonArray();
            for (JsonObject extra : m_extras) {
                arr.add(extra);
            }
            obj.add("extras", arr);
        }
        return obj;
    }
}

package com.neothedeveloper.synapser.minecraft.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.minecraft.builders.ChatBuilder;
import com.neothedeveloper.synapser.minecraft.datatypes.Color;
import com.neothedeveloper.synapser.minecraft.datatypes.Component;

import java.util.ArrayList;
import java.util.List;

public class ChatParser {
    public static JsonObject ParseWithCodeChar(char codeChar, String msg) {
        String[] strings;
        strings = msg.split(String.format("((%s([abcdefklmnor]|[0-9]))+)", codeChar));
        List<ChatBuilder> builders = new ArrayList<>();
        for (String string : strings) {
            builders.add(new ChatBuilder().setText(string.replace("\\n", "\n")));
        }
        boolean onCodeChar = false;
        boolean onString = false;
        int stringI = 0;
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (c == codeChar) {
                if (onString && !onCodeChar) {
                    stringI++;
                }
                i++;
                c = msg.charAt(i);
                onCodeChar = true;
                onString = false;
                Component comp = Component.DetermineComponent(c);
                assert(stringI < builders.size());
                if (comp == Component.color) {
                    builders.get(stringI).setColor(Color.fromChar(c));
                } else if (comp == Component.reset) {
                    builders.get(stringI).reset();
                } else {
                    builders.get(stringI).determineByComponent(comp);
                }
            } else {
                onString = true;
                onCodeChar = false;
            }
        }
        JsonObject obj = null;
        JsonArray extras = new JsonArray();
        int i = 0;
        for (ChatBuilder b : builders) {
            if (i == 0) obj = b.build();
            else {
                extras.add(b.build());
            }
            i++;
        }
        if (i > 1) obj.add("extra", extras);
        return obj;
    }
}

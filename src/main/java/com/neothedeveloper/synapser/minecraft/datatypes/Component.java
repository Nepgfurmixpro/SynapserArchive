package com.neothedeveloper.synapser.minecraft.datatypes;

public enum Component {
    text,
    bold,
    italic,
    underline,
    strikethrough,
    obfuscated,
    font,
    reset,
    color;

    public static Component DetermineComponent(char c) {
        if (String.valueOf(c).matches("[klmnor]")) {
            switch (c) {
                case 'k' -> {
                    return Component.obfuscated;
                }
                case 'l' -> {
                    return Component.bold;
                }
                case 'm' -> {
                    return Component.strikethrough;
                }
                case 'n' -> {
                    return Component.underline;
                }
                case 'o' -> {
                    return Component.italic;
                }
                case 'r' -> {
                    return Component.reset;
                }
            }
        }
        if (String.valueOf(c).matches("[0123456789abcdef]")) {
            return Component.color;
        }
    }
}

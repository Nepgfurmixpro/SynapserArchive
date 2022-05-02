package com.neothedeveloper.synapser.minecraft.datatypes;

public enum Color {
    black,
    dark_blue,
    dark_green,
    dark_aqua,
    dark_red,
    dark_purple,
    gold,
    gray,
    dark_gray,
    blue,
    green,
    aqua,
    red,
    light_purple,
    yellow,
    white,
    none;
    private static final Color[] map = {
            black,
            dark_blue,
            dark_green,
            dark_aqua,
            dark_red,
            dark_purple,
            gold,
            gray,
            dark_gray,
            blue,
            green,
            aqua,
            red,
            light_purple,
            yellow,
            white
    };
    public static Color fromInteger(int i) {
        return map[i];
    }
    public static Color fromChar(char c) { return fromInteger(Integer.parseInt(String.valueOf(c), 16)); }
}

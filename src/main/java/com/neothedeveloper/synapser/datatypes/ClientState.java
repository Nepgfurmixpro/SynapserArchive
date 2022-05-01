package com.neothedeveloper.synapser.datatypes;

public enum ClientState {
    NONE,
    STATUS,
    LOGIN,
    PLAY;
    public static ClientState fromInteger(int state) {
        switch (state) {
            case 1 -> {
                return STATUS;
            }
            case 2 -> {
                return LOGIN;
            }
            case 3 -> {
                return PLAY;
            }
            case 4 -> {
                return NONE;
            }
        }
        return null;
    }
}

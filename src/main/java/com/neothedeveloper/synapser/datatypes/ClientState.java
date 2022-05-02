package com.neothedeveloper.synapser.datatypes;

public enum ClientState {
    HANDSHAKING,
    STATUS,
    LOGIN,
    PLAY;
    public static ClientState fromInteger(int state) {
        switch (state) {
            case 0 -> {
                return HANDSHAKING;
            }
            case 1 -> {
                return STATUS;
            }
            case 2 -> {
                return LOGIN;
            }
            case 3 -> {
                return PLAY;
            }
        }
        return null;
    }
}

package com.neothedeveloper.latte.server;

import java.security.*;

public class Server {
    private final int PROTOCOL_VERSION = 758;
    private final String MINECRAFT_VERSION = "1.18.2";
    private int m_playerCount = 0;
    private int m_lastEID = 0;
    public int getPlayers() {
        return m_playerCount;
    }
    public int protocolVersion() {
        return PROTOCOL_VERSION;
    }
    public String minecraftVersion() {
        return MINECRAFT_VERSION;
    }
}

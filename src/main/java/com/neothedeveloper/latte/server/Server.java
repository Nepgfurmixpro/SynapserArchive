package com.neothedeveloper.latte.server;

import com.neothedeveloper.synapser.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int PROTOCOL_VERSION = 758;
    private final String MINECRAFT_VERSION = "1.18.2";
    private List<Entity> m_entities = new ArrayList<>();
    private int m_lastEID = 0;
    public int protocolVersion() {
        return PROTOCOL_VERSION;
    }
    public String minecraftVersion() {
        return MINECRAFT_VERSION;
    }
    public int getLastEID() { return m_lastEID - 1; }
    public int getNewEID() { m_lastEID++; return m_lastEID - 1; }
    public void addEntity(Entity entity) {
        m_entities.add(entity);
        m_lastEID++;
    }
    public void removeEntity(Entity entity) {
        m_entities.remove(entity);
    }
}

package com.neothedeveloper.latte;

// Latte is the thing that stores things such as online players, entities, etc.
// The public and private key for the server is in here

import com.neothedeveloper.latte.registries.DimensionRegistry;
import com.neothedeveloper.latte.server.ClientBrandRetriever;
import com.neothedeveloper.latte.server.Protocol;
import com.neothedeveloper.latte.server.Server;
import com.neothedeveloper.latte.server.Synapser;

public class Latte {
    protected static final Server m_server = new Server();
    protected static final Synapser m_synapser = new Synapser();
    protected static final ClientBrandRetriever m_retriever = new ClientBrandRetriever();
    public static Server server() {
        return m_server;
    }
    public static Synapser synapser() {
        return m_synapser;
    }
    public static ClientBrandRetriever clientBrandRetriever() { return m_retriever; }

    public static DimensionRegistry DIMENSION_REGISTRY = new DimensionRegistry();
}

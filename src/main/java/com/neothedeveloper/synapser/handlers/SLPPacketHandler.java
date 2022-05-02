package com.neothedeveloper.synapser.handlers;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;

public class SLPPacketHandler extends PacketHandler {
    public SLPPacketHandler() {
        super(ClientState.STATUS);
    }
    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        JsonObject slp = new JsonObject();
        JsonObject m;
        // Version
        m = new JsonObject();
        m.addProperty("name", "1.18.2");
        m.addProperty("protocol", 758);
        slp.add("version", m);
        // Players
        m = new JsonObject();
        m.addProperty("max", Integer.parseInt(Synapser.SERVER_PROPERTIES.GetProperty("max-players")));
        m.addProperty("online", 0);
        slp.add("players", m);
        // MOTD
        m = new JsonObject();
        m.addProperty("text", Synapser.SERVER_PROPERTIES.GetProperty("motd"));
        slp.add("description", m);
        socket.Write(new OutboundPacketBuilder(0x00).AddStringField(slp.toString()).Build());
    }
}

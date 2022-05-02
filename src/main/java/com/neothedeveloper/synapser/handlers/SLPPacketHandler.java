package com.neothedeveloper.synapser.handlers;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.minecraft.utils.ChatParser;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.server.Protocol;

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
        m.addProperty("name", Protocol.MINECRAFT_VERSION);
        m.addProperty("protocol", Protocol.VERSION);
        slp.add("version", m);
        // Players
        m = new JsonObject();
        m.addProperty("max", Integer.parseInt(Synapser.SERVER_PROPERTIES.GetProperty("max-players")));
        m.addProperty("online", 0);
        slp.add("players", m);
        // MOTD
        slp.add("description", ChatParser.ParseWithCodeChar('&', Synapser.SERVER_PROPERTIES.GetProperty("motd")));
        socket.Write(new OutboundPacketBuilder(0x00).AddStringField(slp.toString()).Build());
    }
}

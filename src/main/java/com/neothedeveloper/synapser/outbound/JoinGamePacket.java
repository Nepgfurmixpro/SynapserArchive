package com.neothedeveloper.synapser.outbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.minecraft.datatypes.Gamemode;
import com.neothedeveloper.synapser.server.PlayerSocket;
import dev.dewy.nbt.Nbt;

import java.util.Random;

public class JoinGamePacket {
    public static void send(PlayerSocket socket) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x01);
        builder.AddVarIntField(Latte.server().getNewEID());
        builder.AddField((byte) Gamemode.SURIVIVAL.ordinal());
        builder.AddField((byte) -1);
        builder.AddStringField("minecraft:overworld");
        Nbt nbt = new Nbt();
        JsonArray dimensions = new JsonArray();
        JsonObject overworld = new JsonObject();
        JsonObject dimension_type = new JsonObject();

        // TODO: Implement World


        socket.Write(builder.Build());
    }
}

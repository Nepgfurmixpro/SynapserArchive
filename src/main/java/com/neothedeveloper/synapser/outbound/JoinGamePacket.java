package com.neothedeveloper.synapser.outbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.minecraft.datatypes.Gamemode;
import com.neothedeveloper.synapser.minecraft.dimension.dimensions.Overworld;
import com.neothedeveloper.synapser.minecraft.utils.SHA256;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.server.initializers.DimensionInitializer;
import com.neothedeveloper.synapser.utils.LongUtils;
import dev.dewy.nbt.Nbt;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class JoinGamePacket {
    private static long getSeedLong(long seed) {
        byte[] hash = SHA256.generate(ByteBuffer.allocate(Long.BYTES).putLong(seed).array());
        byte[] out = new byte[8];
        System.arraycopy(hash, 0, out, 0, Long.BYTES);
        return LongUtils.GetLongFromBytes(out);
    }
    public static void send(PlayerSocket socket) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x26);
        builder.AddVarIntField(Latte.server().getNewEID());
        builder.AddField((byte) Gamemode.SURIVIVAL.ordinal());
        builder.AddField((byte) -1);
        builder.AddStringField(Latte.DIMENSION_REGISTRY.getIdentifiers().toString());
        builder.AddStringField(Latte.DIMENSION_REGISTRY.finish().toString());
        builder.AddStringField(DimensionInitializer.OVERWORLD.toString());
        builder.AddVarIntField(Integer.parseInt(Synapser.SERVER_PROPERTIES.GetProperty("max-players")));
        builder.AddLongField(getSeedLong(24578764247522L));
        builder.AddVarIntField(12);
        builder.AddVarIntField(12);
        builder.AddBooleanField(false);
        builder.AddBooleanField(true);
        builder.AddBooleanField(true);
        builder.AddBooleanField(true);
        socket.Write(builder.Build());
        PluginMessagePacket.send(socket, "minecraft:brand", Latte.clientBrandRetriever().brandName().getBytes(StandardCharsets.UTF_8));
    }
}

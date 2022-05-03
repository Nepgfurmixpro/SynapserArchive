package com.neothedeveloper.synapser.builders;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.datatypes.VarIntLong;
import com.neothedeveloper.synapser.utils.LongUtils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("UnusedReturnValue")
public class OutboundPacketBuilder {
    private final List<Byte> m_data;
    private final byte m_packetID;

    public OutboundPacketBuilder(int packetID) {
        this.m_packetID = (byte)packetID;
        this.m_data = new ArrayList<>();
    }
    public OutboundPacketBuilder AddField(byte fieldData) {
        this.m_data.add(fieldData);
        return this;
    }
    public OutboundPacketBuilder AddField(byte[] fieldData) {
        for (byte b : fieldData) {
            this.m_data.add(b);
        }
        return this;
    }

    public OutboundPacketBuilder AddStringField(String fieldData) {
        byte[] bytes = fieldData.getBytes(StandardCharsets.UTF_8);
        return AddField(VarIntLong.CreateVarInt(bytes.length)).AddField(bytes);
    }
    public OutboundPacketBuilder AddLongField(long fieldData) {
        return AddField(LongUtils.GetBytesFromLong(fieldData));
    }
    public OutboundPacketBuilder AddVarIntField(int fieldData) {
        return AddField(VarIntLong.CreateVarInt(fieldData));
    }
    public OutboundPacketBuilder AddChatField(JsonObject fieldData) {
        return AddStringField(fieldData.toString());
    }
    public OutboundPacketBuilder AddUUIDField(UUID fieldData) {
        return AddField(LongUtils.GetBytesFromLong(fieldData.getMostSignificantBits())).AddField(LongUtils.GetBytesFromLong(fieldData.getLeastSignificantBits()));
    }
    public OutboundPacketBuilder AddIntField(int fieldData) {
        return AddField(ByteBuffer.allocate(4).putInt(fieldData).array());
    }
    public OutboundPacketBuilder AddBooleanField(boolean fieldData) {
        return AddField((byte) (fieldData ? 0x01 : 0x00));
    }
    public byte[] Build() {
        List<Byte> output = new ArrayList<>();
        for (byte b : VarIntLong.CreateVarInt(m_data.size() + 1)) {
            output.add(b);
        }
        for (byte b : VarIntLong.CreateVarInt(this.m_packetID)) {
            output.add(b);
        }
        output.addAll(this.m_data);
        byte[] bytes = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            bytes[i] = output.get(i);
        }
        return bytes;
    }
}

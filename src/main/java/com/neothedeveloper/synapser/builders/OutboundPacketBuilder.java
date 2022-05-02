package com.neothedeveloper.synapser.builders;

import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.datatypes.VarIntLong;
import com.neothedeveloper.synapser.utils.Logger;
import com.neothedeveloper.synapser.utils.LongUtils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OutboundPacketBuilder {
    private final List<Byte> m_data;
    private final byte m_packetID;

    public OutboundPacketBuilder(int packetID) {
        this.m_packetID = (byte)packetID;
        this.m_data = new ArrayList<>();
    }
    public OutboundPacketBuilder AddField(byte[] fieldData) {
        for (byte bit : fieldData) {
            this.m_data.add(bit);
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

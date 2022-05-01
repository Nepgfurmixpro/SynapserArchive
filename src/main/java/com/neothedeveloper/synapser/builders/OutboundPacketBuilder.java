package com.neothedeveloper.synapser.builders;

import com.neothedeveloper.synapser.datatypes.VarIntLong;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OutboundPacketBuilder {
    private final List<Byte> m_data;
    private final byte m_packetID;

    public OutboundPacketBuilder(byte packetID) {
        this.m_packetID = packetID;
        this.m_data = new ArrayList<>();
    }
    public OutboundPacketBuilder AddField(byte[] fieldData) {
        for (byte bit : fieldData) {
            this.m_data.add(bit);
        }
        return this;
    }

    public OutboundPacketBuilder AddField(String fieldData) {
        return AddField(VarIntLong.CreateVarInt(fieldData.length())).AddField(fieldData.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] Build() {
        List<Byte> output = new ArrayList<>();
        output.add((byte)(m_data.size() + 1));
        output.add(this.m_packetID);
        byte[] bytes = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            bytes[i] = output.get(i);
        }
        return bytes;
    }
}

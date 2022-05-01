package com.neothedeveloper.synapser.decoders;

import com.neothedeveloper.synapser.datatypes.VarIntLong;

public class InboundPacketDecoder {
    private final int m_length;
    private final byte m_packetID;
    private byte[] m_data;
    private final byte[] m_packetData;
    public InboundPacketDecoder(byte[] data) {
        this.m_length = VarIntLong.ReadVarInt(data);
        this.m_packetID = data[VarIntLong.CreateVarInt(this.m_length).length];
        this.m_packetData = data;
        this.m_data = new byte[data.length - 2];
        for (int i = 2; i < data.length; i++) {
            this.m_data[i - 2] = data[i];
        }
    }
    private void removeUsedBits(int amount) {
        if (m_data.length - amount < 0) throw new RuntimeException("Packet data removal amount too big");
        byte[] newBytes = new byte[m_data.length - amount];
        for (int i = amount; i < m_data.length; i++) {
            newBytes[i - amount] = this.m_data[i];
        }
        this.m_data = newBytes;
    }
    public byte PacketID() { return this.m_packetID; }
    public int PacketLength() { return this.m_length; }
    public byte[] RawPacketData() { return this.m_packetData; }
    public byte[] PacketData() {
        return this.m_data;
    }
    public int GetFieldVarInt() {
        int out = VarIntLong.ReadVarInt(this.m_data);
        removeUsedBits(VarIntLong.CreateVarInt(out).length);
        return out;
    }
    public String GetFieldString() {
        int length = GetFieldVarInt();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            out.append((char) this.m_data[i]);
        }
        removeUsedBits(length);
        return out.toString();
    }

    public int GetFieldUnsignedShort() {
        short out = (short)(this.m_data[0] << 8 | this.m_data[1] & 0xFF);
        removeUsedBits(2);
        return Short.toUnsignedInt(out);
    }
}

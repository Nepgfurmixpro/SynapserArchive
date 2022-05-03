package com.neothedeveloper.synapser.decoders;

import com.neothedeveloper.synapser.datatypes.VarIntLong;
import com.neothedeveloper.synapser.utils.LongUtils;

import java.util.UUID;

public class InboundPacketDecoder {
    private final int m_length;
    private final int m_packetID;
    private byte[] m_data;
    private final byte[] m_packetData;
    public InboundPacketDecoder(byte[] data) {
        this.m_packetData = data;
        this.m_data = data;
        this.m_length = GetFieldVarInt();
        this.m_packetID = GetFieldVarInt();
    }
    private void removeUsedBits(int amount) {
        if (m_data.length - amount < 0) throw new RuntimeException("Packet data removal amount too big");
        byte[] newBytes = new byte[m_data.length - amount];
        if (m_data.length - amount >= 0)
            System.arraycopy(this.m_data, amount, newBytes, 0, m_data.length - amount);
        this.m_data = newBytes;
    }
    public boolean HasFields() { return this.m_data.length > 0; }
    public int PacketID() { return this.m_packetID; }
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
    public long GetFieldLong() {
        byte[] longBytes = new byte[Long.BYTES];
        System.arraycopy(this.m_data, 0, longBytes, 0, Long.BYTES);
        removeUsedBits(Long.BYTES);
        return LongUtils.GetLongFromBytes(longBytes);
    }
    public int GetFieldUnsignedShort() {
        short out = (short)(this.m_data[0] << 8 | this.m_data[1] & 0xFF);
        removeUsedBits(2);
        return Short.toUnsignedInt(out);
    }
    public boolean GetFieldBoolean() {
        boolean out;
        out = this.m_data[0] != 0;
        removeUsedBits(1);
        return out;
    }
    public UUID GetFieldUUID() {
        long high = GetFieldLong();
        long low = GetFieldLong();
        return new UUID(high, low);
    }
    public byte[] GetFieldByteArray(int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(this.m_data, 0, bytes, 0, length);
        removeUsedBits(length);
        return bytes;
    }
}

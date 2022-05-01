package com.neothedeveloper.synapser.datatypes;

import java.util.ArrayList;
import java.util.List;

public class VarIntLong {
//    public static byte[] CreateSignedVarLong(long value) {
//        return CreateUnsignedVarLong((value << 1) ^ (value >> 63));
//    }
    public static byte[] CreateVarLong(long value) {
        List<Byte> bytes = new ArrayList<>();
        int i = 0;
        while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
            bytes.add((byte)(value & 0x7F));
            value >>>= 7;
            i++;
        }
        bytes.add((byte)(value & 0x7F));
        byte[] out = new byte[i + 1];
        for (; i >= 0; i--) {
            out[i] = bytes.get(i);
        }
        return out;
    }
//    public static byte[] CreateSignedVarInt(int value) {
//        return Create((value << 1) ^ (value >> 31));
//    }
    public static byte[] CreateVarInt(int value) {
        byte[] bytes = new byte[10];
        int i = 0;
        while ((value & 0xFFFFFF80) != 0L) {
            bytes[i++] = ((byte) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }
        bytes[i] = ((byte) (value & 0x7F));
        byte[] out = new byte[i + 1];
        for (; i >= 0; i--) {
            out[i] = bytes[i];
        }
        return out;
    }
//    public static int ReadSignedVarInt(byte b) {
//        return ReadSignedVarInt(new byte[]{b});
//    }
//    public static int ReadSignedVarInt(byte[] bytes) {
//        int raw = ReadUnsignedVarInt(bytes);
//        int temp = (((raw << 31) >> 31) ^ raw) >> 1;
//        return temp ^ (raw & (1 << 31));
//    }

    public static int ReadVarInt(byte[] bytes) {
        int value = 0;
        int i = 0;
        for (byte b : bytes) {
            value |= (b & 0x7F) << i;
            if ((b & 0x80) == 0) break;
            i += 7;
            if (i > 32) throw new RuntimeException("VarInt is too big");
        }
        return value;
    }
}

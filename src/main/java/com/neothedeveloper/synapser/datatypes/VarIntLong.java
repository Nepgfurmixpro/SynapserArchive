package com.neothedeveloper.synapser.datatypes;

import java.util.ArrayList;
import java.util.List;

public class VarIntLong {
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

package com.neothedeveloper.synapser.builders;

import com.neothedeveloper.synapser.datatypes.VarIntLong;

import java.util.ArrayList;
import java.util.List;

public class PluginMessageBuilder extends OutboundPacketBuilder {
    public PluginMessageBuilder() {
        super(0x00);
    }

    @Override
    public byte[] Build() {
        List<Byte> output = new ArrayList<>(this.getData());
        byte[] bytes = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            bytes[i] = output.get(i);
        }
        return bytes;
    }
}

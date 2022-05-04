package com.neothedeveloper.synapser.server.initializers;

import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.latte.registries.DimensionRegistry;
import com.neothedeveloper.synapser.minecraft.dimension.Dimension;
import com.neothedeveloper.synapser.minecraft.dimension.dimensions.Overworld;

public class DimensionInitializer {
    public static Dimension OVERWORLD = new Overworld();
    public static void initialize() {
        Latte.DIMENSION_REGISTRY.register(OVERWORLD.create());
    }
}

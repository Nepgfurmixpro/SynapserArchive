package com.neothedeveloper.synapser.minecraft.datatypes;

public class BlockPos {
    public int xPos = 0;
    public int yPos = 0;
    public int zPos = 0;
    public BlockPos(int x, int y, int z) {
        xPos = x;
        yPos = y;
        zPos = z;
    }
    public BlockPos() {}
    public BlockPos(int x, int z) {
        xPos = x;
        zPos = z;
    }
}

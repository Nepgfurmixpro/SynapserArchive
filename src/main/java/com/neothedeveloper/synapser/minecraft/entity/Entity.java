package com.neothedeveloper.synapser.minecraft.entity;

import com.neothedeveloper.synapser.minecraft.entity.datatypes.Pose;

public class Entity {
    public boolean onFire = false;
    public boolean isCrouching = false;
    public boolean isSprinting = false;
    public boolean isSwimming = false;
    public boolean isInvisible = false;
    public boolean isGlowing = false;
    public boolean flyingWithElytra = false;

    public int airTicks = 300;
    public String nickname = "";
    public boolean customNameVisible = false;
    public boolean isSilent = false;
    public boolean noGravity = false;
    public Pose pose;
    public int ticksFrozenInPowderedSnow = 0;
}

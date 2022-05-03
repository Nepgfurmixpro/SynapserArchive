package com.neothedeveloper.synapser.minecraft.entity;

import com.neothedeveloper.synapser.minecraft.entity.datatypes.Pose;

public class Entity {
    private boolean m_onFire = false;
    private boolean m_isCrouching = false;
    private boolean m_isSprinting = false;
    private boolean m_isSwimming = false;
    private boolean m_isInvisible = false;
    private boolean m_isGlowing = false;
    private boolean m_flyingWithElytra = false;

    private int m_airTicks = 300;
    private String m_nickname = "";
    private boolean m_customNameVisible = false;
    private boolean m_isSilent = false;
    private boolean m_noGravity = false;
    private Pose m_pose;
    private int m_ticksFrozenInPowderedSnow = 0;
}

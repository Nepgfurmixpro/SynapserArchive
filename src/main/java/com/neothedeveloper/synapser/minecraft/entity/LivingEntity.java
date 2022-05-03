package com.neothedeveloper.synapser.minecraft.entity;

import com.neothedeveloper.synapser.minecraft.datatypes.BlockPos;
import com.neothedeveloper.synapser.minecraft.datatypes.Potion;
import com.neothedeveloper.synapser.minecraft.entity.datatypes.Hand;

public class LivingEntity extends Entity {
    public boolean isHandActive = false;
    public Hand activeHand = Hand.MAIN_HAND;
    public boolean inRiptideSpinAttack = false;

    public float health = 1.0f;
    public Potion activePotionEffect = Potion.NONE;
    public boolean isPotionEffectAmbient = false;
    public int arrowsInEntity = 0;
    public int beeStingersInEntity = 0;
    public BlockPos posOfBedBlock = null;
}

package com.neothedeveloper.synapser.minecraft.entity;

import com.neothedeveloper.synapser.minecraft.entity.datatypes.Hand;
import com.neothedeveloper.synapser.minecraft.entity.datatypes.MainHand;
import dev.dewy.nbt.Nbt;

public class Player extends LivingEntity {
    public float additionalHearts = 0.0f;
    public int score = 0;

    public boolean capeEnabled = false;
    public boolean jacketEnabled = false;
    public boolean leftSleeveEnabled = false;
    public boolean rightSleeveEnabled = false;
    public boolean leftPantsEnabled = false;
    public boolean rightPantsEnabled = false;
    public boolean hatEnabled = false;

    public MainHand mainHand = MainHand.RIGHT;
    public Nbt leftShoulderNBT = new Nbt();
    public Nbt rightShoulderNBT = new Nbt();
}

package com.github.tony84727.unrealuniverse.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

public class Blocks {
    public static final BlockGrenadeCrate GRENADE_CRATE = new BlockGrenadeCrate(Block.Properties.create(
            new Material(
                    MaterialColor.GRASS,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    PushReaction.IGNORE)
    ));
}

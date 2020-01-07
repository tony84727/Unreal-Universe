package com.github.tony84727.unrealuniverse.item;

import com.github.tony84727.unrealuniverse.UnrealUniverse;
import com.github.tony84727.unrealuniverse.entity.EntityExplosiveSnowball;

public class ItemExplosiveSnowball extends ItemEntitySpawner {
    public ItemExplosiveSnowball(Properties builder) {
        super(builder, (world, thrower) -> {
            final EntityExplosiveSnowball entity = new EntityExplosiveSnowball(world, thrower);
            entity.setSplitCount(1);
            entity.shoot(thrower, thrower.rotationPitch, thrower.rotationYaw, 0, 1, 0);
            return entity;
        });
        setRegistryName(UnrealUniverse.MOD_ID + ":explosive_snowball");
    }
}

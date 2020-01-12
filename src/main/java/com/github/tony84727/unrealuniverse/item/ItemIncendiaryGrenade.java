package com.github.tony84727.unrealuniverse.item;

import com.github.tony84727.unrealuniverse.UnrealUniverse;
import com.github.tony84727.unrealuniverse.entity.EntityIncendiaryGrenade;

public class ItemIncendiaryGrenade extends ItemEntitySpawner {
    public ItemIncendiaryGrenade(Properties properties) {
        super(properties, ((world, thrower, itemStack) -> {
            final EntityIncendiaryGrenade entity = new EntityIncendiaryGrenade(world, thrower);
            entity.shoot(thrower, thrower.rotationPitch, thrower.rotationYaw, 0, 1, 0);
            return entity;
        }));
        setRegistryName(UnrealUniverse.MOD_ID + ":incendiary_grenade");
    }
}

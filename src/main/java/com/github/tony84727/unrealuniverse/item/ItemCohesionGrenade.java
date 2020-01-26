package com.github.tony84727.unrealuniverse.item;

import com.github.tony84727.unrealuniverse.entity.EntityCohesionGrenade;
import net.minecraft.item.Item;

import static com.github.tony84727.unrealuniverse.UnrealUniverse.MOD_ID;

public class ItemCohesionGrenade extends ItemEntitySpawner {
    public ItemCohesionGrenade(Item.Properties properties) {
        super(properties, ((world, thrower, itemStack) -> {
            EntityCohesionGrenade entity = new EntityCohesionGrenade(EntityCohesionGrenade.ENTITY_TYPE, thrower, world);
            entity.shoot(thrower, thrower.rotationPitch, thrower.rotationYaw, 0, 1, 0);
            return entity;
        }));
        setRegistryName(MOD_ID, "cohesion_grenade");
    }
}

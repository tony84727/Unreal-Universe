package com.github.tony84727.unrealuniverse.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@FunctionalInterface
public interface EntitySpawner {
    Entity spawn(World world, LivingEntity thrower, ItemStack itemStack);
}

package com.github.tony84727.unrealuniverse.item;

import com.github.tony84727.unrealuniverse.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.github.tony84727.unrealuniverse.UnrealUniverse.MOD_ID;

public class Items {
    public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(INCENDIARY_GRENADE);
        }
    };
    public static final ItemIncendiaryGrenade INCENDIARY_GRENADE = new ItemIncendiaryGrenade(new Item.Properties().group(GROUP));
    public static final ItemExplosiveSnowball EXPLOSIVE_SNOWBALL = new ItemExplosiveSnowball(new Item.Properties().group(GROUP));
    public static final ItemCohesionGrenade COHESION_GRENADE = new ItemCohesionGrenade(new Item.Properties().group(GROUP));
    public static final Item GRENADE_CRATE = new BlockItem(Blocks.GRENADE_CRATE, new Item.Properties().group(GROUP)).setRegistryName(MOD_ID, "grenade_crate");
}

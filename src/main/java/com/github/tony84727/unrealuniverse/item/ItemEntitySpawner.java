package com.github.tony84727.unrealuniverse.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemEntitySpawner extends Item {
    protected EntitySpawner spawner;

    public ItemEntitySpawner(Item.Properties properties, EntitySpawner spawner) {
        super(properties);
        setEntityThrower(spawner);
    }

    public EntitySpawner getEntityThrower() {
        return this.spawner;
    }

    public void setEntityThrower(EntitySpawner thrower) {
        this.spawner = thrower;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        final ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote()) {
            worldIn.addEntity(getEntityThrower().spawn(worldIn, playerIn, itemStack));
        }
        if (!playerIn.isCreative()) {
            itemStack.shrink(1);
        }
        return ActionResult.newResult(ActionResultType.SUCCESS, itemStack);
    }
}

package com.github.tony84727.unrealuniverse.item;

import com.github.tony84727.unrealuniverse.entity.EntityExplosiveSnowball;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemExplosiveSnowball extends Item {

    public ItemExplosiveSnowball(Properties builder) {
        super(builder);
        setRegistryName("explosivesnowball");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        final World world = playerIn.getEntityWorld();
        final EntityExplosiveSnowball entity = new EntityExplosiveSnowball(world, playerIn);
        final ItemStack itemStack = playerIn.getHeldItem(handIn);
        entity.setSplitCount(1);
        entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0, 1, 0);
        world.addEntity(entity);
        if (!playerIn.isCreative()) {
            itemStack.grow(-1);
        }
        return ActionResult.newResult(ActionResultType.SUCCESS, itemStack);
    }
}

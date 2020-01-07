package com.github.tony84727.unrealuniverse.entity;

import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityIncendiaryGrenade extends SnowballEntity {
    public EntityIncendiaryGrenade(World world, LivingEntity thrower) {
        super(world, thrower);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        final Vec3d hitVec = result.getHitVec();
        if (!world.isRemote) {
            world.playSound(null, hitVec.x, hitVec.y, hitVec.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4, 1);
            final BlockPos pos = new BlockPos(hitVec.x, hitVec.y, hitVec.z);
            for (int x = -5; x < 5; x++) {
                for (int y = -5; y < 5; y++) {
                    for (int i = 0; i < 3; i++) {
                        final BlockPos p = pos.add(x, i, y);
                        if (world.getBlockState(p).isAir(world, p)) {
                            world.setBlockState(p, ((FireBlock) Blocks.FIRE).getStateForPlacement(world, pos));
                            break;
                        }
                    }
                }
            }
        }
        super.onImpact(result);
    }
}

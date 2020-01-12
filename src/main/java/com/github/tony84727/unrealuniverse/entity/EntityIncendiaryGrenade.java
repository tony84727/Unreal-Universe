package com.github.tony84727.unrealuniverse.entity;

import com.github.tony84727.unrealuniverse.item.Items;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static com.github.tony84727.unrealuniverse.UnrealUniverse.MOD_ID;

public class EntityIncendiaryGrenade extends ProjectileItemEntity {
    public static final EntityType<EntityIncendiaryGrenade> ENTITY_TYPE = EntityType.Builder.create(
            EntityIncendiaryGrenade::create, EntityClassification.MISC
    ).size(0.25f, 0.25f)
            .setUpdateInterval(4)
            .disableSerialization()
            .disableSummoning()
            .setShouldReceiveVelocityUpdates(true)
            .setCustomClientFactory((spawn, world) -> new EntityIncendiaryGrenade(world, spawn.getPosX(), spawn.getPosY(), spawn.getPosZ()))
            .immuneToFire()
            .setTrackingRange(32)
            .build(MOD_ID + ":incendiary_grenade");

    public EntityIncendiaryGrenade(EntityType<? extends EntityIncendiaryGrenade> entityType, World world) {
        super(entityType, world);
    }

    public EntityIncendiaryGrenade(World world, double x, double y, double z) {
        super(ENTITY_TYPE, x, y, z, world);
    }

    public EntityIncendiaryGrenade(World world, LivingEntity thrower) {
        super(ENTITY_TYPE, thrower, world);
    }

    public static EntityIncendiaryGrenade create(EntityType<? extends EntityIncendiaryGrenade> entityType, World world) {
        return new EntityIncendiaryGrenade(entityType, world);
    }

    protected Item func_213885_i() {
        return Items.INCENDIARY_GRENADE;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
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
            this.remove();
        }
    }
}

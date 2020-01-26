package com.github.tony84727.unrealuniverse.entity;

import com.github.tony84727.unrealuniverse.concurrent.BasicTickScheduler;
import com.github.tony84727.unrealuniverse.item.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

import static com.github.tony84727.unrealuniverse.UnrealUniverse.MOD_ID;

@Mod.EventBusSubscriber
public class EntityCohesionGrenade extends ProjectileItemEntity {
    public static final double RADIUS = 15;
    public static final EntityType<EntityCohesionGrenade> ENTITY_TYPE = EntityType.Builder
            .create(EntityCohesionGrenade::create, EntityClassification.MISC)
            .size(.25f, .25f)
            .setUpdateInterval(4)
            .disableSerialization()
            .disableSummoning()
            .setShouldReceiveVelocityUpdates(true)
            .setTrackingRange(32)
            .immuneToFire()
            .build(MOD_ID + ":cohesion_grenade");

    public EntityCohesionGrenade(EntityType<? extends EntityCohesionGrenade> entityType, World world) {
        super(entityType, world);
    }

    public EntityCohesionGrenade(EntityType<? extends EntityCohesionGrenade> entityType, LivingEntity thrower, World world) {
        super(entityType, thrower, world);
    }

    public static EntityCohesionGrenade create(EntityType<? extends EntityCohesionGrenade> entityType, World world) {
        return new EntityCohesionGrenade(entityType, world);
    }

    private static AxisAlignedBB getAxisAlignedBBOfCohensionField(Vec3d epicenter) {
        return new AxisAlignedBB(epicenter.x - RADIUS, epicenter.y - RADIUS, epicenter.z - RADIUS, epicenter.x + RADIUS, epicenter.y + RADIUS, epicenter.z + RADIUS);
    }

    @Override
    protected Item func_213885_i() {
        return Items.COHESION_GRENADE;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote) {
            final Vec3d epicenter = result.getHitVec();
            List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, getAxisAlignedBBOfCohensionField(epicenter));
            for (Entity e : entities) {
                if (e instanceof EntityCohesionGrenade) {
                    continue;
                }
                e.setMotion(e.getPositionVec().subtract(epicenter).normalize().scale(-2));
            }
            BasicTickScheduler.INSTANCE.schedule(18, () -> {
                world.createExplosion(this, epicenter.x, epicenter.y, epicenter.z, 4, Explosion.Mode.DESTROY);
            });
            remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

package com.github.tony84727.unrealuniverse.entity;

import com.github.tony84727.unrealuniverse.physics.RandomScatterPattern;
import com.github.tony84727.unrealuniverse.physics.ScatterExplosion;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityExplosiveSnowball extends SnowballEntity {

    private int splitCount;

    public EntityExplosiveSnowball(World world, LivingEntity thrower) {
        super(world, thrower);
    }

    public void setSplitCount(int splitCount) {
        this.splitCount = splitCount;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        world.createExplosion(this, posX, posY, posZ, 4, Explosion.Mode.BREAK);
        if (!world.isRemote && splitCount > 0) {
            ScatterExplosion explosion = new ScatterExplosion(new RandomScatterPattern(), (position, direction) -> {
                final EntityExplosiveSnowball newEntity = new EntityExplosiveSnowball(world, getThrower());
                newEntity.setSplitCount(splitCount - 1);
                newEntity.setPosition(position.x, position.y, position.z);
                newEntity.setMotion(direction.x, direction.y, direction.z);
                world.addEntity(newEntity);
            }, 40);
            explosion.explode(getPositionVec(), getMotion());
        }
        super.onImpact(result);
    }
}

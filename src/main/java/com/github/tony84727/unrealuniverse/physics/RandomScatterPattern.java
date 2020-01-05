package com.github.tony84727.unrealuniverse.physics;

import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class RandomScatterPattern implements ScatterPattern {
    private Random random;

    public RandomScatterPattern() {
        this.random = new Random();
    }

    @Override
    public Vec3d getNormal(Vec3d position, Vec3d velocity) {
        return new Vec3d(random.nextDouble() - 0.5, random.nextDouble() - 0.5, random.nextDouble() - 0.5);
    }
}

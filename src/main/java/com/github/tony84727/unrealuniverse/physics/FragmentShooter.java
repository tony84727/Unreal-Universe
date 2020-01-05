package com.github.tony84727.unrealuniverse.physics;

import net.minecraft.util.math.Vec3d;

@FunctionalInterface
public interface FragmentShooter {
    void shootFragment(Vec3d position, Vec3d velocity);
}

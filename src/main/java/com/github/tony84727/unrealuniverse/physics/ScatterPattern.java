package com.github.tony84727.unrealuniverse.physics;

import net.minecraft.util.math.Vec3d;

public interface ScatterPattern {
    Vec3d getNormal(Vec3d position, Vec3d velocity);
}

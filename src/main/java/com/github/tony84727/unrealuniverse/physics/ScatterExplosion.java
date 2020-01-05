package com.github.tony84727.unrealuniverse.physics;

import net.minecraft.util.math.Vec3d;

public class ScatterExplosion {
    private FragmentShooter shooter;
    private ScatterPattern pattern;
    private int quantity;

    public ScatterExplosion(ScatterPattern pattern, FragmentShooter shooter, int quantity) {
        this.shooter = shooter;
        this.pattern = pattern;
        this.quantity = quantity;
    }

    public void explode(Vec3d position, Vec3d velocity) {
        for (int i = 0; i < quantity; i++) {
            shooter.shootFragment(position, this.pattern.getNormal(position, velocity));
        }
    }
}

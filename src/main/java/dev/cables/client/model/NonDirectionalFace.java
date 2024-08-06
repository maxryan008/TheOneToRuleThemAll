package dev.cables.client.model;

import net.minecraft.util.math.Direction;
import org.joml.Vector3d;

public class NonDirectionalFace {


    private final Direction direction;
    private final Vector3d[] points;

    public NonDirectionalFace(Direction direction, Vector3d[] points) {
        this.direction = direction;
        this.points = points;
    }

    public Vector3d[] getPoints() {
        return points;
    }

    public Direction getDirection() {
        return direction;
    }
}

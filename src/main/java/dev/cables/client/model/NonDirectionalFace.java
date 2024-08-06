package dev.cables.client.model;

import net.minecraft.util.math.Direction;

public class NonDirectionalFace {


    private final Direction direction;
    private final float[] points;

    public NonDirectionalFace(Direction direction, float[] floats) {
        this.direction = direction;
        this.points = floats;
    }

    public static NonDirectionalFace octagonalFace(Direction direction, float radius) {
        float[] points = new float[16];
        // Define points for an octagon based on the radius
        float half = radius / 2.0f;
        points[0] = 0.5f - half;
        points[1] = 0.5f + radius;
        points[2] = 0.5f + half;
        points[3] = 0.5f + radius;
        points[4] = 0.5f + radius;
        points[5] = 0.5f + half;
        points[6] = 0.5f + radius;
        points[7] = 0.5f - half;
        points[8] = 0.5f + half;
        points[9] = 0.5f - radius;
        points[10] = 0.5f - half;
        points[11] = 0.5f - radius;
        points[12] = 0.5f - radius;
        points[13] = 0.5f - half;
        points[14] = 0.5f - radius;
        points[15] = 0.5f + half;
        return new NonDirectionalFace(direction, points);
    }

    public float[] getPoints() {
        return points;
    }

    public Direction getDirection() {
        return direction;
    }
}

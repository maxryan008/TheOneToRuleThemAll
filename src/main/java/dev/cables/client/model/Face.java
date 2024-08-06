package dev.cables.client.model;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;

import static dev.cables.client.model.ModelDirection.getBooleanProperty;

public class Face {
    private final Direction direction;
    private final float left;
    private final float bottom;
    private final float right;
    private final float top;
    private final float depth;
    //offsets can be -1, 0 or 1 for - radius, nothing or + radius
    private final int leftOffset;
    private final int bottomOffset;
    private final int rightOffset;
    private final int topOffset;
    private final int depthOffset;

    public Face(Direction direction, float left, float bottom, float right, float top, float depth, int leftOffset, int bottomOffset, int rightOffset, int topOffset, int depthOffset) {
        this.direction = direction;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
        this.depth = depth;
        this.leftOffset = leftOffset;
        this.bottomOffset = bottomOffset;
        this.rightOffset = rightOffset;
        this.topOffset = topOffset;
        this.depthOffset = depthOffset;
    }

    public static Face squareFace(Direction direction)
    {
        return new Face(direction, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -1, -1, 1, 1, 1);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public float getLeft() {
        return this.left;
    }

    public float getBottom() {
        return this.bottom;
    }

    public float getRight() {
        return this.right;
    }

    public float getTop() {
        return this.top;
    }

    public float getDepth() {
        return this.depth;
    }

    public int getLeftOffset() {
        return this.leftOffset;
    }

    public int getBottomOffset() {
        return this.bottomOffset;
    }

    public int getRightOffset() {
        return this.rightOffset;
    }

    public int getTopOffset() {
        return this.topOffset;
    }

    public int getDepthOffset() {
        return this.depthOffset;
    }

    public BooleanProperty getProperty() {
        return getBooleanProperty(this.direction);
    }
}

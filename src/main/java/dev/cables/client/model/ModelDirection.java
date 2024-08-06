package dev.cables.client.model;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModelDirection {
    private final Direction direction;
    private final Face[] faces;
    private final NonDirectionalFace[] nonDirectionalFaces;

    public ModelDirection(Direction direction, Face[] faces) {
        this.direction = direction;
        this.faces = faces;
        this.nonDirectionalFaces = null;
    }

    public ModelDirection(Direction direction, NonDirectionalFace[] faces) {
        this.direction = direction;
        this.nonDirectionalFaces = faces;
        this.faces = null;
    }

    public Face[] getFaces() {
        return this.faces;
    }

    public NonDirectionalFace[] getNonDirectionalFaces() {
        return this.nonDirectionalFaces;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public BooleanProperty getProperty() {
        return getBooleanProperty(this.direction);
    }

    @NotNull
    static BooleanProperty getBooleanProperty(Direction direction) {
        return switch (direction) {
            case UP -> Properties.UP;
            case DOWN -> Properties.DOWN;
            case NORTH -> Properties.NORTH;
            case EAST -> Properties.EAST;
            case SOUTH -> Properties.SOUTH;
            case WEST -> Properties.WEST;
        };
    }
}

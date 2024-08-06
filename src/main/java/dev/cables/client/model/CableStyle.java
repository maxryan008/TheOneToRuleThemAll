package dev.cables.client.model;

import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class CableStyle {
    public static final Face[] BASIC_CUBE = new Face[]{
            Face.squareFace(Direction.UP),
            Face.squareFace(Direction.DOWN),
            Face.squareFace(Direction.NORTH),
            Face.squareFace(Direction.EAST),
            Face.squareFace(Direction.SOUTH),
            Face.squareFace(Direction.WEST)
    };

    public static final CableStyle RECTANGULAR = new CableStyle(new ModelDirection[]
            {
                    new ModelDirection(Direction.UP,new Face[]{
                            new Face(Direction.NORTH,0.5f, 0.5f, 0.5f, 1, 0.5f, -1, 1, 1, 0, 1),
                            new Face(Direction.SOUTH,0.5f, 0.5f, 0.5f, 1, 0.5f, -1, 1, 1, 0, 1),
                            new Face(Direction.EAST,0.5f, 0.5f, 0.5f, 1, 0.5f, -1, 1, 1, 0, 1),
                            new Face(Direction.WEST,0.5f, 0.5f, 0.5f, 1, 0.5f, -1, 1, 1, 0, 1)
                    }),
                    new ModelDirection(Direction.DOWN,new Face[]{
                            new Face(Direction.NORTH,0.5f, 0, 0.5f, 0.5f, 0.5f, -1, 0, 1, -1, 1),
                            new Face(Direction.SOUTH,0.5f, 0, 0.5f, 0.5f, 0.5f, -1, 0, 1, -1, 1),
                            new Face(Direction.EAST,0.5f, 0, 0.5f, 0.5f, 0.5f, -1, 0, 1, -1, 1),
                            new Face(Direction.WEST,0.5f, 0, 0.5f, 0.5f, 0.5f, -1, 0, 1, -1, 1)
                    }),
                    new ModelDirection(Direction.NORTH,new Face[]{
                            new Face(Direction.UP, 0.5f, 0, 0.5f, 0.5f, 0.5f, -1, 0, 1, -1, 1),
                            new Face(Direction.DOWN, 0.5f, 0, 0.5f, 0.5f, 0.5f, -1, 0, 1, -1, 1),
                            new Face(Direction.EAST, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1, -1, 1, 1),
                            new Face(Direction.WEST, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1, -1, 1, 1)
                    }),
                    new ModelDirection(Direction.EAST,new Face[]{
                            new Face(Direction.UP, 0.5f, 0.5f, 1, 0.5f, 0.5f, 1, -1, 0, 1, 1),
                            new Face(Direction.DOWN, 0.5f, 0.5f, 1, 0.5f, 0.5f, 1, -1, 0, 1, 1),
                            new Face(Direction.NORTH, 0.5f, 0.5f, 1, 0.5f, 0.5f, 1, -1, 0, 1, 1),
                            new Face(Direction.SOUTH, 0.5f, 0.5f, 1, 0.5f, 0.5f, 1, -1, 0, 1, 1)
                    }),
                    new ModelDirection(Direction.SOUTH,new Face[]{
                            new Face(Direction.UP, 0.5f, 0.5f, 0.5f, 1, 0.5f, -1, 1, 1, 0, 1),
                            new Face(Direction.DOWN, 0.5f, 0.5f, 0.5f, 1, 0.5f, -1, 1, 1, 0, 1),
                            new Face(Direction.EAST, 0.5f, 0.5f, 1, 0.5f, 0.5f, 1, -1, 0, 1, 1),
                            new Face(Direction.WEST, 0.5f, 0.5f, 1, 0.5f, 0.5f, 1, -1, 0, 1, 1)
                    }),
                    new ModelDirection(Direction.WEST,new Face[]{
                            new Face(Direction.UP, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1, -1, 1, 1),
                            new Face(Direction.DOWN, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1, -1, 1, 1),
                            new Face(Direction.NORTH, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1, -1, 1, 1),
                            new Face(Direction.SOUTH, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1, -1, 1, 1)
                    }),
            },
            BASIC_CUBE);


    private final ModelDirection[] modelDirections;
    private final Face[] modelCenter;

    public CableStyle(ModelDirection[] modelDirections, Face[] modelCenter)
    {
        this.modelDirections = modelDirections;
        this.modelCenter = modelCenter;
    }

    public ModelDirection[] getModelDirections()
    {
        return this.modelDirections;
    }

    public Face[] getModelCenter()
    {
        return this.modelCenter;
    }
}

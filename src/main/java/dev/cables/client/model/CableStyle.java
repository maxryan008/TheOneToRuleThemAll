package dev.cables.client.model;

import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import org.joml.Vector3d;

public class CableStyle {
    public static final Face[] BASIC_CUBE = new Face[]{
            Face.squareFace(Direction.UP),
            Face.squareFace(Direction.DOWN),
            Face.squareFace(Direction.NORTH),
            Face.squareFace(Direction.EAST),
            Face.squareFace(Direction.SOUTH),
            Face.squareFace(Direction.WEST)
    };

    public static final NonDirectionalFace[] OCTAGONAL_CENTER = new NonDirectionalFace[]{
            //top
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                {
                        new Vector3d(-0.41421, 1, 0.41421),
                        new Vector3d(0.41421, 1, 0.41421),
                        new Vector3d(0.41421, 1, -0.41421),
                        new Vector3d(-0.41421, 1, -0.41421),
                }),
            //top-north
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(0.41421, 0.41421, 1),
                            new Vector3d(0.41421, 1, 0.41421),
                            new Vector3d(-0.41421, 1, 0.41421),
                            new Vector3d(-0.41421, 0.41421, 1),
                    }),
            //top-east
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(1, 0.41421, -0.41421),
                            new Vector3d(0.41421, 1, -0.41421),
                            new Vector3d(0.41421, 1, 0.41421),
                            new Vector3d(1, 0.41421, 0.41421),
                    }),
            //top-south
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(-0.41421, 0.41421, -1),
                            new Vector3d(-0.41421, 1, -0.41421),
                            new Vector3d(0.41421, 1, -0.41421),
                            new Vector3d(0.41421, 0.41421, -1),
                    }),
            //top-west
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(-1, 0.41421, 0.41421),
                            new Vector3d(-0.41421, 1, 0.41421),
                            new Vector3d(-0.41421, 1, -0.41421),
                            new Vector3d(-1, 0.41421, -0.41421),
                    }),
            //top-north-east
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(0.41421, 0.41421, 1),
                            new Vector3d(1, 0.41421, 0.41421),
                            new Vector3d(0.41421, 1, 0.41421),
                    }),
            //top-south-east
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(1, 0.41421, -0.41421),
                            new Vector3d(0.41421, 0.41421, -1),
                            new Vector3d(0.41421, 1, -0.41421),
                    }),
            //top-south-west
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(-0.41421, 0.41421, -1),
                            new Vector3d(-1, 0.41421, -0.41421),
                            new Vector3d(-0.41421, 1, -0.41421),
                    }),
            //top-north-west
            new NonDirectionalFace(Direction.UP, new Vector3d[]
                    {
                            new Vector3d(-1, 0.41421, 0.41421),
                            new Vector3d(-0.41421, 0.41421, 1),
                            new Vector3d(-0.41421, 1, 0.41421),
                    }),
            //north
            new NonDirectionalFace(Direction.NORTH, new Vector3d[]
                    {
                            new Vector3d(-0.41421, 0.41421, -1),
                            new Vector3d(0.41421, 0.41421, -1), //1
                            new Vector3d(0.41421, -0.41421, -1), //4
                            new Vector3d(-0.41421, -0.41421, -1),
                    }),
            //north-east
            new NonDirectionalFace(Direction.NORTH, new Vector3d[]
                    {
                            new Vector3d(0.41421, 0.41421, -1),
                            new Vector3d(1, 0.41421, -0.41421),
                            new Vector3d(1, -0.41421, -0.41421),
                            new Vector3d(0.41421, -0.41421, -1)
                    }),
            //east
            new NonDirectionalFace(Direction.EAST, new Vector3d[]
                    {
                            new Vector3d(1, 0.41421, -0.41421), //2
                            new Vector3d(1, 0.41421, 0.41421),
                            new Vector3d(1, -0.41421, 0.41421),
                            new Vector3d(1, -0.41421, -0.41421), //3
                    }),
            //south-east
            new NonDirectionalFace(Direction.NORTH, new Vector3d[]
                    {
                            new Vector3d(1, 0.41421, 0.41421),
                            new Vector3d(0.41421, 0.41421, 1),
                            new Vector3d(0.41421, -0.41421, 1),
                            new Vector3d(1, -0.41421, 0.41421),
                    }),
            //south
            new NonDirectionalFace(Direction.SOUTH, new Vector3d[]
                    {
                            new Vector3d(0.41421, 0.41421, 1),
                            new Vector3d(-0.41421, 0.41421, 1),
                            new Vector3d(-0.41421, -0.41421, 1),
                            new Vector3d(0.41421, -0.41421, 1),
                    }),
            //south-west
            new NonDirectionalFace(Direction.NORTH, new Vector3d[]
                    {
                            new Vector3d(-0.41421, 0.41421, 1),
                            new Vector3d(-1, 0.41421, 0.41421),
                            new Vector3d(-1, -0.41421, 0.41421),
                            new Vector3d(-0.41421, -0.41421, 1),
                    }),
            //west
            new NonDirectionalFace(Direction.WEST, new Vector3d[]
                    {
                            new Vector3d(-1, 0.41421, 0.41421),
                            new Vector3d(-1, 0.41421, -0.41421),
                            new Vector3d(-1, -0.41421, -0.41421),
                            new Vector3d(-1, -0.41421, 0.41421),
                    }),
            //north-west
            new NonDirectionalFace(Direction.NORTH, new Vector3d[]
                    {
                            new Vector3d(-1, 0.41421, -0.41421),
                            new Vector3d(-0.41421, 0.41421, -1),
                            new Vector3d(-0.41421, -0.41421, -1),
                            new Vector3d(-1, -0.41421, -0.41421),
                    }),
            //bottom
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(-0.41421, -1, -0.41421),
                            new Vector3d(0.41421, -1, -0.41421),
                            new Vector3d(0.41421, -1, 0.41421),
                            new Vector3d(-0.41421, -1, 0.41421),
                    }),
            //bottom-north
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(-0.41421, -0.41421, 1),
                            new Vector3d(-0.41421, -1, 0.41421),
                            new Vector3d(0.41421, -1, 0.41421),
                            new Vector3d(0.41421, -0.41421, 1),
                    }),
            //bottom-east
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(1, -0.41421, 0.41421),
                            new Vector3d(0.41421, -1, 0.41421),
                            new Vector3d(0.41421, -1, -0.41421),
                            new Vector3d(1, -0.41421, -0.41421),
                    }),
            //bottom-south
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(0.41421, -0.41421, -1),
                            new Vector3d(0.41421, -1, -0.41421),
                            new Vector3d(-0.41421, -1, -0.41421),
                            new Vector3d(-0.41421, -0.41421, -1),
                    }),
            //bottom-west
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(-1, -0.41421, -0.41421),
                            new Vector3d(-0.41421, -1, -0.41421),
                            new Vector3d(-0.41421, -1, 0.41421),
                            new Vector3d(-1, -0.41421, 0.41421),
                    }),
            //bottom-north-east
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(0.41421, -1, 0.41421),
                            new Vector3d(1, -0.41421, 0.41421),
                            new Vector3d(0.41421, -0.41421, 1),
                    }),
            //bottom-south-east
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(0.41421, -1, -0.41421),
                            new Vector3d(0.41421, -0.41421, -1),
                            new Vector3d(1, -0.41421, -0.41421),
                    }),
            //bottom-south-west
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(-0.41421, -1, -0.41421),
                            new Vector3d(-1, -0.41421, -0.41421),
                            new Vector3d(-0.41421, -0.41421, -1),
                    }),
            //bottom-north-west
            new NonDirectionalFace(Direction.DOWN, new Vector3d[]
                    {
                            new Vector3d(-0.41421, -1, 0.41421),
                            new Vector3d(-0.41421, -0.41421, 1),
                            new Vector3d(-1, -0.41421, 0.41421),
                    }),
    };

    public static final CableStyle OCTAGONAL = new CableStyle(new ModelDirection[]
            {

            },
            OCTAGONAL_CENTER);

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
    private final NonDirectionalFace[] modelCenterFaces;

    public CableStyle(ModelDirection[] modelDirections, Face[] modelCenter)
    {
        this.modelDirections = modelDirections;
        this.modelCenter = modelCenter;
        this.modelCenterFaces = null;
    }

    public CableStyle(ModelDirection[] modelDirections, NonDirectionalFace[] modelCenter)
    {
        this.modelDirections = modelDirections;
        this.modelCenterFaces = modelCenter;
        this.modelCenter = null;
    }

    public ModelDirection[] getModelDirections()
    {
        return this.modelDirections;
    }

    public Face[] getModelCenter()
    {
        return this.modelCenter;
    }

    public NonDirectionalFace[] getModelCenterFaces() {
        return this.modelCenterFaces;
    }
}

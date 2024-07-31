package dev.cables.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

public class CableBlock extends ConnectingBlock {
    public static final MapCodec<CableBlock> CODEC = createCodec(settings -> new CableBlock(settings, new Identifier("minecraft", "block/bedrock"), false, "Bedrock Cable"));
    private final boolean TRANSPARENT;
    private final Identifier TEXTURE;
    private final String TRANSLATION_NAME;

    public CableBlock(Settings settings, Identifier texture, boolean transparent, String translationName) {
        super(2f/16f, settings);
        this.TRANSPARENT = transparent;
        this.TEXTURE = texture;
        this.TRANSLATION_NAME = translationName;
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(NORTH, Boolean.FALSE)
                        .with(EAST, Boolean.FALSE)
                        .with(SOUTH, Boolean.FALSE)
                        .with(WEST, Boolean.FALSE)
                        .with(UP, Boolean.FALSE)
                        .with(DOWN, Boolean.FALSE)
        );    }


    @Override
    public MapCodec<CableBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public Identifier getTexture() {
        return TEXTURE;
    }

    public Boolean getTransparent() {
        return TRANSPARENT;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) ? true : super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return TRANSPARENT;
    }

    public String getTranslationName() {
        return TRANSLATION_NAME;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        checkAndUpdateConnections(world, pos, state);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);

        checkAndUpdateConnections(world, pos, state);
    }

    private void checkAndUpdateConnections(World world, BlockPos pos, BlockState state) {
        boolean north = isConnected(world, pos.north());
        boolean south = isConnected(world, pos.south());
        boolean east = isConnected(world, pos.east());
        boolean west = isConnected(world, pos.west());
        boolean up = isConnected(world, pos.up());
        boolean down = isConnected(world, pos.down());

        BlockState newState = state
                .with(NORTH, north)
                .with(SOUTH, south)
                .with(EAST, east)
                .with(WEST, west)
                .with(UP, up)
                .with(DOWN, down);

        world.setBlockState(pos, newState, 3);
    }

    private boolean isConnected(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.getBlock() instanceof CableBlock;
    }
}

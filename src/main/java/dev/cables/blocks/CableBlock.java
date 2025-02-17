package dev.cables.blocks;

import com.mojang.serialization.MapCodec;
import dev.cables.client.model.CableStyle;
import dev.cables.items.ModItems;
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

import static dev.cables.Cables.MOD_ID;

public class CableBlock extends ConnectingBlock {
    public static final MapCodec<CableBlock> CODEC = createCodec(settings -> new CableBlock(settings, new Identifier("minecraft", "block/bedrock"), false, "Bedrock Cable", 2, CableStyle.RECTANGULAR));
    private final boolean TRANSPARENT;
    private final Identifier TEXTURE;
    private final String TRANSLATION_NAME;
    private final float RADIUS;
    private final CableStyle style;

    public CableBlock(Settings settings, Identifier texture, boolean transparent, String translationName, float radius, CableStyle style) {
        super(radius/16f, settings);
        this.style = style;
        this.RADIUS = radius/16f;
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
        );
    }


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

    public float getRadius() {
        return RADIUS;
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

    public boolean getTransparent()
    {
        return TRANSPARENT;
    }

    public String getTranslationName() {
        return TRANSLATION_NAME;
    }

    public CableStyle getStyle() {
        return style;
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

    public Identifier[] getIds() {
        return new Identifier[]{new Identifier(MOD_ID, "block/" + ModItems.toSnakeCase(this.getTranslationName())), new Identifier(MOD_ID, "item/" + ModItems.toSnakeCase(this.getTranslationName()))};
    }

    public Identifier getBlockId() {
        return new Identifier(MOD_ID, "block/" + ModItems.toSnakeCase(this.getTranslationName()));
    }

    public Identifier getItemId() {
        return new Identifier(MOD_ID, "item/" + ModItems.toSnakeCase(this.getTranslationName()));
    }
}

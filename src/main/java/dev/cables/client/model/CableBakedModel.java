package dev.cables.client.model;

import dev.cables.blocks.CableBlock;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class CableBakedModel implements BakedModel {

    private final Sprite textureSprite;
    private final CableBlock type;

    public CableBakedModel(Sprite textureSprite, CableBlock type) {
        this.textureSprite = textureSprite;
        this.type = type;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        QuadEmitter emitter = context.getEmitter();
        BlockState worldState = blockView.getBlockState(pos);
        CableBlock block = (CableBlock) worldState.getBlock();
        float radius = block.getRadius();
        //center part
        {
            // Top face (y = 0.5f + radius)
            doubleFace(Direction.UP, 0.5f - radius, 0.5f - radius, 0.5f + radius, 0.5f + radius, 0.5f + radius, emitter);

            // Bottom face (y = 0.5f - radius)
            doubleFace(Direction.DOWN, 0.5f - radius, 0.5f - radius, 0.5f + radius, 0.5f + radius, 0.5f + radius, emitter);

            // North face (z = 0.5f - radius)
            doubleFace(Direction.NORTH, 0.5f - radius, 0.5f - radius, 0.5f + radius, 0.5f + radius, 0.5f + radius, emitter);

            // South face (z = 0.5f + radius)
            doubleFace(Direction.SOUTH, 0.5f - radius, 0.5f - radius, 0.5f + radius, 0.5f + radius, 0.5f + radius, emitter);

            // West face (x = 0.5f - radius)
            doubleFace(Direction.WEST, 0.5f - radius, 0.5f - radius, 0.5f + radius, 0.5f + radius, 0.5f + radius, emitter);

            // East face (x = 0.5f + radius)
            doubleFace(Direction.EAST, 0.5f - radius, 0.5f - radius, 0.5f + radius, 0.5f + radius, 0.5f + radius, emitter);
        }

        if (state.get(Properties.UP))
        {

        }
        if (state.get(Properties.DOWN))
        {

        }
        if (state.get(Properties.NORTH))
        {

        }
        if (state.get(Properties.EAST))
        {

        }
        if (state.get(Properties.SOUTH))
        {

        }
        if (state.get(Properties.WEST))
        {

        }
    }

    private void emitFace(Direction direction, float left, float bottom, float right, float top, float depth, QuadEmitter emitter) {
        switch (direction) {
            case UP:
                emitter.pos(3, left, depth, bottom);
                emitter.pos(2, right, depth, bottom);
                emitter.pos(1, right, depth, top);
                emitter.pos(0, left, depth, top);
                break;
            case DOWN:
                emitter.pos(0, left, 1-depth, bottom);
                emitter.pos(1, right, 1-depth, bottom);
                emitter.pos(2, right, 1-depth, top);
                emitter.pos(3, left, 1-depth, top);
                break;
            case NORTH:
                emitter.pos(3, left, bottom, 1-depth);
                emitter.pos(2, right, bottom, 1-depth);
                emitter.pos(1, right, top, 1-depth);
                emitter.pos(0, left, top, 1-depth);
                break;
            case SOUTH:
                emitter.pos(0, left, bottom, depth);
                emitter.pos(1, right, bottom, depth);
                emitter.pos(2, right, top, depth);
                emitter.pos(3, left, top, depth);
                break;
            case WEST:
                emitter.pos(0, 1-depth, bottom, left);
                emitter.pos(1, 1-depth, bottom, right);
                emitter.pos(2, 1-depth, top, right);
                emitter.pos(3, 1-depth, top, left);
                break;
            case EAST:
                emitter.pos(3, depth, bottom, left);
                emitter.pos(2, depth, bottom, right);
                emitter.pos(1, depth, top, right);
                emitter.pos(0, depth, top, left);
                break;
        }

        emitter.cullFace(direction);
        emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.color(-1, -1, -1, -1);
        emitter.emit();
    }

    private void doubleFace(Direction direction, float left, float bottom, float right, float top, float depth, QuadEmitter emitter) {
        switch (direction) {
            case UP:
                emitter.pos(3, left, depth, bottom);
                emitter.pos(2, right, depth, bottom);
                emitter.pos(1, right, depth, top);
                emitter.pos(0, left, depth, top);
                break;
            case DOWN:
                emitter.pos(0, left, 1-depth, bottom);
                emitter.pos(1, right, 1-depth, bottom);
                emitter.pos(2, right, 1-depth, top);
                emitter.pos(3, left, 1-depth, top);
                break;
            case NORTH:
                emitter.pos(3, left, bottom, 1-depth);
                emitter.pos(2, right, bottom, 1-depth);
                emitter.pos(1, right, top, 1-depth);
                emitter.pos(0, left, top, 1-depth);
                break;
            case SOUTH:
                emitter.pos(0, left, bottom, depth);
                emitter.pos(1, right, bottom, depth);
                emitter.pos(2, right, top, depth);
                emitter.pos(3, left, top, depth);
                break;
            case WEST:
                emitter.pos(0, 1-depth, bottom, left);
                emitter.pos(1, 1-depth, bottom, right);
                emitter.pos(2, 1-depth, top, right);
                emitter.pos(3, 1-depth, top, left);
                break;
            case EAST:
                emitter.pos(3, depth, bottom, left);
                emitter.pos(2, depth, bottom, right);
                emitter.pos(1, depth, top, right);
                emitter.pos(0, depth, top, left);
                break;
        }

        emitter.cullFace(direction);
        emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.color(-1, -1, -1, -1);
        emitter.emit();

        if (this.type.getTransparent())
        {
            switch (direction) {
                case UP:
                    emitter.pos(3, left, 1-depth, bottom);
                    emitter.pos(2, right, 1-depth, bottom);
                    emitter.pos(1, right, 1-depth, top);
                    emitter.pos(0, left, 1-depth, top);
                    break;
                case DOWN:
                    emitter.pos(0, left, depth, bottom);
                    emitter.pos(1, right, depth, bottom);
                    emitter.pos(2, right, depth, top);
                    emitter.pos(3, left, depth, top);
                    break;
                case NORTH:
                    emitter.pos(3, left, bottom, depth);
                    emitter.pos(2, right, bottom, depth);
                    emitter.pos(1, right, top, depth);
                    emitter.pos(0, left, top, depth);
                    break;
                case SOUTH:
                    emitter.pos(0, left, bottom, 1-depth);
                    emitter.pos(1, right, bottom, 1-depth);
                    emitter.pos(2, right, top, 1-depth);
                    emitter.pos(3, left, top, 1-depth);
                    break;
                case WEST:
                    emitter.pos(0, depth, bottom, left);
                    emitter.pos(1, depth, bottom, right);
                    emitter.pos(2, depth, top, right);
                    emitter.pos(3, depth, top, left);
                    break;
                case EAST:
                    emitter.pos(3, 1-depth, bottom, left);
                    emitter.pos(2, 1-depth, bottom, right);
                    emitter.pos(1, 1-depth, top, right);
                    emitter.pos(0, 1-depth, top, left);
                    break;
            }

            emitter.cullFace(direction.getOpposite());
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();
        }
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        QuadEmitter emitter = context.getEmitter();
        float Radius = type.getRadius();
        {
            // Top face (y = 0.5f + Radius)
            emitter.pos(0, 0.5f - Radius, 0.5f + Radius, 0.5f - Radius);
            emitter.pos(1, 0.5f - Radius, 0.5f + Radius, 0.5f + Radius);
            emitter.pos(2, 0.5f + Radius, 0.5f + Radius, 0.5f + Radius);
            emitter.pos(3, 0.5f + Radius, 0.5f + Radius, 0.5f - Radius);
            emitter.cullFace(Direction.UP);
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();
            // Bottom face (y = 0.5f - Radius)
            emitter.pos(0, 0.5f - Radius, 0.5f - Radius, 0.5f - Radius);
            emitter.pos(1, 0.5f + Radius, 0.5f - Radius, 0.5f - Radius);
            emitter.pos(2, 0.5f + Radius, 0.5f - Radius, 0.5f + Radius);
            emitter.pos(3, 0.5f - Radius, 0.5f - Radius, 0.5f + Radius);
            emitter.cullFace(Direction.DOWN);
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();

            // North face (z = 0.5f - Radius)
            emitter.pos(0, 0.5f - Radius, 0.5f - Radius, 0.5f - Radius);
            emitter.pos(1, 0.5f - Radius, 0.5f + Radius, 0.5f - Radius);
            emitter.pos(2, 0.5f + Radius, 0.5f + Radius, 0.5f - Radius);
            emitter.pos(3, 0.5f + Radius, 0.5f - Radius, 0.5f - Radius);
            emitter.cullFace(Direction.NORTH);
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();

            // South face (z = 0.5f + Radius)
            emitter.pos(0, 0.5f - Radius, 0.5f - Radius, 0.5f + Radius);
            emitter.pos(1, 0.5f + Radius, 0.5f - Radius, 0.5f + Radius);
            emitter.pos(2, 0.5f + Radius, 0.5f + Radius, 0.5f + Radius);
            emitter.pos(3, 0.5f - Radius, 0.5f + Radius, 0.5f + Radius);
            emitter.cullFace(Direction.SOUTH);
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();

            // West face (x = 0.5f - Radius)
            emitter.pos(0, 0.5f - Radius, 0.5f - Radius, 0.5f - Radius);
            emitter.pos(1, 0.5f - Radius, 0.5f - Radius, 0.5f + Radius);
            emitter.pos(2, 0.5f - Radius, 0.5f + Radius, 0.5f + Radius);
            emitter.pos(3, 0.5f - Radius, 0.5f + Radius, 0.5f - Radius);
            emitter.cullFace(Direction.WEST);
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();

            // East face (x = 0.5f + Radius)
            emitter.pos(0, 0.5f + Radius, 0.5f - Radius, 0.5f - Radius);
            emitter.pos(1, 0.5f + Radius, 0.5f + Radius, 0.5f - Radius);
            emitter.pos(2, 0.5f + Radius, 0.5f + Radius, 0.5f + Radius);
            emitter.pos(3, 0.5f + Radius, 0.5f - Radius, 0.5f + Radius);
            emitter.cullFace(Direction.EAST);
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();
        }
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        return Collections.emptyList();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean hasDepth() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return textureSprite;
    }

    @Override
    public ModelTransformation getTransformation() {
        return ModelHelper.MODEL_TRANSFORM_BLOCK;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }
}

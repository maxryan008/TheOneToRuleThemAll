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
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter.CULL_FACE_EPSILON;
import static net.minecraft.state.property.Properties.*;

public class CableBakedModel implements BakedModel {

    private final Sprite textureSprite;
    private final CableBlock type;
    private final CableStyle style;

    public CableBakedModel(Sprite textureSprite, CableBlock type) {
        this.textureSprite = textureSprite;
        this.type = type;
        this.style = type.getStyle();
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
            for (Face face : this.style.getModelCenter()) {
                float leftOffset = radius * face.getLeftOffset();
                float bottomOffset = radius * face.getBottomOffset();
                float rightOffset = radius * face.getRightOffset();
                float topOffset = radius * face.getTopOffset();
                float depthOffset = radius * face.getDepthOffset();
                if (!state.get(face.getProperty()))
                {
                    if (!state.get(getOppositeBooleanProperty(face.getDirection())))
                    {
                        doubleFace(face.getDirection(), face.getLeft() + leftOffset, face.getBottom() + bottomOffset, face.getRight() + rightOffset, face.getTop() + topOffset, face.getDepth() + depthOffset, emitter);
                    }else
                    {
                        outsideFace(face.getDirection(), face.getLeft() + leftOffset, face.getBottom() + bottomOffset, face.getRight() + rightOffset, face.getTop() + topOffset, face.getDepth() + depthOffset, emitter);
                    }
                }else {
                    insideFace(face.getDirection(), face.getLeft() + leftOffset, face.getBottom() + bottomOffset, face.getRight() + rightOffset, face.getTop() + topOffset, face.getDepth() + depthOffset, emitter);
                }
            }
        }

        for (int i = 0; i < this.style.getModelDirections().length; i++) {
            if (state.get(this.style.getModelDirections()[i].getProperty()))
            {
                for (int j = 0; j < this.style.getModelDirections()[i].getFaces().length; j++) {
                    Face face = this.style.getModelDirections()[i].getFaces()[j];
                    float leftOffset = radius * face.getLeftOffset();
                    float bottomOffset = radius * face.getBottomOffset();
                    float rightOffset = radius * face.getRightOffset();
                    float topOffset = radius * face.getTopOffset();
                    float depthOffset = radius * face.getDepthOffset();
                    doubleFace(face.getDirection(), face.getLeft() + leftOffset, face.getBottom() + bottomOffset, face.getRight() + rightOffset, face.getTop() + topOffset, face.getDepth() + depthOffset, emitter);
                }
            }
        }
    }

    @NotNull
    static BooleanProperty getOppositeBooleanProperty(Direction direction) {
        return switch (direction) {
            case UP -> Properties.DOWN;
            case DOWN -> Properties.UP;
            case NORTH -> Properties.SOUTH;
            case EAST -> Properties.WEST;
            case SOUTH -> Properties.NORTH;
            case WEST -> Properties.EAST;
        };
    }

    private void outsideFace(Direction direction, float left, float bottom, float right, float top, float depth, QuadEmitter emitter) {
        Direction cullFace;
        if (Math.abs(1-depth) < CULL_FACE_EPSILON) {
            cullFace = direction;
            depth = 0; // avoid any inconsistency for face quads
        } else {
            cullFace = null;
        }

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

        emitter.cullFace(cullFace);
        emitter.nominalFace(direction);
        emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.color(-1, -1, -1, -1);
        emitter.emit();
    }

    private void insideFace(Direction direction, float left, float bottom, float right, float top, float depth, QuadEmitter emitter) {
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
            emitter.nominalFace(direction.getOpposite());
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();
        }
    }

    private void doubleFace(Direction direction, float left, float bottom, float right, float top, float depth, QuadEmitter emitter) {
        Direction cullFace;
        if (Math.abs(1-depth) < CULL_FACE_EPSILON) {
            cullFace = direction;
            depth = 0; // avoid any inconsistency for face quads
        } else {
            cullFace = null;
        }

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

        emitter.cullFace(cullFace);
        emitter.nominalFace(direction);
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

            if (cullFace != null) {
                emitter.cullFace(cullFace.getOpposite());
            }
            else
            {
                emitter.cullFace(null);
            }
            emitter.nominalFace(direction.getOpposite());
            emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();
        }
    }

    private void renderFace(Direction direction, Vector3f bottomLeft, Vector3f bottomRight, Vector3f topRight, Vector3f topLeft, QuadEmitter emitter)
    {
        emitter.pos(0, bottomLeft.x, bottomLeft.y, bottomLeft.z);
        emitter.pos(1, bottomRight.x, bottomRight.y, bottomRight.z);
        emitter.pos(2, topRight.x, topRight.y, topRight.z);
        emitter.pos(3, topLeft.x, topLeft.y, topLeft.z);
        emitter.nominalFace(direction);
        emitter.spriteBake(textureSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.color(-1, -1, -1, -1);
        emitter.emit();
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

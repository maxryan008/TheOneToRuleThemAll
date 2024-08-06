package dev.cables;

import dev.cables.blocks.CableBlock;
import dev.cables.blocks.ModBlocks;
import dev.cables.client.model.CableUnbakedModel;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.cables.blocks.ModBlocks.CABLE_BLOCKS;

public class ModelResolverImpl implements ModelResolver {
    private static final Set<Identifier> CABLE_MODEL_LOCATIONS = Set.of(
            Cables.id("block/glass_cable"),
            Cables.id("item/glass_cable")
    );



    @Override
    @Nullable
    public UnbakedModel resolveModel(Context context)
    {
        Identifier id = context.id();
        for (int i = 0; i < CABLE_BLOCKS.size(); i++) {
            if (Arrays.stream(CABLE_BLOCKS.get(i).getIds()).toList().contains(id)) {
                System.out.println("cable model resolved of id: " + id);
                return new CableUnbakedModel(CABLE_BLOCKS.get(i));
            }
        }

        return null;
    }
}

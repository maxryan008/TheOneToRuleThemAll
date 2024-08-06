package dev.cables.datagen;

import dev.cables.Cables;
import dev.cables.blocks.CableBlock;
import dev.cables.blocks.ModBlocks;
import dev.cables.items.ModItem;
import dev.cables.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;

import java.util.Optional;

import static dev.cables.Cables.MOD_ID;
import static dev.cables.blocks.ModBlocks.CABLE_BLOCKS;
import static dev.cables.datagen.CustomCableModel.*;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput generator) {
        super(generator);
    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        CABLE_BLOCKS.forEach(block -> {
            newCable(block, blockStateModelGenerator);
        });
    }

    private void newCable(CableBlock block, BlockStateModelGenerator blockStateModelGenerator) {
        TextureMap textureMap = new TextureMap().put(TextureKey.ALL, block.getTexture());
        Identifier blockIdentifier = Models.CUBE_ALL.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(block, blockIdentifier);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, blockIdentifier));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ModItems.ITEMS.forEach(item -> {
            registerItem(item, itemModelGenerator);
        });
    }

    public void registerItem(ModItem item, ItemModelGenerator itemModelGenerator)
    {
        Models.GENERATED.upload(ModelIds.getItemModelId(item), new TextureMap().put(TextureKey.LAYER0, item.getTexture()), itemModelGenerator.writer);
    }
}

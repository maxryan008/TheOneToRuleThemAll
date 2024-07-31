package dev.cables.datagen;

import dev.cables.blocks.CableBlock;
import dev.cables.blocks.ModBlocks;
import dev.cables.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;

import java.util.Optional;

import static dev.cables.blocks.ModBlocks.CABLE_BLOCKS;
import static dev.cables.datagen.CustomCableModel.*;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput generator) {
        super(generator);
    }

    public static final CustomCableModel CABLE_TEMPLATE_CENTER = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_center"), createCenterElements(4), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_UP = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_side_up"), createDirectionalElements(new Vec3i(6, 10, 6), new Vec3i(10, 16, 10)), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_DOWN = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_side_down"), createDirectionalElements(new Vec3i(6, 0, 6), new Vec3i(10, 6, 10)), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_NORTH = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_side_north"), createDirectionalElements(new Vec3i(6, 6, 0), new Vec3i(10, 10, 6)), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_EAST = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_side_east"), createDirectionalElements(new Vec3i(10, 6, 6), new Vec3i(16, 10, 10)), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_SOUTH = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_side_south"), createDirectionalElements(new Vec3i(6, 6, 10), new Vec3i(10, 10, 16)), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_WEST = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_side_west"), createDirectionalElements(new Vec3i(0, 6, 6), new Vec3i(6, 10, 10)), TextureKey.TEXTURE, TextureKey.PARTICLE);
    public static final CustomCableModel CABLE_TEMPLATE_INVENTORY = new CustomCableModel(Optional.of(new Identifier("cables", "block/cable_template")), Optional.of("_inventory"), createInventoryElements(), TextureKey.TEXTURE);


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        initalizeTemplates(blockStateModelGenerator);
        CABLE_BLOCKS.forEach(block -> {
            newCable(block, blockStateModelGenerator);
        });
        //newCable(ModBlocks.TINTED_GLASS_CABLE, blockStateModelGenerator);
        //newCable(ModBlocks.CUSTOM_CABLE, "test2", blockStateModelGenerator);
    }

    private void newCable(CableBlock block, BlockStateModelGenerator blockStateModelGenerator) {
        TextureMap textureMap = new TextureMap().put(TextureKey.TEXTURE, block.getTexture()).put(TextureKey.PARTICLE, block.getTexture());
        Model cableCenter = new Model(Optional.of(new Identifier("cables", "block/cable_template_center")), Optional.of("_center"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierCenter = cableCenter.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableUp = new Model(Optional.of(new Identifier("cables", "block/cable_template_side_up")), Optional.of("_side_up"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierUp = cableUp.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableDown = new Model(Optional.of(new Identifier("cables", "block/cable_template_side_down")), Optional.of("_side_down"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierDown = cableDown.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableNorth = new Model(Optional.of(new Identifier("cables", "block/cable_template_side_north")), Optional.of("_side_north"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierNorth = cableNorth.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableEast = new Model(Optional.of(new Identifier("cables", "block/cable_template_side_east")), Optional.of("_side_east"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierEast = cableEast.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableSouth = new Model(Optional.of(new Identifier("cables", "block/cable_template_side_south")), Optional.of("_side_south"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierSouth = cableSouth.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableWest = new Model(Optional.of(new Identifier("cables", "block/cable_template_side_west")), Optional.of("_side_west"), TextureKey.TEXTURE, TextureKey.PARTICLE);
        Identifier identifierWest = cableWest.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Model cableInventory = new Model(Optional.of(new Identifier("cables", "block/cable_template_inventory")), Optional.of("_inventory"), TextureKey.TEXTURE);

        blockStateModelGenerator.blockStateCollector
                .accept(createCustomBlockState(block, identifierCenter, identifierUp, identifierDown, identifierNorth, identifierEast, identifierSouth, identifierWest));
        Identifier identifierInventory = cableInventory.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(block, identifierInventory);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        registerItem(ModItems.DIAMOND, "test", itemModelGenerator);
    }

    public void registerItem(Item item, String texture, ItemModelGenerator itemModelGenerator)
    {
        Models.GENERATED.upload(ModelIds.getItemModelId(item), new TextureMap().put(TextureKey.LAYER0, new Identifier("cables","item/" + texture)), itemModelGenerator.writer);
    }

    private void initalizeTemplates(BlockStateModelGenerator blockStateModelGenerator) {
        TextureMap textureMap = new TextureMap().put(TextureKey.TEXTURE, new Identifier("cables", "block/texture")).put(TextureKey.PARTICLE, new Identifier("cables", "block/particle"));
        Identifier templateCenterModelId = new Identifier("cables:block/cable_template_center");
        Identifier templateUpModelId = new Identifier("cables:block/cable_template_side_up");
        Identifier templateDownModelId = new Identifier("cables:block/cable_template_side_down");
        Identifier templateNorthModelId = new Identifier("cables:block/cable_template_side_north");
        Identifier templateEastModelId = new Identifier("cables:block/cable_template_side_east");
        Identifier templateSouthModelId = new Identifier("cables:block/cable_template_side_south");
        Identifier templateWestModelId = new Identifier("cables:block/cable_template_side_west");
        Identifier templateInventoryModelId = new Identifier("cables:block/cable_template_inventory");

        CABLE_TEMPLATE_CENTER.upload(templateCenterModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_UP.upload(templateUpModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_DOWN.upload(templateDownModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_NORTH.upload(templateNorthModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_EAST.upload(templateEastModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_SOUTH.upload(templateSouthModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_WEST.upload(templateWestModelId, textureMap, blockStateModelGenerator.modelCollector);
        CABLE_TEMPLATE_INVENTORY.upload(templateInventoryModelId, textureMap, blockStateModelGenerator.modelCollector);
    }

//    public void newCable(Block customCableBlock, String texture, BlockStateModelGenerator blockStateModelGenerator) {
//        TextureMap textureMap = new TextureMap().put(TextureKey.TEXTURE, new Identifier("cables", "block/" + texture)).put(TextureKey.PARTICLE, new Identifier("cables", "block/" + texture));
//
//        // Define model IDs for all models
//        Identifier centerModelId = new Identifier("cables:block/custom_cable_center");
//        Identifier upModelId = new Identifier("cables:block/custom_cable_up");
//        Identifier downModelId = new Identifier("cables:block/custom_cable_down");
//        Identifier northModelId = new Identifier("cables:block/custom_cable_north");
//        Identifier eastModelId = new Identifier("cables:block/custom_cable_east");
//        Identifier southModelId = new Identifier("cables:block/custom_cable_south");
//        Identifier westModelId = new Identifier("cables:block/custom_cable_west");
//        Identifier inventoryModelId = new Identifier("cables:block/custom_cable_inventory");
//
//        // Upload detailed models
//        uploadModelIfNotExists(CUSTOM_CABLE_CENTER, centerModelId, textureMap, blockStateModelGenerator.modelCollector);
//        uploadModelIfNotExists(CUSTOM_CABLE_UP, upModelId, textureMap, blockStateModelGenerator.modelCollector);
//        uploadModelIfNotExists(CUSTOM_CABLE_DOWN, downModelId, textureMap, blockStateModelGenerator.modelCollector);
//        uploadModelIfNotExists(CUSTOM_CABLE_NORTH, northModelId, textureMap, blockStateModelGenerator.modelCollector);
//        uploadModelIfNotExists(CUSTOM_CABLE_EAST, eastModelId, textureMap, blockStateModelGenerator.modelCollector);
//        uploadModelIfNotExists(CUSTOM_CABLE_SOUTH, southModelId, textureMap, blockStateModelGenerator.modelCollector);
//        uploadModelIfNotExists(CUSTOM_CABLE_WEST, westModelId, textureMap, blockStateModelGenerator.modelCollector);
//
//        // Upload template-based models
//        uploadTemplateModel(customCableBlock, texture, "_center_template", blockStateModelGenerator);
//        uploadTemplateModel(customCableBlock, texture, "_side_up_template", blockStateModelGenerator);
//        uploadTemplateModel(customCableBlock, texture, "_side_down_template", blockStateModelGenerator);
//        uploadTemplateModel(customCableBlock, texture, "_side_north_template", blockStateModelGenerator);
//        uploadTemplateModel(customCableBlock, texture, "_side_east_template", blockStateModelGenerator);
//        uploadTemplateModel(customCableBlock, texture, "_side_south_template", blockStateModelGenerator);
//        uploadTemplateModel(customCableBlock, texture, "_side_west_template", blockStateModelGenerator);
//
//        Identifier identifierInventory = CUSTOM_CABLE_INVENTORY.upload(inventoryModelId, textureMap, blockStateModelGenerator.modelCollector);
//        blockStateModelGenerator.registerParentedItemModel(customCableBlock, identifierInventory);
//
//        blockStateModelGenerator.blockStateCollector.accept(createCustomCableBlockState(customCableBlock));
//    }

//    private void uploadModelIfNotExists(Model model, Identifier modelId, TextureMap textureMap, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
//        if (!uploadedModels.contains(modelId)) {
//            model.upload(modelId, textureMap, modelCollector);
//            uploadedModels.add(modelId);
//        }
//    }

//    private void uploadTemplateModel(Block block, String texture, String suffix, BlockStateModelGenerator blockStateModelGenerator) {
//        String path = "block/" + block.getTranslationKey().split("\\.")[1] + suffix;
//        Identifier modelId = new Identifier("cables", path);
//        if (!uploadedModels.contains(modelId)) {
//            Model templateModel = new CustomCableModel(Optional.of(new Identifier("cables", "block/" + block.getTranslationKey().split("\\.")[1])), Optional.of(suffix), TextureKey.TEXTURE);
//            templateModel.upload(block, new TextureMap().put(TextureKey.TEXTURE, new Identifier("cables", "block/" + texture)), blockStateModelGenerator.modelCollector);
//            uploadedModels.add(modelId);
//        }
//    }

    public static BlockStateSupplier createCustomBlockState(Block block, Identifier identifierCenter, Identifier identifierUp, Identifier identifierDown, Identifier identifierNorth, Identifier identifierEast, Identifier identifierSouth, Identifier identifierWest) {
        return MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifierCenter))
                .with(When.create().set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifierUp).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.DOWN, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifierDown).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifierNorth).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifierEast).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifierSouth).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifierWest).put(VariantSettings.UVLOCK, false));
    }
}

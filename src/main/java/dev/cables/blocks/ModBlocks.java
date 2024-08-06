package dev.cables.blocks;

import dev.cables.Cables;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static dev.cables.items.ModItems.CABLES_ITEM_GROUP_KEY;
import static dev.cables.items.ModItems.toSnakeCase;

public class ModBlocks {
    public static final AbstractBlock.Settings CABLE_SETTINGS = AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOL).nonOpaque().allowsSpawning((state, world, pos, entityType) -> false).solidBlock((state, world, pos) -> false).suffocates((state, world, pos) -> false).blockVision((state, world, pos) -> false);

    public static List<CableBlock> CABLE_BLOCKS = new ArrayList<>();

    public static void initialize() {
        CABLE_BLOCKS.add(newCable("Red Wool Cable", "minecraft", "red_wool", false));
        CABLE_BLOCKS.add(newCable("Light Blue Wool Cable", "minecraft", "light_blue_wool", false));
        CABLE_BLOCKS.add(newCable("Lime Wool Cable", "minecraft", "lime_wool", false));
        CABLE_BLOCKS.add(newCable("Yellow Wool Cable", "minecraft", "yellow_wool", false));
        CABLE_BLOCKS.add(newCable("Magenta Wool Cable", "minecraft", "magenta_wool", false));
        CABLE_BLOCKS.add(newCable("Orange Wool Cable", "minecraft", "orange_wool", false));
        CABLE_BLOCKS.add(newCable("Glowstone Cable", "minecraft", "glowstone", false, CABLE_SETTINGS.sounds(BlockSoundGroup.GLASS).luminance(state -> 15)));
        CABLE_BLOCKS.add(newCable("Glass Cable", "minecraft", "glass", true, CABLE_SETTINGS.sounds(BlockSoundGroup.GLASS)));
        CABLE_BLOCKS.add(newCable("Tinted Glass Cable", "minecraft", "tinted_glass", true, CABLE_SETTINGS.sounds(BlockSoundGroup.GLASS)));
        CABLE_BLOCKS.add(newCable("Large Tinted Glass Cable", "minecraft", "tinted_glass", true, 4, CABLE_SETTINGS.sounds(BlockSoundGroup.GLASS)));


        System.out.println("test" + ModBlocks.CABLE_BLOCKS.stream()
                .flatMap(block -> Stream.of(block.getIds()))
                .toList());
    }

    private static CableBlock newCable(String translationName, String modId, String blockId, boolean transparent) {
        return register(new CableBlock(CABLE_SETTINGS.sounds(BlockSoundGroup.GLASS), new Identifier(modId, "block/" + blockId), transparent, translationName, 2), toSnakeCase(translationName), CABLES_ITEM_GROUP_KEY);
    }

    private static CableBlock newCable(String translationName, String modId, String blockId, boolean transparent, AbstractBlock.Settings settings) {
        return register(new CableBlock(settings, new Identifier(modId, "block/" + blockId), transparent, translationName, 2), toSnakeCase(translationName), CABLES_ITEM_GROUP_KEY);
    }

    private static CableBlock newCable(String translationName, String modId, String blockId, boolean transparent, float radius) {
        return register(new CableBlock(CABLE_SETTINGS.sounds(BlockSoundGroup.GLASS), new Identifier(modId, "block/" + blockId), transparent, translationName, radius), toSnakeCase(translationName), CABLES_ITEM_GROUP_KEY);
    }

    private static CableBlock newCable(String translationName, String modId, String blockId, boolean transparent, float radius, AbstractBlock.Settings settings) {
        return register(new CableBlock(settings, new Identifier(modId, "block/" + blockId), transparent, translationName, radius), toSnakeCase(translationName), CABLES_ITEM_GROUP_KEY);
    }

    public static CableBlock register(Block block, String id, RegistryKey<ItemGroup> group)
    {
        Identifier blockId = new Identifier(Cables.MOD_ID, id);

        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, blockId, blockItem);

        ItemGroupEvents.modifyEntriesEvent(group)
                .register((itemGroup) -> itemGroup.add(blockItem));

        return (CableBlock) Registry.register(Registries.BLOCK, blockId, block);
    }

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = new Identifier(Cables.MOD_ID, name);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
}
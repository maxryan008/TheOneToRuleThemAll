package dev.cables.items;

import dev.cables.Cables;
import dev.cables.Recipe;
import dev.cables.blocks.CableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static dev.cables.Cables.MOD_ID;

public class ModItems {

    public static List<ModItem> ITEMS = new ArrayList<>();
    public static final RegistryKey<ItemGroup> CABLES_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(MOD_ID, "cables"));
    public static final ItemGroup CABLES_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.GLOWSTONE_DUST))
            .displayName(Text.translatable("itemGroup.cables"))
            .build();





    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CABLES_ITEM_GROUP_KEY, CABLES_ITEM_GROUP);
        ITEMS.add(newItem("Diamond", MOD_ID, "test"));
        ITEMS.add(newItem("Emerald thingy", "minecraft", "emerald"));
        ITEMS.add(newDamageItem("Crowbar", MOD_ID, "crowbar", 10f, 1f, new Recipe(" 0101010 ", Items.RED_WOOL, Items.IRON_INGOT)));
    }

    private static ModItem newItem(String translationName, String modId, String textureId, Recipe recipe) {
        return register(new ModItem(new FabricItemSettings(), new Identifier(modId, "item/" + textureId), translationName, recipe), toSnakeCase(translationName),CABLES_ITEM_GROUP_KEY);
    }

    private static ModItem newItem(String translationName, String modId, String textureId) {
        return register(new ModItem(new FabricItemSettings(), new Identifier(modId, "item/" + textureId), translationName), toSnakeCase(translationName),CABLES_ITEM_GROUP_KEY);
    }

    private static ModDamageItem newDamageItem(String translationName, String modId, String textureId, float attackDamage, float attackSpeed, Recipe recipe) {
        return register(new ModDamageItem(new FabricItemSettings(), new Identifier(modId, "item/" + textureId), translationName, attackDamage, attackSpeed, recipe), toSnakeCase(translationName),CABLES_ITEM_GROUP_KEY);
    }

    private static ModDamageItem newDamageItem(String translationName, String modId, String textureId, float attackDamage, float attackSpeed) {
        return register(new ModDamageItem(new FabricItemSettings(), new Identifier(modId, "item/" + textureId), translationName, attackDamage, attackSpeed), toSnakeCase(translationName),CABLES_ITEM_GROUP_KEY);
    }

    public static String toSnakeCase(String string) {
        if (string == null) {
            return null;
        }
        String lowerCaseString = string.toLowerCase();
        return lowerCaseString.replace(" ", "_");
    }

    public static ModItem register(ModItem item, String id, RegistryKey<ItemGroup> group)
    {
        Identifier itemId = new Identifier(MOD_ID, id);

        ModItem registeredItem = Registry.register(Registries.ITEM, itemId, item);

        ItemGroupEvents.modifyEntriesEvent(group)
                .register((itemGroup) -> itemGroup.add(registeredItem));

        return registeredItem;
    }

    public static ModDamageItem register(ModDamageItem item, String id, RegistryKey<ItemGroup> group)
    {
        Identifier itemId = new Identifier(MOD_ID, id);

        ModDamageItem registeredItem = Registry.register(Registries.ITEM, itemId, item);

        ItemGroupEvents.modifyEntriesEvent(group)
                .register((itemGroup) -> itemGroup.add(registeredItem));

        return registeredItem;
    }
}

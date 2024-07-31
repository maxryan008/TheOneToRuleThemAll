package dev.cables.items;

import dev.cables.Cables;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final RegistryKey<ItemGroup> CABLES_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(Cables.MOD_ID, "cables"));
    public static final ItemGroup CABLES_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.GLOWSTONE_DUST))
            .displayName(Text.translatable("itemGroup.cables"))
            .build();

    public static final Item DIAMOND = register(new Item(new FabricItemSettings()), "diamond", CABLES_ITEM_GROUP_KEY);





    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CABLES_ITEM_GROUP_KEY, CABLES_ITEM_GROUP);
    }

    public static Item register(Item item, String id, RegistryKey<ItemGroup> group)
    {
        Identifier itemId = new Identifier(Cables.MOD_ID, id);

        Item registeredItem = Registry.register(Registries.ITEM, itemId, item);

        ItemGroupEvents.modifyEntriesEvent(group)
                .register((itemGroup) -> itemGroup.add(registeredItem));

        return registeredItem;
    }
}

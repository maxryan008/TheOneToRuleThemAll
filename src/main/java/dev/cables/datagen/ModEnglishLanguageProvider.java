package dev.cables.datagen;

import dev.cables.blocks.ModBlocks;
import dev.cables.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static dev.cables.blocks.ModBlocks.CABLE_BLOCKS;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        ModItems.ITEMS.forEach(item -> translationBuilder.add(item, item.getTranslationName()));
        CABLE_BLOCKS.forEach(cableBlock -> {translationBuilder.add(cableBlock, cableBlock.getTranslationName());});
    }
}

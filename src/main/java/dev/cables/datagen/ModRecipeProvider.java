package dev.cables.datagen;

import dev.cables.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.Map;

import static dev.cables.Cables.MOD_ID;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ModItems.ITEMS.forEach(item -> {
            if (item.hasRecipe())
            {
                String[] pattern = item.getRecipe().getPattern();
                Map<Character, Item> patternMap = item.getRecipe().getPatternMap();
                ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, item, 1);
                for (int i = 0; i < pattern.length; i++) {
                    recipe.pattern(pattern[i]);
                }
                patternMap.forEach((character, recipeItem) -> {
                    recipe.input(character, recipeItem);
                    recipe.criterion(hasItem(recipeItem), conditionsFromItem(recipeItem));
                });
                recipe.offerTo(exporter, new Identifier(MOD_ID, getRecipeName(item)));
            }
        });
    }
}

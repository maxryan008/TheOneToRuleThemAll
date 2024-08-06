package dev.cables.items;

import dev.cables.Recipe;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModItem extends Item {
    private final String TRANSLATION;
    private final Identifier TEXTURE;
    private final Recipe RECIPE;
    public ModItem(Settings settings, Identifier texture, String translation) {
        super(settings);
        this.TEXTURE = texture;
        this.TRANSLATION = translation;
        this.RECIPE = null;
    }

    public ModItem(Settings settings, Identifier texture, String translation, Recipe recipe) {
        super(settings);
        this.TEXTURE = texture;
        this.TRANSLATION = translation;
        this.RECIPE = recipe;
    }

    public Identifier getTexture() {
        return this.TEXTURE;
    }

    public String getTranslationName() {
        return this.TRANSLATION;
    }

    public boolean hasRecipe() {
        return this.RECIPE != null;
    }

    public Recipe getRecipe() {
        return this.RECIPE;
    }
}

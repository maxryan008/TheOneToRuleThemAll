package dev.cables;

import dev.cables.blocks.ModBlocks;
import dev.cables.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cables implements ModInitializer {
	public static final String MOD_ID = "cables";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String s) {
		return new Identifier(MOD_ID, s);
	}

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
	}
}
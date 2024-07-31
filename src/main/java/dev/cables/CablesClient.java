package dev.cables;

import dev.cables.blocks.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static dev.cables.blocks.ModBlocks.CABLE_BLOCKS;

public class CablesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitializeRenderLayers();
    }

    private void InitializeRenderLayers() {
        CABLE_BLOCKS.forEach(cableBlock ->
        {
            if (cableBlock.getTransparent() == true)
            {
                BlockRenderLayerMap.INSTANCE.putBlock(cableBlock,RenderLayer.getTranslucent());
            }
        });
    }
}

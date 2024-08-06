package dev.cables;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;

import static dev.cables.blocks.ModBlocks.CABLE_BLOCKS;

public class CablesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitializeRenderLayers();
        InitializeModelLoading();
    }

    private void InitializeModelLoading() {
        ModelLoadingPlugin.register(pluginContext -> {
            ModelResolverImpl modelResolver = new ModelResolverImpl();
            pluginContext.resolveModel().register(modelResolver);
        });
    }

    private void InitializeRenderLayers() {
        CABLE_BLOCKS.forEach(cableBlock ->
        {
            if (cableBlock.getTransparent())
            {
                BlockRenderLayerMap.INSTANCE.putBlock(cableBlock,RenderLayer.getTranslucent());
            }
        });
    }

    public ModelResolver.Context newContext(Identifier id) {
        return new ModelResolver.Context() {
            @Override
            public Identifier id() {
                return id;
            }

            @Override
            public UnbakedModel getOrLoadModel(Identifier id) {
                return null;
            }

            @Override
            public ModelLoader loader() {
                return null;
            }
        };
    }

    //Todo: might need to copy gc5 code to register model type and listener
}

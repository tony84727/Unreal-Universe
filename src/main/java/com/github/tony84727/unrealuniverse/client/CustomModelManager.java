package com.github.tony84727.unrealuniverse.client;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CustomModelManager {
    private List<CustomModel> models;
    private Logger logger;

    public CustomModelManager() {
        this.models = new LinkedList<>();
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void add(CustomModel model) {
        models.add(model);
    }

    public Map<ModelResourceLocation, IBakedModel> bake(ModelBakery modelBakery, Function<ResourceLocation, TextureAtlasSprite> textureGetter) {
        Map<ModelResourceLocation, IBakedModel> bakedModels = new HashMap<>();
        models.forEach((m) -> {
            try {
                final IUnbakedModel unbaked = OBJLoader.INSTANCE.loadModel(m.getSource());
                final IBakedModel baked = unbaked.bake(modelBakery, textureGetter, new ISprite() {
                    @Override
                    public boolean isUvLock() {
                        return false;
                    }
                }, DefaultVertexFormats.ITEM);
                for (ModelResourceLocation location : m.getModelLocations()) {
                    bakedModels.put(location, baked);
                }
            } catch (Exception exception) {
                if (logger != null) {
                    logger.catching(exception);
                }
            }
        });
        return bakedModels;
    }
}

package com.github.tony84727.unrealuniverse.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class ClientProxy {
    public ClientProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onBake);
    }

    @SubscribeEvent
    public void onBake(ModelBakeEvent e) {
        try {
            final IUnbakedModel unbakedModel = OBJLoader.INSTANCE.loadModel(new ResourceLocation("unrealuniverse:models/item/incendiary_grenade.obj"));
            final IBakedModel baked = unbakedModel.bake(e.getModelLoader(), (location) -> Minecraft.getInstance().getTextureMap().getAtlasSprite(location.toString()), new ISprite() {
                @Override
                public boolean isUvLock() {
                    return false;
                }
            }, DefaultVertexFormats.ITEM);
            e.getModelRegistry().put(new ModelResourceLocation("unrealuniverse:incendiary_grenade", "inventory"), baked);
        } catch (ModelLoaderRegistry.LoaderException exception) {
            exception.getCause().printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

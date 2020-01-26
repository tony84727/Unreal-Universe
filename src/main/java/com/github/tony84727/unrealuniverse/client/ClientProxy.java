package com.github.tony84727.unrealuniverse.client;

import com.github.tony84727.unrealuniverse.block.Blocks;
import com.github.tony84727.unrealuniverse.entity.EntityCohesionGrenade;
import com.github.tony84727.unrealuniverse.entity.EntityIncendiaryGrenade;
import com.github.tony84727.unrealuniverse.item.Items;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

import static com.github.tony84727.unrealuniverse.UnrealUniverse.MOD_ID;


public class ClientProxy {
    private CustomModelManager customModelManager;

    public ClientProxy() {
        customModelManager = new CustomModelManager();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onBake);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onSetup);
        customModelManager.add(CustomModel.forItem(Items.INCENDIARY_GRENADE, new ResourceLocation(MOD_ID, "models/item/incendiary_grenade.obj")));
        customModelManager.add(CustomModel.forBlock(Blocks.GRENADE_CRATE, new ResourceLocation(MOD_ID, "models/block/grenade_crate.obj")));
        customModelManager.add(CustomModel.forItem(Items.COHESION_GRENADE, new ResourceLocation(MOD_ID, "models/item/cohesion_grenade.obj")));
    }

    public void onBake(final ModelBakeEvent e) {
        Map<ModelResourceLocation, IBakedModel> models = customModelManager.bake(e.getModelLoader(), ModelLoader.defaultTextureGetter());
        e.getModelRegistry().putAll(models);
    }

    public void onSetup(final FMLClientSetupEvent e) {
        final ItemRenderer renderer = e.getMinecraftSupplier().get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(EntityIncendiaryGrenade.class, (manager) -> new SpriteRenderer<>(manager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityCohesionGrenade.class, (manager) -> new SpriteRenderer<>(manager, renderer));
    }
}

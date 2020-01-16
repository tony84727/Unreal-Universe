package com.github.tony84727.unrealuniverse;

import com.github.tony84727.unrealuniverse.client.ClientProxy;
import com.github.tony84727.unrealuniverse.entity.EntityIncendiaryGrenade;
import com.github.tony84727.unrealuniverse.item.Items;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(UnrealUniverse.MOD_ID)
public class UnrealUniverse {
    public static final String MOD_ID = "unrealuniverse";
    public static final Items items = new Items();

    public UnrealUniverse() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientProxy::new);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().registerAll(Items.EXPLOSIVE_SNOWBALL, Items.INCENDIARY_GRENADE);
        }

        @SubscribeEvent
        public static void onModelRegistry(final ModelRegistryEvent e) {
            OBJLoader.INSTANCE.addDomain(UnrealUniverse.MOD_ID);
        }

        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
            EntityIncendiaryGrenade.ENTITY_TYPE.setRegistryName(MOD_ID, "incendiary_grenade");
            event.getRegistry().register(EntityIncendiaryGrenade.ENTITY_TYPE);
        }
    }
}

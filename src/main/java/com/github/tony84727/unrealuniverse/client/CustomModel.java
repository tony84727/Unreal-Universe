package com.github.tony84727.unrealuniverse.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class CustomModel {
    private ModelResourceLocation[] modelLocation;

    // model source file
    private ResourceLocation source;

    /**
     * @param source ResourceLocation of the obj model to load
     * @param block  the block that will use the model.
     */
    public CustomModel(ResourceLocation source, Block block) {
        this(source, new ModelResourceLocation[]{new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory")});
    }

    /**
     * @param source         ResourceLocation of the obj model to load
     * @param modelLocations ModelResourceLocation that will put into the model registry
     */
    public CustomModel(ResourceLocation source, ModelResourceLocation[] modelLocations) {
        this.source = source;
        this.modelLocation = modelLocations;
    }

    public static CustomModel forBlock(Block block, ResourceLocation toLoad) {
        return new CustomModel(toLoad, new ModelResourceLocation[]{new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), ""), new ModelResourceLocation(block.getRegistryName(), "inventory")});
    }

    public static CustomModel forItem(Item item, ResourceLocation toLoad) {
        return new CustomModel(toLoad, new ModelResourceLocation[]{new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory")});
    }

    public ModelResourceLocation[] getModelLocations() {
        return this.modelLocation;
    }

    public ResourceLocation getSource() {
        return this.source;
    }
}

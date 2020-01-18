package com.github.tony84727.unrealuniverse.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import static com.github.tony84727.unrealuniverse.UnrealUniverse.MOD_ID;

public class BlockGrenadeCrate extends Block {
    public BlockGrenadeCrate(Properties properties) {
        super(properties);
        setRegistryName(MOD_ID, "grenade_crate");
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.create(0, 0, 0, 1, 0.2, 1);
    }
}

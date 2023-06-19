package net.isaacj.tfw.mixin;

import net.isaacj.tfw.tag.CustomBiomeTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

// Mixin for WaterFluid
@Mixin(FluidBlock.class)
public abstract class FluidMixin extends Block {
    public FluidMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void modifyFluidTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        FluidState fluidState = state.getFluidState();

        // Check if the fluid state is a source block
        if (fluidState.isStill()) {
            Fluid fluid = fluidState.getFluid();

            // Check if the block is in your custom biome and above Y level -10
            if (world.getBiome(pos).isIn(CustomBiomeTags.IS_FROZEN) && pos.getY() > -10) {
                if (fluid.matchesType(Fluids.WATER)) {
                    // Freeze water into ice
                    // Schedule a block update to change the block to ice
                    world.createAndScheduleBlockTick(pos, Blocks.ICE, random.nextInt(1));

                } else if (fluid.matchesType(Fluids.LAVA)) {
                    // Solidify lava into obsidian
                    world.createAndScheduleBlockTick(pos, Blocks.OBSIDIAN, random.nextInt(2));
                }
            }
        }
    }
}
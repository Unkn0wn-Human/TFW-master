package net.isaacj.tfw.mixin;

import net.isaacj.tfw.tag.CustomBiomeTags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

public abstract class NoGrowthMixin {

    @Mixin(CropBlock.class)
    public static class BlockCropMixin {
        @Inject(method = "randomTick", at = @At(value = "HEAD"), cancellable = true)
        private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
            if (world.getBiome(pos).isIn(CustomBiomeTags.IS_FROZEN)) {
                ci.cancel();
            }
        }
    }

    @Mixin(SaplingBlock.class)
    public static class BlockSaplingMixin {
        @Inject(method = "randomTick", at = @At(value = "HEAD"), cancellable = true)
        private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
            if (world.getBiome(pos).isIn(CustomBiomeTags.IS_FROZEN)) {
                ci.cancel();
            }
        }
    }

    @Mixin(LeavesBlock.class)
    public abstract static class LeavesBlockDimensionMixin {

        @Shadow
        @Final
        public static IntProperty DISTANCE;

        @Inject(method = "updateDistanceFromLogs", at = @At("HEAD"), cancellable = true)
        private static void updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
            if (world.getBiome(pos).isIn(CustomBiomeTags.IS_FROZEN)) {
                cir.setReturnValue(state.with(DISTANCE, 7));
            }
        }
    }
}


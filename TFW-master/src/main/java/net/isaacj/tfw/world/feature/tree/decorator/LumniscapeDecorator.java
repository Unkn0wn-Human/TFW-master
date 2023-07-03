package net.isaacj.tfw.world.feature.tree.decorator;

import com.mojang.serialization.Codec;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class LumniscapeDecorator extends TreeDecorator {
    public static final LumniscapeDecorator INSTANCE = new LumniscapeDecorator();
    // Our constructor doesn't have any arguments, so we create a unit codec that returns the singleton instance
    public static final Codec<LumniscapeDecorator> CODEC = Codec.unit(() -> INSTANCE);

    private LumniscapeDecorator() {}

    @Override
    protected TreeDecoratorType<?> getType() {
        return TFWmod.LUMNISCAPE_TREE_DECORATOR;
    }

    protected static void placeGlimmerlace(BiConsumer<BlockPos, BlockState> replacer, BlockPos pos, BooleanProperty facing) {
        replacer.accept(pos, (BlockState)ModBlocks.GLIMMERLACE.getDefaultState().with(facing, true));
    }
    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        if (random.nextFloat() <= 0.65) { // 65% chance to generate the decorator at all.
            int treeHeight = logPositions.size();
            int lowerThirdHeight = treeHeight / 3; // Calculate the lower third of the tree.

            for (int i = 0; i < lowerThirdHeight; i++) { // Loop only through the lower third of the tree.
                BlockPos pos = logPositions.get(i);

                if (random.nextFloat() <= 0.20) {
                    BlockPos blockPos;
                    blockPos = checkAndDecorate(pos.west(), world, replacer, VineBlock.EAST);
                    blockPos = checkAndDecorate(pos.east(), world, replacer, VineBlock.WEST);
                    blockPos = checkAndDecorate(pos.north(), world, replacer, VineBlock.SOUTH);
                    blockPos = checkAndDecorate(pos.south(), world, replacer, VineBlock.NORTH);
                }
            }
        }
    }

    private BlockPos checkAndDecorate(BlockPos pos, TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, BooleanProperty facing) {
        if (Feature.isAir(world, pos)) {
            placeGlimmerlace(replacer, pos, facing);
        }
        return pos;
    }
}

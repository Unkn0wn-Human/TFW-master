package net.isaacj.tfw.world.feature.features;

import com.mojang.serialization.Codec;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class FrozenWarmOceanLargeSpreadFeature extends Feature<DefaultFeatureConfig> {
    private static final Block[] OCEAN_BLOCKS = new Block[]{Blocks.WATER, ModBlocks.VENTIAN, ModBlocks.BLUELEAF_THETTLE,
            ModBlocks.SMALL_BLUELEAF_THETTLE, ModBlocks.FROST_BLOOM_PLANT};
    private static final int[] OCEAN_BLOCK_CHANCES = new int[]{100, 86, 3, 8, 1};
    private static final int TOTAL_CHANCE = calculateTotalChance();

    public FrozenWarmOceanLargeSpreadFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();

        int patchCount = context.getRandom().nextInt(6) == 0 ? 1 : 0; // Adjust the chance of generating a patch

        for (int i = 0; i < patchCount; i++) {
            int x = pos.getX() + context.getRandom().nextInt(16);
            int z = pos.getZ() + context.getRandom().nextInt(16);

            int patchWidth = 3 + context.getRandom().nextInt(8); // Adjust the width range of the patches
            int patchLength = 3 * patchWidth; // The length of the patch will be twice the width

            // Place 1-3 magma blocks randomly in the patch
            for (int j = 0; j < context.getRandom().nextInt(3) + 1; j++) {
                int magmaX = x + context.getRandom().nextInt(patchWidth * 2) - patchWidth;
                int magmaZ = z + context.getRandom().nextInt(patchWidth * 2) - patchWidth;
                BlockPos magmaPos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, new BlockPos(magmaX, 0, magmaZ));
                if (!world.isWater(magmaPos.down())) {
                    world.setBlockState(magmaPos.down(), Blocks.MAGMA_BLOCK.getDefaultState(), 2);
                }
            }

            for (int xOffset = -patchWidth; xOffset <= patchWidth; xOffset++) {
                for (int zOffset = -patchLength / 2; zOffset <= patchLength / 2; zOffset++) {
                    double noiseValue = context.getRandom().nextDouble();
                    if (noiseValue > Math.abs((double) xOffset) / patchWidth || noiseValue > Math.abs((double) zOffset) / (patchLength / 2.0)) {
                        BlockPos blockPos = new BlockPos(x + xOffset, 0, z + zOffset);

                        // Find the top Y value for the current column
                        int y = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, blockPos.getX(), blockPos.getZ());

                        // Pick a block based on its chance
                        int blockIndex = pickBlockIndex(context.getRandom());
                        final Block block = OCEAN_BLOCKS[blockIndex];

                        if (!world.isWater(blockPos.down()) && !world.getBlockState(blockPos.down()).isOf(Blocks.MAGMA_BLOCK)) {
                            BlockPos blockToPlacePos = new BlockPos(x + xOffset, y, z + zOffset);
                            world.setBlockState(blockToPlacePos, block.getDefaultState(), 2);

                            if (block == ModBlocks.FROST_BLOOM_PLANT) {
                                // Set the initial position of the Frost Bloom plant
                                world.setBlockState(blockToPlacePos, ModBlocks.FROST_BLOOM_PLANT.getDefaultState(), 2);

                                // Determine the random height for the Frost Bloom plant between 2 and 5
                                final int frostBloomHeight = 2 + context.getRandom().nextInt(4);

                                // Loop through and place Frost Bloom plant blocks according to the random height
                                for (int yOff = 1; yOff <= frostBloomHeight; yOff++) {
                                    BlockPos abovePos = blockToPlacePos.up(yOff);
                                    if (!world.getBlockState(abovePos).isOf(Blocks.ICE)) {
                                        world.setBlockState(abovePos, ModBlocks.FROST_BLOOM_PLANT.getDefaultState(), 2);
                                    } else {
                                        break; // Stop the loop if there is no water above
                                    }
                                }
                                // Place an ice block on top of the last Frost Bloom plant
                                BlockPos icePos = blockToPlacePos.up(frostBloomHeight + 1);
                                world.setBlockState(icePos, ModBlocks.FROST_BLOOM.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    // Helper method to pick a block based on its chance
    private int pickBlockIndex(Random random) {
        int randomNumber = random.nextInt(TOTAL_CHANCE);
        int cumulativeChance = 0;
        for (int i = 0; i < OCEAN_BLOCKS.length; i++) {
            cumulativeChance += OCEAN_BLOCK_CHANCES[i];
            if (randomNumber < cumulativeChance) {
                return i;
            }
        }

        return 0;
    }

    // Helper method to calculate the total chance
    private static int calculateTotalChance() {
        int totalChance = 0;
        for (int chance : OCEAN_BLOCK_CHANCES) {
            totalChance += chance;
        }
        return totalChance;
    }
}
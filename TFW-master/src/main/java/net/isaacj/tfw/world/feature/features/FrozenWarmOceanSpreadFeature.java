package net.isaacj.tfw.world.feature.features;

import com.mojang.serialization.Codec;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class FrozenWarmOceanSpreadFeature extends Feature<DefaultFeatureConfig> {
    private static final Block[] OCEAN_BLOCKS = new Block[]{Blocks.WATER, ModBlocks.VENTIAN, ModBlocks.BLUELEAF_THETTLE, ModBlocks.SMALL_BLUELEAF_THETTLE};
    private static final int[] OCEAN_BLOCK_CHANCES = new int[]{100, 88, 2, 6};

    public FrozenWarmOceanSpreadFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();


        int patchCount = context.getRandom().nextInt(3) == 0 ? 1 : 0; // Adjust the chance of generating a patch

        for (int i = 0; i < patchCount; i++) {
            int x = pos.getX() + context.getRandom().nextInt(16);
            int z = pos.getZ() + context.getRandom().nextInt(16);

            int patchWidth = 1 + context.getRandom().nextInt(3); // Adjust the width range of the patches
            int patchLength = 3 * patchWidth; // The length of the patch will be twice the width


            for (int xOffset = -patchWidth; xOffset <= patchWidth; xOffset++) {
                for (int zOffset = -patchLength / 2; zOffset <= patchLength / 2; zOffset++) {
                    double noiseValue = context.getRandom().nextDouble();
                    if (noiseValue > Math.abs((double) xOffset) / patchWidth || noiseValue > Math.abs((double) zOffset) / (patchLength / 2.0)) {
                        BlockPos blockPos = new BlockPos(x + xOffset, 0, z + zOffset);

                        // Find the top Y value for the current column
                        int y = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, blockPos.getX(), blockPos.getZ());

                        // Pick a block based on its chance
                        int blockIndex = pickBlockIndex(context.getRandom());
                        Block block = OCEAN_BLOCKS[blockIndex];


                        if (!world.isWater(blockPos.down()) && !world.getBlockState(blockPos.down()).isOf(Blocks.MAGMA_BLOCK)) {
                            world.setBlockState(new BlockPos(x + xOffset, y, z + zOffset), block.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }

    // Helper method to pick a block based on its chance
    private int pickBlockIndex(Random random) {
        int totalChance = 0;
        for (int chance : OCEAN_BLOCK_CHANCES) {
            totalChance += chance;
        }

        int randomNumber = random.nextInt(totalChance);
        int cumulativeChance = 0;
        for (int i = 0; i < OCEAN_BLOCKS.length; i++) {
            cumulativeChance += OCEAN_BLOCK_CHANCES[i];
            if (randomNumber < cumulativeChance) {
                return i;
            }
        }

        return 0;
    }
}
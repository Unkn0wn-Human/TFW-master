package net.isaacj.tfw.world.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.random.AbstractRandom;

import java.util.Random;

public class IceMoundFeature extends Feature<DefaultFeatureConfig> {
    private static final Block ICE_BLOCK = Blocks.ICE;
    public IceMoundFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }


    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        // Get necessary objects from context
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        BlockPos pos = context.getOrigin();

        // Find the top Y position at the given x, z coordinates
        int topY = world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ());

        // Adjust pos to be at the top of the world
        pos = new BlockPos(pos.getX(), topY, pos.getZ());

        // Randomize the size of the pillar (2x2, 3x3, or 4x4)
        int size = 2 + random.nextInt(3);

        // Randomize the height of the pillar (between 4 and 13)
        int height = 4 + random.nextInt(10);

        // Generate the pillar
        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                for (int y = 0; y < height; y++) {
                    // Don't place a block if the random number is 0 (to create the natural look)
                    if (random.nextInt(10) != 0) {
                        world.setBlockState(pos.add(x, y, z), Blocks.ICE.getDefaultState(), 2);
                    }
                }
            }
        }

        // Arc the top of the pillar
        if (size > 2 && random.nextInt(3) > 0) { // don't arc for 2x2 pillars, 33% chance for others
            for (int x = -1; x <= size; x++) {
                for (int z = -1; z <= size; z++) {
                    // Don't place a block if the random number is 0 or 1 (to create the natural look)
                    if (random.nextInt(10) > 1) {
                        world.setBlockState(pos.add(x, height, z), Blocks.ICE.getDefaultState(), 2);
                    }
                }
            }
        }

        return true;
    }
}

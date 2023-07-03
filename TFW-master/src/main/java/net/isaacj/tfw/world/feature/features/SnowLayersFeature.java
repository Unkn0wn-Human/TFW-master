package net.isaacj.tfw.world.feature.features;

import com.mojang.serialization.Codec;
import net.isaacj.tfw.block.ModBlocks;
import net.isaacj.tfw.tag.CustomBiomeTags;
import net.isaacj.tfw.tag.CustomBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Arrays;
import java.util.List;

public class SnowLayersFeature extends Feature<DefaultFeatureConfig> {


    public SnowLayersFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }


    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable mutable2 = new BlockPos.Mutable();

        final List<Block> BLOCKS_ALLOWED = Arrays.asList(
                Blocks.STONE, Blocks.DIRT, Blocks.WATER, Blocks.COARSE_DIRT, Blocks.ICE, Blocks.GRANITE, Blocks.SAND,
                Blocks.DIORITE, Blocks.ANDESITE, Blocks.TUFF);

        final List<Block> BLOCKS_ALLOWED2 = Arrays.asList(
                Blocks.STONE, Blocks.DIRT, Blocks.WATER, Blocks.ICE, Blocks.GRANITE, Blocks.SAND, Blocks.DIORITE,
                Blocks.ANDESITE, Blocks.TUFF);

        final List<Block> COMPACT_SNOW_SAPLING = Arrays.asList(
                ModBlocks.COMPACT_SNOW_TADACIA, ModBlocks.COMPACT_SNOW_BURNT_TADACIA, ModBlocks.COMPACT_SNOW_SYPH,
                ModBlocks.COMPACT_SNOW_TZIER);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int k = blockPos.getX() + x;
                int l = blockPos.getZ() + z;
                int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, k, l);
                mutable.set(k, m, l);
                mutable2.set(k, m, l).move(Direction.UP, 1);



                if (structureWorldAccess.getBiomeAccess().getBiome(mutable).isIn(CustomBiomeTags.FULL_SNOW_LAYERS)) {

                    if (BLOCKS_ALLOWED.contains(structureWorldAccess.getBlockState(mutable.down()).getBlock())) {
                        structureWorldAccess.setBlockState(mutable, ModBlocks.COMPACT_SNOW.getDefaultState(), 2);
                        structureWorldAccess.setBlockState(mutable2, ModBlocks.COMPACT_SNOW.getDefaultState(), 2);
                    }
                    if(COMPACT_SNOW_SAPLING.contains(structureWorldAccess.getBlockState(mutable.down()).getBlock())){
                        structureWorldAccess.setBlockState(mutable2, ModBlocks.COMPACT_SNOW.getDefaultState(), 2);
                    }

                }
                if (structureWorldAccess.getBiomeAccess().getBiome(mutable).isIn(CustomBiomeTags.ONE_SNOW_LAYER)) {
                    if (BLOCKS_ALLOWED2.contains(structureWorldAccess.getBlockState(mutable.down()).getBlock())) {
                        structureWorldAccess.setBlockState(mutable, ModBlocks.COMPACT_SNOW.getDefaultState(), 2);
                    }
                }
                if (structureWorldAccess.getBiomeAccess().getBiome(mutable).isIn(CustomBiomeTags.Glacial)) {
                    if (BLOCKS_ALLOWED.contains(structureWorldAccess.getBlockState(mutable.down()).getBlock())) {
                        structureWorldAccess.setBlockState(mutable, Blocks.ICE.getDefaultState(), 2);
                        structureWorldAccess.setBlockState(mutable2, Blocks.ICE.getDefaultState(), 2);
                    }
                }
            }
        }

        return true;
    }
}





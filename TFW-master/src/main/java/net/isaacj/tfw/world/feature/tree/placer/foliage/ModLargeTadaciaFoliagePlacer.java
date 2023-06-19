package net.isaacj.tfw.world.feature.tree.placer.foliage;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class ModLargeTadaciaFoliagePlacer extends FoliagePlacer {

    public static final Codec<ModLargeTadaciaFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance)
                    .and(IntProvider.createValidatingCodec(1, 512).fieldOf("height")
                            .forGetter(ModLargeTadaciaFoliagePlacer::getFoliageHeight))
                    .apply(instance, ModLargeTadaciaFoliagePlacer::new));


    private final IntProvider foliageHeight;

    public ModLargeTadaciaFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider foliageHeight) {
        super(radius, offset);

        this.foliageHeight = foliageHeight;
    }

    public IntProvider getFoliageHeight() {
        return this.foliageHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return TFWmod.MOD_LARGE_TADACIA_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                            Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode,
                            int foliageHeight, int radius, int offset) {
        int squareRadius;
        int y = offset;

        while (y > offset - foliageHeight - 1) {
            if (y > offset - 2) {
                squareRadius = 1; // top 2 blocks
            } else if (y <= offset - foliageHeight + 2) {
                squareRadius = 3; // bottom 2 blocks
            } else if (y <= offset - foliageHeight + 5) {
                squareRadius = 2; //  middle blocks
            } else {
                squareRadius = 2; // upper middle blocks
            }
            if (y == offset - 5) {

                generateSquare(world, replacer, random, config, treeNode.getCenter(), squareRadius, y, treeNode.isGiantTrunk());
            } else {
                generateSquareNoCorners(world, replacer, random, config, treeNode.getCenter(), squareRadius, y, treeNode.isGiantTrunk());
            }
            y--;
        }

    }

    protected void generateSquareNoCorners(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
        int i = giantTrunk ? 1 : 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();


        for(int j = -radius; j <= radius + i; ++j) {
            for(int k = -radius; k <= radius + i; ++k) {
                if (radius > 1) {
                    if ((j == -radius && k == -radius) ||
                            (j == -radius && k == radius) ||
                            (j == radius && k == -radius) ||
                            (j == radius && k == radius)) {
                        continue;
                    }
                    if (j == -radius + 1 && k == -radius) continue;
                    if (j == -radius && k == -radius + 1) continue;
                    if (j == radius - 1 && k == -radius) continue;
                    if (j == radius && k == -radius + 1) continue;
                    if (j == radius - 1 && k == radius) continue;
                    if (j == radius && k == radius - 1) continue;
                    if (j == -radius + 1 && k == radius) continue;
                    if (j == -radius && k == radius - 1) continue;
                }

                if (!this.isPositionInvalid(random, j, y, k, radius, giantTrunk)) {
                    mutable.set(centerPos, j, y, k);
                    placeFoliageBlock(world, replacer, random, config, mutable);
                }
            }
        }
    }



    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return foliageHeight.get(random);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && (random.nextInt(2) == 0 || y == 0);
    }
}


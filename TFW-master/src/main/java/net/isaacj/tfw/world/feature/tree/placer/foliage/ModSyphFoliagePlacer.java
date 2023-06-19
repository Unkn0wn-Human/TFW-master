package net.isaacj.tfw.world.feature.tree.placer.foliage;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.isaacj.tfw.TFWmod;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class ModSyphFoliagePlacer extends FoliagePlacer {

    public static final Codec<ModSyphFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance)
                    .and(IntProvider.createValidatingCodec(1, 512).fieldOf("height")
                            .forGetter(ModSyphFoliagePlacer::getFoliageHeight))
                    .apply(instance, ModSyphFoliagePlacer::new));


    private final IntProvider foliageHeight;

    public ModSyphFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider foliageHeight) {
        super(radius, offset);

        this.foliageHeight = foliageHeight;
    }

    public IntProvider getFoliageHeight() {
        return this.foliageHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return TFWmod.MOD_SYPH_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                            Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode,
                            int foliageHeight, int radius, int offset) {

        for (int i = offset; i >= offset - foliageHeight - 1; --i) {
            int j = Math.max(radius + treeNode.getFoliageRadius() - 1 - i / 2, 0);
            this.generateSquare(world, replacer, random, config, treeNode.getCenter(), j, i, treeNode.isGiantTrunk());
        }
        int l = 2;

        for (int k = offset; k >= offset -1; --k) {
            int j = 1;
            this.generateSquare(world, replacer, random, config, treeNode.getCenter().down(l), j, k, treeNode.isGiantTrunk());
            l = l + 1;
        }

        this.placeFoliageBlock(world, replacer, random, config, new BlockPos(treeNode.getCenter().up()));
        this.placeFoliageBlock(world, replacer, random, config, new BlockPos(treeNode.getCenter()));
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


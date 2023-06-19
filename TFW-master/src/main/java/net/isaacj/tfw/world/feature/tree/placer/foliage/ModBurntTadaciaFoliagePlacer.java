package net.isaacj.tfw.world.feature.tree.placer.foliage;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.isaacj.tfw.TFWmod;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class ModBurntTadaciaFoliagePlacer extends FoliagePlacer {

    public static final Codec<ModBurntTadaciaFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance)
                    .and(IntProvider.createValidatingCodec(1, 512).fieldOf("height")
                            .forGetter(ModBurntTadaciaFoliagePlacer::getFoliageHeight))
                    .apply(instance, ModBurntTadaciaFoliagePlacer::new));


    private final IntProvider foliageHeight;

    public ModBurntTadaciaFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider foliageHeight) {
        super(radius, offset);

        this.foliageHeight = foliageHeight;
    }

    public IntProvider getFoliageHeight() {
        return this.foliageHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return TFWmod.MOD_BURNT_TADACIA_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                            Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode,
                            int foliageHeight, int radius, int offset) {

        if (treeNode.isGiantTrunk()) {
            int size = treeNode.getFoliageRadius();
            int x = treeNode.getCenter().getX();
            int y = treeNode.getCenter().getY();
            int z = treeNode.getCenter().getZ();

            for (int i = -size; i <= size; i++) {
                for (int j = -size; j <= size; j++) {
                    placeFoliageBlock(world, replacer, random, config, new BlockPos(x + i, y, z + j));
                }
            }
        } else {
            Direction[] directions = {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.DOWN};

            for (Direction direction : directions) {
                this.placeFoliageBlock(world, replacer, random, config, new BlockPos(treeNode.getCenter()).offset(direction));
            }
            for (int i = 0; i < 3; i++) {
                this.placeFoliageBlock(world, replacer, random, config, new BlockPos(treeNode.getCenter()).up(i));
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


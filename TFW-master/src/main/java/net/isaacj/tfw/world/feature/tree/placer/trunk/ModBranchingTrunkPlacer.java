package net.isaacj.tfw.world.feature.tree.placer.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.isaacj.tfw.TFWmod;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;


public class ModBranchingTrunkPlacer extends TrunkPlacer {

    public static final Codec<ModBranchingTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillTrunkPlacerFields(instance).apply(instance, ModBranchingTrunkPlacer::new));

    public ModBranchingTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return TFWmod.BRANCHING_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {

        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();

        setToDirt(world, replacer, random, startPos.down(), config);

        Direction direction = Direction.EAST;
        Direction direction1 = Direction.NORTH;
        Direction direction2 = Direction.WEST;
        Direction direction3 = Direction.SOUTH;


        Direction usedDirection;


        for (int i = 0; i < height; i++) {
            this.getAndSetState(world, replacer, random, startPos.up(i), config);

        }
        list.add(new FoliagePlacer.TreeNode(startPos.up(height),1, false));



        int branchNumber = 0;

        while (branchNumber <= 3) {

            if (branchNumber == 0) {
                usedDirection = direction;


            } else if (branchNumber == 1) {
                usedDirection = direction1;


            } else if (branchNumber == 2) {
                usedDirection = direction2;


            } else {
                usedDirection = direction3;
            }

            boolean makeBranch = random.nextBoolean();
            int branchStartHeight = 4 + (int) (Math.random() * ((8 - 4) + 1));
            int branchEndHeight = 2 + (int) (Math.random() * ((5 - 1) + 1));
            int branchMaxLength = 5;


            if (makeBranch) {
                int x = 0;
                int y;

                for (y = height - branchStartHeight; y < height - branchEndHeight && x < branchMaxLength; y++) {

                    x++;
                    BlockPos blockPos = startPos.up(y).offset(usedDirection, x);

                    this.getAndSetState(world, replacer, random, blockPos, config);
                    this.getAndSetState(world, replacer, random, blockPos.down(), config);
                }

                BlockPos nodeBlockPos = startPos.up(height - branchEndHeight / 2).offset(usedDirection,x / 2 + (1));

                list.add(new FoliagePlacer.TreeNode(new BlockPos(startPos.up(y).offset(usedDirection,x)),0,false));
                list.add(new FoliagePlacer.TreeNode(new BlockPos(nodeBlockPos),1,false));
            }
            branchNumber++;
        }
        list.add(new FoliagePlacer.TreeNode(startPos.up(height / 2 + (2)), 0, false));
        list.add(new FoliagePlacer.TreeNode(startPos.up(height / 2 + (5)), 0, false));

        return list;
    }

}






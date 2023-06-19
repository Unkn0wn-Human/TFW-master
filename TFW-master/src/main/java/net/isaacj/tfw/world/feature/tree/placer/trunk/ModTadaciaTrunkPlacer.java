package net.isaacj.tfw.world.feature.tree.placer.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.isaacj.tfw.TFWmod;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
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


public class ModTadaciaTrunkPlacer extends TrunkPlacer {

    public static final Codec<ModTadaciaTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillTrunkPlacerFields(instance).apply(instance, ModTadaciaTrunkPlacer::new));

    public ModTadaciaTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return TFWmod.TADACIA_TRUNK_PLACER;
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
        Direction.Axis axis;


        Direction usedDirection;


        for (int i = 0; i < height; i++) {
            this.getAndSetState(world, replacer, random, startPos.up(i), config);

        }
        list.add(new FoliagePlacer.TreeNode(startPos.up(height),1, false));
        list.add(new FoliagePlacer.TreeNode(startPos.up(height - 1),1, true));


        int branchNumber = random.nextInt(4);
        int branchHeight = 2;

        while(branchHeight < height - 2){
            if (branchNumber == 0) {
                usedDirection = direction;
                axis = Direction.Axis.X;
                branchNumber++;


            } else if (branchNumber == 1) {
                usedDirection = direction1;
                axis = Direction.Axis.Z;
                branchNumber++;


            } else if (branchNumber == 2) {
                usedDirection = direction2;
                axis = Direction.Axis.X;
                branchNumber++;


            } else {
                usedDirection = direction3;
                axis = Direction.Axis.Z;
                branchNumber = 0;
            }

            BlockPos blockPos = startPos.up(branchHeight).offset(usedDirection,1);
            BlockState blockState = config.trunkProvider.getBlockState(random,blockPos);
            BlockState rotatedState = blockState.with(Properties.AXIS, axis);

            replacer.accept(blockPos,rotatedState);
            this.getAndSetState(world,replacer, random, blockPos, config);
            list.add(new FoliagePlacer.TreeNode(blockPos,1, false));

            branchHeight += random.nextInt(1,4);
        }

        return list;
    }
}






package net.isaacj.tfw.block.custom;

import com.google.common.annotations.VisibleForTesting;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.block.enums.Thickness;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PointedIceBlock extends PointedDripstoneBlock {
    public PointedIceBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(VERTICAL_DIRECTION, Direction.UP)).with(THICKNESS, Thickness.TIP)).with(WATERLOGGED, false));
    }
    private static void place(WorldAccess world, BlockPos pos, Direction direction, Thickness thickness) {
        BlockState blockState = (BlockState)((BlockState)((BlockState)ModBlocks.POINTED_ICE.getDefaultState().with(VERTICAL_DIRECTION, direction)).with(THICKNESS, thickness)).with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);
        world.setBlockState(pos, blockState, 3);
    }

    private static void growMerged(BlockState state, WorldAccess world, BlockPos pos) {
        BlockPos blockPos2;
        BlockPos blockPos;
        if (state.get(VERTICAL_DIRECTION) == Direction.UP) {
            blockPos = pos;
            blockPos2 = pos.up();
        } else {
            blockPos2 = pos;
            blockPos = pos.down();
        }

        place(world, blockPos2, Direction.DOWN, Thickness.TIP_MERGE);
        place(world, blockPos, Direction.UP, Thickness.TIP_MERGE);
    }

    private static boolean isTip(BlockState state, boolean allowMerged) {
        if (!state.isOf(ModBlocks.POINTED_ICE)) {
            return false;
        } else {
            Thickness thickness = (Thickness)state.get(THICKNESS);
            return thickness == Thickness.TIP || allowMerged && thickness == Thickness.TIP_MERGE;
        }
    }
    private static Optional<BlockPos> searchInDirection(WorldAccess world, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> continuePredicate, Predicate<BlockState> stopPredicate, int range) {
        Direction direction2 = Direction.get(direction, Direction.Axis.Y);
        BlockPos.Mutable mutable = pos.mutableCopy();

        for(int i = 1; i < range; ++i) {
            mutable.move(direction2);
            BlockState blockState = world.getBlockState(mutable);
            if (stopPredicate.test(blockState)) {
                return Optional.of(mutable.toImmutable());
            }

            if (world.isOutOfHeightLimit(mutable.getY()) || !continuePredicate.test(mutable, blockState)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        dripTick(state, world, pos, random.nextFloat());
        if (random.nextFloat() < 0.011377778F && isHeldByPointedDripstone(state, world, pos)) {
            tryGrow(state, world, pos, random);
        }

    }




    @Nullable
    private static BlockPos getTipPos(BlockState state, WorldAccess world, BlockPos pos, int range, boolean allowMerged) {
        if (isTip(state, allowMerged)) {
            return pos;
        } else {
            Direction direction = (Direction)state.get(VERTICAL_DIRECTION);
            BiPredicate<BlockPos, BlockState> biPredicate = (posx, statex) -> {
                return statex.isOf(ModBlocks.POINTED_ICE) && statex.get(VERTICAL_DIRECTION) == direction; // Check if the block is of type POINTED_ICE instead of POINTED_DRIPSTONE
            };
            return (BlockPos)searchInDirection(world, pos, direction.getDirection(), biPredicate, (statex) -> {
                return isTip(statex, allowMerged);
            }, range).orElse((BlockPos) null);
        }
    }

    private static Optional<BlockPos> getSupportingPos(World world, BlockPos pos, BlockState state, int range) {
        Direction direction = (Direction)state.get(VERTICAL_DIRECTION);
        BiPredicate<BlockPos, BlockState> biPredicate = (posx, statex) -> {
            return statex.isOf(ModBlocks.POINTED_ICE) && statex.get(VERTICAL_DIRECTION) == direction;
        };
        return searchInDirection(world, pos, direction.getOpposite().getDirection(), biPredicate, (statex) -> {
            return !statex.isOf(ModBlocks.POINTED_ICE);
        }, range);
    }

    private static boolean isPointedDripstoneFacingDirection(BlockState state) {
        return state.isOf(ModBlocks.POINTED_ICE) && state.get(VERTICAL_DIRECTION) == Direction.DOWN;
    }

    private static boolean isPointingDown(BlockState state) {
        return isPointedDripstoneFacingDirection(state);
    }

    private static boolean isHeldByPointedDripstone(BlockState state, WorldView world, BlockPos pos) {
        return isPointingDown(state) && !world.getBlockState(pos.up()).isOf(ModBlocks.POINTED_ICE);
    }



}